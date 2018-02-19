package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;
import org.usfirst.frc1073.robot18.subsystems.robotCollector;
import org.usfirst.frc1073.robot18.subsystems.robotElevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftElevator extends Command {
	
	double speed = 0;
	
    public LiftElevator() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
  
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	speed = Robot.oi.operatorControl.getRawAxis(1);
    	
//    	if(RobotMap.liftSwitchTop.get() == true){
//    		if(speed > 0){
//    			speed = 0;
//    		}
//    	}
//    	if(RobotMap.liftSwitchBottom.get() == true){
//    		if(speed < 0){
//    			speed = 0;
//    		}
//    	}
<<<<<<< HEAD
    	if(RobotMap.liftSwitchBottom.get() || speed < 0)
    	{
    		RobotMap.elevatorMotorLeft.set(speed/2);
    	}
    	else {
    		RobotMap.elevatorMotorLeft.set(0);
    	}
=======
    	
    	RobotMap.elevatorMotorLeft.set(speed);
>>>>>>> 5bc5cd8d758910d66955e9ce64c950cbbc7a4315
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.elevatorMotorLeft.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	RobotMap.elevatorMotorLeft.set(0);
    }
}
