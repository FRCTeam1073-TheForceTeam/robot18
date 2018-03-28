package org.usfirst.frc1073.robot18.commands.AutonomousChooser;

import org.usfirst.frc1073.robot18.AutoVars;
import org.usfirst.frc1073.robot18.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1073.robot18.commands.AutonomousTools.*;

public class Auto3CommandR_RR extends CommandGroup {
	/** If Chooser is set to Left and FMS is LLL */
	public Auto3CommandR_RR(){
		switch(Robot.autonomousMatchType.getSelected().getString()) {
		case "quals":
			System.out.println("Auto3CommandR_RR - quals"); //Places 2 cubes in switch
			addParallel(new LiftElevatorToDistanceScale(AutoVars.LiftDistSwitch));
			addParallel(new OpenClaw()); //Claw must be open to cross plane of switch
			addSequential(new AdvancedDrive(AutoVars.BothADSpeed, AutoVars.BothAD1Distance, AutoVars.BothAD1Timeout));
			addParallel(new Dropoff(AutoVars.DropoffTime, AutoVars.RightDropoff));
			addParallel(new LiftElevatorToDistanceScale(AutoVars.LiftDistFloor));
			addSequential(new AdvancedDrive(AutoVars.BothADSpeed, AutoVars.BothAD2Distance, AutoVars.BothAD2Timeout)); //Drives forward while lowering lift and dropping off cube
			addSequential(new TurnWithGyro(AutoVars.BothVisionTurnSpeed, AutoVars.BothVisionTurnDistance, AutoVars.RightVisionTurn));
			addSequential(new CubeGetter());
			addParallel(new LiftElevatorToDistanceScale(30));
			addSequential(new TurnWithGyro(AutoVars.TurnWithGyroSpeed, 30, "clockwise"));
			addSequential(new SpitOutCube(1, 0));
			break;
		case "elims":
			System.out.println("Auto3CommandR_RR - elims"); //Places 1 cube in scale + gets another cube
			addParallel(new LiftElevatorToDistanceScale(60));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 260, 0));
			addSequential(new TurnWithGyro(AutoVars.TurnWithGyroSpeed, 90, "clockwise"));
			addSequential(new SpitOutCube(1, 0));
			addParallel(new LiftElevatorToDistanceScale(0));
			addSequential(new TurnWithGyro(AutoVars.TurnWithGyroSpeed, 45, "counterclockwise"));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 60, 0));
			addSequential(new CubeGetter());
			break;
		default:
			SmartDashboard.putString("MatchType", "!!!Chooser Not Set!!!");
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 80, 80)); //Gets to autoline
			break;
		}
	}
}
