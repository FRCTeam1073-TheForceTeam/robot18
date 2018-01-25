
package org.usfirst.frc1073.robot18.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class VariableDifferentialDrive extends Command {

    private double forwardDist;
    private double turnRadius;
	
    public VariableDifferentialDrive(double forward, double turn) {
        requires(Robot.drivetrain);
        forwardDist = forward;
        turnRadius = turn;

    }

    DifferentialDrive difDrive;
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	difDrive = new DifferentialDrive(RobotMap.leftMotor3E, RobotMap.rightMotor3E);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	difDrive.arcadeDrive(forwardDist, turnRadius);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
