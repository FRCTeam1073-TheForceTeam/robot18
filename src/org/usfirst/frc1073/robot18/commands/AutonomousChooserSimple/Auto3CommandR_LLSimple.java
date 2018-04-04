package org.usfirst.frc1073.robot18.commands.AutonomousChooserSimple;

import org.usfirst.frc1073.robot18.AutoVars;
import org.usfirst.frc1073.robot18.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1073.robot18.commands.HighGearLift;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.*;

public class Auto3CommandR_LLSimple extends CommandGroup {
	/** If Chooser is set to Right and FMS is LLL */
	public Auto3CommandR_LLSimple() {
		addSequential(new AdvancedDrive(-.8, 150, 100));
		addSequential(new TurnWithGyro(-.8, 90,"counterclockwise"));
		addSequential(new AdvancedDrive(-.8, 150, 0));
		addSequential(new TurnWithGyro(-.8, 90, "clockwise"));
		addParallel(new ElevatorMove("high", 1, 85));
		addSequential(new AdvancedDrive(-.8, 25, 0));
		addSequential(new TurnWithGyro(-.8, 90, "clockwise"));
		addSequential(new SpitOutCube(.5));
	}
}