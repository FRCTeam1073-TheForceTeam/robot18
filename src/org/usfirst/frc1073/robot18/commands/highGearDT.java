
package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc1073.robot18.subsystems.*;
/**
 *
 */
public class highGearDT extends Command {

	protected void initialize() {
	
		Robot.pneumatic.driveTrainHighGear();

	}

	protected void execute() {

	}

	protected boolean isFinished() {
		
			return true;
		}

		//SmartDashboard.putString("lidar info", "isFinished");

		//Checks the cancel button for its state
		//isPressed = Robot.oi.cancelAny.get();
		//if (true){
		//SmartDashboard.putString("hello_world", "isFinished");
		//return false;
	
	//else 
	//	return true;
	// }

	protected void end() {
		
	}

	protected void interrupted() {
		end();
	}
}
