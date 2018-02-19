package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CubeGetter extends CommandGroup {
	
	public CubeGetter() {
		addSequential(new openClaw());
		addSequential(new VisionCubeTracker());
		addSequential(new SuckInCube(2));
	}
}
