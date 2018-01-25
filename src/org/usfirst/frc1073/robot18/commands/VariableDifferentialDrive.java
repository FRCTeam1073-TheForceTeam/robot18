
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
    
    private final double DEADZONE_VALUE = .05;
    private final double WHEEL_DIA = 3.9;
    private final double CUBIC_SCALE = .02;
    private final double CUB = CUBIC_SCALE/2;
	
    
    public double deadzone(double input,double deadzoneVal)
    {
    	if(input <= deadzoneVal && input > 0) input = 0;
    	
    	if(input >= -deadzoneVal && input < 0) input = 0;
    	
    	return input;
    }
    
    public double cubicScale(double in, double cub)
    {
    	return cub*in + (1 - cub) * Math.pow(in, 3);
    }
    
    
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
    	
    	difDrive.arcadeDrive(cubicScale(deadzone(forwardDist,DEADZONE_VALUE),CUBIC_SCALE), turnRadius);
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
