package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1073.robot18.OI;
import org.usfirst.frc1073.robot18.Robot;

public class selectCommand extends Command{

	NetworkTable netTable;
	public double selectedCommandNum;

	/** Selects a command using bumper inputs.
	 * @category Autonomous
	 * @param None No parameters
	 * @author Nathaniel
	 */
	public selectCommand() {
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		selectedCommandNum = 0;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.oi.rightBumper.get() == true) {
			selectedCommandNum++;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (selectedCommandNum > 9) selectedCommandNum = 0;
		}
		else if (Robot.oi.leftBumper.get() == true) {
			selectedCommandNum--;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (selectedCommandNum < 0) selectedCommandNum = 9;
		}

		if (selectedCommandNum == 0) {
			SmartDashboard.putString("SelectedCommand", "LidarAlign");
		}
		if (selectedCommandNum == 1) {
			SmartDashboard.putString("SelectedCommand", "LidarAlign360");
		}
		if (selectedCommandNum == 2) {
			SmartDashboard.putString("SelectedCommand", "LidarMoveAway");
		}
		if (selectedCommandNum == 3) {
			SmartDashboard.putString("SelectedCommand", "LidarTest");
		}
		if (selectedCommandNum == 4) {
			SmartDashboard.putString("SelectedCommand", "Vision");
		}
		if (selectedCommandNum == 5) {
			SmartDashboard.putString("SelectedCommand", "null");
		}
		if (selectedCommandNum == 6) {
			SmartDashboard.putString("SelectedCommand", "null");
		}
		if (selectedCommandNum == 7) {
			SmartDashboard.putString("SelectedCommand", "null");
		}
		if (selectedCommandNum == 8) {
			SmartDashboard.putString("SelectedCommand", "null");
		}
		if (selectedCommandNum == 9) {
			SmartDashboard.putString("SelectedCommand", "null");
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		boolean finished = false;
		if (Robot.oi.cancel.get() || Robot.oi.RobotPRGMInit.get()) {
			finished = true;
		}
		return finished;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
