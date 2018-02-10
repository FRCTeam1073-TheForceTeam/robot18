package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/*** If Chooser is set to Left */
public class Auto2FMSL extends CommandGroup {
	/** If Chooser is set to Left */
	public Auto2FMSL() {
		switch(Robot.FMS) {
		case "RRR":
			addSequential(new Auto3CommandL_RR());
			break;
		case "RLR":
			addSequential(new Auto3CommandL_RL());
			break;
		case "LLL":
			addSequential(new Auto3CommandL_LL());
			break;
		case "LRL":
			addSequential(new Auto3CommandL_LR());
			break;
		default:
			SmartDashboard.putString("FMS DATA", "!!!Field Fault!!!");
			break;
		}
	}
}
