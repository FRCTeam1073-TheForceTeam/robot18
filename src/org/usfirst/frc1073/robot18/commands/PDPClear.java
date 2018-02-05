package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.hal.PDPJNI;

public class PDPClear extends Command {
	
	public PDPClear() {
		
	}
	
	protected void initialize() {
		PDPJNI.clearPDPStickyFaults(59);
	}
	
	protected boolean isFinished() {
		return true;
	}

}
