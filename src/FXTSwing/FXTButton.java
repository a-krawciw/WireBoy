package FXTSwing;

import main.Main;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by Windows on 2016-11-14.
 */
public class FXTButton extends JButton {

    public FXTButton(String text){
        super(text);

        //switch back with fore and vice versa
        //because that is how it works out
        setBackground(Color.BLACK);
        setForeground(Main.fixitGreen);
    }



}
