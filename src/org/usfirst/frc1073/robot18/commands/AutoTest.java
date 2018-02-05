package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.subsystems.robotDrivetrain;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoTest extends CommandGroup {

    public AutoTest() {
    	
    	//addSequential(new VariableCurvatureDrive (.5, .5, false));
    	addSequential(new AdvancedDrive(.25, .25, 10));	
    	//addSequential(new TurnWithGyro(0,0,"clockwise"));
    }
}
