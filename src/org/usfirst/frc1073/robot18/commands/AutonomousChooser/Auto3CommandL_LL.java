package org.usfirst.frc1073.robot18.commands.AutonomousChooser;

import org.usfirst.frc1073.robot18.AutoVars;
import org.usfirst.frc1073.robot18.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.*;

public class Auto3CommandL_LL extends CommandGroup {
	/** If Chooser is set to Left and FMS is LLL */
	public Auto3CommandL_LL(){
		addParallel(new LiftElevatorToDistanceScale(AutoVars.LiftDistSwitch));
		addParallel(new OpenClaw()); //Claw must be open to cross plane of switch
		addSequential(new AdvancedDrive(AutoVars.BothADSpeed, AutoVars.BothAD1Distance, AutoVars.BothAD1Timeout));
		addParallel(new Dropoff(AutoVars.DropoffTime, AutoVars.LeftDropoff));
		addParallel(new LiftElevatorToDistanceScale(AutoVars.LiftDistFloor));
		addSequential(new AdvancedDrive(AutoVars.BothADSpeed, AutoVars.BothAD2Distance, AutoVars.BothAD2Timeout)); //Drives forward while lowering lift and dropping off cube
		addSequential(new TurnWithGyro(AutoVars.BothVisionTurnSpeed, AutoVars.BothVisionTurnDistance, AutoVars.LeftVisionTurn));
		addSequential(new CubeGetter()); //Sets up for depositing on scale if we add it by grabbing a cube
	}
}