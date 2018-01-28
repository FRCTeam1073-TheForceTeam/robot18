
package org.usfirst.frc1073.robot18;

import org.usfirst.frc1073.robot18.XboxController;
import org.usfirst.frc1073.robot18.commands.*;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public XboxController driverControl;
	public JoystickButton RobotTeleInit;
	public JoystickButton visionButton;
	public JoystickButton lidarButton;
	public JoystickButton cancel;
	public boolean cancelPushed;
	
    public OI() {
    	
    	driverControl = new XboxController(0);
    	
    	RobotTeleInit = driverControl.start;
    	
    	visionButton = driverControl.b;
    	visionButton.whenPressed(new VisionCubeTracker());
    	
    	lidarButton = driverControl.y;
    	lidarButton.whenPressed(new LidarAlign());
    	
    	cancel = driverControl.a;

        // SmartDashboard Buttons
        SmartDashboard.putData("Drive", new ControllerDifferentialDrive());
        SmartDashboard.putData("MoveWithPID", new AutoTest());
        SmartDashboard.putData("MoveWithEnc", new MoveWithEnc(10, 0.25));
        SmartDashboard.putData("Lidar Align", new LidarAlignAuto());
    }
}

