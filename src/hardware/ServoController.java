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

    public ServoController (String name, int port){
        super(name, port);
        image = XMLParser.readImage("/hardwareImages/servoController.png");
    }

    public void drawModule(Graphics2D g) {
        g.setColor(Color.RED);
        super.drawModule(g);
    }

    @Override
    public String getName() {
        return super.getName() + " \n" + getSerialNumber();
    }
}
