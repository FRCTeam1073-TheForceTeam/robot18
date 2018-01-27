package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc1073.robot18.Robot;

@SuppressWarnings("deprecation")
public class VisionCubeTracker extends Command{
	
	NetworkTable netTable;
	public double xDelta;
	public double xWidth;
	public double yDelta;
	public double yWidth;
	public double blockCount;
	public String dir;
	public boolean fullDir;
	public double driveDir;

	/** Stays about 2 feet away from a cube. Will back up or move forwards and turn as necessary.
	 * @category Autonomous
	 * @param None No parameters
	 * @author Nathaniel
	 */
	public VisionCubeTracker() {
		netTable = NetworkTable.getTable("TurretTable");
	}

	// Called just before this Command runs the first time
	protected void initialize() {

		driveDir = 0;
		dir = "not set";
		fullDir = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Pulls variables from Network Tables
		xDelta =  netTable.getNumber("centerDistX", 0);
		xWidth =  netTable.getNumber("AverageWidth", 0);
		yDelta =  netTable.getNumber("centerDistY", 0);
		yWidth =  netTable.getNumber("AverageHeight", 0);
		blockCount = netTable.getNumber("Blocks", 0);

	// Creates an array of the Center Points
		double[] location = {xDelta, yDelta};

	// Defines speed and slow down markers
		double speedStart = 0.22;
		double speedEnd = 0;
		double side = 25; // Marks the reasonable area around the center	

	// Puts variables from Network Tables on SmartDashboard
		SmartDashboard.putNumber("xDelta", xDelta);
		SmartDashboard.putNumber("xWidth", xWidth);
		SmartDashboard.putNumber("yDelta", yDelta);
		SmartDashboard.putNumber("yWidth", yWidth);
		SmartDashboard.putNumber("Block Count", blockCount);
		SmartDashboard.putNumberArray("Object Locator", location);

	// BLockCount asks the Pixy how many things it sees
	// when it sees something, we track it
		if (blockCount > 0) {
			SmartDashboard.putString("Current State", "Targeting (" + blockCount + ")");

	// Increases speed of the bot's rotation depending on 
	// how far the target is to the left or right
	// by increasing the size of the increment
			if (Math.abs(xDelta) > side) {
				if (Math.abs(xDelta) > side + 35) {
					// Second fastest
					speedEnd = (1.2 * speedStart);
				}
				if (Math.abs(xDelta) > side + 65) {
					// Fastest speed
					speedEnd = (1.3 * speedStart);
					fullDir = true;
				}
				else {
					// Third fastest
					speedEnd = (1 * speedStart);
				}
			}
			else {
				// Nothing changes
				speedEnd = speedStart;
			}

	// This code handles the left and right motion of the bot
	// based on the Pixy's values
			if (xDelta > side) {
				dir = "Right";
				SmartDashboard.putString("Target", "Right");
			}
			else if (xDelta < -(side)) {
				dir = "Left";
				SmartDashboard.putString("Target", "Left");
			}
			else {
				dir = "Center";
				SmartDashboard.putString("Target", "Centered");
			}
			
	// If block is far away: sets motor directions
			if (xWidth < 100) {
				if (xWidth < 80) {
					if (xWidth < 60) {
						if (xWidth < 40) {
							if (xWidth < 20) {
								driveDir = 2;
							}
							else {
								driveDir = 1.75;
							}
						}
						else {
							driveDir = 1.5;
						}
					}
					else {
						driveDir = 1.25;	
					}
				}
				else {
					driveDir = 1;
				}
			}
			else if (xWidth > 130) {
				driveDir = -1;
			}
			else {
				driveDir = 0;
			}
			if (dir.equals("Right") && driveDir >= 0) {
				if (fullDir == true) {
					Robot.drivetrain.basicDrive(-speedEnd * driveDir, -speedEnd * driveDir);
				}
				else {
					Robot.drivetrain.basicDrive(-speedEnd * driveDir, (speedEnd / 2) * driveDir);
				}
			}
			else if (dir.equals("Left") && driveDir >= 0) {
				if (fullDir == true) {
					Robot.drivetrain.basicDrive(speedEnd * driveDir, speedEnd * driveDir);
				}
				else {
					Robot.drivetrain.basicDrive((-speedEnd / 2) * driveDir, speedEnd * driveDir);
				}
			}
			else if (dir.equals("Center")) {
				Robot.drivetrain.basicDrive(-speedEnd * driveDir, speedEnd * driveDir);
			}
		}

	// When no blocks are seen, we strafe back and forth, and up and down,
	// while the bot looks for the target
		else {
			SmartDashboard.putString("Current State", "Searching (" + blockCount + ")");
			Robot.drivetrain.basicDrive(0, 0);
		}
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
