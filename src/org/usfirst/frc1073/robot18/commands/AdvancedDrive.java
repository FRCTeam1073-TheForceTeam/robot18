package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AdvancedDrive extends Command {
	
	private double speed, currentSpeed, currentSpeedL, currentSpeedR, dist, toBeTraveled, inch, leftEncDif, rightEncDif, startleftEncDif, percentComplete, avgEncDif, startrightEncDif, originalDegrees, n;
	
	private boolean fin;
	
	/** PID, but not because this actually works.
     * @author Nathaniel
     * @param left speed
     * @param right speed
     * @param dist in inches (must be positive)
     * @category Drive Command
     */
	public AdvancedDrive(double speed, double dist) {
		this.speed = speed;
		this.dist = dist;
		RobotMap.leftMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		RobotMap.rightMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		
	}
	
	protected void initialize() {
		startleftEncDif = RobotMap.leftMotor1.getSelectedSensorPosition(0);
		startrightEncDif = RobotMap.rightMotor1.getSelectedSensorPosition(0);
		
		currentSpeed = speed;
		
		double rotation = 1440;
    	double circumference = 12.25221134900019363000430919479;
    	inch = 117.52980412939963256779108679819;
    	
    	toBeTraveled = (dist * inch * 1.045);
    	
    	n = 0;
    	fin = false;
	}
	
	protected void execute() {
		leftEncDif = Math.abs(startleftEncDif - RobotMap.leftMotor1.getSelectedSensorPosition(0));
		rightEncDif = Math.abs(startrightEncDif - RobotMap.rightMotor1.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Left Encoder", leftEncDif);
		SmartDashboard.putNumber("Right Encoder", rightEncDif);
		
		if (leftEncDif > (rightEncDif * 1.005)) {
			currentSpeedL = .90;
		}
		else {
			currentSpeedL = 1;
		}
		if (rightEncDif > (leftEncDif * 1.005)) {
			currentSpeedR = .90;
		}
		else {
			currentSpeedR = 1;
		}
		
		avgEncDif = (leftEncDif + rightEncDif) / 2;
		
		percentComplete = avgEncDif/toBeTraveled;
		
		if (percentComplete < 1) {
			if (percentComplete > .90) {
				currentSpeed = currentSpeed / 20;
			}
    	}
		
		Robot.drivetrain.difDrive.tankDrive(-currentSpeed * currentSpeedL, -currentSpeed * currentSpeedR);
		
		/*
		Robot.drivetrain.basicDrive(-speed, speed);
		SmartDashboard.putNumber("toBeTraveled", n);
		n++; 
		*/
	}

	protected boolean isFinished() {
		boolean isFinished = false;
    	if (Robot.oi.cancel.get() == true || percentComplete >= .99) {
    		isFinished = true;
    	}
		return isFinished;
	}
}