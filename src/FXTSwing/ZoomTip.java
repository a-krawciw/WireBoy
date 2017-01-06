package FXTSwing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

/**
 * Created by Windows on 2017-01-02.
 */
public class ZoomTip extends JPanel {
    private Image toDraw;
    Color c = new Color(0, 0, 0, 0);

    public ZoomTip(int width, int height){
        setBackground(c);
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(toDraw != null){
            g.drawImage(toDraw, 0, 0, getWidth(), getHeight(), null);
        }
        g.setColor(c);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public void update(Point loc, Image pic){
        toDraw = pic;
        int x = loc.x ;
        int y = loc.y ;

        setLocation(x, y);

    }
}
