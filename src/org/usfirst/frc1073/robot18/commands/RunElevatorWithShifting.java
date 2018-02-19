package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RunElevatorWithShifting extends Command {
	
	private double speed;
	private boolean highGear;
	
	public RunElevatorWithShifting() {
		requires(Robot.elevator);
	}
	
	protected void initialize() {
		speed = 0;
		//highGear = false;
	}
	
	protected void execute() {
		
		if (Robot.oi.operatorControl.getRawAxis(1) > 0.05 || Robot.oi.operatorControl.getRawAxis(1) < -0.05) {
			if (highGear == true){
			Robot.pneumatic.liftLowGear();
			}
			speed = Robot.oi.operatorControl.getRawAxis(1);
			highGear = false;
		}
		else if (Robot.oi.operatorControl.getRawAxis(5) > 0.05 || Robot.oi.operatorControl.getRawAxis(5) < -0.05) {
			if (highGear == false) {
			Robot.pneumatic.liftHighGear();
			}
			speed = Robot.oi.operatorControl.getRawAxis(5);
			highGear = true;
		}
		else {
			speed = 0;
		}
		SmartDashboard.putBoolean("Gear High?", highGear);
		//SmartDashboard.putNumber("collllet", speed);
		Robot.elevator.elevatorDrive.tankDrive(speed, -speed);
		
	}

	protected boolean isFinished() {
		return false;
	}

}
