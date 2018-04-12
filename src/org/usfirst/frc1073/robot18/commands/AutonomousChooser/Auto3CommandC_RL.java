package org.usfirst.frc1073.robot18.commands.AutonomousChooser;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc1073.robot18.commands.LowGearDT;
import org.usfirst.frc1073.robot18.AutoVars;
import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.commands.HighGearDT;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.*;

/*** If Chooser is set to Center and FMS is RLR */
public class Auto3CommandC_RL extends CommandGroup {
	/** If Chooser is set to Center and FMS is RLR */
	public Auto3CommandC_RL(){
		switch(Robot.autonomousMatchType.getSelected().getString()) {
		case "quals":
			System.out.println("Auto3CommandC_RL - quals"); //Places 2 cubes in switch
			addSequential(new LowGearDT());
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, AutoVars.SwitchDist, 200));
			addSequential(new SpitOutCube(1, AutoVars.SpitOutSpeed));
/*
			addSequential(new HighGearDT());
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 6, 0));
			addSequential(new TurnWithGyro(AutoVars.TurnSpeed, 45, "clockwise"));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 130, 0));
			addParallel(new LiftElevatorToDistanceScale(AutoVars.LiftDistSwitch));
			addSequential(new TurnWithGyro(AutoVars.TurnSpeed, 45, "counterclockwise")); //Drives to left side of field parallel to side of switch and raises elevator
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 20, AutoVars.SwitchAD2Timeout));
			addSequential(new TurnWithGyro(AutoVars.TurnSpeed, 90, "clockwise"));
			addSequential(new AdvancedDrive(-AutoVars.ADSpeed, 20, 0));
			addSequential(new SpitOutCube(1, 0));
			addParallel(new LiftElevatorToDistanceScale(AutoVars.LiftDistFloor));
			addSequential(new TurnWithGyro(AutoVars.TurnSpeed, 90, "counterclockwise"));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 60, 0));  //Drives forward, after turning and dropping off cube in switch
			addSequential(new TurnWithGyro(AutoVars.SwitchVisionTurnSpeed, AutoVars.ScaleVisionTurnDistance, AutoVars.LeftVisionTurn)); //lowers elevator and turns towards cubes
			addSequential(new CubeGetter());
			addSequential(new TurnWithGyro(AutoVars.TurnSpeed, 30, "counterclockwise")); //Turns towards switch again after obtaining cube
			addSequential(new SpitOutCube(1, 0 ));
			System.out.println("Auto Completed");
*/
			break;
		case "elims":
			System.out.println("Auto3CommandC_RL - elims"); //Places 1 cube in scale + gets another cube
			addSequential(new HighGearDT());
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 6, 0));
			addSequential(new TurnWithGyro(AutoVars.TurnSpeed, 50, "counterclockwise"));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 120, 0));
			addSequential(new TurnWithGyro(AutoVars.TurnSpeed, 50, "clockwise")); //Drives to left wall and aligns parallel to it
			addParallel(new LiftElevatorToDistanceScale(AutoVars.LiftDistScale));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 175, 0)); //Drives to scale
			addSequential(new TurnWithGyro(AutoVars.TurnSpeed, 80, "counterclockwise"));
			addSequential(new SpitOutCube(1, 0)); //Deposits cube into scale
			addParallel(new LiftElevatorToDistanceScale(AutoVars.LiftDistFloor));
			addParallel(new CloseClaw());
			addSequential(new TurnWithGyro(AutoVars.TurnSpeed, 50, "clockwise")); //Turns away from scale and lowers elevator and closes claw
			addSequential(new AdvancedDrive(-AutoVars.ADSpeed, 60, 0));
			addSequential(new CubeGetter()); //Retrieves another cube
			System.out.println("Auto Completed");
			break;
		default:
			SmartDashboard.putString("MatchType", "!!!Chooser Not Set!!!");
			addSequential(new AdvancedDrive(-.8, 80, 80));
			System.out.println("Auto Completed");
			break;
		}
		System.out.println("Auto Completed");
	}
}
