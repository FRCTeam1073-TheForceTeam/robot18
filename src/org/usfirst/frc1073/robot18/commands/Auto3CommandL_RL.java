package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto3CommandL_RL extends CommandGroup {
	/** If Chooser is set to Left and FMS is RLR */
	public Auto3CommandL_RL(){
		addParallel(new Dropoff(5, "left"));
		addSequential(new AdvancedDrive(-.8, 200, 80));
		addSequential(new TurnWithGyro(.8, 20, "counterclockwise"));
		addSequential(new CubeGetter());
	}
}