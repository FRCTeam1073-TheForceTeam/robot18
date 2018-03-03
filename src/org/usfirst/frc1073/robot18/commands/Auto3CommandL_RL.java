package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/*** If Chooser is set to Left and FMS is RLR */
public class Auto3CommandL_RL extends CommandGroup {
	/** If Chooser is set to Left and FMS is RLR */
	public Auto3CommandL_RL(){
		addParallel(new AutoDropoff("left"));
		addSequential(new AdvancedDrive(-.8, 150));
		addSequential(new CubeGetter());
		addSequential(new AdvancedDrive(-.8, 50));
		addSequential(new TurnWithGyro(.8, 105, "counterclockwise"));
		addSequential(new LiftElevatorToDistanceScale(65.0));
		addSequential(new AdvancedDrive(.75, 5));
		addSequential(new SpitOutCube(.5, .1));
	}
}