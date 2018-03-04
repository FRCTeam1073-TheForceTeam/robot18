package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/*** If Chooser is set to Right and FMS is LRL */
public class Auto3CommandR_LR extends CommandGroup {
	/** If Chooser is set to Right and FMS is LRL */
	public Auto3CommandR_LR(){
		addParallel(new Dropoff(.5, "right"));
		addSequential(new AdvancedDrive(-.8, 150));
		addSequential(new CubeGetter());
		addSequential(new AdvancedDrive(-.8, 50));
		addSequential(new TurnWithGyro(.8, 105, "clockwise"));
		addSequential(new LiftElevatorToDistanceScale(65.0));
		addSequential(new AdvancedDrive(.75, 5));
		addSequential(new SpitOutCube(.5, .1));
	}
}