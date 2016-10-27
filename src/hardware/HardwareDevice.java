package hardware;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static main.XMLParser.devices;
import static main.XMLParser.topY;

/**
 * Created by Windows on 2016-10-23.
 */
public abstract class HardwareDevice {
    BufferedImage image;
    int port = 0;
    protected int drawingX = 0;
    String name = "";
    protected ArrayList<HardwareDevice> children;

    public HardwareDevice(String name, int port){
        this.name = name;
        this.port = port;
        children = new ArrayList<>();
    }

    public int drawModule(Graphics g, int startX){
        int x = startX;
        Color c = g.getColor();

        if(children.size() == 1){
            g.drawRect(startX  + 50, topY + 500, 100, 100);
            drawStringInBox(g, children.get(0).getName(), x + 50, topY + 500, 100, 100, 5);
            x += 210;
        } else {
            for (HardwareDevice child : children) {
                g.setColor(c);
                g.drawRect(x, topY + 500, 100, 100);
                drawStringInBox(g, child.getName(), x, topY + 500, 100, 100, 5);
                x += 105;
            }
        }

        g.setColor(c);
        drawingX = (children.size() > 1)? (x - startX) / 2 - 100 + startX: startX;

        if(image != null){
            g.drawImage(image, drawingX, topY + 250, 200, 200, null);
        } else {
            g.drawRect(drawingX, 200, topY + 250, 200);

        }

        drawWires(g);
        return x + 5;
    }

    protected void drawWires(Graphics g){

        if(drawingX + 160 < 500 && drawingX + 160 > 250){
            System.out.println(name);
            g.fillRect(drawingX + 160, topY + 240, 5, 10);
            if(drawingX < 375){
                g.fillRect(230 - port * 5, topY + 240, drawingX - 70 + port * 5, 5);
                g.fillRect(230 - port * 5, port * 10, 5, topY + 240 - port * 10);
                g.fillRect(230 - port * 5, port * 10, 65 + 28 * port + port * 5 , 5);
            } else {
                g.fillRect(230 - port * 5, topY + 240, drawingX - 70 + port * 5, 5);
                g.fillRect(230 - port * 5, port * 10, 5, topY + 240 - port * 10);
                g.fillRect(230 - port * 5, port * 10, 65 + 28 * port + port * 5 , 5);
            }
        } else {
            g.fillRect(drawingX + 160, port * 10, 5, topY + 250 - port * 10);

            int width = Math.abs(295 + 28 * port - drawingX - 160);
            if(295 + 28 * port > drawingX + 160){
                g.fillRect(drawingX + 160, port * 10, width, 5);
            } else {
                g.fillRect(295 + 28 * port, port * 10, width, 5);
            }
        }




        g.fillRect(295 + 28 * port, port * 10, 5, topY + 30 - port * 10);
    }

    public String getName(){
        return name;
    }


    protected void drawStringInBox(Graphics g, String s, int x, int y, int width, int height, int padding){
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
        g.drawRect(x + padding, y + padding, width - 2 * padding, height - 2 * padding);
        int stringWidth = strSize(g, s);
        int fontSize = g.getFontMetrics().getHeight();

        if(stringWidth > width - 2 * padding){
            String [] words = s.split(" ");

            String line = "";
            int numLines = 1;
            for (int j = 0; j < words.length; j++) {
                System.out.println(words[j]);
                if (strSize(g, line + " " + words[j]) > width - 2 * padding) {
                    if((padding + fontSize) * numLines > height) break;
                    g.drawString(line, x + width / 2 - strSize(g, line) / 2, y + (padding + fontSize) * numLines);
                    numLines++;
                    line = words[j];
                } else {
                    line += " " + words[j];
                }

                if(j == words.length - 1){
                    g.drawString(line, x + width / 2 - strSize(g, line) / 2, y + (padding + fontSize) * numLines);
                }
            }

        } else {
            g.drawString(s, x + width / 2 - stringWidth / 2, y + padding + fontSize);
        }



    }

    protected int strSize(Graphics g, String str){
        return g.getFontMetrics().stringWidth(str);
    }

    public void setName(String name){
        this.name = name;
    }

    public void addChild(HardwareDevice h){
        children.add(h);
    }

    public String toString(){
        String childrenName = "";
        for(HardwareDevice h: children){
            childrenName += "\n    " + h;
        }
        return "Name: " + name + " Port: " + port + childrenName;
    }

    public int numChildren(){
        return children.size();
    }

    public int futureSize(){
        return (numChildren() > 1)?numChildren() * 110:220;
    }



}
