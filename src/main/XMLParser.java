package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import hardware.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

    public class XMLParser {
        public static Color mrGrey = Color.decode("#E4E4E4");
        public static ArrayList<HardwareDevice> devices = new ArrayList<>();

        public static int topY = 50;

        public static void main(String[] args){
            parseXML("lily.xml");

            topY = 10 + 10 * devices.size();

            BufferedImage image = generateImage();
            try {
                ImageIO.write(image, "png", new File("output.png"));
            } catch (IOException e) {
                e.printStackTrace();
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
                                System.out.println("Motor Controller " + mc);
                                devices.add(mc);
                                break;
                            case "ServoController":
                                ServoController sc = new ServoController(e.getAttribute("name"), (temp - 1) / 2);
                                sc.setSerialNumber(e.getAttribute("serialNumber"));
                                addChildren(sc, nNode.getChildNodes());
                                System.out.println("Servo Controller " + sc);
                                devices.add(sc);
                                break;
                            case "DeviceInterfaceModule":
                                SensorModule sm = new SensorModule(e.getAttribute("name"), (temp - 1) / 2);
                                sm.setSerialNumber(e.getAttribute("serialNumber"));
                                addChildren(sm, nNode.getChildNodes());
                                System.out.println("Sensor Module " + sm);
                                devices.add(sm);
                                break;
                        }
                    } catch (Exception e){
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
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
                }catch (Exception e){}

            }

        }


        public static BufferedImage generateImage(){
            int totalNum = 0;

            for (HardwareDevice parent :  devices) {
                totalNum += parent.futureSize();
            }

            System.out.println(totalNum);

            BufferedImage bm = new BufferedImage(totalNum, 1500, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g = bm.createGraphics();

            g.setColor(Color.WHITE);
            g.fillRect(0, 0, totalNum, 1500);

            BufferedImage battery = readImage("/hardwareImages/battery.png");
            BufferedImage powerModule = readImage("/hardwareImages/powerModule.png");
            g.setColor(Color.BLACK);

//            g.drawRoundRect(5, 5, 200, 100, 5, 5);

            Point loc = PointConstants.powerModule;

            g.drawImage(powerModule, loc.x, loc.y, 250, 200, null);

//            g.drawRect(595, 5, 110, 190);
            int x = 0;
            for (HardwareDevice h : devices) {
                h.drawModule(g);
                System.out.println(x);
            }
            return bm;
        }


        public static BufferedImage readImage(String filePath){
            try {
                return ImageIO.read(XMLParser.class.getResourceAsStream(filePath));
            } catch (Exception e){
                System.out.println("Could not find file:" + filePath);
            }
            return null;
        }









    }




