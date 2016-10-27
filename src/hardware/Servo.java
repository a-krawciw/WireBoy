package hardware;

import main.XMLParser;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Windows on 2016-10-23.
 */
public class Servo extends HardwareDevice{


    public Servo (String name, int port){
        super(name, port);
        image = XMLParser.readImage("/hardwareImages/servo.png");

    }

    @Override
    public int drawModule(Graphics g, int startX) {
        return 0;
    }

}
