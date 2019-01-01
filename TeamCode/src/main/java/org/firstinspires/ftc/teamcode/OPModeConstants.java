package org.firstinspires.ftc.teamcode;

public class OPModeConstants {
	
	// Constant Values
	
	public final double ticksPerInch = 91.6732d;
	public final double gyroErrorThreshold = 1.0d;
	
	// Enumerations
	
	// Auto Speeds, LOW = 0.5; MEDIUM = 0.75; FAST = 1.0
	public enum AutonomousSpeed{
		LOW,
		MEDIUM,
		HIGH
	}
	
	public enum DriveDirection{
		FORWARD,
		REVERSE
	}
	
	public enum TurnDirection{
		RIGHT,
		LEFT
	}
	
	// Instance variables
	
	private static OPModeConstants opModeConstants;
	private AutonomousSpeed autoSpeed;
	private DriveDirection driveDirection;
	private TurnDirection turnDirection;
	private double runtimeLimitMilliseconds;
	
	// Constructor
	
	private OPModeConstants() {
		setAutoSpeed(AutonomousSpeed.HIGH);
		setDriveDirection(DriveDirection.FORWARD);
		setTurnDirection(TurnDirection.RIGHT);
		setRuntimeLimitMilliseconds(30000d);
	}
	
	public static OPModeConstants getInstance() {
        if(opModeConstants==null) {
            opModeConstants = new OPModeConstants();
        }
        return opModeConstants;
    }

	// Get and set instance variables
	
	// Auto speed
	public void setAutoSpeed(AutonomousSpeed speed) {
		autoSpeed = speed;
	}
	
	public AutonomousSpeed getAutoSpeed() {
		return autoSpeed;
	}
	
	// Drive direction
	public void setDriveDirection(DriveDirection direction){
		driveDirection = direction;
	}
	
	public DriveDirection getDriveDirection() {
		return driveDirection;
	}
	
	// Turn Direction
	public void setTurnDirection(TurnDirection direction) {
		turnDirection = direction;
	}
	
	public TurnDirection getTurnDirection() {
		return turnDirection;
	}

	// Runtime Limit (Only needed in autonomous mode)
	public void setRuntimeLimitMilliseconds(double milliseconds) {runtimeLimitMilliseconds = milliseconds; }

	public double getRuntimeLimitMilliseconds() { return runtimeLimitMilliseconds; }
}