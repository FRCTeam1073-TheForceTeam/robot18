package org.usfirst.frc1073.robot18.commands.AutonomousTools;

import org.usfirst.frc1073.robot18.Robot;

import edu.wpi.first.wpilibj.command.Command;
/***
 * Moves Elevator based on time
 * @param gear "high" or "low"
 * @param speed 1 = up, -1 = down
 * @param time (x20 milliseconds)
 */
public class ElevatorMove extends Command {

	public String gear;
	public double timer, timeout, dir;

	/**
	 * Moves Elevator based on time
	 * @param gear "high" or "low"
	 * @param speed 1 = up, -1 = down
	 * @param time (x20 milliseconds)
	 */
	public ElevatorMove(String gear, double dir, double time) {
		this.timeout = time;
		this.gear = gear;
		this.dir = dir;
	}

	protected void initialize() {
		if (gear.equals("high")) {
			Robot.pneumatic.liftHighGear();
		}
		else if (gear.equals("low")) {
			Robot.pneumatic.liftLowGear();
		}
		else {
			System.out.println("Bad input for gear: " + gear);
		}

		timer = 0;
	}

	protected void execute() {
		if (timer < timeout) {
			Robot.elevator.elevatorDrive.tankDrive(-dir, dir);
		}
		else {
			Robot.elevator.elevatorDrive.tankDrive(0, 0);
		}
		timer++;
	}

	protected boolean isFinished() {
		boolean finished = false;
		if (timer >= timeout) {
			finished = true;
		}
		return finished;
	}

}
