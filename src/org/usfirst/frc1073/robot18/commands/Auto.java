package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
/***
 * Fully Autonomous Auto System
 * @author Nathaniel
 * @version 1.0.0
 */
public class Auto extends CommandGroup {

	public Auto() {
		addSequential(new WhereAmI());
	}
}
