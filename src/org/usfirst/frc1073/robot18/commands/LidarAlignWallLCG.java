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
		addSequential(new LidarWall());
		SmartDashboard.putString("Stat", "wall");
		addSequential(new TurnWithGyro(1, 85, "counterclockwise"));
		SmartDashboard.putString("Stat", "gyro");
		lidarSendTable.putString("Turn", "left");
		addSequential (new LidarWall());
		addSequential(new LidarWallwObstacles());
	}
}
