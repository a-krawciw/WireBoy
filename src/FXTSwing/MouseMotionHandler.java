package FXTSwing;

import main.XMLParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import static main.XMLParser.image;

/**
 * Created by Windows on 2017-01-02.
 */
public class MouseMotionHandler implements MouseMotionListener {
    Point mouseLoc = new Point(0, 0);
    ZoomTip toUpdate;
    public int zoomFactor = 2;

    public MouseMotionHandler(ZoomTip toUpdate){
        this.toUpdate = toUpdate;
    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseLoc = e.getPoint();
        if(toUpdate != null && XMLParser.image != null){
            double scaleFactor = image.getWidth() / 760.0;
            toUpdate.update(mouseLoc, XMLParser.getSubimage(scaleFactor * mouseLoc.x, (mouseLoc.y - (295 - (380 * image.getHeight() / image.getWidth()))) * scaleFactor, toUpdate.getWidth() / zoomFactor, toUpdate.getHeight() / zoomFactor));
        }
    }

    public Point getMouseLocation(){
        return mouseLoc;
    }
}
