
package org.usfirst.frc1073.robot18.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ControllerDifferentialDrive extends Command {

    public ControllerDifferentialDrive() {
        requires(Robot.drivetrain);

    }
    
    //DifferentialDrive difDrive;
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	//difDrive = new DifferentialDrive(RobotMap.leftMotor3E, RobotMap.rightMotor3E);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/**RAWAXIS LIST
    	 * 1 - Left X
    	 * 2 - Left Y
    	 * 3 - Triggers
    	 * 4 - Right X
    	 * 5 - Right Y
    	 * 6 - DPad left/right
    	 **/
    	
    	double forward = Robot.oi.driverControl.getRawAxis(1);
    	double turn = Robot.oi.driverControl.getRawAxis(4); //Assigns joystick values to the forward and turn speed values
    	
    	
    	if((Robot.oi.driverControl.getRawAxis(1) < .05 && Robot.oi.driverControl.getRawAxis(1) > 0) 
    			|| (Robot.oi.driverControl.getRawAxis(1) > (-0.05) && Robot.oi.driverControl.getRawAxis(1) < 0))
    	{
    		forward = 0;
    	}
    	
    	if((Robot.oi.driverControl.getRawAxis(4) < .05 && Robot.oi.driverControl.getRawAxis(4) > 0) 
    			|| (Robot.oi.driverControl.getRawAxis(4) > (-0.05) && Robot.oi.driverControl.getRawAxis(4) < 0))
    	{
    		turn = 0;
    	} //Adds deadzone under a joystick value of .05 for both forward and turn values

    	SmartDashboard.putNumber("forward", forward);
    	SmartDashboard.putNumber("turn", turn);
    	
    	Robot.drivetrain.difDrive.arcadeDrive(forward, turn*-1);
    	
    	//Robot.drivetrain.arcadeDrive(forward, turn*-1);
    
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
