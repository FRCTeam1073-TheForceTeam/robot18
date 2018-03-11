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
		addParallel(new LidarSeeRobot());
		addSequential(new AdvancedDrive(-1, 6, 0));
		addSequential(new LowGearDT());
		addSequential(new TurnWithGyro(1, 45, "counterclockwise"));
		addSequential(new HighGearDT());
		addParallel(new LidarSeeRobot());
		addSequential(new AdvancedDrive(-1, 160, 0));
		addSequential(new LowGearDT());
		addSequential(new TurnWithGyro(1, 45, "clockwise"));
		addParallel(new LiftElevatorToDistanceScale(30));
		addSequential(new HighGearDT());
		addParallel(new LidarSeeRobot());
		addSequential(new AdvancedDrive(-1, 160, 0));
		addSequential(new LowGearDT());
		addSequential(new TurnWithGyro(1, 90, "counterclockwise"));
		addSequential(new SpitOutCube(1.5, .1));
	}
}