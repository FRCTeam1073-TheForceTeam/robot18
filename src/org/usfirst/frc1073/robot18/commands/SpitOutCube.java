package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpitOutCube extends Command {
	
	//Until Set
	//Solenoid rightSolenoid = new Solenoid(1); 

    public SpitOutCube() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	//if (rightSolenoid.get() == true)
			//rightSolenoid.set(false);
    	
    	RobotMap.leftCollectorMotor.set(-0.5);
    	RobotMap.rightCollectorMotor.set(0.5);
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
