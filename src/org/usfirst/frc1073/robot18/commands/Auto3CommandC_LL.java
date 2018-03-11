package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/*** If Chooser is set to Center and FMS is LLL */
public class Auto3CommandC_LL extends CommandGroup {
	/** If Chooser is set to Center and FMS is LLL */
	public Auto3CommandC_LL(){
		/*addSequential(new AdvancedDrive(-.8, 10, 20));
		addSequential(new TurnWithGyro(.65, 90, "counterclockwise"));
		addSequential(new Dropoff(.5, "right")); //Drops cube onto floor near exchange
		addSequential(new TurnWithGyro(.65, 90, "counterclockwise"));
		addSequential(new CubeGetter()); //Should cross auto line on way to grab cube*/
		addSequential(new AdvancedDrive(-.8, 6, 0));
		addSequential(new TurnWithGyro(.8, 45, "counterclockwise"));
		addSequential(new AdvancedDrive(-.8, 160, 0));
		addSequential(new TurnWithGyro(.8, 45, "clockwise"));
		addParallel(new LiftElevatorToDistanceScale(30));
		addSequential(new AdvancedDrive(-.8, 160, 0));
		addSequential(new TurnWithGyro(.6, 90, "counterclockwise"));
		addSequential(new SpitOutCube(1, 1));
	}
}