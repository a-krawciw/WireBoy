package hardware;

import main.XMLParser;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Windows on 2016-10-23.
 */
public class Motor extends HardwareDevice{

    public Motor (String name, int port){
        super(name, port);
    }

    public int drawModule(Graphics g, int startX) {
        return 0;
    }
}
