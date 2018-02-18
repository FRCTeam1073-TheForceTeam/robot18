package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;
import org.usfirst.frc1073.robot18.subsystems.robotCollector;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LiftElevatorTo4FtScale extends Command {
	
	double speed = 0;
	double distance;
	double startDistance;
	
    public LiftElevatorTo4FtScale() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.elevatorMotorLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
    	startDistance = RobotMap.elevatorMotorLeft.getSelectedSensorPosition(0);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	distance = RobotMap.elevatorMotorLeft.getSelectedSensorPosition(0);
    	
    	RobotMap.elevatorMotorLeft.set(-.75);
    	
    	SmartDashboard.putNumber("Distance", distance);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean finish = false;
    	if((Math.abs(distance-startDistance)) >= ((15/9.42)*1440*2*(16/5)*(15/20))){
    		finish = true;
    	}
    	
        return finish;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.elevatorMotorLeft.set(0);
		RobotMap.elevatorMotorLeft.setSelectedSensorPosition(0, 0, 10);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	RobotMap.elevatorMotorLeft.set(0);
		RobotMap.elevatorMotorLeft.setSelectedSensorPosition(0, 0, 10);
    }
}
