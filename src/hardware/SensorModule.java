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
}
