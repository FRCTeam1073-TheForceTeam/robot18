package org.usfirst.frc1073.robot18.subsystems;

import org.usfirst.frc1073.robot18.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class robotCollector extends Subsystem {
	
    private final WPI_TalonSRX rightCollectorMotor = RobotMap.rightCollectorMotor;
    private final WPI_TalonSRX leftCollectorMotor = RobotMap.leftCollectorMotor;
    
    public void initDefaultCommand() {
    	
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

