package org.usfirst.frc1073.robot18.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc1073.robot18.RobotMap;
import org.usfirst.frc1073.robot18.commands.ControllerDifferentialDrive;
import org.usfirst.frc1073.robot18.commands.LiftElevator;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class robotElevator extends Subsystem {
	
	// Initialization of the elevator objects
	private final DigitalInput switchTop = RobotMap.liftSwitchTop;
    private final Encoder liftEncoder = RobotMap.liftEncoder;
    private final WPI_TalonSRX liftMotor = RobotMap.liftMotor;
    private final WPI_TalonSRX elevatorMotorRight = RobotMap.elevatorMotorRight;
    private final WPI_TalonSRX elevatorMotorLeft = RobotMap.elevatorMotorLeft;
    private final DigitalInput switchBottom = RobotMap.liftSwitchBottom;
    
	public robotElevator() {
		elevatorMotorRight.follow(elevatorMotorLeft);
	}

	@Override
	protected void initDefaultCommand() {
		// setDefaultCommand(new elevatorCommand);
		
		setDefaultCommand(new LiftElevator());
	}
	
	public void periodic() {
        // Put code here to be run every loop

    }
	

}
