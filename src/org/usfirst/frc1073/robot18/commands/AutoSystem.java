package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSystem extends CommandGroup {

    public AutoSystem() {
    	
    /**Selects an autonomous using choices from the AutoTable on the Smart Dashboard
     * @author Jack
     * @category Autonomous
     * @param Position "1" to "3", left to right, in front of the driver stations
     * @param Elevator Working "yes" or "no", if we can use the elevator to pick up cubes or not
     * @param Objective Positions "LLL", "RRR", "LRL", "RLR", set by the FMS
     * @param Others Scale "yes" or "no", whether other robots on the alliance can put their cubes on the scale
     */
    String position = null;
    String elevatorWorking = null;
    String othersScale = null;
    if(DriverStation.Alliance.Blue != null) {
    	String alliance = "blue";
    }else {
    	String alliance = "red";
    }
    /*
    switch(position) {
    case "1":
    	switch(elevatorWorking) {
    	case "yes":
    		switch(Robot.gameData) {
    		case "RRR":
    			addSequential(new DriveWithPID(1));
    			addSequential(new TurnWithGyro(.1, 90, "clockwise"));
    			addSequential(new DriveWithPID(1));
    			addSequential(new TurnWithGyro(.1, 90, "clockwise")); //Turns and drives into position 3
    			addSequential(new DriveWithPID(1)); //Drives forward to switch
    			addSequential(new GearShift());
    			//addSequential(new robotElevator.drop()); //Drops cube into switch
    			addSequential(new TurnWithGyro(.1, 90, "clockwise"));
    			addSequential(new DriveWithPID(1));
    			addSequential(new TurnWithGyro(.1, 90, "clockwise"));
    			addSequential(new DriveWithPID(1));
    			addSequential(new TurnWithGyro(.1, 90, "clockwise"));
    			addSequential(new DriveWithPID(1)); //Moved to cube near platform zone
    			//addSequential(new robotElevator.pickUp();
    			addSequential(new TurnWithGyro(.1, 90, "clockwise"));
    			addSequential(new DriveWithPID(1)); //Move to scale
    			for(int i = 0; i < 6 && !; i++ ) {
    				//addSequential(new robotElevator.drop());
    			addSequential(new TurnWithGyro(.1, 90, "clockwise"));
    			addSequential(new DriveWithPID(1)); //Turn around and drive to cube
    			//addSequential(new robotElevator.pickUp();
    			addSequential(new TurnWithGyro(.1, 90, "clockwise"));
    			addSequential(new DriveWithPID(1));
    			//addSequential(new robotElevator.drop());
    			}
    			break;
    		case "LRL":
    			addSequential(new DriveWithPID(1));
    			break;
    		case "RLR":
    			addSequential(new DriveWithPID(1));
    			break;
    		case "LLL":
    			switch(othersScale) {
    			case "yes":
        			addSequential(new DriveWithPID(1));
        			break;
    			case "no":
        			addSequential(new DriveWithPID(1));
        			break;
    			}
    			break;
    		}
    			break;
    	case "no":
    		switch(Robot.gameData) {
    		case "RRR":
    			addSequential(new DriveWithPID(1));
    			break;
    		case "LRL":
    			addSequential(new DriveWithPID(1));
    			break;
    		case "RLR":
    			addSequential(new DriveWithPID(1));
    			break;
    		case "LLL":
    			addSequential(new DriveWithPID(1));
    			break;
    		}
    			break;
    }
    			break;
    case "2":
    	switch(elevatorWorking) {
    	case "yes":
    		switch(Robot.gameData) {
    		case "RRR":
    			addSequential(new DriveWithPID(1));
    			break;
    		case "LRL":
    			addSequential(new DriveWithPID(1));
    			break;
    		case "RLR":
    			addSequential(new DriveWithPID(1));
    			break;
    		case "LLL":
    			switch(othersScale) {
    			case "yes":
        			addSequential(new DriveWithPID(1));
        			break;
    			case "no":
        			addSequential(new DriveWithPID(1));
        			break;
    			}
    			break;
    		}
    			break;
    	case "no":
    		switch(Robot.gameData) {
    		case "RRR":
    			addSequential(new DriveWithPID(1));
    			break;
    		case "LRL":
    			addSequential(new DriveWithPID(1));
    			break;
    		case "RLR":
    			addSequential(new DriveWithPID(1));
    			break;
    		case "LLL":
    			addSequential(new DriveWithPID(1));
    			break;
    		}
    			break;
    }
    			break;
    case "3":
    	switch(elevatorWorking) {
    	case "yes":
    		switch(Robot.gameData) {
    		case "RRR":
    			addSequential(new DriveWithPID(1));
    			break;
    		case "LRL":
    			addSequential(new DriveWithPID(1));
    			break;
    		case "RLR":
    			addSequential(new DriveWithPID(1));
    			break;
    		case "LLL":
    			switch(othersScale) {
    			case "yes":
        			addSequential(new DriveWithPID(1));
        			break;
    			case "no":
        			addSequential(new DriveWithPID(1));
        			break;
    			}
    			break;
    		}
    			break;
    	case "no":
    		switch(Robot.gameData) {
    		case "RRR":
    			addSequential(new DriveWithPID(1));
    			break;
    		case "LRL":
    			addSequential(new DriveWithPID(1));
    			break;
    		case "RLR":
    			addSequential(new DriveWithPID(1));
    			break;
    		case "LLL":
    			addSequential(new DriveWithPID(1));
    			break;
    		}
    			break;
    }
    			break;
    
    }}

	private void until(boolean b) {
		// TODO Auto-generated method stub
	*/	
	}}
