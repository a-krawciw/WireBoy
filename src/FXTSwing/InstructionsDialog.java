package FXTSwing;

import main.XMLParser;

import javax.swing.*;

/**
 * Created by Windows on 2016-11-13.
 */
public class InstructionsDialog extends JDialog {

    public InstructionsDialog(){
        add(new JLabel(new ImageIcon(XMLParser.readImage("/otherImages/instructions.png"))));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

}
