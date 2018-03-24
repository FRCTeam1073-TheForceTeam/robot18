package org.usfirst.frc1073.robot18;

public class AutoVars {
	
	public static double LiftDistUp, DropoffTime, LiftDistDown, 
	
	ADSpeed, VisionTurnSpeed, TurnWithGyroSpeed,
	
	BothADSpeed, BothAD1Distance, BothAD1Timeout, 
	BothAD2Distance, BothAD2Timeout, BothVisionTurnSpeed, BothVisionTurnDistance,
	
	SwitchADSpeed, SwitchAD1Distance, SwitchAD1Timeout,
	SwitchAD2Distance, SwitchAD2Timeout, SwitchVisionTurnSpeed, SwitchVisionTurnDistance,
	
	ScaleADSpeed, ScaleAD1Distance, ScaleAD1Timeout,
	ScaleAD2Distance, ScaleAD2Timeout, ScaleVisionTurnSpeed, ScaleVisionTurnDistance,
	
	NeitherADSpeed, NeitherAD1Distance, NeitherAD1Timeout,
	NeitherAD2Distance, NeitherAD2Timeout, NeitherVisionTurnSpeed, NeitherVisionTurnDistance;
	
	public static String LeftDropoff, RightDropoff, LeftVisionTurn, RightVisionTurn;
	
	public AutoVars() {
		/* Speeds */
		ADSpeed = -.8;
		VisionTurnSpeed = .8;
		TurnWithGyroSpeed = .5;
		
		/* Requirements to drop off cube in switch or near coin slot */
		LiftDistUp = 30;
		LiftDistDown = 0;
		DropoffTime = 5;
		
		/* Left Strings */
		LeftDropoff = "left";
		LeftVisionTurn = "counterclockwise";
		
		/* Right Strings */
		RightDropoff = "right";
		RightVisionTurn = "clockwise";
		
		/* Moves and turns for "Switch in favor" */
		SwitchADSpeed = ADSpeed;
		SwitchAD1Distance = 125;
		SwitchAD1Timeout = 80;
		SwitchAD2Distance = 75;
		SwitchAD2Timeout = 0;
		SwitchVisionTurnSpeed = VisionTurnSpeed;
		SwitchVisionTurnDistance = 20;
		
		/* Moves and turns for "Scale in favor" */
		ScaleADSpeed = ADSpeed;
		ScaleAD1Distance = SwitchAD1Distance + SwitchAD2Distance;
		ScaleAD1Timeout = 120;
		ScaleVisionTurnSpeed = VisionTurnSpeed;
		ScaleVisionTurnDistance = SwitchVisionTurnDistance;
		
		/* Moves and turns for "Both in favor" */
		BothADSpeed = ADSpeed;
		BothAD1Distance = SwitchAD1Distance;
		BothAD1Timeout = SwitchAD1Timeout;
		BothAD2Distance = SwitchAD2Distance;
		BothAD2Timeout = SwitchAD2Timeout;
		BothVisionTurnSpeed = VisionTurnSpeed;
		BothVisionTurnDistance = ScaleVisionTurnDistance;
		
		/* Moves and turns for "Neither in favor" */
		NeitherADSpeed = ADSpeed;
		NeitherAD1Distance = ScaleAD1Distance;
		NeitherAD1Timeout = ScaleAD1Timeout;
		NeitherVisionTurnSpeed = VisionTurnSpeed;
		NeitherVisionTurnDistance = ScaleVisionTurnDistance;
	}
}