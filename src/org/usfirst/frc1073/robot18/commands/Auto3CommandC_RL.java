package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/*** If Chooser is set to Center and FMS is RLR */
public class Auto3CommandC_RL extends CommandGroup {
	/** If Chooser is set to Center and FMS is RLR */
	public Auto3CommandC_RL(){
		SmartDashboard.putString("CurrentCommand", "C_RL is running");
	}
}