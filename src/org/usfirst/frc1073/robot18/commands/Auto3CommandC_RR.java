package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/*** If Chooser is set to Center and FMS is RRR */
public class Auto3CommandC_RR extends CommandGroup {
	/** If Chooser is set to Center and FMS is RRR */
	public Auto3CommandC_RR(){
		addSequential(new AdvancedDrive(-.8, 10, 20));
		addSequential(new TurnWithGyro(.65, 90, "counterclockwise"));
		addSequential(new Dropoff(.5, "right")); //Drops cube onto floor near exchange
		addSequential(new TurnWithGyro(.65, 90, "counterclockwise"));
		addSequential(new CubeGetter()); //Should cross auto line on way to grab cube
	}
}