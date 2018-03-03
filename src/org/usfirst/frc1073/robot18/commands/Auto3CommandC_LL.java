package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/*** If Chooser is set to Center and FMS is LLL */
public class Auto3CommandC_LL extends CommandGroup {
	/** If Chooser is set to Center and FMS is LLL */
	public Auto3CommandC_LL(){
		addSequential(new AdvancedDrive(-1, 10));
		addSequential(new TurnWithGyro(.65, 90, "counterclockwise"));
		addSequential(new AutoDropoff("right"));
		addSequential(new TurnWithGyro(.65, 90, "counterclockwise"));
		addSequential(new CubeGetter());
	}
}