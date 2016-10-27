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
public class MotorController extends HardwareDevice {

    public MotorController (String name, int port){
        super(name, port);
        image = XMLParser.readImage("/hardwareImages/motorController.png");
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String serialNumber = "";

    @Override
    public int drawModule(Graphics g, int startX) {
        g.setColor(Color.BLUE);
        startX = super.drawModule(g, startX);
        g.drawString(getSerialNumber(), drawingX + 35, topY + 275);
        g.setColor(Color.BLACK);
        drawStringInBox(g, name, drawingX + 20, topY + 320, 160, 40, 5);
        return startX;
    }

    public String getName(){
        return name + " \n" + getSerialNumber();
    }


}
