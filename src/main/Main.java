package main;

import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.swing.JFileChooser.APPROVE_OPTION;

/**
 * Created by Windows on 2016-11-13.
 */
public class Main {
    static JFrame frame;
    static JButton chooseFile;
    static JButton save;
    static JButton instructions;
    static JFileChooser files;
    static JPanel picture;


    public static void main(String [] args){
        frame = new JFrame("WireBoy");
        frame.setLayout(new MigLayout("debug, pack, flowy"));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setIconImage(XMLParser.readImage("/hardwareImages/ftclogo.jpg"));

        picture = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                if(XMLParser.image != null){
                    g.drawImage(XMLParser.image, 0, 0, 760, 590, null);
                }
            }
        };
        frame.add(picture, "w 760, h 590, wrap");

        instructions = new JButton("Instructions");
        instructions.addActionListener(new ButtonHandler());
        frame.add(instructions, "split 3");

        chooseFile = new JButton("Choose File");
        chooseFile.addActionListener(new ButtonHandler());
        frame.add(chooseFile);

        save = new JButton("Save");
        save.addActionListener(new ButtonHandler());
        frame.add(save);

        files = new JFileChooser();



        frame.pack();
        frame.setVisible(true);
    }

    private static class ButtonHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(save) && XMLParser.image != null){
                files.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        if(f.isDirectory()) return true;
                        return (f.getName().endsWith(".jpg") || f.getName().endsWith(".png"));
                    }

                    @Override
                    public String getDescription() {
                        return "Images";
                    }
                });
                files.setSelectedFile(new File(""));
                int result = files.showSaveDialog(frame);

                if(result == APPROVE_OPTION){
                    try {
                        ImageIO.write(XMLParser.image, "png", new File(removeFileExtensions(files.getSelectedFile().getName()) + ".png"));
                    } catch (IOException f) {
                        f.printStackTrace();
                    }
                }

            } else if(e.getSource().equals(chooseFile)){
                files.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        if(f.isDirectory()) return true;
                        return f.getName().endsWith(".xml");
                    }

                    @Override
                    public String getDescription() {
                        return ".XML";
                    }
                });
                int result = files.showOpenDialog(frame);
                if(result == APPROVE_OPTION){
                    XMLParser.parseXML(files.getSelectedFile().getAbsolutePath());
                    XMLParser.generateImage(removeFileExtensions(files.getSelectedFile().getName()));
                    frame.repaint();
                }
            } else if(e.getSource().equals(instructions)){
                new InstructionsDialog();
            }
        }
    }

    private static String removeFileExtensions(String s){
        System.out.println(s);
        String [] bits = s.split("\\.");
        return  bits[0];
    }

}
