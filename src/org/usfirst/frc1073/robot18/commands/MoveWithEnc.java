package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.RobotMap;
import org.usfirst.frc1073.robot18.subsystems.*;

import com.ctre.phoenix.motorcontrol.*;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MoveWithEnc extends Command {

	double target;
	double speed;
	
    public MoveWithEnc(double _target, double _speed) {
    	target = _target*1440;
    	speed = _speed;
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	int absolutePosition = RobotMap.leftMotor3E.getSelectedSensorPosition(0) & 0xFFF;
    	
    	RobotMap.leftMotor3E.setSelectedSensorPosition(absolutePosition, 0, 0);
    	RobotMap.rightMotor3E.setSelectedSensorPosition(absolutePosition, 0, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    		RobotMap.leftMotor3E.set(ControlMode.Position, -speed);
    		RobotMap.rightMotor3E.set(ControlMode.PercentOutput, speed);
    		
    		RobotMap.leftMotor3E.setSensorPhase(true);
    		
        	double Eright = RobotMap.rightMotor3E.getSelectedSensorPosition(0);
        	double Eleft = RobotMap.leftMotor3E.getSelectedSensorPosition(0);
        	
        	SmartDashboard.putNumber("Encoder Right", Eright);
        	SmartDashboard.putNumber("Encoder Left", Eleft);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (RobotMap.leftMotor3E.getSelectedSensorPosition(0) >= target)
    	{
    		return true;
    	}
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
