package org.firstinspires.ftc.teamcode;

public class OPModeConstants {
	
	public final double ticksPerInch = 22.9183d;

	// Auto Speeds, LOW = 0.5; MEDIUM = 0.75; FAST = 1.0
	public enum AutonomousSpeed{
		LOW,
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
