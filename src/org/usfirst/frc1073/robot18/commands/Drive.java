
package org.usfirst.frc1073.robot18.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class Drive extends Command {

    public Drive() {
        requires(Robot.driveTrain);

    }

    DifferentialDrive difDrive;
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	difDrive = new DifferentialDrive(RobotMap.leftMotor3E, RobotMap.rightMotor3E);
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
    	double turn = Robot.oi.driverControl.getRawAxis(4);

    	difDrive.arcadeDrive(forward, turn);
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
