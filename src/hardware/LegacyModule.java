package hardware;

import main.Main;
import main.XMLParser;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BandCombineOp;
import java.awt.image.BufferedImage;

/**
 * Created by Windows on 2016-12-16.
 */
public class LegacyModule extends HardwareDevice{

    public LegacyModule(String name, int port) {
        super(name, port);

        BufferedImage temp = XMLParser.readImage("/hardwareImages/legacyModule.png");
        image = new BufferedImage(temp.getHeight(), temp.getWidth(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.translate((temp.getHeight() - temp.getWidth())/2, (temp.getWidth() - temp.getHeight())/2);
        g.rotate(Math.PI, temp.getWidth()/2, temp.getHeight()/2);
        g.drawRenderedImage(temp, null);
        g.dispose();
        loc = Main.PointConstants.MODULE_LOC[port];
    }

    public void drawModule(Graphics2D g) {
        g.setColor(Color.GREEN);
        super.drawModule(g);

    }

    public void drawChildren(Graphics2D g){
        for (HardwareDevice child : children) {
            if(child instanceof LegacyMotorController || child instanceof LegacyServoController)
                child.drawModule(g);
        }
    }
    
    
    public BufferedImage getImage(){
        return image;
    }
}
