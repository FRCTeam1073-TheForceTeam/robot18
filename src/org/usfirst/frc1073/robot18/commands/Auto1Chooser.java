package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/***
 * Fully Autonomous Auto System
 * @author Nathaniel
 * @version 1.0.0
 */
public class Auto1Chooser extends CommandGroup {

	public Auto1Chooser() {
		addSequential(new Auto1WhereAmI());

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
			break;
		}
	}
}