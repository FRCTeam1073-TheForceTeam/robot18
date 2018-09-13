package org.usfirst.frc1073.robot18.commands.AutonomousTools;

import org.usfirst.frc1073.robot18.AutoVars;
import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1073.robot18.commands.HighGearDT;
import org.usfirst.frc1073.robot18.commands.LowGearDT;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.*;

/*** If Chooser is set to Center and FMS is LLL */
public class ElbowFlip extends Command {
	/** 
	 * @author Cam
	 */
	double time = 0;
	boolean finished;
	
	protected void initialize() {
		RobotMap.elbowMotor.setSelectedSensorPosition(0, 0, 0);
	}
	protected void execute(){
		while(time < 1) {
			Robot.elevator.elevatorDrive.tankDrive(-.5, -.5);
			time = time + 1;
			if(time == 1) {
				break;
			}
		}
		if(RobotMap.elbowMotor.getSelectedSensorPosition(0)<=1000 || RobotMap.elbowMotor.getSelectedSensorPosition(0 )>=-1000) {
			Robot.elevator.elbowDrive.tankDrive(-.5, -.5);
			finished = false;
		}
		else {
			Robot.elevator.elbowDrive.tankDrive(0, 0);
			finished = true;
		//	isFinished = true;
		}
	}
	protected boolean isFinished() {
	return finished;
	}
	
}
