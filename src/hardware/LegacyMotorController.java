package hardware;

import main.Main;
import main.XMLParser;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by Windows on 2017-01-01.
 */
public class LegacyMotorController extends HardwareDevice {

    public LegacyMotorController(String name, int port) {
        super(name, port);
        image = XMLParser.readImage("/hardwareImages/motorController.png");

        loc = Main.PointConstants.LEGACY_MODULE_LOC[port];
    }




    public void drawWires(Graphics2D g){
        g.setColor(Color.BLUE);
        Point portLoc = Main.PointConstants.LEGACY_PORTS[port];

        g.setStroke(new BasicStroke(5));

        int level = 2 * port - 5;

        g.drawLine(portLoc.x , portLoc.y , portLoc.x - 10 * level , portLoc.y );
        g.drawLine(portLoc.x - 10 * level, portLoc.y, portLoc.x - 10 * level, loc.y + cablePoint.y);
        g.drawLine(portLoc.x - 10 * level, loc.y + cablePoint.y, loc.x + cablePoint.x, loc.y + cablePoint.y);
    }

}
