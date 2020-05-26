package org.firstinspires.ftc.teamcode;

class OPModeConstants {

    // Constant Values
    final double MS_PER_FORWARD_INCH = 52.5;
    final double MS_PER_DEGREE = 6;
    final double MS_PER_HORIZONTAL_INCH = 60;
    final double DRIVE_TURN_SPEED = 1;
    final double DRIVE_HORIZONTAL_SPEED = 1;
    final double DRIVE_SPEED = 0.7;

    // Time limits
    final double servoTimeLimit = 2000;
    final double lowerArmFromInitialTime = 5000;
    final double raiseArmTime = 1000;

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

    private static OPModeConstants opModeConstants;

    static OPModeConstants getInstance() {
        if (opModeConstants == null) {
            opModeConstants = new OPModeConstants();
        }
        return opModeConstants;
    }
}