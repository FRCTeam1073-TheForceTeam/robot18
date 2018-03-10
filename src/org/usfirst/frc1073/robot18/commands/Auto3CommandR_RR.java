package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.AutoVars;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto3CommandR_RR extends CommandGroup {
	/** If Chooser is set to Right and FMS is RRR */
	public Auto3CommandR_RR(){
		addParallel(new LiftElevatorToDistanceScale(AutoVars.LiftDistUp));
		addParallel(new OpenClaw());
		addSequential(new AdvancedDrive(AutoVars.BothADSpeed, AutoVars.BothAD1Distance, AutoVars.BothAD1Timeout));
		addParallel(new Dropoff(AutoVars.DropoffTime, AutoVars.RightDropoff));
		addParallel(new LiftElevatorToDistanceScale(AutoVars.LiftDistDown));
		addParallel(new CloseClaw());
		addSequential(new AdvancedDrive(AutoVars.BothADSpeed, AutoVars.BothAD2Distance, AutoVars.BothAD2Timeout));
		addSequential(new TurnWithGyro(AutoVars.BothVisionTurnSpeed, AutoVars.BothVisionTurnDistance, AutoVars.RightVisionTurn));
		addSequential(new CubeGetter());
	}
}