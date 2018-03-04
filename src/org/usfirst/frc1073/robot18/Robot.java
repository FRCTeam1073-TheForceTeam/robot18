package org.usfirst.frc1073.robot18;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
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
	public double isDone = 0;
    public double isDoneLift = 0;
	public boolean turnRight;
	public boolean turnLeft;
	public boolean cancelPushed;
	public boolean haveCube;
	public boolean clawIsOpen;
	public boolean collectorStatus;
	public boolean s1;
	public boolean s2;
	public boolean s3;
	public boolean s4;
	public boolean s5;
	public boolean s6;
    public static Alliance alliance;

	public static String FMS;
	public static SendableChooser<AutoObject> autonomousChooser;
	public AutoObject left;
	public AutoObject center;
	public AutoObject right;
	public AutoObject other;

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
	public double highGearLift;
	public double lowGearLift;
	public double collectorIntake;
	public double collectorPurge;
	public double conveyorLeftLeft;
	public double conveyorRightRight;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		RobotMap.init();
		RobotMap.headingGyro.reset();
		robotPreferences = Preferences.getInstance();
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
		other = new AutoObject(4);

		/* Jack's Auto Variables*/
		position = (int) SmartDashboard.getNumber("Position", 1);
		elevatorWorking = String.valueOf(SmartDashboard.getBoolean("Elevator Working?", true));
		othersScale = String.valueOf(SmartDashboard.getBoolean("Other Bots Scale?", false));

		/* The Chooser */
		autonomousChooser = new SendableChooser<AutoObject>();
		autonomousChooser.addDefault("None", other);
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


		RobotMap.leftMotor1.configOpenloopRamp(0, 10);
		RobotMap.rightMotor1.configOpenloopRamp(0, 10);

	}

	/**
	 * This function is called when the disabled button is hit.
	 * You can use it to reset subsystems before shutting down.
	 */
	public void disabledInit(){



	}

	public void disabledPeriodic() {
	}

	public void autonomousInit() {

		Robot.pneumatic.driveTrainHighGear();
		Robot.pneumatic.liftHighGear();

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
		  if (alliance == DriverStation.Alliance.Blue){
	        	//true = blue, false = red
	        	if (FMS == "RRR"){
	        		s1 = true;
	        		s2 = false;
	        		s3 = true;
	        		s4 = false;
	        		s5 = true;
	        		s6 = false;
	        	}
	        	else if (FMS == "LLL"){
	        		s1 = false;
	        		s2 = true;
	        		s3 = false;
	        		s4 = true;
	        		s5 = false;
	        		s6 = true;
	        	}
	        	else if (FMS == "LRL"){
	        		s1 = false;
	        		s2 = true;
	        		s3 = true;
	        		s4 = false;
	        		s5 = false;
	        		s6 = true;
	        	}
	        	else if (FMS == "RLR"){
	        		s1 = true;
	        		s2 = false;
	        		s3 = false;
	        		s4 = true;
	        		s5 = true;
	        		s6 = false;
	        		
	        	}
	        	
	        }
	        else if (alliance == DriverStation.Alliance.Red){
	        	if (FMS == "RRR"){
	        		s1 = false;
	        		s2 = true;
	        		s3 = false;
	        		s4 = true;
	        		s5 = false;
	        		s6 = true;
	        	}
	        	else if (FMS == "LLL"){
	        		s1 = true;
	        		s2 = false;
	        		s3 = true;
	        		s4 = false;
	        		s5 = true;
	        		s6 = false;
	        	}
	        	else if (FMS == "LRL"){
	        		s1 = true;
	        		s2 = false;
	        		s3 = false;
	        		s4 = true;
	        		s5 = true;
	        		s6 = false;
	        	}
	        	else if (FMS == "RLR"){
	        		s1 = false;
	        		s2 = true;
	        		s3 = true;
	        		s4 = false;
	        		s5 = false;
	        		s6 = true;
	        		
	        	}
	        	
	        }
		SmartDashboard.putBoolean("s1", s1);
		SmartDashboard.putBoolean("s2", s2);
		SmartDashboard.putBoolean("s3", s3);
		SmartDashboard.putBoolean("s4", s4);
		SmartDashboard.putBoolean("s5", s5);
		SmartDashboard.putBoolean("s6", s6);
		}
	

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Gyro", RobotMap.headingGyro.getAngle());
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		new LidarMiniMap();
		if (autonomousCommand != null) autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Gyro", RobotMap.headingGyro.getAngle());
		SmartDashboard.putNumber("Left", RobotMap.leftMotor1.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Right", RobotMap.rightMotor1.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Gyro", RobotMap.headingGyro.getAngle());
    	FMS = DriverStation.getInstance().getGameSpecificMessage();
		alliance = DriverStation.getInstance().getAlliance();
		Scheduler.getInstance().run();
		if(RobotMap.leftMotor1.get() > RobotMap.rightMotor1.get()) {
        	turnRight = false;
        	turnLeft = true;
        }
        else if (RobotMap.rightMotor1.get() > RobotMap.leftMotor1.get()) {
        	turnRight = true;
        	turnLeft = false;
        }
        else {
        	turnRight = false;
        	turnLeft = false;
        }
        SmartDashboard.putNumber("Lift Speed", RobotMap.elevatorMotorRight.get());
        SmartDashboard.putBoolean("turn Left", turnLeft);
        SmartDashboard.putBoolean("", turnRight);
        if(RobotMap.clawSensor.getAverageVoltage() > 1) {
        	haveCube = true;
        }
        else {
        	haveCube = false;
        }
        SmartDashboard.putBoolean("Do you have a cube?", haveCube);
        if(RobotMap.leftWrist.get() == true && RobotMap.rightWrist.get( )== false) {
        	clawIsOpen = true;
        }
        else {
        	clawIsOpen = false;
        }
        SmartDashboard.putBoolean("Claw Open?", clawIsOpen);
        
        if(RobotMap.collectorLeft.get() == false && RobotMap.collectorRight.get() == true) {
        	collectorStatus = true;
        	SmartDashboard.putString("Collector", "Up");
        }
        else {
        	collectorStatus = false;
        	SmartDashboard.putString("Collector", "Down");
        }
        SmartDashboard.putBoolean("Collector Up?", collectorStatus);
        if (RobotMap.gearLow.get() == false && RobotMap.gearHigh.get() == true){
        	SmartDashboard.putString("DT Gear", "High");
	}
        else if (RobotMap.gearLow.get() == true && RobotMap.gearHigh.get() == false){
        	SmartDashboard.putString("DT Gear", "Low");
	}
        else {
        	SmartDashboard.putString("DT Gear", "ERROR: PLEASE SHIFT NOW!");
        }
        
        if (RobotMap.liftLow.get() == false && RobotMap.liftHigh.get() == true){
        	SmartDashboard.putString("Lift Gear", "High");
	}
        else if (RobotMap.liftLow.get() == true && RobotMap.liftHigh.get() == false){
        	SmartDashboard.putString("Lift Gear", "Low");
	}
        else {
        	SmartDashboard.putString("Lift Gear", "ERROR: PLEASE SHIFT NOW!");
        }
        SmartDashboard.putNumber("Match Time1", Timer.getMatchTime());
        SmartDashboard.putNumber("Match Time1", Timer.getMatchTime());

	SmartDashboard.putNumber("Elevator Enc enc", RobotMap.elevatorMotorLeft.getSelectedSensorPosition(0));
	SmartDashboard.putNumber("Right Enc", RobotMap.rightMotor1.getSelectedSensorPosition(0));
	SmartDashboard.putNumber("Left Enc", RobotMap.leftMotor1.getSelectedSensorPosition(0));
	SmartDashboard.putBoolean("Bottom Limit", RobotMap.liftSwitchBottom.get());
	SmartDashboard.putBoolean("Top Limit", RobotMap.liftSwitchTop.get());
	SmartDashboard.putNumber("IR Voltage", RobotMap.clawSensor.getVoltage());
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
