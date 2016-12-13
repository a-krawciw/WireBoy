package hardware;

import main.XMLParser;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Windows on 2016-10-23.
 */
public class Sensor extends HardwareDevice {
    String type = "";

    public Sensor (String name, int port, String type){
        super(name, port);
        this.type = type;
    }


    public int drawModule(Graphics g, int startX) {
        return 0;
    }

    public String getPort(){
        switch (type){
            case "AdafruitColorSensor":
            case "AdafruitBNO055IMU":
            case "ColorSensor":
            case "I2cDeviceSynch":
            case "IrSeekerV3":
            case "ModernRoboticsI2cRangeSensor":
            case "ModernRoboticsI2cCompassSensor":
            case "I2cDevice": return "I2C" + port;
            case "AnalogInput":
            case "OpticalDistanceSensor": return "A" + port;
            case "Led":
            case "TouchSensor":
            case "DigitalDevice": return "D" + port;
            case "AnalogOutput": return "AO" + port;
            case "PulseWidthDevice": return "PWM" + port;
        }
        return "" + port;
    }
}
