
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
	
	public double dropoffSpeed = Robot.robotPreferences.getDouble("Dropoff Speed", 1);
	
	public XboxController driverControl;
	public XboxController operatorControl;
	
	public JoystickButton RobotTeleInit;
	public JoystickButton visionButton;
	public JoystickButton lidarButton;
	public JoystickButton cancel;
	public JoystickButton conveyorRight;
	public JoystickButton conveyorLeft;
	public JoystickButton suckInButton;
	public JoystickButton suckOutButton;
	public boolean cancelPushed;
	
    public OI() {
    	
    	driverControl = new XboxController(0);
    	
    	RobotTeleInit = driverControl.start;
    	
    	visionButton = driverControl.b;
    	visionButton.whenPressed(new VisionCubeTracker());
    	
    	lidarButton = driverControl.y;
    	lidarButton.whenPressed(new LidarAlign());
    	
    	cancel = driverControl.a;
    	
    	conveyorRight = driverControl.rightBumper;
    	conveyorRight.whenPressed(new Dropoff(-(dropoffSpeed)));
    	conveyorRight.whenReleased(new Dropoff(0));
    	conveyorLeft = driverControl.leftBumper;
    	conveyorLeft.whenPressed(new Dropoff(dropoffSpeed));
    	conveyorLeft.whenReleased(new Dropoff(0));
    	suckInButton = operatorControl.a;
    	suckInButton.whenPressed(new SuckInCube());
    	
    	operatorControl = new XboxController(1);
    	suckOutButton = operatorControl.b;
    	suckOutButton.whenPressed(new SpitOutCube());
    	
        // SmartDashboard Buttons
        SmartDashboard.putData("Drive", new ControllerDifferentialDrive());
        SmartDashboard.putData("Lidar Align", new LidarAlignAuto());
        SmartDashboard.putData("DriveWithPID", new DriveWithPID(10));
        SmartDashboard.putData("lidaralign360", new LidarAlign360());
        SmartDashboard.putData("LidarMoveAway", new LidarMoveAway());
        SmartDashboard.putData("LidarMoveAway360", new LidarMoveAway360());
        SmartDashboard.putData("LidarWallTurnRight", new LidarAlignWallRCG());
        SmartDashboard.putData("LidarWallTurnLeft", new LidarAlignWallLCG());
    }
}

