package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/*** If Chooser is set to Left and FMS is LLL */
public class Auto3CommandL_LL extends CommandGroup {
	/** If Chooser is set to Left and FMS is LLL */
	public Auto3CommandL_LL(){
		SmartDashboard.putString("CurrentCommand", "L_LL is running");
	}
}