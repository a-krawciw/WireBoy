package main;

import main.Main.PointConstants;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import hardware.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import sun.rmi.runtime.Log;


public class XMLParser {
        public static Color mrGrey = Color.decode("#E4E4E4");
        public static ArrayList<HardwareDevice> devices = new ArrayList<>();
        public static BufferedImage image;

        public static int topY = 50;

        public static void main(String[] args){
            parseXML("lilssy.xml");

            topY = 10 + 10 * devices.size();

            image = generateImage("lily");
            try {
                ImageIO.write(image, "png", new File("output.png"));
            } catch (IOException e) {
                show(e.getMessage());
            }


        }

        public static void parseXML(String filepath){
            try {
                File inputFile = new File(filepath);
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(inputFile);
                doc.getDocumentElement().normalize();

                NodeList nList = doc.getDocumentElement().getChildNodes();

                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    try {
                        Element e = ((Element) nNode);

                        switch (e.getTagName()) {
                            case "MotorController":
                                MotorController mc = new MotorController(e.getAttribute("name"), (temp - 1) / 2);
                                mc.setSerialNumber(e.getAttribute("serialNumber"));
                                addChildren(mc, e.getChildNodes());
                                devices.add(mc);
                                break;
                            case "ServoController":
                                ServoController sc = new ServoController(e.getAttribute("name"), (temp - 1) / 2);
                                sc.setSerialNumber(e.getAttribute("serialNumber"));
                                addChildren(sc, nNode.getChildNodes());
                                devices.add(sc);
                                break;
                            case "DeviceInterfaceModule":
                                SensorModule sm = new SensorModule(e.getAttribute("name"), (temp - 1) / 2);
                                sm.setSerialNumber(e.getAttribute("serialNumber"));
                                addChildren(sm, nNode.getChildNodes());
                                devices.add(sm);
                                break;
                        }
                    } catch (Exception e){
                    }

                }
            } catch (Exception e) {
                show("out" + e.getMessage());
            }
        }


        public static void addChildren(HardwareDevice parent, NodeList nl){
            for (int temp = 0; temp < nl.getLength(); temp++) {
                Node nNode = nl.item(temp);
                try {
                    Element e = ((Element) nNode);

                    switch (e.getTagName()) {
                        case "Motor":
                            Motor m = new Motor(e.getAttribute("name"), Integer.parseInt(e.getAttribute("port")));
                            parent.addChild(m);
                            break;
                        case "Servo":
                            Servo s = new Servo(e.getAttribute("name"), Integer.parseInt(e.getAttribute("port")));
                            parent.addChild(s);
                            break;
                        default:
                            Sensor se = new Sensor(e.getAttribute("name"), Integer.parseInt(e.getAttribute("port")), e.getTagName());
                            parent.addChild(se);
                            break;
                    }
                }catch (Exception e){
                }

            }

        }


        public static BufferedImage generateImage(String fileName){

            BufferedImage bm = new BufferedImage(1520, 1180, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g = bm.createGraphics();

            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 1520, 1180);

            g.setColor(HardwareDevice.greyText);
            Font defaultFont = g.getFont();
            g.setFont(defaultFont.deriveFont(50.0f));
            g.setColor(Color.BLACK);
            g.drawString(fileName, 1520 / 2 - (g.getFontMetrics().stringWidth(fileName) / 2), 1050);
            g.setFont(defaultFont);
            BufferedImage battery = readImage("/hardwareImages/battery.PNG");
            BufferedImage phone = readImage("/hardwareImages/phone.png");
            BufferedImage powerModule = readImage("/hardwareImages/powerModule.png");
            g.setColor(Color.BLACK);

            Point loc = PointConstants.powerModule;

            g.drawImage(powerModule, loc.x, loc.y, 250, 200, null);

            loc = PointConstants.battery;
            g.drawImage(battery, loc.x, loc.y, 100, 200, null);
            g.setColor(Color.BLACK);
            g.setStroke(new BasicStroke(6));
            g.drawLine(PointConstants.POWER_BATTERY.x, PointConstants.POWER_BATTERY.y, PointConstants.POWER_BATTERY.x, PointConstants.POWER_BATTERY.y - 40);
            g.drawLine(PointConstants.POWER_BATTERY.x, PointConstants.POWER_BATTERY.y - 40, PointConstants.POWER_BATTERY.x + 205, PointConstants.POWER_BATTERY.y - 40);
            g.drawLine(PointConstants.POWER_BATTERY.x + 205, PointConstants.POWER_BATTERY.y - 40, PointConstants.POWER_BATTERY.x + 205, PointConstants.POWER_POWER_MODULE.y);
            g.drawLine(PointConstants.POWER_POWER_MODULE.x, PointConstants.POWER_POWER_MODULE.y, PointConstants.POWER_BATTERY.x + 205, PointConstants.POWER_POWER_MODULE.y);

            loc = PointConstants.phone;
            g.drawImage(phone, loc.x, loc.y, null);
            g.drawLine(PointConstants.USB_PHONE.x, PointConstants.USB_PHONE.y, PointConstants.USB_PHONE.x - 10, PointConstants.USB_PHONE.y);
            g.drawLine(PointConstants.USB_PHONE.x - 10, PointConstants.USB_PHONE.y, PointConstants.USB_PHONE.x - 10, PointConstants.USB_PHONE.y - 220);
            g.drawLine(PointConstants.USB_PHONE.x - 10, PointConstants.USB_PHONE.y - 220, PointConstants.USB_PHONE.x + 140, PointConstants.USB_PHONE.y - 220);
            g.drawLine(PointConstants.USB_PHONE.x + 140, PointConstants.USB_PHONE.y - 220, PointConstants.USB_PHONE.x + 140, PointConstants.USB_POWER_MODULE.y);
            g.drawLine(PointConstants.USB_PHONE.x + 140, PointConstants.USB_POWER_MODULE.y, PointConstants.USB_POWER_MODULE.x, PointConstants.USB_POWER_MODULE.y);

            for (HardwareDevice h : devices) {
                h.drawModule(g);
            }
            image = bm;
            return bm;
        }


        public static BufferedImage readImage(String filePath){
            try {
                return ImageIO.read(XMLParser.class.getClass().getResourceAsStream(filePath));
            } catch (Exception e){
                show("Could not find file:" + filePath);
            }
            return null;
        }

        public static void reset(){
            devices.clear();
        }




        public static void show(String text){
            JOptionPane.showMessageDialog(Main.frame, text);
        }




    }




