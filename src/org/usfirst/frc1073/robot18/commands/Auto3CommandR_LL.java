package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/*** If Chooser is set to Right and FMS is LLL */
public class Auto3CommandR_LL extends CommandGroup {
	/** If Chooser is set to Right and FMS is LLL */
	public Auto3CommandR_LL(){
		addParallel(new Dropoff(.5, "right"));
		addSequential(new AdvancedDrive(-.8, 5, 0));/*
		addSequential(new TurnWithGyro(.8, 5, "counterclockwise"));
		addSequential(new AdvancedDrive(-.8, 150));
		addSequential(new TurnWithGyro(.8, 90, "clockwise"));
		addSequential(new CubeGetterSupremeExtreme9000());*/
	}
}