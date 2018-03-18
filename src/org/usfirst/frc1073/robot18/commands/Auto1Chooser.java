package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/***
 * Fully Autonomous Auto System
 * @author Nathaniel
 * @version 2.0.1
 */
public class Auto1Chooser extends CommandGroup {

	/* NOTE:  You can only use a conditional in a Command group if you do it in AutonomousInit() and is
	 *        you only use information that is available when AutonomousInit() is called. 
    */
	 
	private String FMS;
	public Auto1Chooser() {
		addSequential(new Auto1WhereAmI());
		FMS = Robot.FMS;
		System.out.println("Auto1Chooser FMS=" + FMS);
		System.out.println("Auto1Chooser Position=" + Robot.autonomousChooser.getSelected().getString());
		System.out.println("Auto1Chooser Priority=" + Robot.autonomousPriority.getSelected().getString());

		/*  Second version  */ 
		// If this works it may be broken out into sub commands
		switch(Robot.autonomousChooser.getSelected().getString()) {
		case "center":
			System.out.println("Auto1Chooser start center");
			switch(Robot.autonomousPriority.getSelected().getString()) {
			case "switch":
				System.out.println("Auto1Chooser - start center - priorty switch");
				//addParallel(new LidarSeeRobot());
				//addSequential(new AdvancedDrive(-1, 6, 0));
				if (FMS.charAt(0) == 'L'){
					System.out.println("Auto1Chooser- start center - priorty switch - left switch");
					//addSequential(new TurnWithGyro(.8, 45, "counterclockwise"));
				}
				else if (FMS.charAt(0) == 'R'){
					System.out.println("Auto1Chooser - start center - priorty switch - right switch");
					//addSequential(new TurnWithGyro(.8, 45, "clockwise"));
				}
				else{
					System.out.println("Auto1Chooser case switch - NIEHTER L or R, THIS IS BAD");
				}
				//addParallel(new LidarSeeRobot());
				//addSequential(new AdvancedDrive(-1, 6, 0));
				break;
			case "scale":
				System.out.println("Auto1Chooser - start center - priorty scale");
				break;
			default:
				System.out.println("Auto1Chooser - start center - priorty not set");
				addParallel(new LidarSeeRobot());
				addSequential(new AdvancedDrive(-1, 6, 0));
				if (FMS.charAt(0) == 'L'){
					System.out.println("Auto1Chooser- start center - priorty not set - left switch");
					addSequential(new TurnWithGyro(.8, 45, "counterclockwise"));
				}
				else if (FMS.charAt(0) == 'R'){
					System.out.println("Auto1Chooser - start center - priorty not set - right switch");
					addSequential(new TurnWithGyro(.8, 45, "clockwise"));
				}
				else{
					System.out.println("Auto1Chooser case switch - NIEHTER L or R, THIS IS BAD");
				}
				addParallel(new LidarSeeRobot());
				addSequential(new AdvancedDrive(-1, 6, 0));
				
				break;
		case "right":
		case "left":
			System.out.println("Auto1Chooser start L/R");
			
			break;
			}
		default:
			System.out.println("Auto1Chooser start default");
			SmartDashboard.putString("Chooser", "!!!Chooser Not Set!!!");
			//addSequential(new AdvancedDrive(-.8, 80, 80));
			break;
		}

		/*  First version   */
		/*
		switch(Robot.autonomousChooser.getSelected().getString()) {
		case "left":
			addSequential(new Auto2FMSL());
			break;
		case "center":
			addSequential(new Auto2FMSC());
			break;
		case "right":
			addSequential(new Auto2FMSR());
			break;
		default:
			SmartDashboard.putString("Chooser", "!!!Chooser Not Set!!!");
			addSequential(new AdvancedDrive(-.8, 80, 80));
			break;
		}
		*/
	}
}