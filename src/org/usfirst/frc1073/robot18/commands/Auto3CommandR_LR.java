package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/*** If Chooser is set to Right and FMS is LRL */
public class Auto3CommandR_LR extends CommandGroup {
	/** If Chooser is set to Right and FMS is LRL */
	public Auto3CommandR_LR(){
		SmartDashboard.putString("CurrentCommand", "R_LR is running");
	}
}