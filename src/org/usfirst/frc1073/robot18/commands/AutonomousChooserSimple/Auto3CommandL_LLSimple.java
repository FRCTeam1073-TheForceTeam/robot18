package org.usfirst.frc1073.robot18.commands.AutonomousChooserSimple;

import org.usfirst.frc1073.robot18.AutoVars;
import org.usfirst.frc1073.robot18.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1073.robot18.commands.HighGearDT;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.*;

public class Auto3CommandL_LLSimple extends CommandGroup {
	/** If Chooser is set to Left and FMS is LLL */
	public Auto3CommandL_LLSimple() {
		addSequential(new AdvancedDrive(-.8, 115, 100));
		addSequential(new Dropoff(2, "left"));
	}
}