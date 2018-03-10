package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.AutoVars;
import org.usfirst.frc1073.robot18.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto3CommandL_LL extends CommandGroup {
	/** If Chooser is set to Left and FMS is LLL */
	public Auto3CommandL_LL(){
		addParallel(new LiftElevatorToDistanceScale(AutoVars.LiftDistUp));
		addParallel(new OpenClaw());
		addSequential(new AdvancedDrive(AutoVars.BothADSpeed, AutoVars.BothAD1Distance, AutoVars.BothAD1Timeout));
		addParallel(new Dropoff(AutoVars.DropoffTime, AutoVars.LeftDropoff));
		addParallel(new LiftElevatorToDistanceScale(AutoVars.LiftDistDown));
		addParallel(new CloseClaw());
		addSequential(new AdvancedDrive(AutoVars.BothADSpeed, AutoVars.BothAD2Distance, AutoVars.BothAD2Timeout));
		addSequential(new TurnWithGyro(AutoVars.BothVisionTurnSpeed, AutoVars.BothVisionTurnDistance, AutoVars.LeftVisionTurn));
		addSequential(new CubeGetter());
	}
}