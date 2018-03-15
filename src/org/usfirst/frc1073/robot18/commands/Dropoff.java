package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc1073.robot18.Bling;
/*** Drops cube off side of conveyor at speed and direction set in params */
public class Dropoff extends Command {

	private double time, timer, endTime, dir;

	private String direction;

	/** 
	 * Drops cube off side of conveyor at speed and direction set in params
	 * @author Jack
	 * @param time in seconds
	 * @param direction "left" or "right"
	 * @category Auto Command
	 */
	public Dropoff(double time, String direction) {
		this.time = time;
		this.direction = direction;
	}

	protected void initialize() {
		timer = 0;
		endTime = time * 20;
		if (direction.equals("right")) {
			dir = 1;
			Robot.bling.sendDropOff();
		
		}
		else if (direction.equals("left")) {
			dir = -1;
			Robot.bling.sendDropOff();
		}
		else {
			dir = 0;
			System.out.println("DropOff error: Called wrong...");
			System.out.println("You typed: " + direction);
		}
	}

	protected void execute() {
		if (timer < endTime) {
			Robot.conveyor.conveyorDrive.tankDrive(dir, dir);
		}
		timer++;
	}

	protected boolean isFinished() {
		boolean finished = false;
		if (timer >= endTime) {
			Robot.conveyor.conveyorDrive.tankDrive(0, 0);
			finished = true;
			Robot.bling.sendFinished();
		}
		return finished;
	}
}
