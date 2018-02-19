package org.usfirst.frc1073.robot18.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc1073.robot18.RobotMap;
import org.usfirst.frc1073.robot18.commands.ControllerDifferentialDrive;
import org.usfirst.frc1073.robot18.commands.LiftElevator;
import org.usfirst.frc1073.robot18.commands.runElevatorWithShifting;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class robotElevator extends Subsystem {
	
	// Initialization of the elevator objects
	private final DigitalInput switchTop = RobotMap.liftSwitchTop;
    private final Encoder liftEncoder = RobotMap.liftEncoder;
    private final WPI_TalonSRX leftMotor = RobotMap.elevatorMotorLeft;
    private final WPI_TalonSRX rightMotor = RobotMap.elevatorMotorRight;
    private final DigitalInput switchBottom = RobotMap.liftSwitchBottom;
    
    public DifferentialDrive elevatorDrive;
	public robotElevator() {
<<<<<<< HEAD
		rightMotor.follow(leftMotor);
		
=======
		rightMotor.setSafetyEnabled(false);
		leftMotor.setSafetyEnabled(false);
		
		elevatorDrive = new DifferentialDrive(RobotMap.elevatorMotorLeft, RobotMap.elevatorMotorRight);
		//rightMotor.follow(leftMotor);
>>>>>>> 5bc5cd8d758910d66955e9ce64c950cbbc7a4315
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new runElevatorWithShifting());
		
	}
	
	public void periodic() {
        // Put code here to be run every loop

    }
	

}
