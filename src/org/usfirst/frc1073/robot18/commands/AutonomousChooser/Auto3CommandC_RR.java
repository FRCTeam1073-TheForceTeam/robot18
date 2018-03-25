package org.usfirst.frc1073.robot18.commands.AutonomousChooser;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1073.robot18.AutoVars;
import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.commands.HighGearDT;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.*;

/*** If Chooser is set to Center and FMS is RRR */
public class Auto3CommandC_RR extends CommandGroup {
	/** If Chooser is set to Center and FMS is RRR */
	public Auto3CommandC_RR(){
		switch(Robot.autonomousMatchType.getSelected().getString()) {
		case "quals":
			
			break;
		case "elims":
			System.out.println("Auto3CommandC_RR - elims"); //Places 1 cube in scale + gets another cube
			addSequential(new HighGearDT());
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 6, 0));
			addSequential(new TurnWithGyro(AutoVars.TurnWithGyroSpeed, 50, "clockwise"));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 120, 0));
			addSequential(new TurnWithGyro(AutoVars.TurnWithGyroSpeed, 50, "counterclockwise")); //Drives to left wall and aligns parallel to it
			addParallel(new LiftElevatorToDistanceScale(AutoVars.LiftDistScale));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 175, 0)); //Drives to scale
			addSequential(new TurnWithGyro(AutoVars.TurnWithGyroSpeed, 80, "clockwise"));
			addSequential(new SpitOutCube(1, 0)); //Deposits cube into scale
			addParallel(new LiftElevatorToDistanceScale(AutoVars.LiftDistFloor));
			addParallel(new CloseClaw());
			addSequential(new TurnWithGyro(AutoVars.TurnWithGyroSpeed, 50, "counterclockwise")); //Turns away from scale and lowers elevator and closes claw
			//addSequential(new AdvancedDrive(-AutoVars.ADSpeed, 60, 0));
			//addSequential(new CubeGetter()); //Retrieves another cube
			break;
		default:
			SmartDashboard.putString("MatchType", "!!!Chooser Not Set!!!");
			addSequential(new AdvancedDrive(-.8, 80, 80));
			break;
		}
		System.out.println("Auto Completed");
	}
}