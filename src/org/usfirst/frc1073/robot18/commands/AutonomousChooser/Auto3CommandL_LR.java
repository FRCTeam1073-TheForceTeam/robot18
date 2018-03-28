package org.usfirst.frc1073.robot18.commands.AutonomousChooser;

import org.usfirst.frc1073.robot18.AutoVars;
import org.usfirst.frc1073.robot18.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.*;

public class Auto3CommandL_LR extends CommandGroup {
	/** If Chooser is set to Left and FMS is LRL */
	public Auto3CommandL_LR(){
		switch(Robot.autonomousMatchType.getSelected().getString()) {
		case "quals":
			System.out.println("Auto3CommandL_LR - quals"); //Places 2 cubes in switch
			addParallel(new LiftElevatorToDistanceScale(AutoVars.LiftDistSwitch));
			addParallel(new OpenClaw()); //Claw must be open to cross plane of switch
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, AutoVars.SwitchAD1Distance, AutoVars.SwitchAD1Timeout));
			addParallel(new Dropoff(AutoVars.DropoffTime, AutoVars.LeftDropoff));
			addParallel(new LiftElevatorToDistanceScale(AutoVars.LiftDistFloor));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, AutoVars.SwitchAD2Distance, AutoVars.SwitchAD2Timeout));
			addSequential(new TurnWithGyro(AutoVars.SwitchVisionTurnSpeed, AutoVars.ScaleVisionTurnDistance, AutoVars.LeftVisionTurn));
			addSequential(new CubeGetter());
			addParallel(new LiftElevatorToDistanceScale(AutoVars.LiftDistSwitch));
			addSequential(new TurnWithGyro(AutoVars.TurnWithGyroSpeed, 30, "clockwise"));
			addSequential(new SpitOutCube(1, 0));
			System.out.println("Auto Completed");
			break;
		case "elims":
			System.out.println("Auto3CommandL_LR - elims");

			break;
		default:
			SmartDashboard.putString("MatchType", "!!!Chooser Not Set!!!");
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 80, 80)); //Gets to autoline
			break;
		}
	}
}
