package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

public class WhereAmI extends Command {

	protected void initialize() {
		/* Sets FMS for the rest of the match */
		Robot.FMS = DriverStation.getInstance().getGameSpecificMessage();
		
		/* Based on FMS and start decision */
		if (Robot.autonomousChooser.getSelected().equals("left")) {
			if (Robot.FMS.equals("RRR")) {
				Robot.drivetrain.basicDriveTimed(.5, .5, 5000);
			}
			else if (Robot.FMS.equals("RLR")) {
				
			}
			else if (Robot.FMS.equals("LRL")) {
				
			}
			else if (Robot.FMS.equals("LLL")) {
				
			}
			else {
				// Do nothing
			}
		}
	}

	protected void execute() {
		
	}

	protected boolean isFinished() {
		boolean isFinished = true;
		return isFinished;
	}

}
