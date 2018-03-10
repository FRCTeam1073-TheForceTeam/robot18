package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.AutoVars;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/*** If Chooser is set to Left and FMS is RLR */
public class Auto3CommandL_RL extends CommandGroup {
	/** If Chooser is set to Left and FMS is RLR */
	public Auto3CommandL_RL(){
		addParallel(new Dropoff(AutoVars.DropoffTime, AutoVars.LeftDropoff));
		addSequential(new AdvancedDrive(AutoVars.ScaleADSpeed, AutoVars.ScaleAD1Distance, AutoVars.ScaleAD1Timeout));
		addSequential(new TurnWithGyro(AutoVars.ScaleVisionTurnSpeed, AutoVars.ScaleVisionTurnDistance, AutoVars.LeftVisionTurn));
		addSequential(new CubeGetter()); //Drops cube near exchange, then turns around and crosses auto line while aligning to a cube
	}
}