package org.usfirst.frc1073.robot18.commands.AutonomousChooser;

import org.usfirst.frc1073.robot18.AutoVars;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.*;

/*** If Chooser is set to Left and FMS is RRR */
public class Auto3CommandL_RR extends CommandGroup {
	/** If Chooser is set to Left and FMS is RRR */
	public Auto3CommandL_RR(){
		addParallel(new Dropoff(AutoVars.DropoffTime, AutoVars.LeftDropoff));
		addSequential(new AdvancedDrive(AutoVars.ADSpeed, AutoVars.NeitherAD1Distance, AutoVars.NeitherAD1Timeout));
		addSequential(new TurnWithGyro(AutoVars.NeitherVisionTurnSpeed, AutoVars.NeitherVisionTurnDistance, AutoVars.LeftVisionTurn));
		addSequential(new CubeGetter()); //Drops cube near exchange, then turns around and crosses auto line while aligning to a cube
		System.out.println("Auto Completed");
	}
}