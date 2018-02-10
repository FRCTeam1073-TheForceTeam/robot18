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
	
	/** Drive Command that moves a certain distance
	 * @param target Distance you want to go in revolutions
	 * @param speed Speed that you want motors to move at(0-1)
	 * @author sreekar and anderson
	 * @category Drive Command
	 */
    public MoveWithEnc(double target, double speed) {
    	this.target = target*1440;
    	this.speed = speed;
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	int absolutePosition = RobotMap.leftMotor1.getSelectedSensorPosition(0) & 0xFFF;
    	
    	RobotMap.leftMotor1.setSelectedSensorPosition(absolutePosition, 0, 0);
    	RobotMap.rightMotor1.setSelectedSensorPosition(absolutePosition, 0, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    		RobotMap.leftMotor1.set(ControlMode.PercentOutput, -speed);
    		RobotMap.rightMotor1.set(ControlMode.PercentOutput, speed);
    		
    		RobotMap.leftMotor1.setSensorPhase(true);
    		
        	double Eright = RobotMap.rightMotor1.getSelectedSensorPosition(0);
        	double Eleft = RobotMap.leftMotor1.getSelectedSensorPosition(0);
        	
        	SmartDashboard.putNumber("Encoder Right", Eright);
        	SmartDashboard.putNumber("Encoder Left", Eleft);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (RobotMap.leftMotor1.getSelectedSensorPosition(0) >= target)
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
