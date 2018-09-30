package org.usfirst.frc1073.robot18.commands.AutonomousChooser;

import org.usfirst.frc1073.robot18.AutoVars;
import org.usfirst.frc1073.robot18.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1073.robot18.commands.LowGearDT;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.*;

public class Auto3CommandR_RR extends CommandGroup {
	/** If Chooser is set to Left and FMS is LLL
	 * @author Jack
	 */
	public Auto3CommandR_RR(){
		switch(Robot.autonomousMatchType.getSelected().getString()) {
		case "quals":
			System.out.println("Auto3CommandR_RR - quals"); //Places 1 cube in switch
			addSequential(new LowGearDT());
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, AutoVars.SideDist, 100));
			addSequential(new TurnWithGyro(AutoVars.TurnSpeed, 90, "counterclockwise"));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, AutoVars.SideApproach, 25));
			addSequential(new SpitOutCube(1, AutoVars.SpitOutSpeed));
			System.out.println("Auto Completed");
			break;
		case "elims":
			System.out.println("Auto3CommandR_RR - elims"); //Places 1 cube in scale
			addSequential(new LowGearDT());
			addParallel(new LiftElevatorWithTime(AutoVars.LiftDistScale));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 260, 0));
			addSequential(new TurnWithGyro(AutoVars.TurnSpeed, 90, "counterclockwise"));
			addSequential(new SpitOutCube(1, 0));
			System.out.println("Auto Completed");
			break;
		case "experimental":
			System.out.println("Hello World!");
			addSequential(new LowGearDT());
			/*shoots in switch*/
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 120, 150));
			addSequential(new TurnWithGyro(AutoVars.TurnSpeed, 90, "counterclockwise"));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 25, 30));
			addSequential(new SpitOutCube(1, AutoVars.SpitOutSpeed));
			/*grabs new cube*/
			addSequential(new ElbowFlip());
			//addSequential(new LiftElevatorWithTime(500));
			
			
			addSequential(new DropElevatorWithTime(450));
			
			addSequential(new TurnToPoint(AutoVars.TurnSpeed, 0));
			//addParallel(new LiftElevatorWithTime(1000));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 80, 80));
			addSequential(new TurnToPoint(AutoVars.TurnSpeed, 50));
			addSequential(new CubeGetter());
			addSequential(new AdvancedDrive(AutoVars.ADSpeed,10,10));
			/*shoots in switch*/
			addSequential(new TurnToPoint(AutoVars.TurnSpeed, 0));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 10, 10));
			addSequential(new SpitOutCube(1, AutoVars.SpitOutSpeed));
			System.out.println("Auto Completed");
		default:
			SmartDashboard.putString("MatchType", "!!!Chooser Not Set!!!");
			addSequential(new LowGearDT());
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 80, 80)); //Gets to autoline
			System.out.println("Auto Completed");
			break;
		}
	}
}
