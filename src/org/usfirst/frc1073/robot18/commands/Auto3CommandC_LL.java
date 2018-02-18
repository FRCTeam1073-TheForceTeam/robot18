package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/*** If Chooser is set to Center and FMS is LLL */
public class Auto3CommandC_LL extends CommandGroup {
	/** If Chooser is set to Center and FMS is LLL */
	public Auto3CommandC_LL(){
		addSequential(new AdvancedDrive(1, 100));
		addSequential(new TurnWithGyro(1, 90, "counterclockwise"));
		addSequential(new AdvancedDrive(1, 50));
		addSequential(new TurnWithGyro(1, 90, "clockwise"));
		addSequential(new AdvancedDrive(1, 50));
	}
}