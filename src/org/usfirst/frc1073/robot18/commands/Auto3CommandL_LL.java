package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto3CommandL_LL extends CommandGroup {
	/** If Chooser is set to Left and FMS is LLL */
	public Auto3CommandL_LL(){
		addParallel(new LiftElevatorToDistanceScale(30.0));
		addParallel(new OpenClaw());
		addSequential(new AdvancedDrive(-.8, 125, 80));
		addParallel(new Dropoff(5, "left"));
		addParallel(new LiftElevatorToDistanceScale(0));
		addParallel(new CloseClaw());
		addSequential(new AdvancedDrive(-.8, 75, 0));
		addSequential(new TurnWithGyro(.8, 20, "counterclockwise"));
		addSequential(new CubeGetter());
	}
}