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
	 * @param inches Exact distance you want to go in inches
	 * @author Sreekar Chilakapati and Anderson Steckler
	 * @category Drive Command
	 */
    public DriveWithPID(double inches) {
    	this.target = (inches/(Math.PI*3.9))*1440;
    	//this.target += RobotMap.leftMotor1.getSelectedSensorPosition(0);
    	
        /* use the low level API to set the quad encoder signal */
        RobotMap.leftMotor1.setSelectedSensorPosition(0, 0, 0);
        RobotMap.rightMotor1.setSelectedSensorPosition(0, 0, 0);
        
        RobotMap.leftMotor1.set(ControlMode.PercentOutput, 0);
        RobotMap.rightMotor1.set(ControlMode.PercentOutput, 0);
    }
    // Called just before this Command runs the first time
    protected void initialize() {   
    
    	SmartDashboard.putNumber("P", p);
    	SmartDashboard.putNumber("I", i);
    	SmartDashboard.putNumber("D", d);
    	
    	/*config left side*/
     	
    	
    	/* lets grab the 360 degree position of the MagEncoder's absolute position */
		//int absolutePosition = RobotMap.leftMotor1.getSelectedSensorPosition(0) & 0xFFF; /* mask out the bottom12 bits, we don't care about the wrap arounds */
        /* use the low level API to set the quad encoder signal */
        RobotMap.leftMotor1.setSelectedSensorPosition(0, 0, 10);
        
//        /* choose the sensor and sensor direction */
        RobotMap.leftMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
        RobotMap.leftMotor1.setSensorPhase(true);
//        
//        /* set the peak and nominal outputs, 12V means full */
        //RobotMap.leftMotor3E.configNominalOutputForward(0, 10);
        //RobotMap.leftMotor3E.configNominalOutputReverse(0, 10);
        RobotMap.leftMotor1.configPeakOutputForward(.9, 10);
        RobotMap.leftMotor1.configPeakOutputReverse(-.9, 10);
//        /* set the allowable closed-loop error,
//         * Closed-Loop output will be neutral within this range.
//         * See Table in Section 17.2.1 for native units per rotation. 
//         */
//        RobotMap.leftMotor1.configAllowableClosedloopError(0, 0, 10); /* always servo */
//        /* set closed loop gains in slot0 */
        RobotMap.leftMotor1.config_kF(0, 0.0, 10);
        RobotMap.leftMotor1.config_kP(0, SmartDashboard.getNumber("P", 1), 10);
        RobotMap.leftMotor1.config_kI(0, SmartDashboard.getNumber("I", 0), 10);
        RobotMap.leftMotor1.config_kD(0, SmartDashboard.getNumber("D", 0), 10);
        
        /*config right side*/
    	
    	/* lets grab the 360 degree position of the MagEncoder's absolute position */
		//int absolutePosition1 = RobotMap.rightMotor1.getSelectedSensorPosition(0) & 0xFFF; /* mask out the bottom12 bits, we don't care about the wrap arounds */
        /* use the low level API to set the quad encoder signal */
        RobotMap.rightMotor1.setSelectedSensorPosition(0, 0, 10);
        
        RobotMap.rightMotor1.setSensorPhase(true);
        
        /* choose the sensor and sensor direction */
        RobotMap.rightMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
        
        /* set the peak and nominal outputs, 12V means full */
        //RobotMap.rightMotor3E.configNominalOutputForward(0, 10);
        //RobotMap.rightMotor3E.configNominalOutputReverse(0, 10);
        RobotMap.rightMotor1.configPeakOutputForward(.9, 10);
        RobotMap.rightMotor1.configPeakOutputReverse(-.9, 10);
        /* set the allowable closed-loop error,
         * Closed-Loop output will be neutral within this range.
         * See Table in Section 17.2.1 for native units per rotation. 
         */
        RobotMap.rightMotor1.configAllowableClosedloopError(0, 0, 10); /* always servo */
        /* set closed loop gains in slot0 */
        
        RobotMap.rightMotor1.config_kF(0, 0.0, 10);
        RobotMap.rightMotor1.config_kP(0, SmartDashboard.getNumber("P", 1), 10);
        RobotMap.rightMotor1.config_kI(0, SmartDashboard.getNumber("I", 0), 10);
        RobotMap.rightMotor1.config_kD(0, SmartDashboard.getNumber("D", 0), 10);
        
        try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	/*Set the talons*/
    	
    	RobotMap.leftMotor1.set(ControlMode.Position, -target);
        RobotMap.rightMotor1.set(ControlMode.Position, target);
    	
    	rightspeed = RobotMap.rightMotor1.get();
    	leftspeed = RobotMap.leftMotor1.get();
    	
    	SmartDashboard.putNumber("Right Speed", rightspeed);
    	SmartDashboard.putNumber("Left Speed", leftspeed);
    	
    	errorleft = Math.abs(RobotMap.leftMotor1.getClosedLoopError(0));
    	errorright = Math.abs(RobotMap.rightMotor1.getClosedLoopError(0));
    	
    	Eright = RobotMap.rightMotor1.getSelectedSensorPosition(0);
    	Eleft = RobotMap.leftMotor1.getSelectedSensorPosition(0);
    	
    	SmartDashboard.putNumber("Error Right", errorright);
    	SmartDashboard.putNumber("Error Left", errorleft);
    	SmartDashboard.putNumber("Encoder Right", Eright);
    	SmartDashboard.putNumber("Encoder Left", Eleft);
    	
    	SmartDashboard.putNumber("Left Voltage", RobotMap.leftMotor1.getMotorOutputVoltage());    	
    	SmartDashboard.putNumber("Right Voltage", RobotMap.rightMotor1.getMotorOutputVoltage());   
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean finish = false;
		if(Math.abs(errorright) < 100 || Math.abs(errorleft) < 100 || Robot.oi.cancel.get() == true){
	        RobotMap.leftMotor1.set(ControlMode.PercentOutput, 0);
	        RobotMap.rightMotor1.set(ControlMode.PercentOutput, 0);
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
		//int absolutePosition = RobotMap.leftMotor1.getSelectedSensorPosition(0) & 0xFFF; /* mask out the bottom12 bits, we don't care about the wrap arounds */
		
        /* use the low level API to set the quad encoder signal */
        RobotMap.leftMotor1.setSelectedSensorPosition(0, 0, 0);
        RobotMap.rightMotor1.setSelectedSensorPosition(0, 0, 0);
        
        RobotMap.leftMotor1.set(ControlMode.PercentOutput, 0);
        RobotMap.rightMotor1.set(ControlMode.PercentOutput, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
