package org.usfirst.frc1073.robot18.commands.AutonomousChooser;

import org.usfirst.frc1073.robot18.AutoVars;
import org.usfirst.frc1073.robot18.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1073.robot18.commands.HighGearDT;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.*;

/*** If Chooser is set to Center and FMS is LLL */
public class Auto3CommandC_LL extends CommandGroup {
	/** If Chooser is set to Center and FMS is LLL */
	public Auto3CommandC_LL(){
		System.out.println("Auto3CommandC_LL");
		switch(Robot.autonomousMatchType.getSelected().getString()) {
		case "quals":
			System.out.println("Auto3CommandC_LL - quals");
			addSequential(new HighGearDT());
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 6, 0));
			addSequential(new TurnWithGyro(AutoVars.TurnWithGyroSpeed, 45, "counterclockwise"));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 80, 0));
			addSequential(new TurnWithGyro(AutoVars.TurnWithGyroSpeed, 45, "clockwise"));
			addParallel(new LiftElevatorToDistanceScale(AutoVars.LiftDistDown));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 140, AutoVars.SwitchAD2Timeout));
			addSequential(new TurnWithGyro(AutoVars.SwitchVisionTurnSpeed, AutoVars.ScaleVisionTurnDistance, AutoVars.LeftVisionTurn));
			//addSequential(new CubeGetter());
			addParallel(new LiftElevatorToDistanceScale(30));
			addSequential(new TurnWithGyro(AutoVars.TurnWithGyroSpeed, 30, "clockwise"));
			addSequential(new OpenClawSpitOutCube());
			break;
		case "elims":
			System.out.println("Auto3CommandC_LL - elims");
			addSequential(new HighGearDT());
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 6, 0));
			addSequential(new TurnWithGyro(AutoVars.TurnWithGyroSpeed, 45, "counterclockwise"));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 150, 0));
			addSequential(new TurnWithGyro(AutoVars.TurnWithGyroSpeed, 45, "clockwise"));
			addParallel(new LiftElevatorToDistanceScale(60));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 160, 0));
			addSequential(new TurnWithGyro(AutoVars.TurnWithGyroSpeed, 90, "counterclockwise"));
			addSequential(new OpenClawSpitOutCube());
			addParallel(new LiftElevatorToDistanceScale(0));
			addParallel(new CloseClaw());
			addSequential(new TurnWithGyro(AutoVars.TurnWithGyroSpeed, 45, "clockwise"));
			addSequential(new AdvancedDrive(-AutoVars.ADSpeed, 60, 0));
			addSequential(new CubeGetter());
			break;
		default:
			SmartDashboard.putString("MatchType", "!!!Chooser Not Set!!!");
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 80, 80));
			break;
		}
		System.out.println("Auto Completed");
	}
}