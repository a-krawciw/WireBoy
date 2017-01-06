package FXTSwing;

import main.Main;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static main.Main.textColor;

/**
 * Created by Windows on 2016-11-14.
 */
public class FXTButton extends JButton {

    public FXTButton(String text){
        super(text);
        setBackground(new Color(0xF57F28));
        setForeground(textColor);
        setBorderPainted(false);
    }



}
