package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/*** If Chooser is set to Center and FMS is LLL */
public class LidarAlignWallLCG extends CommandGroup {
	/** If Chooser is set to Center and FMS is LLL */
	NetworkTable lidarSendTable;
	public LidarAlignWallLCG(){
		lidarSendTable = NetworkTable.getTable("LidarSendTable");
		double piState = lidarSendTable.getNumber("piState", 0.0);
		piState = 1.0;
		addSequential(new LidarWall());
		SmartDashboard.putString("Stat", "wall");
		addSequential(new TurnWithGyro(1, 85, "counterclockwise"));
		SmartDashboard.putString("Stat", "gyro");
		lidarSendTable.putString("Turn", "left");
		piState = 2.0;
		addSequential (new LidarWall());
		piState = 3.0;
		addSequential(new LidarWallwObstacles());
		
	}
}
