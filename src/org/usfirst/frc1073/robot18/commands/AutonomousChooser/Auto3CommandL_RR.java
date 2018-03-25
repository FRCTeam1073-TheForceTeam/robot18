package org.usfirst.frc1073.robot18.commands.AutonomousChooser;

import org.usfirst.frc1073.robot18.AutoVars;
import org.usfirst.frc1073.robot18.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1073.robot18.commands.AutonomousTools.*;

/*** If Chooser is set to Right and FMS is LLL */
public class Auto3CommandL_RR extends CommandGroup {
	/** If Chooser is set to Right and FMS is LLL */
	public Auto3CommandL_RR(){

		switch(Robot.autonomousMatchType.getSelected().getString()) {
		case "quals":
			System.out.println("Auto3CommandL_RR - quals");
			addParallel(new Dropoff(AutoVars.DropoffTime, AutoVars.LeftDropoff));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, AutoVars.NeitherAD1Distance, AutoVars.NeitherAD1Timeout));
			addSequential(new TurnWithGyro(AutoVars.NeitherVisionTurnSpeed, AutoVars.NeitherVisionTurnDistance, AutoVars.LeftVisionTurn));
			addSequential(new CubeGetter()); //Drops cube near exchange, then turns around and crosses auto line while aligning to a cube
			System.out.println("Auto Completed");
			break;
		case "elims":
			System.out.println("Auto3CommandL_RR - elims");
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 200, 0));
			addSequential(new TurnWithGyro(AutoVars.TurnWithGyroSpeed, 90, "clockwise"));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 190, 0));
			addParallel(new LiftElevatorToDistanceScale(60));
			addSequential(new TurnWithGyro(AutoVars.TurnWithGyroSpeed, 90, "counterclockwise"));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 70, 0));
			addSequential(new TurnWithGyro(AutoVars.TurnWithGyroSpeed, 90, "clockwise"));
			addSequential(new SpitOutCube());
			addParallel(new CloseClaw());
			addParallel(new LiftElevatorToDistanceScale(0));
			addSequential(new TurnWithGyro(AutoVars.TurnWithGyroSpeed, 45, "counterclockwise"));
			break;
		default:
			SmartDashboard.putString("MatchType", "!!!Chooser Not Set!!!");
			addSequential(new AdvancedDrive(-.8, 80, 80));
			break;
		}
	}
}