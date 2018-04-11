package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RunElevatorWithShifting extends Command {
	
	private double speed;
	private boolean highGear;
	private double speed2;
	
	public RunElevatorWithShifting() {
		requires(Robot.elevator);
	}
	
	protected void initialize() {
		speed = 0;
		speed2 = 0;
		//highGear = false;

	}
	
	protected void execute() {
		//moves the lift
		if (Robot.oi.operatorControl.getRawAxis(1) > 0.05 ) {
			
			speed = (Robot.oi.operatorControl.getRawAxis(1)*0.5);
			highGear = false;
		}
		else if(Robot.oi.operatorControl.getRawAxis(1) < -0.05) {
			speed = (Robot.oi.operatorControl.getRawAxis(1));
			highGear = false;
		}
		else {
			speed = 0;
		}
		
		if(RobotMap.liftSwitchBottom.get() || speed < 0)
    	{
			Robot.elevator.elevatorDrive.tankDrive(speed, speed);
    	}
    	else {
    		Robot.elevator.elevatorDrive.tankDrive(0, 0);
    	}
		
		//moves the elbow
		if ((Robot.oi.operatorControl.getRawAxis(5) > 0.05)){
			if(RobotMap.elbowMotor.getSelectedSensorPosition(0)>5000) {
				speed2 = .25;
			}
			else {
			speed2 = (Robot.oi.operatorControl.getRawAxis(5)*0.75);
		}
		}
		else if(Robot.oi.operatorControl.getRawAxis(5) < -0.05) {
			speed2 = (Robot.oi.operatorControl.getRawAxis(5)*0.5);
		}
		if(RobotMap.collectorFlip.get() || speed2 <0 || RobotMap.collectorSwitchFront.get()) {
			Robot.elevator.elbowDrive.tankDrive(0, 0);
			}
		else {
			speed2 = 0;
		}
			Robot.elevator.elbowDrive.tankDrive(speed2, speed2);
			
		
		
	}

	protected boolean isFinished() {
		return false;
	}

}
