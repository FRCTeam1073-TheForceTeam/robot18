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
		SmartDashboard.putNumber("time", time);
	}
	protected void execute(){
		SmartDashboard.putNumber("time", time);
		while(time < 2000) {
			Robot.elevator.elbowDrive.tankDrive(.8, .8);
			time = time + 1;
			finished = false;
			SmartDashboard.putNumber("time", time);
		}
		
	
//		if(RobotMap.elbowMotor.getSelectedSensorPosition(0)<=1000 || RobotMap.elbowMotor.getSelectedSensorPosition(0 )>=-1000) {
//			Robot.elevator.elbowDrive.tankDrive(-.5, -.5);
//			finished = false;
//		}
//		else {
//			Robot.elevator.elbowDrive.tankDrive(0, 0);
//			finished = true;
//		}
	}
	protected boolean isFinished() {
		if (Robot.oi.driverCancel.get() == true || Robot.oi.operatorCancel.get() == true) {
			finished = true;
		}
	return finished;
	}
	
}
