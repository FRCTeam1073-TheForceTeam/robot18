package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;
import org.usfirst.frc1073.robot18.subsystems.*;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveWithInches extends Command {

	double speed;
	int inches;
	double startingEncoderL;
	double targetEncoderL;
	
	
	/** Drive Command that moves an an estimated distance with time
	 * @param inputDistance 
	 * @param inputInches
	 * @author Mr. Robey
	 * @category Drive Command
	 */
    public DriveWithInches(double inputSpeed, int inputInches ) {
        speed = inputSpeed;
        inches = inputInches;
     	SmartDashboard.putString("DriveWithTimeState", "constructor");
     	}
    // Called just before this Command runs the first time
    protected void initialize() {   
    	SmartDashboard.putString("DriveWithTimeState", "Init");
		RobotMap.leftMotor3E.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		startingEncoderL = Math.abs(RobotMap.leftMotor3E.getSelectedSensorPosition(0));
		RobotMap.rightMotor3E.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
    	targetEncoderL = startingEncoderL+(inches * (1440/(3.14*3.9)));
		SmartDashboard.putNumber("left enc target", targetEncoderL);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("DriveWithTimeState", "execute");
		SmartDashboard.putNumber("left enc", Math.abs( RobotMap.leftMotor3E.getSelectedSensorPosition(0)));
		SmartDashboard.putNumber("right enc", Math.abs(RobotMap.rightMotor3E.getSelectedSensorPosition(0)));
    	Robot.drivetrain.difDrive.arcadeDrive(speed *-1, 0);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {

  
    	if (Math.abs(RobotMap.leftMotor3E.getSelectedSensorPosition(0))  > targetEncoderL){
    	  	SmartDashboard.putString("DriveWithTimeState", "finished");
    		return true;
    	}
    return false;
  }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.basicDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
