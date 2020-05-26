package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class OPModeDriveHelper {
    private static OPModeDriveHelper instance;
    private  OPModeConstants opModeConstants;
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;

    // Returns instance or creates a new one if null
    static OPModeDriveHelper getInstance() {
        if (instance == null) {
            instance = new OPModeDriveHelper();
        }
        return instance;
    }

    void init(HardwareMap hardwareMap, OPModeConstants opModeConstants) {
        this.opModeConstants = opModeConstants;

        leftFront = hardwareMap.get(DcMotor.class, "left_front_wheel");
        rightFront = hardwareMap.get(DcMotor.class, "right_front_wheel");
        leftBack = hardwareMap.get(DcMotor.class, "left_back_wheel");
        rightBack = hardwareMap.get(DcMotor.class, "right_back_wheel");

        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    private OPModeDriveHelper() {}

    // Drives horizontally
    void driveHorizontal(double inches, OPModeConstants.HorizontalDriveDirection direction) {
        setAllStop();
        setHorizontalDriveDirection(direction);

        leftBack.setPower(opModeConstants.DRIVE_HORIZONTAL_SPEED);
        leftFront.setPower(opModeConstants.DRIVE_HORIZONTAL_SPEED);
        rightBack.setPower(opModeConstants.DRIVE_HORIZONTAL_SPEED);
        rightFront.setPower(opModeConstants.DRIVE_HORIZONTAL_SPEED);
        sleep((long) (inches * opModeConstants.MS_PER_HORIZONTAL_INCH));
        setAllStop();
    }

    // Drives in a line
    void drive(double inches, OPModeConstants.DriveDirection direction) {
        setAllStop();
        setDriveDirection(direction);

        leftBack.setPower(opModeConstants.DRIVE_SPEED);
        leftFront.setPower(opModeConstants.DRIVE_SPEED);
        rightBack.setPower(opModeConstants.DRIVE_SPEED);
        rightFront.setPower(opModeConstants.DRIVE_SPEED);
        sleep((long) (inches * opModeConstants.MS_PER_FORWARD_INCH * 1.8));
        setAllStop();
    }

    void driveTurn(double angle, OPModeConstants.TurnDirection direction){
        setAllStop();
        setTurnDirection(direction);

        leftBack.setPower(opModeConstants.DRIVE_TURN_SPEED);
        leftFront.setPower(opModeConstants.DRIVE_TURN_SPEED);
        rightBack.setPower(opModeConstants.DRIVE_TURN_SPEED);
        rightFront.setPower(opModeConstants.DRIVE_TURN_SPEED);
        sleep((long) (angle * opModeConstants.MS_PER_DEGREE));
        setAllStop();
    }

    // Sets wheels to drive forward or backward
    private void setDriveDirection(OPModeConstants.DriveDirection direction) {
        switch(direction) {
            case FORWARD:
                rightFront.setDirection(DcMotor.Direction.FORWARD);
                rightBack.setDirection(DcMotor.Direction.FORWARD);
                leftFront.setDirection(DcMotor.Direction.REVERSE);
                leftBack.setDirection(DcMotor.Direction.REVERSE);
                break;
            case REVERSE:
                rightFront.setDirection(DcMotor.Direction.REVERSE);
                rightBack.setDirection(DcMotor.Direction.REVERSE);
                leftFront.setDirection(DcMotor.Direction.FORWARD);
                leftBack.setDirection(DcMotor.Direction.FORWARD);
                break;
        }
    }

    // Sets wheels to drive horizontally
    private void setHorizontalDriveDirection(OPModeConstants.HorizontalDriveDirection direction) {
        switch (direction) {
            case LEFT:
                rightFront.setDirection(DcMotor.Direction.FORWARD);
                rightBack.setDirection(DcMotor.Direction.REVERSE);
                leftFront.setDirection(DcMotor.Direction.FORWARD);
                leftBack.setDirection(DcMotor.Direction.REVERSE);
                break;
            case RIGHT:
                rightFront.setDirection(DcMotor.Direction.REVERSE);
                rightBack.setDirection(DcMotor.Direction.FORWARD);
                leftFront.setDirection(DcMotor.Direction.REVERSE);
                leftBack.setDirection(DcMotor.Direction.FORWARD);
                break;
        }
    }

    // Sets wheels to turn right or left
    private void setTurnDirection(OPModeConstants.TurnDirection direction) {
        switch(direction) {
            case RIGHT:
                rightFront.setDirection(DcMotor.Direction.FORWARD);
                rightBack.setDirection(DcMotor.Direction.FORWARD);
                leftFront.setDirection(DcMotor.Direction.FORWARD);
                leftBack.setDirection(DcMotor.Direction.FORWARD);
                break;
            case LEFT:
                rightFront.setDirection(DcMotor.Direction.REVERSE);
                rightBack.setDirection(DcMotor.Direction.REVERSE);
                leftFront.setDirection(DcMotor.Direction.REVERSE);
                leftBack.setDirection(DcMotor.Direction.REVERSE);
                break;
        }
    }

    // Stops the robot
    private void setAllStop() {
        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);
    }

    public final void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
