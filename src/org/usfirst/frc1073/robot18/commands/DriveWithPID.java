package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.RobotMap;
import org.usfirst.frc1073.robot18.subsystems.*;

import com.ctre.phoenix.motorcontrol.*;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveWithPID extends Command {

	double target;
	double errorleft;
	double errorright;
	double Eright;
	double Eleft;
    public DriveWithPID(double _target) {
    	target = _target*Math.PI*3.9;
    }
    // Called just before this Command runs the first time
    protected void initialize() {    	
    	/*config left side*/
    	
    	/* lets grab the 360 degree position of the MagEncoder's absolute position */
		int absolutePosition = RobotMap.leftMotor3E.getSelectedSensorPosition(0) & 0xFFF; /* mask out the bottom12 bits, we don't care about the wrap arounds */
        /* use the low level API to set the quad encoder signal */
        RobotMap.leftMotor3E.setSelectedSensorPosition(absolutePosition, 0, 0);
        
        /* choose the sensor and sensor direction */
        RobotMap.leftMotor3E.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        RobotMap.leftMotor3E.setSensorPhase(true);
        
        /* set the peak and nominal outputs, 12V means full */
        RobotMap.leftMotor3E.configNominalOutputForward(0, 0);
        RobotMap.leftMotor3E.configNominalOutputReverse(0, 0);
        RobotMap.leftMotor3E.configPeakOutputForward(1, 0);
        RobotMap.leftMotor3E.configPeakOutputReverse(-1, 0);
        /* set the allowable closed-loop error,
         * Closed-Loop output will be neutral within this range.
         * See Table in Section 17.2.1 for native units per rotation. 
         */
        RobotMap.leftMotor3E.configAllowableClosedloopError(0, 0, 0); /* always servo */
        /* set closed loop gains in slot0 */
        RobotMap.leftMotor3E.config_kF(0, 0.1, 0);
        RobotMap.leftMotor3E.config_kP(0, 1, 0);
        RobotMap.leftMotor3E.config_kI(0, 0.0, 0);
        RobotMap.leftMotor3E.config_kD(0, 0.0, 0);
        
        /*config right side*/
    	
    	/* lets grab the 360 degree position of the MagEncoder's absolute position */
		//int absolutePosition = RobotMap.rightMotor3E.getSelectedSensorPosition(0) & 0xFFF; /* mask out the bottom12 bits, we don't care about the wrap arounds */
        /* use the low level API to set the quad encoder signal */
        RobotMap.rightMotor3E.setSelectedSensorPosition(absolutePosition, 0, 0);
        
        /* choose the sensor and sensor direction */
        RobotMap.rightMotor3E.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        RobotMap.rightMotor3E.setSensorPhase(true);
        
        /* set the peak and nominal outputs, 12V means full */
        RobotMap.rightMotor3E.configNominalOutputForward(0, 0);
        RobotMap.rightMotor3E.configNominalOutputReverse(0, 0);
        RobotMap.rightMotor3E.configPeakOutputForward(1, 0);
        RobotMap.rightMotor3E.configPeakOutputReverse(-1, 0);
        /* set the allowable closed-loop error,
         * Closed-Loop output will be neutral within this range.
         * See Table in Section 17.2.1 for native units per rotation. 
         */
        RobotMap.rightMotor3E.configAllowableClosedloopError(0, 0, 0); /* always servo */
        /* set closed loop gains in slot0 */
        RobotMap.rightMotor3E.config_kF(0, 0.1, 0);
        RobotMap.rightMotor3E.config_kP(0, 1, 0);
        RobotMap.rightMotor3E.config_kI(0, 0.0, 0);
        RobotMap.rightMotor3E.config_kD(0, 0.0, 0);
    	
        /*Set the talons*/
        
        RobotMap.leftMotor3E.set(ControlMode.Position, target);
        RobotMap.rightMotor3E.set(ControlMode.Position, -target);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	errorleft = Math.abs(RobotMap.leftMotor3E.getClosedLoopError(0));
    	errorright = Math.abs(RobotMap.rightMotor3E.getClosedLoopError(0));
    	
    	Eright = RobotMap.rightMotor3E.get();
    	Eleft = RobotMap.leftMotor3E.get();
    	
    	SmartDashboard.putNumber("Error Right", errorright);
    	SmartDashboard.putNumber("Error Left", errorleft);
    	SmartDashboard.putNumber("Encoder Right", Eright);
    	SmartDashboard.putNumber("Encoder Left", Eright);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		if(Math.abs(errorright) < 100 || Math.abs(errorleft) < 100){
    		return true;
    	}
    	else
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
