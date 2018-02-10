package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/*** If Chooser is set to Right and FMS is RLR */
public class Auto3CommandR_RL extends CommandGroup {
	/** If Chooser is set to Right and FMS is RLR */
	public Auto3CommandR_RL(){
		SmartDashboard.putString("CurrentCommand", "R_RL is running");
	}
}