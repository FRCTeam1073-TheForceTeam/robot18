package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AdvancedDrive extends Command {
	
	public static double left, right, dist, toBeTraveled, inch, n;
	
	
	/** PID, but not because this actually works.
     * @author Nathaniel
     * @param left speed
     * @param right speed
     * @param dist in inches
     * @category Drive Command
     */
	public AdvancedDrive(double left1, double right1, double dist1) {
		left = left1;
		right = right1;
		dist = dist1;
	}
	
	protected void initialize() {
		RobotMap.leftEnc.reset();
		double rotation = 1440;
    	double circumference = 12.25221134900019363000430919479;
    	
    	inch = 117.52980412939963256779108679819;
    	toBeTraveled = dist * inch;
    	n = RobotMap.leftEnc.get();
	}
	
	protected void execute() {
		if (n < toBeTraveled) {
    		Robot.drivetrain.basicDrive(left, right);
    	}
		else {
			Robot.drivetrain.basicDrive(0, 0);
		}
		SmartDashboard.putNumber("n", n);
		SmartDashboard.putNumber("toBeTraveled", toBeTraveled);
    	n = RobotMap.leftEnc.get();
	}

	protected boolean isFinished() {
		boolean isFinished = false;
    	if (Robot.oi.cancel.get() == true) {
    		isFinished = true;
    	}
    	else {
    		isFinished = false;
    	}
		return isFinished;
	}

}
