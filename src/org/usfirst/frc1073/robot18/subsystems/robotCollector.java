
package org.usfirst.frc1073.robot18.subsystems;

import org.usfirst.frc1073.robot18.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

public class robotCollector extends Subsystem {
	
    private final WPI_VictorSPX rightCollectorMotor = RobotMap.rightCollectorMotor;
    private final WPI_VictorSPX leftCollectorMotor = RobotMap.leftCollectorMotor;
    private final Encoder collectorEncoder = RobotMap.collectorEncoder;
    private final DigitalInput collectorSwitchBottom = RobotMap.collectorSwitchBottom;
    
    public void initDefaultCommand() {
    	
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
