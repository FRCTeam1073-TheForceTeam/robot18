package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.AutoVars;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/*** If Chooser is set to Right and FMS is LRL */
public class Auto3CommandR_LR extends CommandGroup {
	/** If Chooser is set to Right and FMS is LRL */
	public Auto3CommandR_LR(){
		addParallel(new Dropoff(AutoVars.DropoffTime, AutoVars.RightDropoff));
		addSequential(new AdvancedDrive(AutoVars.ScaleADSpeed, AutoVars.ScaleAD1Distance, AutoVars.ScaleAD1Timeout));
		addSequential(new TurnWithGyro(AutoVars.ScaleVisionTurnSpeed, AutoVars.ScaleVisionTurnDistance, AutoVars.RightVisionTurn));
		addSequential(new CubeGetter()); //Drops cube near exchange, then turns around and crosses auto line while aligning to a cube
	}
}