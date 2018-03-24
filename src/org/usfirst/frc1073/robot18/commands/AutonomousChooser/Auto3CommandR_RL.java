package org.usfirst.frc1073.robot18.commands.AutonomousChooser;

import org.usfirst.frc1073.robot18.AutoVars;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.*;

public class Auto3CommandR_RL extends CommandGroup {
	/** If Chooser is set to Right and FMS is RLR */
	public Auto3CommandR_RL(){
		addParallel(new LiftElevatorToDistanceScale(AutoVars.LiftDistSwitch));
		addParallel(new OpenClaw()); //Claw must be open to cross plane of switch
		addSequential(new AdvancedDrive(AutoVars.ADSpeed, AutoVars.SwitchAD1Distance, AutoVars.SwitchAD1Timeout));
		addParallel(new Dropoff(AutoVars.DropoffTime, AutoVars.RightDropoff));
		addParallel(new LiftElevatorToDistanceScale(AutoVars.LiftDistFloor));
		addSequential(new AdvancedDrive(AutoVars.ADSpeed, AutoVars.SwitchAD2Distance, AutoVars.SwitchAD2Timeout));
		addSequential(new TurnWithGyro(AutoVars.SwitchVisionTurnSpeed, AutoVars.ScaleVisionTurnDistance, AutoVars.RightVisionTurn));
		addSequential(new CubeGetter()); //Sets up for depositing on scale if we add it by grabbing a cube
		System.out.println("Auto Completed");
	}
}