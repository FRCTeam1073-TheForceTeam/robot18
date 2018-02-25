package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoSwitchScale extends CommandGroup {

    public AutoSwitchScale() {
    	
    	boolean infinite = true;
    	
    	if(Robot.gameData.charAt(0) == 'R') {
    		if(Robot.position == 1) {
    			addSequential(new DriveWithPID(10));
    			addSequential(new TurnWithGyro(.4, 90, "clockwise"));
    			addSequential(new DriveWithPID(10));
    			addSequential(new TurnWithGyro(.4, 90, "clockwise")); //Turns and drives into position 3
    		}else if(Robot.position == 2) {
    			addSequential(new DriveWithPID(10));
    			addSequential(new TurnWithGyro(.4, 90, "clockwise"));
    			addSequential(new DriveWithPID(10));
    			addSequential(new TurnWithGyro(.4, 90, "clockwise")); //Turns and drives into position 3
    		}
    	}else if(Robot.gameData.charAt(0) == 'L') {
        	if(Robot.position == 3) {
        		addSequential(new DriveWithPID(10));
        		addSequential(new TurnWithGyro(.1, 90, "clockwise"));
        		addSequential(new DriveWithPID(10));
        		addSequential(new TurnWithGyro(.1, 90, "clockwise")); //Turns and drives into position 1
        	}else if(Robot.position == 2) {
        		addSequential(new DriveWithPID(10));
        		addSequential(new TurnWithGyro(.1, 90, "clockwise"));
        		addSequential(new DriveWithPID(10));
        		addSequential(new TurnWithGyro(.1, 90, "clockwise")); //Turns and drives into position 1
        	}
        }
    	
    	
		addSequential(new DriveWithPID(1)); //Drives forward to switch
	//	addSequential(new GearShift());
		//addSequential(new robotElevator.drop()); //Drops cube into switch
		
		if(Robot.gameData.charAt(0) == 'R') {
			addSequential(new TurnWithGyro(.4, 90, "clockwise"));
			addSequential(new DriveWithPID(10));
			addSequential(new TurnWithGyro(.4, 90, "clockwise"));
			addSequential(new DriveWithPID(10));
			addSequential(new TurnWithGyro(.4, 90, "clockwise"));
			addSequential(new DriveWithPID(10)); //Moved to cube near platform zone on right side
		}else if(Robot.gameData.charAt(0) == 'L') {
			addSequential(new TurnWithGyro(.4, 90, "clockwise"));
			addSequential(new DriveWithPID(10));
			addSequential(new TurnWithGyro(.4, 90, "clockwise"));
			addSequential(new DriveWithPID(10));
			addSequential(new TurnWithGyro(.4, 90, "clockwise"));
			addSequential(new DriveWithPID(10)); //Moved to cube near platform zone on left side
		}
		
		//addSequential(new robotElevator.pickUp();
		if((Robot.gameData.charAt(0) == 'L'&&Robot.gameData.charAt(1) == 'L')||(Robot.gameData.charAt(0) == 'L'&&Robot.gameData.charAt(1) == 'L')) {
			addSequential(new TurnWithGyro(.4, 90, "clockwise"));
			addSequential(new DriveWithPID(10)); //Move to scale
		}else if(Robot.gameData.charAt(0) == 'L'&&Robot.gameData.charAt(1) == 'R') {
			addSequential(new TurnWithGyro(.4, 90, "clockwise"));
			addSequential(new DriveWithPID(10));
			addSequential(new TurnWithGyro(.4, 90, "clockwise"));
			addSequential(new DriveWithPID(10));
			addSequential(new TurnWithGyro(.4, 90, "clockwise"));//Move to scale when scale/switch are opposite sides
		}else if(Robot.gameData.charAt(0) == 'R'&&Robot.gameData.charAt(1) == 'L') {
			addSequential(new TurnWithGyro(.4, 90, "clockwise"));
			addSequential(new DriveWithPID(10));
			addSequential(new TurnWithGyro(.4, 90, "clockwise"));
			addSequential(new DriveWithPID(10));
			addSequential(new TurnWithGyro(.4, 90, "clockwise"));//Move to scale when scale/switch are opposite sides
		
		//addSequential(new robotElevator.drop());
		while(infinite) {
			addSequential(new TurnWithGyro(.4, 90, "clockwise"));
			addSequential(new DriveWithPID(10)); //Turn around and drive to cube
			//addSequential(new robotElevator.pickUp();
			addSequential(new TurnWithGyro(.4, 90, "clockwise"));
			addSequential(new DriveWithPID(10));
			//addSequential(new robotElevator.drop());
		}
    }
}
}
