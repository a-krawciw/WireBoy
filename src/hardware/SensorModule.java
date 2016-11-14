package hardware;

import main.XMLParser;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Windows on 2016-10-23.
 */
public class SensorModule extends HardwareDevice {

    public SensorModule (String name, int port){
        super(name, port);
        image = XMLParser.readImage("/hardwareImages/sensorModule.png");

    }

    public void drawModule(Graphics2D g) {
        g.setColor(Color.ORANGE);
        super.drawModule(g);
    }

    public void drawNames(Graphics g){
        int childrenY = loc.y + 30;
        if (isOnLeft()) {
            g.setColor(greySerial);
            g.drawString(serialNumber, loc.x - 130, loc.y + 30);

            g.setColor(greyText);
            for (HardwareDevice child : children) {
                g.drawString(child.port + "    " + child.getName(), loc.x - 150, childrenY += 20);
            }//for

            g.setColor(greyLine);
            g.drawLine(loc.x - 150, loc.y + 35, loc.x - 50, loc.y + 35);
            g.drawLine(loc.x - 135, loc.y + 10, loc.x - 135, loc.y + 155);
        } else {
            g.setColor(greySerial);
            g.drawString(serialNumber, loc.x + 350, loc.y + 30);

            g.setColor(greyText);
            for (HardwareDevice child : children) {
                g.drawString(child.getPort() + "    " + child.getName(), loc.x + 340 - g.getFontMetrics().stringWidth(child.getPort()), childrenY += 20);
            }//for

            g.setColor(greyLine);
            g.drawLine(loc.x + 330, loc.y + 35, loc.x + 430, loc.y + 35);
            g.drawLine(loc.x + 345, loc.y + 10, loc.x + 345, loc.y + 155);
        }//else
    }
}
