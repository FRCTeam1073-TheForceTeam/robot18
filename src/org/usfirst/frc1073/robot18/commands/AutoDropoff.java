package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDropoff extends Command {
	
	String dropoffDirection;
    
	public AutoDropoff(String direction) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	dropoffDirection = direction;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.conveyor.autoDropoff(dropoffDirection);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
