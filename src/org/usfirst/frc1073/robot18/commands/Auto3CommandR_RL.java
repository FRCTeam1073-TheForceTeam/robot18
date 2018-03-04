package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto3CommandR_RL extends CommandGroup {
	/** If Chooser is set to Right and FMS is RLR */
	public Auto3CommandR_RL(){
		addParallel(new LiftElevatorToDistanceScale(30.0));
		addParallel(new OpenClaw());
		addSequential(new AdvancedDrive(-.8, 125));
		addParallel(new Dropoff(.5, "right"));
		addParallel(new LiftElevatorToDistanceScale(0));
		addParallel(new CloseClaw());
		addSequential(new AdvancedDrive(-.8, 75));
		addSequential(new TurnWithGyro(.8, 40, "clockwise"));
		addSequential(new CubeGetter());
		addSequential(new LiftElevatorToDistanceScale(24.0));
		addSequential(new SpitOutCube(1, .1));
	}
}