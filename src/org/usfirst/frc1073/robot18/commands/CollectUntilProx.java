package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CollectUntilProx extends Command {

	double target;
	double total;
	double voltage;
	double distance;
	
	boolean reverse;
	
	//Until Set
	Solenoid rightSolenoid = new Solenoid(1); 
	
    public CollectUntilProx(double target, boolean reverse) {
    	this.target = target;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Proximity code
    	//Tag someone who wrote this code @ramen_nooodles
    	total = 0;
		for (int i = 0; i < 10; i++) {
			voltage = RobotMap.frontSensor.getVoltage();
			distance = (Robot.voltage - 0.0399)/0.0234;  
			total += distance;
		}
		total = total/10;
		
    	if (distance <= target) 
    	{
    		if (!reverse) {
    			RobotMap.leftCollectorMotor.set(-0.5);
    			RobotMap.rightCollectorMotor.set(-0.5);
    			
    			if (rightSolenoid.get() == true)
    				rightSolenoid.set(false);
    		}
    		else 
    		{
    			RobotMap.leftCollectorMotor.set(0.5);
    			RobotMap.rightCollectorMotor.set(0.5);
    			
    			if (rightSolenoid.get() == false)
    				rightSolenoid.set(true);
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.leftCollectorMotor.set(0);
    	RobotMap.rightCollectorMotor.set(0);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	RobotMap.leftCollectorMotor.set(0);
    	RobotMap.rightCollectorMotor.set(0);
    }
}
