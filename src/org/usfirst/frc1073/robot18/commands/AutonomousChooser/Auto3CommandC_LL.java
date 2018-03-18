package org.usfirst.frc1073.robot18.commands.AutonomousChooser;

import org.usfirst.frc1073.robot18.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.*;

/*** If Chooser is set to Center and FMS is LLL */
public class Auto3CommandC_LL extends CommandGroup {
	/** If Chooser is set to Center and FMS is LLL */
	public Auto3CommandC_LL(){
		switch(Robot.autonomousMatchType.getSelected().getString()) {
		case "quals":
			
			break;
		case "elims":
			
			break;
		default:
			SmartDashboard.putString("MatchType", "!!!Chooser Not Set!!!");
			addSequential(new AdvancedDrive(-.8, 80, 80));
			break;
		}
		System.out.println("Auto Completed");
	}
}