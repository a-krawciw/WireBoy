package FXTSwing;

import main.Main;
import main.XMLParser;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Windows on 2016-11-14.
 */
public class CreditsPage extends JDialog {

    public CreditsPage(){
        setLayout(new MigLayout());
        JLabel text = new JLabel("<html>WireBoy: <br/> Team 3491 FIX IT<br/>Victoria BC Canada<br/>Visit Our Website at ftcvictoria.org</html>");
        text.setForeground(Main.fixitGreen);
        text.setBackground(Color.BLACK);
        add(text);
        add(new JLabel(new ImageIcon(XMLParser.readImage("/otherImages/fixitlogo.png"))));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setForeground(Color.GREEN);
        getContentPane().setBackground(Color.BLACK);
        pack();
        setVisible(true);
    }
}
