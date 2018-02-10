package org.usfirst.frc1073.robot18;

/*** Used for different autonomousChooser settings
 * @author Nathaniel
 * @category Dashboard Stuff
 * @param Value (int) that will later be used to equate to a string
 */
public class AutoObject {
	public int n;
	
	/** Takes an integer when setting and that int will correspond to a string */
	public AutoObject(int v) {
		n = v;
	}
	
	/** Gets the position of the robot from the Chooser */
	public String getString() {
		String send = "";
		
		if (n == 1) {
			send = "left";
		}
		else if (n == 2) {
			send = "center";
		}
		else if (n == 3) {
			send = "right";
		}
		else {
			send = "not set";
		}
		
		return send;
	}
}