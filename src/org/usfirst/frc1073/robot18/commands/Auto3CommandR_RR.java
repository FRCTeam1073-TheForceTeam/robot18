package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto3CommandR_RR extends CommandGroup {
	/** If Chooser is set to Right and FMS is RRR */
	public Auto3CommandR_RR(){
		addParallel(new LiftElevatorToDistanceScale(30.0));
		addParallel(new OpenClaw());
		addSequential(new AdvancedDrive(-.8, 125));
		addParallel(new Dropoff(.5, "right"));
		addParallel(new LiftElevatorToDistanceScale(0));
		addParallel(new CloseClaw());
		addSequential(new AdvancedDrive(-.8, 75));
		addSequential(new TurnWithGyro(.8, 40, "clockwise"));
		addSequential(new CubeGetter());
		addSequential(new AdvancedDrive(-.8, 70));
		addSequential(new TurnWithGyro(.8, 55, "clockwise"));
		addSequential(new LiftElevatorToDistanceScale(65.0));
		addSequential(new SpitOutCube(1, .1));
	}
}