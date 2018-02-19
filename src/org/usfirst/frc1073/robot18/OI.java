
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
    //public double collectorSpeed = Robot.robotPreferences.getDouble("Collector Speed", 1);
	public double isDone = 0;
    public double isDoneLift = 0;
	
	public boolean cancelPushed;
	
	public XboxController driverControl;
	public XboxController operatorControl;
	
	public JoystickButton RobotTeleInit;
	public JoystickButton visionButton;
	public JoystickButton lidarButton;
	public JoystickButton cancel;
	public JoystickButton conveyorRight;
	public JoystickButton conveyorLeft;
	public JoystickButton intake;
	public JoystickButton purge;
	public JoystickButton suckInButton;
	public JoystickButton suckOutButton;
    public JoystickButton highGearDT;
    public JoystickButton lowGearDT;
    public JoystickButton clawOpen;
    public JoystickButton collectorUD;
    public double highGearLift;
    public double lowGearLift;
    public double collectorIntake;
    public double collectorPurge;
    public double conveyorLeftLeft;
    public double conveyorRightRight;
	
    public OI() {
    	
    	driverControl = new XboxController(0);
    	
    	RobotTeleInit = driverControl.start;
    	
    	visionButton = driverControl.b;
    	visionButton.whileHeld(new CubeGetterSupremeExtreme9000());
    	
    	lidarButton = driverControl.y;
    	lidarButton.whenPressed(new LidarAlign());
    	
    	cancel = driverControl.a;
    	
        //shifts drive train to high gear
        highGearDT = driverControl.leftBumper;
        highGearDT.whenPressed(new highGearDT());

        //shifts drive train to low gear
        lowGearDT = driverControl.rightBumper;
        lowGearDT.whenPressed(new lowGearDT());

    	conveyorRight = driverControl.rightBumper;
    	conveyorRight.whenPressed(new Dropoff(-(dropoffSpeed)));
    	conveyorRight.whenReleased(new Dropoff(0));
    	conveyorLeft = driverControl.leftBumper;
    	conveyorLeft.whenPressed(new Dropoff(dropoffSpeed));
    	conveyorLeft.whenReleased(new Dropoff(0));
    	
        //right conveyor
    	conveyorRightRight = driverControl.getRightTrigger();
    	if (conveyorRightRight >0) {
    		Robot.conveyor.teleDropoff(conveyorRightRight);
    	}
    	//left conveyor
    	conveyorLeftLeft = driverControl.getLeftTrigger();
    	if (conveyorLeftLeft >0) {
    		Robot.conveyor.teleDropoff(conveyorLeftLeft);
    	}
    	operatorControl = new XboxController(1);
    	
        //opens and closes the claw
        clawOpen = operatorControl.leftBumper;
        clawOpen.whileHeld(new openClaw());
        clawOpen.whenReleased(new closeClaw());

        //Makes the collector go up or down
        collectorUD = operatorControl.rightBumper;
        if (collectorUD.equals(1)) {
            isDone =+ 1;
        }
        if (isDone%2 == 1 && collectorUD.equals(1)) {
            collectorUD.whenPressed(new collectorDown());
        }
        if (isDone%2 == 0 && collectorUD.equals(1)) {
            collectorUD.whenPressed(new collectorUp());
        }
        //low gear lift
        
        //high gear lift

        
        
        //intake = operatorControl.rightBumper;
    	//intake.whenPressed(new SpinCollectorTelev2());
    	
    	//purge = operatorControl.leftBumper;
    	//purge.whenPressed(new SpinCollectorTelev3());
    	


        // SmartDashboard Buttons
        SmartDashboard.putData("Drive", new ControllerDifferentialDrive());
        SmartDashboard.putData("Lidar Align", new LidarAlignAuto());
        SmartDashboard.putData("DriveWithPID", new DriveWithPID(10));
        SmartDashboard.putData("lidaralign360", new LidarAlign360());
        SmartDashboard.putData("LidarMoveAway", new LidarMoveAway());
        SmartDashboard.putData("LidarMoveAway360", new LidarMoveAway360());
        SmartDashboard.putData("LidarWallTurnRight", new LidarAlignWallRCG());
        SmartDashboard.putData("LidarWallTurnLeft", new LidarAlignWallLCG());
        SmartDashboard.putData("openClaw", new openClaw());
        SmartDashboard.putData("closeClaw", new closeClaw());
        SmartDashboard.putData("HighDT", new highGearDT());
        SmartDashboard.putData("LowDT", new lowGearDT());
        SmartDashboard.putData("HighLift", new highGearLift());
        SmartDashboard.putData("LowLift", new lowGearLift());
        SmartDashboard.putData("ClawUp", new collectorUp());

    }
}

