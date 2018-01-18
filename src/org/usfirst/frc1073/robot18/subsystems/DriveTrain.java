
package org.usfirst.frc1073.robot18.subsystems;

import org.usfirst.frc1073.robot18.RobotMap;
import org.usfirst.frc1073.robot18.commands.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem {
	
    private final WPI_TalonSRX rightMotor1 = RobotMap.rightMotor1;
    private final WPI_TalonSRX rightMotor2 = RobotMap.rightMotor2;
    private final WPI_TalonSRX rightMotor3E = RobotMap.rightMotor3E;
    private final WPI_TalonSRX leftMotor1E = RobotMap.leftMotor1E;
    private final WPI_TalonSRX leftMotor2 = RobotMap.leftMotor2;
    private final WPI_TalonSRX leftMotor3 = RobotMap.leftMotor3;
    private final Encoder rightEnc = RobotMap.rightEnc;
    private final Encoder leftEnc = RobotMap.leftEnc;
    
    private boolean leftInverted = true;
    private boolean rightInverted = true;
    
	public Drivetrain() {
	    	leftMotor1E.setInverted(leftInverted);
	    	leftMotor2.setInverted(leftInverted);
	    	leftMotor3.setInverted(leftInverted);
	    	rightMotor1.setInverted(rightInverted);
	    	rightMotor2.setInverted(rightInverted);
	    	rightMotor3E.setInverted(rightInverted);
	    	
	    	leftMotor2.follow(leftMotor1E);
	    	leftMotor3.follow(leftMotor1E);
	    	rightMotor2.follow(rightMotor3E);
	    	rightMotor1.follow(rightMotor3E);
	}
    
    @Override
    public void initDefaultCommand() {
    	setDefaultCommand(new Drive());

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    public void periodic() {
        // Put code here to be run every loop

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    //Used for non-PID auto routines (semi-temp).
    public void basicDrive(double left, double right) {
    	
    	rightMotor3E.set(right);
    	leftMotor1E.set(left);
    }

}

