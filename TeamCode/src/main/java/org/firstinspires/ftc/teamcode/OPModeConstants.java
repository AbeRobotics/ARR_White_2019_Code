package org.firstinspires.ftc.teamcode;

/**
 * Created by Kyle Stang on 31-Oct-2018
 * Adapted from Akanksha Joshi
 */
public class OPModeConstants {
	
	// Constant Values
	final double ticksPerInch = 91.6732d;
	final double horizontalTicksPerInch = 91.6732d; // same for now but need to calculate it
	final double degreesToInch = 0.13308135546d * 1.075;
	final double driveErrorThreshold = 3d;
	final double slowdownMultiplier = 0.03d;
	final double armRaisePower = 0.4d;
	final double armBrakePosition = 0.85d;

	//Time Limits
	final double armRaiseTimeMilli = 4000;
	final double dropFlagTimeMilli = 2000;
	final double armBrakeTimeMilli = 1000;
	
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

	public enum HorizontalDriveDirection {
		RIGHT,
		LEFT
	}
	
	// Instance variables
	
	private static OPModeConstants opModeConstants;
	private AutonomousSpeed autoSpeed;
	private DriveDirection driveDirection;
	private TurnDirection turnDirection;
	private HorizontalDriveDirection horizontalDriveDirection;
	
	// Constructor
	
	private OPModeConstants() {
		setAutoSpeed(AutonomousSpeed.LOW);
		setDriveDirection(DriveDirection.FORWARD);
		setTurnDirection(TurnDirection.RIGHT);
	}
	
	public static OPModeConstants getInstance() {
        if(opModeConstants==null) {
            opModeConstants = new OPModeConstants();
        }
        return opModeConstants;
    }

	// Get and set instance variables
	
	// Auto speed
	void setAutoSpeed(AutonomousSpeed speed) {
		autoSpeed = speed;
	}
	
	AutonomousSpeed getAutoSpeed() {
		return autoSpeed;
	}
	
	// Drive direction
	void setDriveDirection(DriveDirection direction){
		driveDirection = direction;
	}
	
	DriveDirection getDriveDirection() {
		return driveDirection;
	}
	
	// Turn Direction
	void setTurnDirection(TurnDirection direction) {
		turnDirection = direction;
	}
	
	TurnDirection getTurnDirection() {
		return turnDirection;
	}

	// Horizontal direction
	void setHorizontalDriveDirection(HorizontalDriveDirection direction) { horizontalDriveDirection = direction; }

	HorizontalDriveDirection getHorizontalDriveDirection() { return horizontalDriveDirection; }
}