package org.usfirst.frc1073.robot18.commands.AutonomousChooser;

import org.usfirst.frc1073.robot18.AutoVars;
import org.usfirst.frc1073.robot18.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1073.robot18.commands.HighGearDT;
import org.usfirst.frc1073.robot18.commands.LowGearDT;
import org.usfirst.frc1073.robot18.commands.NeverEndingSpit;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.*;

/*** If Chooser is set to Right and FMS is LLL */
public class Auto3CommandR_LL extends CommandGroup {
	/** If Chooser is set to Right and FMS is LLL
	 * @author Jack
	 */
	public Auto3CommandR_LL(){
		switch(Robot.autonomousMatchType.getSelected().getString()) {
		case "quals":
			System.out.println("Auto3CommandR_LL - quals");
			addSequential(new LowGearDT());
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 50, 51));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed/2, 50, 51));
			addSequential(new AdvancedDrive(-AutoVars.ADSpeed, 60, 70));
			addSequential(new TurnToPoint(.8,-90));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 145, 80));
			addSequential(new TurnToPoint(.8,0));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 40, 45));
			addSequential(new NeverEndingSpit());
			break;
		case "elims":
			System.out.println("Auto3CommandR_LL - elims"); //Passes Autoline and goes near scale
			addSequential(new LowGearDT());
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 200, 200));
			addSequential(new TurnWithGyro(AutoVars.TurnSpeed, 90, "counterclockwise"));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 100, 100));
			System.out.println("Auto Completed");
			break;
		default:
			SmartDashboard.putString("MatchType", "!!!Chooser Not Set!!!");
			addSequential(new LowGearDT());
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 80, 80));
			System.out.println("Auto Completed");
			break;
		}
	}
}
