package org.firstinspires.ftc.teamcode;

public class OPModeConstants {
	
	// TODO Need to update with actual value. See GitHub issue #10
	public final double ticksPerInch = 0d;

	// Auto Speeds, SLOW = 0.5; MEDIUM = 0.75; FAST = 1.0
	public enum AutonomousSpeed{
		SLOW,
		MEDIUM,
		HIGH
	}
	
	private static OPModeConstants opModeConstants;
	private AutonomousSpeed autoSpeed;
	
	private OPModeConstants() {
		setAutoSpeed(AutonomousSpeed.HIGH);
	}
	
	public static OPModeConstants getInstance() {
        if(opModeConstants==null) {
            opModeConstants = new OPModeConstants();
        }
        return opModeConstants;
    }

	public void setAutoSpeed(AutonomousSpeed speed) {
		autoSpeed = speed;
	}
	
	public AutonomousSpeed getAutoSpeed() {
		return autoSpeed;
	}
}
