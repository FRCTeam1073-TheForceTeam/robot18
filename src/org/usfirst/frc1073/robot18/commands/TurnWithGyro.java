
package org.usfirst.frc1073.robot18.commands;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

/**
 *
 */
public class TurnWithGyro extends Command {
	
	private double turnSpeed;
	private double turnDegrees;
	private String turnDirection;
	private double originalDegrees;
    public TurnWithGyro(double Speed, double Degrees, String Direction) {
    	turnSpeed = Speed;
    	turnDegrees = Degrees;
    	turnDirection = Direction;
        requires(Robot.drivetrain);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.headingGyro.reset();
    	originalDegrees = RobotMap.headingGyro.getAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double right = 0,left = 0;
    	if(turnDirection == "clockwise") {
    		right = turnSpeed;
    		left = turnSpeed * -1;	
    	} else if(turnDirection == "counterclockwise") {
    		right = turnSpeed * -1;
    		left = turnSpeed;
    	}
    	
 	   if(originalDegrees >= turnDegrees){
 	   	right = turnSpeed/2; 
 	   	left = turnSpeed/2;
 	   }
 	   if(originalDegrees <= turnDegrees){
 	   	right = turnSpeed/2; 
 	   	left = turnSpeed/2;
 	   }
 	   	
 	   	Robot.drivetrain.basicDrive(left, right);
 	   	
    }
   
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(turnDirection == "clockwise") {
    		return (RobotMap.headingGyro.getAngle() >= (turnDegrees - originalDegrees));
    	} else if(turnDirection == "counterclockwise") {
    		return (RobotMap.headingGyro.getAngle() <= ((turnDegrees * -1) - originalDegrees));
    	} else {
    		return false;
    	}
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
