package org.usfirst.frc1073.robot18.commands.AutonomousChooserSimple;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.AdvancedDrive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MEMEAutoTenSeventySad extends CommandGroup {
	
	public MEMEAutoTenSeventySad() {
		switch(Robot.FMS) {
		case "RRR":
			addSequential(new MemeRRR());
			break;
		case "RLR":
			addSequential(new MemeRLR());
			break;
		case "LLL":
			addSequential(new MemeLLL());
			break;
		case "LRL":
			addSequential(new MemeLRL());
			break;
			/** Should never get used. Something is either very right or very wrong if this gets run */
		default:
			SmartDashboard.putString("FMS DATA", "!!!Field Fault!!!");
			addSequential(new AdvancedDrive(-.8, 80, 80));
			break;
		}
	}
}
