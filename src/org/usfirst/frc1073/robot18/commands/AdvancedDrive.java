package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AdvancedDrive extends Command {

	private double speed, currentSpeed, finalSpeed, finalSpeedL, finalSpeedR, dist, toBeTraveled, inch, leftEncDif, rightEncDif, startleftEncDif, percentComplete, avgEncDif, startrightEncDif, originalDegrees, currentDegrees, n;
	private double timeout, timer, timeEnd;
	private boolean fin;

	/** PID, but not because this actually works.
	 * @author Nathaniel
	 * @param speed
	 * @param dist in inches (must be positive)
	 * @param timeout in milliseconds (1000 in a second) 
	 * Note: 0 = no timeout
	 * Note: Minimum of 5 millisecond run time
	 * @category Drive Command
	 */
	public AdvancedDrive(double speed, double dist, double timeout) {
		this.speed = speed;
		this.dist = dist;
		this.timeout = timeout;

		/* Sets up encoders */
		RobotMap.leftMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		RobotMap.rightMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);

	}

	protected void initialize() {
		/* Timer setup and check for if used */
		timeEnd = timeout;
		timer = 0;

		/* Grabs initial robot encoder position */
		startleftEncDif = RobotMap.leftMotor1.getSelectedSensorPosition(0);
		startrightEncDif = RobotMap.rightMotor1.getSelectedSensorPosition(0);

		/* Sets speed to an editable value and zeros out values */
		currentSpeed = speed;
		avgEncDif = 0;

		/* Grabs current heading to use for comparison during drive */
		originalDegrees = RobotMap.headingGyro.getAngle();

		/* Variables for the math of the encoder tick to distance */
		double rotation = 1440;
		double circumference = 12.25221134900019363000430919479;
		inch = 117.52980412939963256779108679819;
		toBeTraveled = (dist * inch * 1.045); // Distance to be traveled as used in the code

		/* Extra variables */
		n = 0;
		fin = false;
	}

	protected void execute() {
		/* Checks how far the robot has gone from the initial position */
		leftEncDif = Math.abs(startleftEncDif - RobotMap.leftMotor1.getSelectedSensorPosition(0));
		rightEncDif = Math.abs(startrightEncDif - RobotMap.rightMotor1.getSelectedSensorPosition(0));

		/* Checks current heading */
		currentDegrees = RobotMap.headingGyro.getAngle();

		/** Code used to adjust the heading for straighter travel */
		if (currentSpeed > 0) {
			if (1 < originalDegrees - currentDegrees) {
				finalSpeedL = 1;
				finalSpeedR = .9;
			}
			else if (-1 > originalDegrees - currentDegrees) {
				finalSpeedL = .9;
				finalSpeedR = 1;
			}
			else {
				finalSpeedL = 1;
				finalSpeedR = 1;
			}
		}
		if (currentSpeed < 0) {
			if (1 < originalDegrees - currentDegrees) {
				finalSpeedL = .9;
				finalSpeedR = 1;
			}
			else if (-1 > originalDegrees - currentDegrees) {
				finalSpeedL = 1;
				finalSpeedR = .9;
			}
			else {
				finalSpeedL = 1;
				finalSpeedR = 1;
			}
		}

		/* Sets up a final speed */
		finalSpeed = currentSpeed;

		/* Grabs a distance traveled based on the average of the two sides */
		avgEncDif = (leftEncDif + rightEncDif) / 2;

		/* Uses that average and the original distance to be traveled to make a percentage total completed */
		percentComplete = avgEncDif/toBeTraveled;

		/** Sets the motor with their respective offsets based on heading adjustment */
		Robot.drivetrain.difDrive.tankDrive(-finalSpeed * finalSpeedL, -finalSpeed * finalSpeedR);

		/* Timer step for if timed */
		timer++;
	}

	protected boolean isFinished() {
		boolean isFinished = false;

		/** Decides if code should use timeout */
		if (Robot.EncoderBoolSet == true && Robot.EncoderBool == false) {
			if (Robot.oi.cancel.get() == true || timer >= timeEnd) {
				Robot.EncoderBool = false;
				isFinished = true;
			}
		}
		if (timer > 5 && avgEncDif == 0) { 
			if (Robot.oi.cancel.get() == true || timer >= timeEnd) {
				Robot.EncoderBoolSet = true;
				Robot.EncoderBool = false;
				isFinished = true;
			}
		}
		else if (timer > 5 && avgEncDif != 0) {
			if (Robot.oi.cancel.get() == true || percentComplete >= .99) {
				Robot.EncoderBoolSet = true;
				Robot.EncoderBool = true;
				isFinished = true;
			}
		}
		else if (Robot.oi.cancel.get() == true) {
			isFinished = true;
		}
		return isFinished;
	}
}