package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/*** If Chooser is set to Center and FMS is RRR */
public class Auto3CommandC_RR extends CommandGroup {
	/** If Chooser is set to Center and FMS is RRR */
	public Auto3CommandC_RR(){
		addSequential(new AdvancedDrive(-1, 70));
		addSequential(new TurnWithGyro(.8, 90, "clockwise"));
		addSequential(new AdvancedDrive(-1, 70));
		addSequential(new TurnWithGyro(.8, 90, "counterclockwise"));
		addSequential(new AdvancedDrive(-1, 45));
	}
}