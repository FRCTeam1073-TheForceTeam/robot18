
package org.usfirst.frc1073.robot18.commands.AutonomousTools;
import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnWithGyroPID extends PIDCommand {
	
	private double turnSpeed;
	private double turnDegrees;
	private String turnDirection;
	private double originalDegrees;
	private double slowdownDistance = 8;
	private double slowdownValue = .8;
	private double slowdownMin = .6;
	private double leftEnc,rightEnc;
	
	/** Uses basic drive to turn based on the gyro's position Using  PIDCOmmand
	 * 
	 * @author Mr. Robey (adapted from Jack's TurnWithGyro)
	 * @category Drive Command
	 * @param Speed from 0 to 1
	 * @param Degrees should be positive
	 * @param Direction should be either "clockwise" or "counterclockwise"
	 * 
	 * for PIDCommand example see http://robottutorial.alexpavel.com
	 */
    public TurnWithGyroPID(double Speed, double Degrees, String Direction, double P, double I, double D) {
        super(P,I,D);
    	turnSpeed = Speed;
    	turnDegrees = Degrees;
    	turnDirection = Direction;
        requires(Robot.drivetrain);

    }
    
    // Called just before this Command runs the first time
    protected void initialize() {

    	
    	originalDegrees = RobotMap.headingGyro.getAngle();
    	setInputRange(-37258,40274);
    	if (turnDirection.equals("clockwise")){
    		setSetpoint(originalDegrees + turnDegrees);
    	}
    	else if (turnDirection.equals("counterclockwise")){
    		setSetpoint(originalDegrees - turnDegrees);
    	}    
    	else
    	{
    		//ERROR
    	}
    	getPIDController().setAbsoluteTolerance(1);  // the number of degrees off we can be and still be done
    	getPIDController().setOutputRange(.6, .6);  //minimum and maximum motor speeds
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//nothing to do here, the real execution stuff is done in usePIDoutput below
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.oi.driverCancel.get() == true || Robot.oi.operatorCancel.get() == true){
    		return true;
    	}
 
    	return getPIDController().onTarget(); 
    }
  
    
    // Called once after isFinished returns true
    protected void end() {
    	getPIDController().disable();
		Robot.drivetrain.difDrive.tankDrive(0, 0);

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

	@Override
	protected double returnPIDInput() {
		// give the PID controller the current gyro value
		double angle = RobotMap.headingGyro.getAngle();
		System.out.println("JRJR TurnWithGyroPID angle " + angle);
		return angle;
	}

	@Override
	protected void usePIDOutput(double output) {
		System.out.println("JRJR TurnWithGyroPID output" + output);
		SmartDashboard.putNumber("JRJR PID output ", output);

		Robot.drivetrain.difDrive.tankDrive(output*-1, output);
		
	}
}
