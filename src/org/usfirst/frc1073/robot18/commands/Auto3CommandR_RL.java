package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.AutoVars;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto3CommandR_RL extends CommandGroup {
	/** If Chooser is set to Right and FMS is RLR */
	public Auto3CommandR_RL(){
		addParallel(new LiftElevatorToDistanceScale(AutoVars.LiftDistUp));
		addParallel(new OpenClaw());
		addSequential(new AdvancedDrive(AutoVars.ADSpeed, AutoVars.SwitchAD1Distance, AutoVars.SwitchAD1Timeout));
		addParallel(new Dropoff(AutoVars.DropoffTime, AutoVars.RightDropoff));
		addParallel(new LiftElevatorToDistanceScale(AutoVars.LiftDistDown));
		addParallel(new CloseClaw());
		addSequential(new AdvancedDrive(AutoVars.ADSpeed, AutoVars.SwitchAD2Distance, AutoVars.SwitchAD2Timeout));
		addSequential(new TurnWithGyro(AutoVars.SwitchVisionTurnSpeed, AutoVars.ScaleVisionTurnDistance, AutoVars.RightVisionTurn));
		addSequential(new CubeGetter());
	}
}