package org.usfirst.frc1073.robot18.commands.AutonomousChooserSimple;

import org.usfirst.frc1073.robot18.commands.AutonomousTools.*;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class MemeRRR extends CommandGroup {
	
	public MemeRRR() {
		addSequential(new AdvancedDrive(.8, 185, 150));
		addSequential(new TurnWithGyro(.8, 15, "counterclockwise"));
		addSequential(new ElevatorMove("high", 1, 95));
		addSequential(new SpitOutCube(.5));
		System.out.println("If this works somebody owes Nathaniel a crisp $20");
	}
}