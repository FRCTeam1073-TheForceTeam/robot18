package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Danktonomous extends CommandGroup {
	
	public Danktonomous() {
		addSequential(new AdvancedDrive(-.8, 20, 20));
		addSequential(new TurnWithGyro(.5, 999999, "clockwise"));
	}
}