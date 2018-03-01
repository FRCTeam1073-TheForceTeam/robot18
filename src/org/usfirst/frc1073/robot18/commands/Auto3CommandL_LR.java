package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto3CommandL_LR extends CommandGroup {
	/** If Chooser is set to Left and FMS is LRL */
	public Auto3CommandL_LR(){
		addParallel(new LiftElevatorToDistanceScale(24.0));
		addParallel(new OpenClaw());
		addSequential(new AdvancedDrive(-.8, 125));
		addParallel(new AutoDropoff("left"));
		addParallel(new LiftElevatorToDistanceScale(0));
		addParallel(new CloseClaw());
		addSequential(new AdvancedDrive(-.8, 75));
		addSequential(new TurnWithGyro(.8, 40, "counterclockwise"));
		addSequential(new CubeGetter());
		addSequential(new LiftElevatorToDistanceScale(24.0));
		addSequential(new SpitOutCube(1, .1));
	}
}