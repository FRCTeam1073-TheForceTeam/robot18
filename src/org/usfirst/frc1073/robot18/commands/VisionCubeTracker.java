package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1073.robot18.OI;
import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

@SuppressWarnings("deprecation")
public class VisionCubeTracker extends Command{

	edu.wpi.first.networktables.NetworkTable netTable;
	NetworkTableInstance netTableInst;
	public double xDelta, xWidth, yDelta, yWidth, blockCount, driveDir, v;
	public String dir;
	public boolean fullDir;

	/** Stays about 2 feet away from a cube. Will back up or move forwards and turn as necessary.
	 * @category Autonomous
	 * @param None No parameters
	 * @author Nathaniel
	 */
	public VisionCubeTracker() {
		netTableInst = NetworkTableInstance.getDefault();
		netTable = netTableInst.getTable("TurretTable");
		//netTable = NetworkTable.getTable("TurretTable");
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// Sees if the command is running 
		//Robot.bling.sendFindingCube();
		v = 0;
		driveDir = 0;
		dir = "not set";
		fullDir = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Pulls variables from Network Tables
		xDelta = netTable.getEntry("centerDistX").getDouble(0);
		xWidth = netTable.getEntry("AverageWidth").getDouble(0);
		yDelta = netTable.getEntry("centerDistY").getDouble(0);
		yWidth = netTable.getEntry("AverageHeight").getDouble(0);
		blockCount = netTable.getEntry("Blocks").getDouble(0);

		// Defines speed and slow down markers
		double speed = .7;
		double side = 25; // Marks the reasonable area around the center	

		// Puts variables from Network Tables on SmartDashboard
		SmartDashboard.putNumber("xDelta", xDelta);
		SmartDashboard.putNumber("xWidth", xWidth);
		SmartDashboard.putNumber("yDelta", yDelta);
		SmartDashboard.putNumber("yWidth", yWidth);
		SmartDashboard.putNumber("Block Count", blockCount);

		// BlockCount asks the Pixy how many things it sees
		// when it sees something, we track it
		if (blockCount > 0) {

			// This code handles the left and right motion of the bot
			// based on the Pixy's values
			if (xDelta > side) {
				dir = "Right";
			}
			else if (xDelta < -(side)) {
				dir = "Left";
			}
			else {
				dir = "Center";
			}

			if (Math.abs(xDelta) > 120) {
				fullDir = false;
			}
			else {
				fullDir = false;
			}

			// If block is far away: sets motor directions
			if (xWidth < 250) {
				if (xWidth < 90) {
					if (xWidth < 75) {
						if (xWidth < 50) {
							if (xWidth < 35) {
								driveDir = 4;
							}
							else {
								driveDir = 3;
							}
						}
						else {
							driveDir = 2;
						}
					}
					else {
						driveDir = 1.5;	
					}
				}
				else {
					driveDir = 1;
				}
			}
			else {
				if (Robot.clawBool == true) {
					driveDir = 0;
					v++;
				}
				else {
					driveDir = .5;
				}
			}
			if (dir.equals("Right") && driveDir >= 0) {
				if (fullDir == true) {
					Robot.drivetrain.difDrive.tankDrive(-speed * driveDir, speed * driveDir / 5);
				}
				else {
					Robot.drivetrain.difDrive.tankDrive(-speed * driveDir / 2, 0);
				}
			}
			else if (dir.equals("Left") && driveDir >= 0) {
				if (fullDir == true) {
					Robot.drivetrain.difDrive.tankDrive(speed * driveDir / 5, -speed * driveDir );
				}
				else {
					Robot.drivetrain.difDrive.tankDrive(0, -speed * driveDir / 2);
				}
			}
			else if (dir.equals("Center")) {
				Robot.drivetrain.difDrive.tankDrive(-speed * driveDir * 1.5, -speed * driveDir * 1.5);
			}
		}
		// When no blocks are seen, we strafe back and forth, and up and down,
		// while the bot looks for the target
		else {
			Robot.drivetrain.difDrive.tankDrive(0, 0);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		boolean finished = false;
		if (v > 10 && Robot.clawBool == true) {
			finished = true;
		}
		if (Robot.oi.cancel.get()) {
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
