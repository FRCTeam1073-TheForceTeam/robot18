package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;
import org.usfirst.frc1073.robot18.subsystems.*;

import com.ctre.phoenix.motorcontrol.*;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */


public class DriveWithProx extends Command {
	double voltage, distance, total;
    public DriveWithProx() {
    	
    	
    }
    // Called just before this Command runs the first time
    protected void initialize() {   
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.difDrive.arcadeDrive(1, 0);
    	total = 0;
		for (int i = 0; i < 10; i++) {
			voltage = RobotMap.frontSensor.getVoltage();
			distance = (Robot.voltage - 0.0399)/0.0234;  
			total += distance;
		}
		total = total/10;
		SmartDashboard.putNumber("Ultrasonic Distance", total );
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (total < 12) return true;
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.difDrive.arcadeDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
