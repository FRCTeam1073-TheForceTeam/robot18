package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;
import org.usfirst.frc1073.robot18.subsystems.robotCollector;
import org.usfirst.frc1073.robot18.subsystems.robotElevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * maybe ??
 */
public class LiftElevator extends Command {
	
	double speed = 0;
	/** wHat is engLISH? !! ?>
	 * 3
	 * 7
	 * 0
	 * 1
	 */
    public LiftElevator() {
    	requires(Robot.elevator);
    }
    
    protected void execute() {
    	
    	speed = Robot.oi.operatorControl.getRawAxis(1);
    	
    	SmartDashboard.putBoolean("high?!", !RobotMap.liftSwitchTop.get());
    	
    	if(!RobotMap.liftSwitchBottom.get()) RobotMap.elevatorMotorLeft.setSelectedSensorPosition(0, 0, 10);
    	
    	if(RobotMap.liftSwitchBottom.get() || speed < 0){
    		RobotMap.elevatorMotorLeft.set(speed);
    	}
    	else if(RobotMap.liftSwitchTop.get() || speed > 0){
    		RobotMap.elevatorMotorLeft.set(speed);
    	}
    	else {
    		RobotMap.elevatorMotorLeft.set(0);
    	}
    }

    protected boolean isFinished() {
        return false;
    }
}
