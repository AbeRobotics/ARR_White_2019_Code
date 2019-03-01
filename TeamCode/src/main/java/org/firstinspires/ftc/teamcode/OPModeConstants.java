package org.firstinspires.ftc.teamcode;

/**
 * Created by Kyle Stang on 31-Oct-2018
 * Adapted from Akanksha Joshi
 */
public class OPModeConstants {
	
	// Constant Values
	public final double ticksPerInch = 91.6732d;
	public final double gyroErrorThreshold = 3.0d;
	public final double degreesToInch = 0.13308135546d * 1.075;
	public final double driveErrorThreshold = 3d;
	public final double slowdownMultiplier = 0.03d;
	public final double armRaiseTicks = 5700d;
	public final double mineralAngleDegrees = 24.376d;
	//TODO Find arm brake position
	public final double armBrakePosition = -1d;

	//Time Limits
	public final double armRaiseTimeMilli = 3000;
	public final double findGoldTimeMilli = 5000;
	public final double dropFlagTimeMilli = 2000;
	public final double armBrakeTimeMilli = 1000;
	public final double moveGoldTimeMilli = 10000;
	
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

	public enum GoldLocation{
		LEFT,
		CENTER,
		RIGHT,
		UNKNOWN
	}
	
	// Instance variables
	
	private static OPModeConstants opModeConstants;
	private AutonomousSpeed autoSpeed;
	private DriveDirection driveDirection;
	private TurnDirection turnDirection;
	private GoldLocation goldLocation;
	
	// Constructor
	
	private OPModeConstants() {
		setAutoSpeed(AutonomousSpeed.LOW);
		setDriveDirection(DriveDirection.FORWARD);
		setTurnDirection(TurnDirection.RIGHT);
		setGoldLocation(GoldLocation.UNKNOWN);
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

	// Gold Location
	public void setGoldLocation(GoldLocation location){ goldLocation = location; }

	public GoldLocation getGoldLocation(){
		return goldLocation;
	}
}