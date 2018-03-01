package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SpinConveyorTele extends Command {
	
	private double speed;
	
	public SpinConveyorTele() {
		requires(Robot.conveyor);
	}
	
	protected void initialize() {
		speed = 0;
	}
	
	protected void execute() {
		if (Robot.oi.driverControl.getRightTrigger() > 0) {
			speed = Robot.oi.driverControl.getRightTrigger();
		}
		else if (Robot.oi.driverControl.getLeftTrigger() > 0) {
			speed = -Robot.oi.driverControl.getLeftTrigger();
		}
		else {
			speed = 0;
		}
		Robot.conveyor.conveyorDrive.tankDrive(speed, speed);
	}

	protected boolean isFinished() {
		return false;
	}

}
