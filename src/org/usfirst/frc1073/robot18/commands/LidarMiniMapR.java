package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/*** If Chooser is set to Center and FMS is RLR */
public class LidarMiniMapR extends CommandGroup {
	/** If Chooser is set to Center and FMS is RLR */
	public LidarMiniMapR(){
		switch(Robot.Side) {
		case "right":
			addSequential(new LidarMiniMapRight());
			break;
		
		case "left":
			addSequential(new LidarMiniMapLeft());
			break;
		default:
			SmartDashboard.putString("Side", "Insert a Value!");
			break;
	}
}
}