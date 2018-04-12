package org.usfirst.frc1073.robot18.commands.AutonomousChooserSimple;

import org.usfirst.frc1073.robot18.commands.AutonomousTools.AdvancedDrive;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.ElevatorMove;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.SpitOutCube;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.TurnWithGyro;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class MemeRLR extends CommandGroup {
	
	public MemeRLR() {
		addSequential(new AdvancedDrive(.8, 165, 150));
		addSequential(new TurnWithGyro(.8, 88, "counterclockwise"));
		addSequential(new AdvancedDrive(.8, 165, 0));
		addSequential(new TurnWithGyro(.8, 88, "clockwise"));
		addParallel(new AdvancedDrive(.75, 20, 0));
		addSequential(new ElevatorMove("high", 1, 95));
		addSequential(new SpitOutCube(.5));
		System.out.println("If this works somebody owes Nathaniel a crisp $20");
	}
}
