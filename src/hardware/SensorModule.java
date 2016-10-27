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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String serialNumber = "";

    @Override
    public int drawModule(Graphics g, int startX) {
        g.setColor(Color.ORANGE);
        startX = super.drawModule(g, startX);

        g.drawString(getSerialNumber(), drawingX + 40, 485);

        return startX;
    }
}
