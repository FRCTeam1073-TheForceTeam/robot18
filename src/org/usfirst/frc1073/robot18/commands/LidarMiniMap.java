package org.usfirst.frc1073.robot18.commands;
import edu.wpi.first.wpilibj.command.Command;


import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LidarMiniMap extends Command {


	NetworkTable lidarSendTable;
	double lidarDegrees;
	double ultimateMeasurement;
	double robotSpeed;
	double degrees;
	double left;
	double right;
	double Inches;
	double x;
	double y;
	String rightLeft = new String (SmartDashboard.getString("rightLeft", "right"));

	//Variable for button used in isFinished
	boolean isPressed = false;

	public LidarMiniMap() {

		requires(Robot.drivetrain);

		//Sets the correct Network Table to pull from the Pixy
		lidarSendTable = NetworkTable.getTable("LidarSendTable");
	}

	protected void initialize() {
		SmartDashboard.putString("lidar info", "init");
		SmartDashboard.putString("hello_world", "x");
		rightLeft = SmartDashboard.getString("rightLeft", "right");
		lidarSendTable.putString("rightLeft", rightLeft);
			
		
		
	}

	protected void execute() {
		SmartDashboard.putString("lidar info", "executing");
		x = lidarSendTable.getNumber("x", 0);
		y = lidarSendTable.getNumber("x", 0);
		SmartDashboard.putNumber("X Coordinate", x);
		SmartDashboard.putNumber("Y Coordinate", y);
	}
	protected boolean isFinished() {
		boolean is_finished = false;
		return is_finished;

		//Checks the cancel button for its state
		//isPressed = Robot.oi.cancelAny.get();
		//if (true){
		//SmartDashboard.putString("hello_world", "isFinished");
		//return false;
	}	
	//else 
	//	return true;
	// }

	protected void end() {
		
	}

	protected void interrupted() {
		SmartDashboard.putString("lidar info", "Interrupted");
		//Robot.bling.sendRemoveGear();
	}
}