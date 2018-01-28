package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.commands.LidarTest;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LidarAuto extends CommandGroup {
	
	public static Command selectedCommand;

	public LidarAuto() {
		addSequential(new selectCommand());
	} 
}
