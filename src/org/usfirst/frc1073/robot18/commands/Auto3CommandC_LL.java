package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto3CommandC_LL extends CommandGroup {
	/** If Chooser is set to Center, FMS is LLL, and other teams don't want to do switch */
	public Auto3CommandC_LL(){
		addSequential(new AdvancedDrive(-.8, 10, 20));
		addSequential(new TurnWithGyro(.65, 90, "counterclockwise"));
		addSequential(new Dropoff(.5, "right"));
		addSequential(new TurnWithGyro(.65, 90, "counterclockwise"));
		addSequential(new CubeGetter());
	}
}