package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/*** If Chooser is set to Center and FMS is LRL */
public class Auto3CommandC_LR extends CommandGroup {
	/** If Chooser is set to Center and FMS is LRL */
	public Auto3CommandC_LR(){
		addSequential(new AdvancedDrive(-1, 10));
		addSequential(new TurnWithGyro(.65, 90, "counterclockwise"));
		addSequential(new AutoDropoff("right"));
		addSequential(new TurnWithGyro(.65, 90, "counterclockwise"));
		addSequential(new CubeGetter());
	}
}