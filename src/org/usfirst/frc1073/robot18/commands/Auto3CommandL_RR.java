package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/*** If Chooser is set to Left and FMS is RRR */
public class Auto3CommandL_RR extends CommandGroup {
	/** If Chooser is set to Left and FMS is RRR */
	public Auto3CommandL_RR(){
		SmartDashboard.putString("CurrentCommand", "L_RR is running");
	}
}