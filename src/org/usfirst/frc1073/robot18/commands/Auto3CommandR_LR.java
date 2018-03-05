package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/*** If Chooser is set to Right and FMS is LRL */
public class Auto3CommandR_LR extends CommandGroup {
	/** If Chooser is set to Right and FMS is LRL */
	public Auto3CommandR_LR(){
		addParallel(new Dropoff(5, "right"));
		addSequential(new AdvancedDrive(-.8, 200, 80));
		addSequential(new TurnWithGyro(.8, 20, "clockwise"));
		addSequential(new CubeGetter());
	}
}