package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSystem extends CommandGroup {

    public AutoSystem() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    /*switch(position) {
    case 1:
    	switch(elevatorWorking) {
    	case yes:
    		switch(objectivePositions) {
    		case RRR:
    			addSequential(new DriveWithPID(1));
    			break;
    		case LRL:
    			addSequential(new DriveWithPID(1));
    			break;
    		case RLR:
    			addSequential(new DriveWithPID(1));
    			break;
    		case LLL:
    			switch(othersScale) {
    			case yes:
        			addSequential(new DriveWithPID(1));
        			break;
    			case no:
        			addSequential(new DriveWithPID(1));
        			break;
    			}
    			break;
    		}
    			break;
    	case no:
    		switch(objectivePositions) {
    		case RRR:
    			addSequential(new DriveWithPID(1));
    			break;
    		case LRL:
    			addSequential(new DriveWithPID(1));
    			break;
    		case RLR:
    			addSequential(new DriveWithPID(1));
    			break;
    		case LLL:
    			addSequential(new DriveWithPID(1));
    			break;
    		}
    			break;
    }
    			break;
    case 2:
    	switch(elevatorWorking) {
    	case yes:
    		switch(objectivePositions) {
    		case RRR:
    			addSequential(new DriveWithPID(1));
    			break;
    		case LRL:
    			addSequential(new DriveWithPID(1));
    			break;
    		case RLR:
    			addSequential(new DriveWithPID(1));
    			break;
    		case LLL:
    			switch(othersScale) {
    			case yes:
        			addSequential(new DriveWithPID(1));
        			break;
    			case no:
        			addSequential(new DriveWithPID(1));
        			break;
    			}
    			break;
    		}
    			break;
    	case no:
    		switch(objectivePositions) {
    		case RRR:
    			addSequential(new DriveWithPID(1));
    			break;
    		case LRL:
    			addSequential(new DriveWithPID(1));
    			break;
    		case RLR:
    			addSequential(new DriveWithPID(1));
    			break;
    		case LLL:
    			addSequential(new DriveWithPID(1));
    			break;
    		}
    			break;
    }
    			break;
    case 3:
    	switch(elevatorWorking) {
    	case yes:
    		switch(objectivePositions) {
    		case RRR:
    			addSequential(new DriveWithPID(1));
    			break;
    		case LRL:
    			addSequential(new DriveWithPID(1));
    			break;
    		case RLR:
    			addSequential(new DriveWithPID(1));
    			break;
    		case LLL:
    			switch(othersScale) {
    			case yes:
        			addSequential(new DriveWithPID(1));
        			break;
    			case no:
        			addSequential(new DriveWithPID(1));
        			break;
    			}
    			break;
    		}
    			break;
    	case no:
    		switch(objectivePositions) {
    		case RRR:
    			addSequential(new DriveWithPID(1));
    			break;
    		case LRL:
    			addSequential(new DriveWithPID(1));
    			break;
    		case RLR:
    			addSequential(new DriveWithPID(1));
    			break;
    		case LLL:
    			addSequential(new DriveWithPID(1));
    			break;
    		}
    			break;
    }
    			break;
    */
    }}
