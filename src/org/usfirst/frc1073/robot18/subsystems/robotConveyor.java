package org.usfirst.frc1073.robot18.subsystems;

import org.usfirst.frc1073.robot18.RobotMap;
import org.usfirst.frc1073.robot18.commands.SpinCollectorTele;
import org.usfirst.frc1073.robot18.commands.SpinConveyorTele;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class robotConveyor extends Subsystem {

	private final WPI_TalonSRX conveyorMotor = RobotMap.conveyorMotor;
	public DifferentialDrive conveyorDrive;
	
	double encoderPosition;
	
	public robotConveyor() {
		conveyorMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		conveyorDrive = new DifferentialDrive(RobotMap.conveyorMotor,RobotMap.conveyorMotor );
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new SpinConveyorTele());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void autoDropoff(String direction) {
    	if(direction == "right") {
    		/*encoderPosition = conveyorMotor.getSelectedSensorPosition(0);
    		while(encoderPosition<(8640)) {
    			encoderPosition = conveyorMotor.getSelectedSensorPosition(0);
    			conveyorMotor.set(-.5);*/
    		conveyorMotor.set(-1);
    		try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		conveyorMotor.set(0);
    		}
    	else if(direction == "left") {
    		/*encoderPosition = conveyorMotor.getSelectedSensorPosition(0);
    		while(encoderPosition>-(8640)) {
    			encoderPosition = conveyorMotor.getSelectedSensorPosition(0);
    			conveyorMotor.set(.5);*/
    		conveyorMotor.set(1);
    		try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		conveyorMotor.set(0);
    		}
    }
    public void teleDropoff(double power){
    	conveyorMotor.set(power);
    }
}