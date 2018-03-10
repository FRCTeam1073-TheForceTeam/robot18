package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/***
 * Cube Collection System
 * If it doesn't get a cube, you did something wrong.S
 * @author Nathaniel
 * @see VisionCubeTracker()
 */
public class CubeGetter extends CommandGroup {
	
	/**
	 * Cube Collection System:
	 * If it doesn't get a cube, you did something wrong
	 * @author Nathaniel
	 * @see IsCubeIn()
	 * @see VisionCubeTracker()
	 */
	public CubeGetter() {
		addSequential(new EncoderCheck());
		addParallel(new CollectorDown());
		addSequential(new OpenClaw());
		addParallel(new IsCubeIn());
		addSequential(new VisionCubeTracker());
		addSequential(new SuckInCube(1.5, .3));
	}
}
