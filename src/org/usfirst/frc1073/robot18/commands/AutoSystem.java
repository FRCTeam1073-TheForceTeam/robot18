package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoSystem extends CommandGroup {

    public AutoSystem() {
    	
    /**Selects an autonomous using choices from the choosers on the Smart Dashboard
     * @author Jack
     * @category Autonomous
     * @param Position "1" to "3", left to right, in front of the driver stations
     * @param Elevator Working "yes" or "no", if we can use the elevator to pick up cubes or not
     * @param Objective Positions "LLL", "RRR", "LRL", "RLR", set by the FMS
     * @param Others Scale "yes" or "no", whether other robots on the alliance can put their cubes on the scale
     */
    /*if(DriverStation.Alliance.Blue != null) {
    	String alliance = "blue";
    }else {
    	String alliance = "red";
    }*/
    
    switch(Robot.position) {
    case 1:
    	switch(Robot.elevatorWorking) {
    	case "yes":
    		switch(Robot.gameData) {
    		case "LLL":
    			addSequential(new AutoSwitchScale());
    			break;
    		case "LRL":
    			addSequential(new AutoSwitchScale());
    			break;
    		case "RLR":
    			addSequential(new AutoSwitchScale());
    			break;
    		case "RRR":
    			switch(Robot.othersScale) {
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
    case 2:
    	switch(Robot.elevatorWorking) {
    	case "yes":
    		switch(Robot.gameData) {
    		case "RRR":
    			addSequential(new AutoSwitchScale());
    			break;
    		case "LRL":
    			addSequential(new AutoSwitchScale());
    			break;
    		case "RLR":
    			addSequential(new AutoSwitchScale());
    			break;
    		case "LLL":
    			switch(Robot.othersScale) {
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
    case 3:
    	switch(Robot.elevatorWorking) {
    	case "yes":
    		switch(Robot.gameData) {
    		case "LLL":
    			addSequential(new AutoSwitchScale());
    			break;
    		case "LRL":
    			addSequential(new AutoSwitchScale());
    			break;
    		case "RLR":
    			addSequential(new AutoSwitchScale());
    			break;
    		case "RRR":
    			switch(Robot.othersScale) {
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
    
    }}}
