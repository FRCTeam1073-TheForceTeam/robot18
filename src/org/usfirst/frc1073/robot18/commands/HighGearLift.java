package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc1073.robot18.subsystems.*;
/** Shifts Elevator into high gear */
public class HighGearLift extends Command {

	protected void initialize() {
		Robot.pneumatic.liftHighGear();
	}
	protected boolean isFinished() {
		return true;
	}
}
