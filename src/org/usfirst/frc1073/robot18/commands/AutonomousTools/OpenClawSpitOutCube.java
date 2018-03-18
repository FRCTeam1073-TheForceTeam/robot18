package org.usfirst.frc1073.robot18.commands.AutonomousTools;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

import javax.management.timer.Timer;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc1073.robot18.subsystems.*;
/** Opens the pneumatic claw */
public class OpenClawSpitOutCube extends Command {

	protected void initialize() {
		Robot.pneumatic.openClaw();
	}
	protected void execute() {
		
		Robot.collector.collectDrive.tankDrive(1, 1);
		
	}
	protected boolean isFinished() {
		return true;
	}
	protected void end() {
		Robot.collector.collectDrive.tankDrive(0, 0);
	}
}
