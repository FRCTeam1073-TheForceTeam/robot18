package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.AutoVars;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/*** If Chooser is set to Right and FMS is LLL */
public class Auto3CommandR_LL extends CommandGroup {
	/** If Chooser is set to Right and FMS is LLL */
	public Auto3CommandR_LL(){
		addParallel(new Dropoff(AutoVars.DropoffTime, AutoVars.RightDropoff));
		addSequential(new AdvancedDrive(AutoVars.ADSpeed, AutoVars.NeitherAD1Distance, AutoVars.NeitherAD1Timeout));
		addSequential(new TurnWithGyro(AutoVars.NeitherVisionTurnSpeed, AutoVars.NeitherVisionTurnDistance, AutoVars.RightVisionTurn));
		addSequential(new CubeGetter()); //Drops cube near exchange, then turns around and crosses auto line while aligning to a cube
	}
}