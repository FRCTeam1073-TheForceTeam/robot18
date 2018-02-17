
package org.usfirst.frc1073.robot18;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.cscore.*;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public static PowerDistributionPanel pdp;
	public static WPI_VictorSPX rightMotor2;
    public static WPI_TalonSRX rightMotor1;
    public static WPI_VictorSPX leftMotor2;
    public static WPI_TalonSRX leftMotor1;
    public static WPI_TalonSRX liftMotor;
    public static Encoder rightEnc;
    public static Encoder leftEnc;
    public static Encoder liftEncoder;
    public static DigitalInput liftSwitchBottom;
    public static DigitalInput liftSwitchTop;
    
    public static AnalogInput leftSensor;
    public static AnalogInput rightSensor;
    public static AnalogInput frontSensor;
    public static AnalogInput backSensor;
    
    public static ADXRS450_Gyro headingGyro;
    
	public static Encoder collectorEncoder;
	public static WPI_TalonSRX leftCollectorMotor;
	public static WPI_TalonSRX rightCollectorMotor;
	public static DigitalInput collectorSwitchBottom;
	public static WPI_TalonSRX conveyorMotor;
    
    public static void init() {
    // Motor init
    	// Right
        rightMotor2 = new WPI_VictorSPX(2);
        rightMotor1 = new WPI_TalonSRX(3);
        //Left
        leftMotor2 = new WPI_VictorSPX(9);
        leftMotor1 = new WPI_TalonSRX(8);
    
    // Encoder init
        // Right
        rightEnc = new Encoder(2, 3, false, EncodingType.k4X);
        rightEnc.setDistancePerPulse(1.0);
        rightEnc.setPIDSourceType(PIDSourceType.kRate);
        LiveWindow.addSensor("DriveTrain", "rightEnc", rightEnc);
        // Left
        leftEnc = new Encoder(0, 1, false, EncodingType.k4X);
        leftEnc.setDistancePerPulse(1.0);
        leftEnc.setPIDSourceType(PIDSourceType.kRate);
        LiveWindow.addSensor("DriveTrain", "leftEnc", leftEnc);
        
    // Proximity Sensors
        leftSensor = new AnalogInput(0);
        rightSensor = new AnalogInput(1);
        frontSensor = new AnalogInput(2);
        backSensor = new AnalogInput(3);
            
    // Gyro
        headingGyro = new ADXRS450_Gyro();
        LiveWindow.addSensor("DriveTrain", "headingGyro", headingGyro);
        
    // Conveyor Motor
        conveyorMotor = new WPI_TalonSRX(10);
        
    // Collector Motors
        leftCollectorMotor = new WPI_TalonSRX(7);
        rightCollectorMotor = new WPI_TalonSRX(4);
        }
}
