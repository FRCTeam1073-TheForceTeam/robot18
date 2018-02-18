package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/*** If Chooser is set to Center and FMS is LLL */
public class Auto3CommandC_LL extends CommandGroup {
	/** If Chooser is set to Center and FMS is LLL */
	public Auto3CommandC_LL(){
		addParallel(new openClaw());
		addSequential(new AdvancedDrive(1, 50));
		addSequential(new TurnWithGyro(.75, 75, "counterclockwise"));
		addSequential(new AdvancedDrive(1, 25));
		addSequential(new TurnWithGyro(.75, 75, "clockwise"));
		addSequential(new AdvancedDrive(1, 25));
		addSequential(new VisionCubeTracker());
		addParallel(new SuckInCube(1000));
		addSequential(new closeClaw());
	}
}