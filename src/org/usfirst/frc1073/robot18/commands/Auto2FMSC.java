package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto2FMSC extends CommandGroup {
	/** If Chooser is set to Center */
	public Auto2FMSC() {
		switch(Robot.FMS) {
		case "RRR":
			addSequential(new Auto3CommandC_RR());
			break;
		case "RLR":
			addSequential(new Auto3CommandC_RL());
			break;
		case "LLL":
			if(SmartDashboard.getBoolean("Other Teams Want To Do Switch", false)){
				addSequential(new Auto3CommandC_LL());
			}else {
				addSequential(new Auto3CommandC_LLAlt());
			}
			break;
		case "LRL":
			addSequential(new Auto3CommandC_LR());
			break;
		default:
			SmartDashboard.putString("FMS DATA", "!!!Field Fault!!!");
			addSequential(new AdvancedDrive(-.8, 40, 40));
			addSequential(new TurnWithGyro(.8, 65, "clockwise"));
			addSequential(new AdvancedDrive(-.8, 60, 60));
			break;
		}
	}
}
