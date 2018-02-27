package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto3CommandL_LL extends CommandGroup {
	/** If Chooser is set to Left and FMS is LLL */
	public Auto3CommandL_LL(){
		SmartDashboard.putString("CurrentCommand", "L_LL is running");
		addParallel(new LiftElevatorToDistanceScale(24.0));
		addParallel(new OpenClaw());
		addSequential(new AdvancedDrive(-.8, 125));
		addParallel(new AutoDropoff("left"));
		addParallel(new LiftElevatorToDistanceScale(0));
		addParallel(new CloseClaw());
		addSequential(new AdvancedDrive(-.8, 75));
		addSequential(new TurnWithGyro(.8, 40, "counterclockwise"));
		addSequential(new CubeGetter());
		addSequential(new AdvancedDrive(-.8, 70));
		addSequential(new TurnWithGyro(.8, 55, "counterclockwise"));
		addSequential(new LiftElevatorToDistanceScale(65.0));
		addSequential(new SpitOutCube(1, .1));
	}
}