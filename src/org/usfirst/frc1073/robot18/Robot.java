package org.usfirst.frc1073.robot18;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.hal.PDPJNI;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1073.robot18.Bling;
import org.usfirst.frc1073.robot18.commands.*;
import org.usfirst.frc1073.robot18.subsystems.*;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	Preferences prefs;
	String OK_Puzzles = "none";
	
	Command autonomousCommand;
    public static Preferences robotPreferences;

	public static OI oi;
	public static robotElevator elevator;
	public static robotDrivetrain drivetrain;
	public static robotCollector collector;
	public static robotConveyor conveyor;
	public static CameraServer cameraSwitcher;
	public static boolean selectedCamera;
    public static robotPneumatic pneumatic;
    
    public static Bling bling;

	public static String FMS;
	public static SendableChooser<AutoObject> autonomousChooser;
	public AutoObject left;
	public AutoObject center;
	public AutoObject right;
	
	public DigitalInput liftSwitchBottom;
	
	public static double voltage;
	public static double distance;

	public static String gameData;
	public static int position;
	public static String elevatorWorking;
	public static String othersScale;
	public static String switchSide;
	public static String scaleSide;
	public static String robotName;
	public static boolean clawBool;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		prefs = Preferences.getInstance();
		//OK_Puzzles = prefs.getString(OK_Puzzles, "Puzzles");
		
		RobotMap.init();
		RobotMap.headingGyro.reset();
		robotPreferences = Preferences.getInstance();
    	OK_Puzzles = robotPreferences.getString("robotName", "unknown");
		elevator = new robotElevator();
		drivetrain = new robotDrivetrain();
		conveyor = new robotConveyor();
        pneumatic = new robotPneumatic();
        collector = new robotCollector();
		oi = new OI();

		
		FMS = "";

        //Instantiating Bling Class for smartbling on Robot.
        bling = new Bling();
        bling.sendRobotInit();
        
		//lift encoder set to 0
		RobotMap.elevatorMotorLeft.setSelectedSensorPosition(0, 0, 10);
		
		/* Chooser Objects */
		left = new AutoObject(1);
		center = new AutoObject(2);
		right = new AutoObject(3);

		/* Jack's Auto Variables*/
		position = (int) SmartDashboard.getNumber("Position", 1);
		elevatorWorking = String.valueOf(SmartDashboard.getBoolean("Elevator Working?", true));
		othersScale = String.valueOf(SmartDashboard.getBoolean("Other Bots Scale?", false));

		/* The Chooser */
		autonomousChooser = new SendableChooser<AutoObject>();
		autonomousChooser.addDefault("None", null);
		autonomousChooser.addObject("Left", left);
		autonomousChooser.addObject("Center", center);
		autonomousChooser.addObject("Right", right);
		SmartDashboard.putData("Autonomous Chooser", autonomousChooser);

		// The first thread, running the front Webcam to the driver station
		Thread camera1Thread = new Thread(() -> {

			// Sets up the camera, its resolution, and limits the framerate
			// to help with bandwidth
			UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(0);   
			camera1.setResolution(320, 240);
			camera1.setFPS(12);

			try {
				Thread.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
			}

			CvSink cvSink = CameraServer.getInstance().getVideo(camera1);
			CvSource outputStream = CameraServer.getInstance().putVideo("Video 1", 320, 240);
			Mat source = new Mat();
			boolean currentCamera = selectedCamera;
			while( !Thread.interrupted() ) {
				if ( currentCamera != selectedCamera ) {
					currentCamera = selectedCamera;
					if ( selectedCamera == false ) {
						cvSink.setSource(camera1);            		
						SmartDashboard.putString("Camera 1", "Camera 1");
					}
				}
				cvSink.grabFrame(source);

				if ( source.empty() == false ) {
					int rows = source.rows();
					int columns = source.cols();

					Point lineStart = new Point(columns/2, 0);
					Point lineEnd = new Point(columns/2, rows);
					Imgproc.line(source, lineStart, lineEnd, new Scalar(0,0,255), 1);

					lineStart = new Point(0,rows/2);
					lineEnd = new Point(columns, rows/2);
					Imgproc.line(source, lineStart, lineEnd, new Scalar(0,0,255), 1);

					outputStream.putFrame(source);
				}

				try{
					Thread.sleep(50);
				} catch(Exception e) {           		
				}

			}
		});

		Thread camera2Thread = new Thread(() -> {

			UsbCamera camera2 = CameraServer.getInstance().startAutomaticCapture(1);   
			camera2.setResolution(320, 240);
			camera2.setFPS(12);

			try {
				Thread.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
			}

			CvSink cvSink = CameraServer.getInstance().getVideo(camera2);
			CvSource outputStream = CameraServer.getInstance().putVideo("Video 2", 320, 240);
			Mat source = new Mat();
			boolean currentCamera = selectedCamera;
			while( !Thread.interrupted() ) {
				if ( currentCamera != selectedCamera ) {
					currentCamera = selectedCamera;
					if ( selectedCamera == false ) {
						cvSink.setSource(camera2);            		
						SmartDashboard.putString("Camera 2", "Camera 2");
					}
				}
				cvSink.grabFrame(source);

				if ( source.empty() == false ) {
					int rows = source.rows();
					int columns = source.cols();

					Point lineStart = new Point(columns/2, 0);
					Point lineEnd = new Point(columns/2, rows);
					Imgproc.line(source, lineStart, lineEnd, new Scalar(0,0,255), 1);

					lineStart = new Point(0,rows/2);
					lineEnd = new Point(columns, rows/2);
					Imgproc.line(source, lineStart, lineEnd, new Scalar(0,0,255), 1);

					outputStream.putFrame(source);
				}

				try{
					Thread.sleep(50);
				} catch(Exception e) {           		
				}

			}
		});
		camera1Thread.start();
		camera2Thread.start();
	}

	/**
	 * This function is called when the disabled button is hit.
	 * You can use it to reset subsystems before shutting down.
	 */
	public void disabledInit(){
		
		

	}

	public void disabledPeriodic() {
		double total = 0;
		for (int i = 0; i < 10; i++) {
			voltage = RobotMap.leftSensor.getVoltage();
			distance = (Robot.voltage - 0.0399)/0.0234;  
			total += distance;
		}
		total = total/10;
		SmartDashboard.putNumber("Ultrasonic Distance", total );
	}

	public void autonomousInit() {
		RobotMap.leftMotor1.configOpenloopRamp(0, 10);
		RobotMap.rightMotor1.configOpenloopRamp(0, 10);
		
		FMS = DriverStation.getInstance().getGameSpecificMessage();
		
		Scheduler.getInstance().run();
		
		new LidarMiniMap();
		
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.charAt(0) == 'L') {
			switchSide = "left";
		}else {
			switchSide = "right";
		}
		if(gameData.charAt(1) == 'L') {
			scaleSide = "left";
		}else {
			scaleSide = "right";
		}
		
		/* instantiate the command used for the autonomous period */
		autonomousCommand = new Auto1Chooser();
		if (autonomousCommand != null) autonomousCommand.start();
	}
	
	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		new LidarMiniMap();
		if (autonomousCommand != null) autonomousCommand.cancel();
		RobotMap.leftMotor1.configOpenloopRamp(.25, 10);
		RobotMap.rightMotor1.configOpenloopRamp(.25, 10);
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
