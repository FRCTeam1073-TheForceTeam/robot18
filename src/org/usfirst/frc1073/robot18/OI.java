
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
	public JoystickButton RobotPRGMInit;
	public JoystickButton visionButton;
	public JoystickButton PIDButton;
	public JoystickButton leftBumper;
	public JoystickButton rightBumper;
	public JoystickButton cancel;
	
	public boolean cancelPushed;
	
    public OI() {
    	
    	driverControl = new XboxController(0);
    	
    	RobotPRGMInit = driverControl.start;
    	
    	leftBumper = driverControl.leftBumper;
    	rightBumper = driverControl.rightBumper;
    	
    	visionButton = driverControl.b;
    	visionButton.whenPressed(new VisionCubeTracker());
    	
    	PIDButton = driverControl.x;
    	PIDButton.whenPressed(new DriveWithPID(5));
    	
    	cancel = driverControl.a;

        // SmartDashboard Buttons
        SmartDashboard.putData("Drive", new ControllerDifferentialDrive());
        SmartDashboard.putData("Lidar Align", new LidarAlignAuto());
        SmartDashboard.putData("DriveWithPID", new DriveWithPID(10));
        SmartDashboard.putData("LidarMiniMap", new LidarMiniMap());
        SmartDashboard.putData("AutoTest", new AutoTest());
        SmartDashboard.putData("Reset Sticky Faults", new ClearStickyFault());
    }
}

