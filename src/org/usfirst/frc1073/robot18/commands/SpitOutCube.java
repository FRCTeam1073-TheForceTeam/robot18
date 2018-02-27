package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc1073.robot18.RobotMap;

/**
 *
 */
public class SpitOutCube extends Command {

	private double time, timer, timeEnd, delay, delayer, delayEnd;

	private boolean clawBool;

	public SpitOutCube() {
		this.time = .5;
		this.delay = 0;
	}

	public SpitOutCube(double time) {
		this.time = time;
		this.delay = 0;
	}

	public SpitOutCube(double time, double delay) {
		this.time = time;
		this.delay = delay;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		clawBool = false;

		delayEnd = delay * 20;
		delayer = 0;
		timeEnd = time * 20;
		timer = 0;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.collector.collectDrive.tankDrive(-1, -1);
		Robot.bling.sendDeliverCube();
		timer++;
		
		if (delayEnd <= delayer) {
			if (clawBool == false) {
				Robot.pneumatic.openClaw();
				clawBool = true;
			}
		}
		delayer++;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		boolean finished = false;
		if (timer > timeEnd) {
			finished = true;
			Robot.collector.collectDrive.tankDrive(0, 0);
		}
		return finished;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
