package hardware;


import main.XMLParser;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static main.XMLParser.topY;

/**
 * Created by Windows on 2016-10-23.
 */
public class ServoController extends HardwareDevice {
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String serialNumber = "";

    public ServoController (String name, int port){
        super(name, port);
        image = XMLParser.readImage("/hardwareImages/servoController.png");
    }

    @Override
    public int drawModule(Graphics g, int startX) {
        g.setColor(Color.BLACK);
        g.setColor(Color.RED);
        startX = super.drawModule(g, startX);

        g.drawString(getSerialNumber(), drawingX + 35, topY + 275);
        g.setColor(Color.BLACK);
        drawStringInBox(g, name, drawingX + 20, topY + 320, 160, 40, 5);
        return startX;
    }

    @Override
    public String getName() {
        return super.getName() + " \n" + getSerialNumber();
    }
}
