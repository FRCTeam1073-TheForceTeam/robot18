package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.AutoVars;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto3CommandL_LR extends CommandGroup {
	/** If Chooser is set to Left and FMS is LRL */
	public Auto3CommandL_LR(){
		addParallel(new LiftElevatorToDistanceScale(AutoVars.LiftDistUp));
		addParallel(new OpenClaw());
		addSequential(new AdvancedDrive(AutoVars.ADSpeed, AutoVars.SwitchAD1Distance, AutoVars.SwitchAD1Timeout));
		addParallel(new Dropoff(AutoVars.DropoffTime, AutoVars.LeftDropoff));
		addParallel(new LiftElevatorToDistanceScale(AutoVars.LiftDistDown));
		addParallel(new CloseClaw());
		addSequential(new AdvancedDrive(AutoVars.ADSpeed, AutoVars.SwitchAD2Distance, AutoVars.SwitchAD2Timeout));
		addSequential(new TurnWithGyro(AutoVars.SwitchVisionTurnSpeed, AutoVars.ScaleVisionTurnDistance, AutoVars.LeftVisionTurn));
		addSequential(new CubeGetter());
	}
}