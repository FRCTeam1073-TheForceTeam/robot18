
package org.usfirst.frc1073.robot18.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	
	/** Uses basic drive to turn based on the gyro's position from the last time the gyro was reset
	 * 
	 * @author Jack
	 * @category Drive Command
	 * @param Speed from 0 to 1
	 * @param Degrees should be positive
	 * @param Direction should be either "clockwise" or "counterclockwise"
	 * 
	 */
    public TurnWithGyro(double Speed, double Degrees, String Direction) {
    	turnSpeed = Speed;
    	turnDegrees = Degrees;
    	turnDirection = Direction;
        requires(Robot.drivetrain);

    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	originalDegrees = RobotMap.headingGyro.getAngle();
    	SmartDashboard.putNumber("originalDegrees", originalDegrees);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double right = 0, left = 0;
    	if(turnDirection.equals("counterclockwise")) {
    		right = turnSpeed;
    		left = turnSpeed;
    	} else if(turnDirection.equals("clockwise")) {
    		right = turnSpeed * -1;
    		left = turnSpeed * -1;
    	}
    	
    	SmartDashboard.putString("TurnStatus", "running");
    	SmartDashboard.putString("TurnType", turnDirection);
    	SmartDashboard.putNumber("TurnSpeed", turnSpeed);
    	
 	   	
    	if(turnDirection.equals("clockwise")) {
 	   		if(Math.abs(RobotMap.headingGyro.getAngle() - (originalDegrees + turnDegrees)) > 11) {
 	   			Robot.drivetrain.basicDrive(left, right);
 	   		}
 	   		else if(Math.abs(RobotMap.headingGyro.getAngle() - (originalDegrees + turnDegrees)) <= 11) {
 	   			Robot.drivetrain.basicDrive(left*left*-1, right*right*-1);
 	   		}
 	   	}else if(turnDirection.equals("counterclockwise")){
 	   		if(Math.abs(RobotMap.headingGyro.getAngle() - (originalDegrees - turnDegrees)) > 11) {
 	   			Robot.drivetrain.basicDrive(left, right);
 	   		}
 	   		else if(Math.abs(RobotMap.headingGyro.getAngle() - (originalDegrees - turnDegrees)) <= 11) {
 	   		Robot.drivetrain.basicDrive(left*left, right*right);
 	   		}
 	   	}

 	   	
    }
   
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean finish = false;
    	if(turnDirection.equals("clockwise")) {
    		finish = (RobotMap.headingGyro.getAngle() >= (originalDegrees + turnDegrees));
    	} else if(turnDirection.equals("counterclockwise")) {
    		finish = (RobotMap.headingGyro.getAngle() <= (originalDegrees - turnDegrees));
    	} else {
    		finish = false;
    	}
    	return finish;
    }
  
    
    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.basicDrive(0, 0);
    	SmartDashboard.putString("TurnStatus", "done");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    	SmartDashboard.putString("TurnStatus", "interrupted");
    }
}
