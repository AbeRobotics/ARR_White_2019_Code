package org.firstinspires.ftc.teamcode;

/**
 * Created by Kyle Stang on 31-Oct-2018
 * Adapted from Akanksha Joshi
 */
public class OPModeConstants {
	
	// Constant Values
	public final double ticksPerInch = 91.6732d;
	public final double gyroErrorThreshold = 3.0d;
	public final double degreesToInch = 0.13308135546d;
	public final double driveErrorThreshold = 4d;
	public final double slowdownMultiplier = 0.03d;
	public final double armRaiseTicks = 5700d;

	//Time Limits
	public final double armRaiseTimeMilli = 5000;
	
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
		MIDDLE,
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