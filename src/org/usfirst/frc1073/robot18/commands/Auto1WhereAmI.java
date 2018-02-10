package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/*** Sets FMS and Displays FMS and Chooser values */
public class Auto1WhereAmI extends Command {

	protected void initialize() {
		/* Sets FMS for the rest of the match */
		Robot.FMS = DriverStation.getInstance().getGameSpecificMessage();
		SmartDashboard.putString("FMS DATA", Robot.FMS);
		SmartDashboard.putString("Chooser", Robot.autonomousChooser.getSelected().getString());
	}

	protected boolean isFinished() {
		boolean isFinished = true;
		return isFinished;
	}

}
