package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/*** If Chooser is set to Right and FMS is RRR */
public class Auto3CommandR_RR extends CommandGroup {
	/** If Chooser is set to Right and FMS is RRR */
	public Auto3CommandR_RR(){
		SmartDashboard.putString("CurrentCommand", "R_RR is running");
	}
}