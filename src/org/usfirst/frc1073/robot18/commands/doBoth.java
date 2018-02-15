package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class doBoth extends Command {

	private double speed, currentSpeed, currentSpeedL, currentSpeedR, dist, toBeTraveled, inch, 
	leftEncDif, rightEncDif, startleftEncDif, percentComplete, avgEncDif, startrightEncDif, originalDegrees, n, v;

	private boolean fin;
	
	edu.wpi.first.networktables.NetworkTable netTable;
	NetworkTableInstance netTableInst;
	public double xDelta, xWidth, yDelta, yWidth, blockCount, driveDir;
	public String dir;
	public boolean fullDir;

	public doBoth(double speed, double dist) {
		this.speed = speed;
		this.dist = dist;
		RobotMap.leftMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		RobotMap.rightMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		
		netTableInst = NetworkTableInstance.getDefault();
        netTable = netTableInst.getTable("TurretTable");
	}

	protected void initialize() {
		startleftEncDif = RobotMap.leftMotor1.getSelectedSensorPosition(0);
		startrightEncDif = RobotMap.rightMotor1.getSelectedSensorPosition(0);

		currentSpeed = speed;

		double rotation = 1440;
		double circumference = 12.25221134900019363000430919479;
		inch = 117.52980412939963256779108679819;

		toBeTraveled = (dist * inch * 1.045);

		n = 0;
		fin = false;

		driveDir = 0;
		dir = "not set";
		fullDir = false;
	}

	protected void execute() {
		leftEncDif = Math.abs(startleftEncDif - RobotMap.leftMotor1.getSelectedSensorPosition(0));
		rightEncDif = Math.abs(startrightEncDif - RobotMap.rightMotor1.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Left Encoder", leftEncDif);
		SmartDashboard.putNumber("Right Encoder", rightEncDif);
		avgEncDif = (leftEncDif + rightEncDif) / 2;
		percentComplete = avgEncDif/toBeTraveled;
		
		/** Runs AdvancedDrive code */
		if (percentComplete <= .99) {
			if (percentComplete < 1) {
				if (percentComplete > .90) {
					currentSpeed = currentSpeed / 20;
				}
			}
			if (leftEncDif > (rightEncDif * 1.005)) {
				currentSpeedL = .90;
			}
			else {
				currentSpeedL = 1;
			}
			if (rightEncDif > (leftEncDif * 1.005)) {
				currentSpeedR = .90;
			}
			else {
				currentSpeedR = 1;
			}
			Robot.drivetrain.difDrive.tankDrive(-currentSpeed * currentSpeedL, -currentSpeed * currentSpeedR);
		}
		
		/** Vision command runs once AdvancedDrive finishes */
		else {

			// Pulls variables from Network Tables
			xDelta = netTable.getEntry("centerDistX").getDouble(0);
			xWidth = netTable.getEntry("AverageWidth").getDouble(0);
			yDelta = netTable.getEntry("centerDistY").getDouble(0);
			yWidth = netTable.getEntry("AverageHeight").getDouble(0);
			blockCount = netTable.getEntry("Blocks").getDouble(0);

		// Defines speed and slow down markers
			double speed = 0.22;
			double side = 50; // Marks the reasonable area around the center	

		// Puts variables from Network Tables on SmartDashboard
			SmartDashboard.putNumber("xDelta", xDelta);
			SmartDashboard.putNumber("xWidth", xWidth);
			SmartDashboard.putNumber("yDelta", yDelta);
			SmartDashboard.putNumber("yWidth", yWidth);
			SmartDashboard.putNumber("Block Count", blockCount);

		// BLockCount asks the Pixy how many things it sees
		// when it sees something, we track it
			if (blockCount > 0) {
				SmartDashboard.putString("Current State", "Targeting (" + blockCount + ")");

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
				
				if (Math.abs(xDelta) > 115) {
					fullDir = true;
				}
				else {
					fullDir = false;
				}
					
		// If block is far away: sets motor directions
				if (xWidth < 100) {
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
				else if (xWidth > 130) {
					if (xWidth > 180) {
						driveDir = -2;
					}
					else {
						driveDir = -1;
					}
				}
				else {
					driveDir = 0;
					v++;
				}
				if (dir.equals("Right") && driveDir >= 0) {
					if (fullDir == true) {
						speed = speed;
						Robot.drivetrain.basicDrive(-speed * driveDir, -speed * driveDir);
					}
					else {
						speed = speed / 1.3;
						Robot.drivetrain.basicDrive(-speed * driveDir, 0);
					}
				}
				else if (dir.equals("Left") && driveDir >= 0) {
					if (fullDir == true) {
						speed = speed;
						Robot.drivetrain.basicDrive(speed * driveDir, speed * driveDir);
					}
					else {
						speed = speed / 1.3;
						Robot.drivetrain.basicDrive(0, speed * driveDir);
					}
				}
				else if (dir.equals("Center")) {
					Robot.drivetrain.basicDrive(-speed * driveDir, speed * driveDir);
				}
			}

		// When no blocks are seen, we strafe back and forth, and up and down,
		// while the bot looks for the target
			else {
				n++;
				SmartDashboard.putString("Current State", "Searching (" + blockCount + ")");
				Robot.drivetrain.basicDrive(0, 0);
			}
		}
	}
	protected boolean isFinished() {
		boolean isFinished = false;
		if (v > 50 || n > 50 || Robot.oi.cancel.get() == true) {
			isFinished = true;
		}
		return isFinished;
	}
}