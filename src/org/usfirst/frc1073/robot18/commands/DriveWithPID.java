package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
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
	
	double rightspeed;
	double leftspeed;
	
	boolean rightInverted;
	boolean leftInverted;
	
	double p = 1;
	double i = 0;
	double d = 0;
	
	/** Drive Command that moves an exact distance with PID
	 * @param target Exact distance you want to go in revolutions
	 * @author sreekar and anderson
	 * @category Drive Command
	 */
    public DriveWithPID(double target) {
    	this.target = target*1440;
    }
    // Called just before this Command runs the first time
    protected void initialize() {   
    	
        rightInverted = false;
        leftInverted = true;
        
    	RobotMap.leftMotor1.setInverted(leftInverted);
    	RobotMap.leftMotor2.setInverted(leftInverted);
    	RobotMap.leftMotor3E.setInverted(leftInverted);
    	RobotMap.rightMotor1.setInverted(rightInverted);
    	RobotMap.rightMotor2.setInverted(rightInverted);
    	RobotMap.rightMotor3E.setInverted(rightInverted);
    	
    	RobotMap.leftMotor2.follow(RobotMap.leftMotor3E);
    	RobotMap.leftMotor1.follow(RobotMap.leftMotor3E);
    	RobotMap.rightMotor2.follow(RobotMap.leftMotor3E);
    	RobotMap.rightMotor1.follow(RobotMap.leftMotor3E);
    	RobotMap.rightMotor3E.follow(RobotMap.leftMotor3E);
    	
    	SmartDashboard.putNumber("P", p);
    	SmartDashboard.putNumber("I", i);
    	SmartDashboard.putNumber("D", d);
    	
    	/*config left side*/
    	
    	/* lets grab the 360 degree position of the MagEncoder's absolute position */
		int absolutePosition = RobotMap.leftMotor3E.getSelectedSensorPosition(0) & 0xFFF; /* mask out the bottom12 bits, we don't care about the wrap arounds */
        /* use the low level API to set the quad encoder signal */
        RobotMap.leftMotor3E.setSelectedSensorPosition(absolutePosition, 0, 10);
        
        /* choose the sensor and sensor direction */
        RobotMap.leftMotor3E.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
        RobotMap.leftMotor3E.setSensorPhase(false);
        
        /* set the peak and nominal outputs, 12V means full */
        //RobotMap.leftMotor3E.configNominalOutputForward(0, 10);
        //RobotMap.leftMotor3E.configNominalOutputReverse(0, 10);
        //RobotMap.leftMotor3E.configPeakOutputForward(1, 10);
        //RobotMap.leftMotor3E.configPeakOutputReverse(-1, 10);
        /* set the allowable closed-loop error,
         * Closed-Loop output will be neutral within this range.
         * See Table in Section 17.2.1 for native units per rotation. 
         */
        RobotMap.leftMotor3E.configAllowableClosedloopError(0, 0, 10); /* always servo */
        /* set closed loop gains in slot0 */
        RobotMap.leftMotor3E.config_kF(0, 0.0, 10);
        RobotMap.leftMotor3E.config_kP(0, SmartDashboard.getNumber("P", 1), 10);
        RobotMap.leftMotor3E.config_kI(0, SmartDashboard.getNumber("I", 0), 10);
        RobotMap.leftMotor3E.config_kD(0, SmartDashboard.getNumber("D", 0), 10);
        
        /*config right side*/
    	
    	/* lets grab the 360 degree position of the MagEncoder's absolute position */
		//int absolutePosition = RobotMap.rightMotor3E.getSelectedSensorPosition(0) & 0xFFF; /* mask out the bottom12 bits, we don't care about the wrap arounds */
        /* use the low level API to set the quad encoder signal */
        //RobotMap.rightMotor3E.setSelectedSensorPosition(absolutePosition, 0, 10);
        
        /* choose the sensor and sensor direction */
        //RobotMap.rightMotor3E.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
        
        /* set the peak and nominal outputs, 12V means full */
        //RobotMap.rightMotor3E.configNominalOutputForward(0, 10);
        //RobotMap.rightMotor3E.configNominalOutputReverse(0, 10);
        //RobotMap.rightMotor3E.configPeakOutputForward(1, 10);
        //RobotMap.rightMotor3E.configPeakOutputReverse(-1, 10);
        /* set the allowable closed-loop error,
         * Closed-Loop output will be neutral within this range.
         * See Table in Section 17.2.1 for native units per rotation. 
         */
        //RobotMap.rightMotor3E.configAllowableClosedloopError(0, 0, 10); /* always servo */
        /* set closed loop gains in slot0 */
        //RobotMap.rightMotor3E.config_kF(0, 0.0, 10);
        //RobotMap.rightMotor3E.config_kP(0, SmartDashboard.getNumber("P", 1), 10);
        //RobotMap.rightMotor3E.config_kI(0, SmartDashboard.getNumber("I", 0), 10);
        //RobotMap.rightMotor3E.config_kD(0, SmartDashboard.getNumber("D", 0), 10);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	/*Set the talons*/
    	
    	RobotMap.leftMotor3E.set(ControlMode.Position, -target);
        //RobotMap.rightMotor3E.set(ControlMode.Position, target);
    	
    	rightspeed = RobotMap.rightMotor3E.get();
    	leftspeed = RobotMap.leftMotor3E.get();
    	
    	SmartDashboard.putNumber("Right Speed", rightspeed);
    	SmartDashboard.putNumber("Left Speed", leftspeed);
    	
    	errorleft = Math.abs(RobotMap.leftMotor3E.getClosedLoopError(0));
    	errorright = Math.abs(RobotMap.rightMotor3E.getClosedLoopError(0));
    	
    	Eright = RobotMap.rightMotor3E.getSelectedSensorPosition(0);
    	Eleft = RobotMap.leftMotor3E.getSelectedSensorPosition(0);
    	
    	SmartDashboard.putNumber("Error Right", errorright);
    	SmartDashboard.putNumber("Error Left", errorleft);
    	SmartDashboard.putNumber("Encoder Right", Eright);
    	SmartDashboard.putNumber("Encoder Left", Eleft);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean finish = false;
		if(Math.abs(errorleft) < 100 || Robot.oi.cancel.get() == true){
    		finish = true;
    	}
    	else {
    		finish = false;
    	}
		return finish;
    }

    // Called once after isFinished returns true
    protected void end() {
    	/* lets grab the 360 degree position of the MagEncoder's absolute position */
		int absolutePosition = RobotMap.leftMotor3E.getSelectedSensorPosition(0) & 0xFFF; /* mask out the bottom12 bits, we don't care about the wrap arounds */
		
        /* use the low level API to set the quad encoder signal */
        RobotMap.leftMotor3E.setSelectedSensorPosition(absolutePosition, 0, 0);
        RobotMap.rightMotor3E.setSelectedSensorPosition(absolutePosition, 0, 0);
        
        leftInverted = true;
        rightInverted = true;
        
        RobotMap.leftMotor1.setInverted(leftInverted);
        RobotMap.leftMotor2.setInverted(leftInverted);
        RobotMap.leftMotor3E.setInverted(leftInverted);
        RobotMap.rightMotor1.setInverted(rightInverted);
        RobotMap.rightMotor2.setInverted(rightInverted);
        RobotMap.rightMotor3E.setInverted(rightInverted);
    	
        RobotMap.leftMotor2.follow(RobotMap.leftMotor3E);
        RobotMap.leftMotor1.follow(RobotMap.leftMotor3E);
        RobotMap.rightMotor2.follow(RobotMap.rightMotor3E);
        RobotMap.rightMotor1.follow(RobotMap.rightMotor3E);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		int absolutePosition = RobotMap.leftMotor3E.getSelectedSensorPosition(0) & 0xFFF; /* mask out the bottom12 bits, we don't care about the wrap arounds */
		
        /* use the low level API to set the quad encoder signal */
        RobotMap.leftMotor3E.setSelectedSensorPosition(absolutePosition, 0, 0);
        RobotMap.rightMotor3E.setSelectedSensorPosition(absolutePosition, 0, 0);
        
        leftInverted = true;
        rightInverted = true;
        
        RobotMap.leftMotor1.setInverted(leftInverted);
        RobotMap.leftMotor2.setInverted(leftInverted);
        RobotMap.leftMotor3E.setInverted(leftInverted);
        RobotMap.rightMotor1.setInverted(rightInverted);
        RobotMap.rightMotor2.setInverted(rightInverted);
        RobotMap.rightMotor3E.setInverted(rightInverted);
    	
        RobotMap.leftMotor2.follow(RobotMap.leftMotor3E);
    	RobotMap.leftMotor1.follow(RobotMap.leftMotor3E);
    	RobotMap.rightMotor2.follow(RobotMap.rightMotor3E);
    	RobotMap.rightMotor1.follow(RobotMap.rightMotor3E);
    }
}
