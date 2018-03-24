package org.usfirst.frc1073.robot18.commands.AutonomousChooser;

import org.usfirst.frc1073.robot18.AutoVars;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.*;

public class Auto3CommandL_LR extends CommandGroup {
	/** If Chooser is set to Left and FMS is LRL */
	public Auto3CommandL_LR(){
		addParallel(new LiftElevatorToDistanceScale(AutoVars.LiftDistUp));
		addParallel(new OpenClaw()); //Claw must be open to cross plane of switch
		addSequential(new AdvancedDrive(AutoVars.ADSpeed, AutoVars.SwitchAD1Distance, AutoVars.SwitchAD1Timeout));
		addParallel(new LiftElevatorToDistanceScale(AutoVars.LiftDistDown));
		addSequential(new AdvancedDrive(AutoVars.ADSpeed, AutoVars.SwitchAD2Distance, AutoVars.SwitchAD2Timeout));
		addSequential(new TurnWithGyro(AutoVars.SwitchVisionTurnSpeed, AutoVars.ScaleVisionTurnDistance, AutoVars.LeftVisionTurn));
		addSequential(new CubeGetter()); //Sets up for depositing on scale if we add it by grabbing a cube
		System.out.println("Auto Completed");
	}
}