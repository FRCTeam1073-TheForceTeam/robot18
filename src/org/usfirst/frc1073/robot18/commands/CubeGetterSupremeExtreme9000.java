package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CubeGetterSupremeExtreme9000 extends CommandGroup {
	
	public CubeGetterSupremeExtreme9000() {
		addParallel(new CollectorDown());
		addSequential(new OpenClaw());
		addParallel(new IsCubeIn());
		addSequential(new VisionCubeTracker());
		addSequential(new SuckInCube(2));
		addSequential(new WasteTime(9000));
		addSequential(new AdvancedDrive(0, 9000, 900000000)); //CubeGetter, but it will never end
	}
}