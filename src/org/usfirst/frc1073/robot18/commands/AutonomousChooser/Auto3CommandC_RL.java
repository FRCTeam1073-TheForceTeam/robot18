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
		case "elims":
			System.out.println("Auto3CommandC_RL"); //Places 1 cube in switch
			addSequential(new LowGearDT());
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, AutoVars.SwitchDist, 200));
			addSequential(new SpitOutCube(1, AutoVars.SpitOutSpeed));
			System.out.println("Auto Completed");
			break;
		case "experimental":
			System.out.println("Hello World!");
			addSequential(new LowGearDT());
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, AutoVars.SwitchDist, 200));
			addSequential(new SpitOutCube(1, AutoVars.SpitOutSpeed));
			
			addSequential(new LiftElevatorToDistanceScale(35));
			addParallel(new ElbowFlip());
			addSequential(new AdvancedDrive(-AutoVars.ADSpeed, 10, 10));
			addParallel(new LiftElevatorToDistanceScale(0));
			addSequential(new TurnToPoint(AutoVars.TurnSpeed, 270));
			addSequential(new CubeGetter());
			addSequential(new TurnToPoint(AutoVars.TurnSpeed, 270));
			addParallel(new LiftElevatorToDistanceScale(30));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 10, 10));
			addSequential(new TurnToPoint(AutoVars.TurnSpeed, 180));
			addSequential(new SpitOutCube(1, AutoVars.SpitOutSpeed));
			System.out.println("Auto Completed");
		default:
			SmartDashboard.putString("MatchType", "!!!Chooser Not Set!!!");
			addSequential(new LowGearDT());
			addSequential(new AdvancedDrive(-.8, 80, 80));
			System.out.println("Auto Completed");
			break;
		}
	}
}
