package FXTSwing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

/**
 * Created by Windows on 2017-01-02.
 */
public class ZoomTip extends JPanel {
    private Image toDraw;

    public ZoomTip(int width, int height){
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(toDraw != null){
            g.drawImage(toDraw, 0, 0, getWidth(), getHeight(), null);
        }
    }

    public void update(Point loc, Image pic){
        toDraw = pic;
        int x = loc.x;
        int y = loc.y;
        if(loc.x > 380){
            x -= getWidth();
        }
        if(loc.y > 290){
            y -= getHeight();
        }

        setLocation(x, y);

    }
}
