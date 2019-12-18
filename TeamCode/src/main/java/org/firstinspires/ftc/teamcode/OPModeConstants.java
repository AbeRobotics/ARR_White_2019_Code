package org.firstinspires.ftc.teamcode;

/**
 * Created by Kyle Stang on 31-Oct-2018
 * Adapted from Akanksha Joshi
 */
public class OPModeConstants {
	
	// Constant Values
	public final double ticksPerInch = 91.6732d;
	public final double horizontalTicksPerInch = 91.6732d; // same for now but need to calculate it
	public final double degreesToInch = 0.13308135546d * 1.075;
	public final double driveErrorThreshold = 3d;
	public final double slowdownMultiplier = 0.03d;
	public final double armRaisePower = 0.4d;
	public final double mineralAngleDegrees = 22.6d;
	public final double armBrakePosition = 0.85d;

	//Time Limits
	public final double armRaiseTimeMilli = 4000;
	public final double findGoldTimeMilli = 5000;
	public final double dropFlagTimeMilli = 2000;
	public final double armBrakeTimeMilli = 1000;
	public final long restTimeMilli = 250;
	
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

	// Horizontal direction
	public void setHorizontalDriveDirection(HorizontalDriveDirection direction) { horizontalDriveDirection = direction; }

	public HorizontalDriveDirection getHorizontalDriveDirection() { return horizontalDriveDirection; }
}