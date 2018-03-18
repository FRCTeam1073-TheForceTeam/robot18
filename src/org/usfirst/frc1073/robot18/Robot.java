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
import org.usfirst.frc1073.robot18.commands.AutonomousChooser.Auto1Chooser;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.LidarSeeRobot;
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
	public static AutoVars autoSetup;
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
	public static SendableChooser<AutoObject> autonomousPosition;
	public static SendableChooser<AutoObject> autonomousMatchType;
	public AutoObject left;
	public AutoObject center;
	public AutoObject right;
	public AutoObject other;
	public AutoObject quals;
	public AutoObject elims;
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
	public static boolean EncoderBool, EncoderBoolSet;
	public static boolean notClear;
	public double highGearLift;
	public double lowGearLift;
	public double collectorIntake;
	public double collectorPurge;
	public double conveyorLeftLeft;
	public double conveyorRightRight;
	public double x,y,leftInit,rightInit,headingInit;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		RobotMap.init();

		System.out.println("Robot Initialize");

		FMS = DriverStation.getInstance().getGameSpecificMessage();
		System.out.println("I'm a dank boi who's ready to go.m,n");
		RobotMap.headingGyro.reset();
		robotPreferences = Preferences.getInstance();
		elevator = new robotElevator();
		drivetrain = new robotDrivetrain();
		conveyor = new robotConveyor();
		pneumatic = new robotPneumatic();
		collector = new robotCollector();
		oi = new OI();
		autoSetup = new AutoVars();


		FMS = "";
		EncoderBoolSet = false;
		EncoderBool = false;
		notClear = false;

		//Instantiating Bling Class for smartbling on Robot.
		bling = new Bling();
		bling.sendRobotInit();

		//lift encoder set to 0
		RobotMap.elevatorMotorLeft.setSelectedSensorPosition(0, 0, 10);

		/* Position Objects */
		left = new AutoObject(1);
		center = new AutoObject(2);
		right = new AutoObject(3);
		other = new AutoObject(4);
		quals = new AutoObject(5);
		elims = new AutoObject(6);

		/* The Position Position */
		autonomousPosition = new SendableChooser<AutoObject>();
		autonomousPosition.addDefault("None", other);
		autonomousPosition.addObject("Left", left);
		autonomousPosition.addObject("Center", center);
		autonomousPosition.addObject("Right", right);
		SmartDashboard.putData("Position", autonomousPosition);

		/* The MatchType Position */
		autonomousMatchType = new SendableChooser<AutoObject>();
		autonomousMatchType.addDefault("None", other);
		autonomousMatchType.addObject("Qualifications", quals);
		autonomousMatchType.addObject("Eliminations", elims);
		SmartDashboard.putData("Match Type", autonomousMatchType);

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
		System.out.println("Auto Setting Up");
		Robot.pneumatic.driveTrainHighGear();
		Robot.pneumatic.liftHighGear();

		FMS = DriverStation.getInstance().getGameSpecificMessage();

		Scheduler.getInstance().run();

		new LidarMiniMap();

		/* instantiate the command used for the autonomous period */
		System.out.println("Auto Starting");
		autonomousCommand = new Auto1Chooser();
		if (autonomousCommand != null) autonomousCommand.start();

		if(DriverStation.getInstance().getAlliance().equals(DriverStation.Alliance.Blue)) {
			SmartDashboard.putString("Alliance", "Blue");
			SmartDashboard.putBoolean("A", true);
			SmartDashboard.putBoolean("B", true);
			SmartDashboard.putBoolean("C", false);
		}
		if(DriverStation.getInstance().getAlliance().equals(DriverStation.Alliance.Red)) {
			SmartDashboard.putString("Alliance", "Red");
			SmartDashboard.putBoolean("A", false);
			SmartDashboard.putBoolean("B", false);
			SmartDashboard.putBoolean("C", true);
		}
		//true = blue, false = red
		//NOTE: THE FOLLOWING CODE GIVES A LIVE UPDATE OF SWITCH AND SCALE COLORS, PLEASE DO NOT ALTER!
		if (DriverStation.getInstance().getAlliance().equals(DriverStation.Alliance.Blue)) {
			if (FMS.equals("RRR")){
				s1 = true;
				s2 = false;
				s3 = true;
				s4 = false;
				s5 = true;
				s6 = false;
			}
			else if (FMS.equals("LLL")){
				s1 = false;
				s2 = true;
				s3 = false;
				s4 = true;
				s5 = false;
				s6 = true;
			}
			else if (FMS.equals("LRL")){
				s1 = false;
				s2 = true;
				s3 = true;
				s4 = false;
				s5 = false;
				s6 = true;
			}
			else if (FMS.equals("RLR")){
				s1 = true;
				s2 = false;
				s3 = false;
				s4 = true;
				s5 = true;
				s6 = false;



			}
		}
		//if (alliance == DriverStation.Alliance.Blue){
		//true = blue, false = red



		if (DriverStation.getInstance().getAlliance().equals(DriverStation.Alliance.Red)){
			if (FMS.equals("RRR")){
				s1 = true;
				s2 = false;
				s3 = true;
				s4 = false;
				s5 = true;
				s6 = false;
			}
			else if (FMS.equals("LLL")){
				s1 = false;
				s2 = true;
				s3 = false;
				s4 = true;
				s5 = false;
				s6 = true;
			}
			else if (FMS.equals("LRL")){
				s1 = false;
				s2 = true;
				s3 = true;
				s4 = false;
				s5 = false;
				s6 = true;
			}
			else if (FMS.equals("RLR")){
				s1 = true;
				s2 = false;
				s3 = false;
				s4 = true;
				s5 = true;
				s6 = false;

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
		new LidarSeeRobot();
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Gyro", RobotMap.headingGyro.getAngle());
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		new LidarMiniMap();
		if(DriverStation.getInstance().getAlliance().equals(DriverStation.Alliance.Blue)) {
			SmartDashboard.putString("AL", "Blue");
		}
		if(DriverStation.getInstance().getAlliance().equals(DriverStation.Alliance.Red)) {
			SmartDashboard.putString("AL", "Red");
		}
		if(DriverStation.getInstance().getAlliance().equals(DriverStation.Alliance.Blue)) {
			SmartDashboard.putString("Alliance", "Blue");
			SmartDashboard.putBoolean("A", true);
			SmartDashboard.putBoolean("B", true);
			SmartDashboard.putBoolean("C", false);
		}
		if(DriverStation.getInstance().getAlliance().equals(DriverStation.Alliance.Red)) {
			SmartDashboard.putString("Alliance", "Red");
			SmartDashboard.putBoolean("A", false);
			SmartDashboard.putBoolean("B", false);
			SmartDashboard.putBoolean("C", true);
		}
		if (autonomousCommand != null) autonomousCommand.cancel();
		FMS = DriverStation.getInstance().getGameSpecificMessage();
		alliance = DriverStation.getInstance().getAlliance();
		SmartDashboard.putString("FMS", FMS);

		/** NOTE: THE FOLLOWING CODE GIVES A LIVE UPDATE OF SWITCH AND SCALE COLORS, PLEASE DO NOT ALTER! */
		if (DriverStation.getInstance().getAlliance().equals(DriverStation.Alliance.Blue)) {
			if (FMS.equals("RRR")){
				s1 = true;
				s2 = false;
				s3 = true;
				s4 = false;
				s5 = true;
				s6 = false;
			}
			else if (FMS.equals("LLL")){
				s1 = false;
				s2 = true;
				s3 = false;
				s4 = true;
				s5 = false;
				s6 = true;
			}
			else if (FMS.equals("LRL")){
				s1 = false;
				s2 = true;
				s3 = true;
				s4 = false;
				s5 = false;
				s6 = true;
			}
			else if (FMS.equals("RLR")){
				s1 = true;
				s2 = false;
				s3 = false;
				s4 = true;
				s5 = true;
				s6 = false;
			}
		}
		if (DriverStation.getInstance().getAlliance().equals(DriverStation.Alliance.Red)){
			if (FMS.equals("RRR")){
				s1 = false;
				s2 = true;
				s3 = false;
				s4 = true;
				s5 = false;
				s6 = true;
			}
			else if (FMS.equals("LLL")){
				s1 = false;
				s2 = true;
				s3 = false;
				s4 = true;
				s5 = false;
				s6 = true;
			}
			else if (FMS.equals("LRL")){
				s1 = true;
				s2 = false;
				s3 = false;
				s4 = true;
				s5 = true;
				s6 = false;
			}
			else if (FMS.equals("RLR")){
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
		y = 0;
		x = 0;
		leftInit = RobotMap.leftMotor1.getSelectedSensorPosition(0);
		rightInit = RobotMap.rightMotor1.getSelectedSensorPosition(0);
		headingInit = RobotMap.headingGyro.getAngle();
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
		SmartDashboard.putNumber("Left Motors", Math.abs(RobotMap.leftMotor1.get()));
		SmartDashboard.putNumber("Right Motors", Math.abs(RobotMap.rightMotor1.get()));
		if(Robot.oi.driverControl.getRawAxis(4)>.05) {
			turnRight = true;
			turnLeft = false;
		}
		else if (Robot.oi.driverControl.getRawAxis(4)<-.05) {
			turnRight = false;
			turnLeft = true;
		}
		else {
			turnRight = false;
			turnLeft = false;
		}
		SmartDashboard.putNumber("Lift Speed", Math.abs(RobotMap.elevatorMotorRight.get()));
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

		int distLeft = RobotMap.leftMotor1.getSelectedSensorPosition(0);
		int distRight = RobotMap.rightMotor1.getSelectedSensorPosition(0);
		double heading = RobotMap.headingGyro.getAngle() - headingInit;

		double distAvg = (((distLeft - leftInit) * (2799 / 1993)) - ((distRight - rightInit) * (1993 / 2799))) / 2;
		double distReal = ((distAvg * 3.9) / 1440) * Math.PI;
		double headingY = Math.cos(Math.toRadians(heading));
		double headingX = Math.sin(Math.toRadians(heading));

		y = distReal * headingY;
		x = distReal * headingX;

		SmartDashboard.putNumber("X:", x);
		SmartDashboard.putNumber("Y:", y);
		SmartDashboard.putNumber("distReal:", distReal);
		SmartDashboard.putNumber("Heading", heading);
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
