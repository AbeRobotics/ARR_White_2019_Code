package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class OPModeDriveHelper {
	private static OPModeDriveHelper instance;
    private  OPModeConstants opModeConstants;
    private  Telemetry telemetry;
    private LinearOpMode opMode;
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    
    // Returns instance or creates a new one if null
    public static OPModeDriveHelper getInstance() {
        if (instance == null) {
            instance = new OPModeDriveHelper();
        }
        return instance;
    }
    
    public void init(Telemetry telemetry, HardwareMap hardwareMap, OPModeConstants opModeConstants, LinearOpMode opMode) {
    	this.telemetry = telemetry;
        this.opModeConstants = opModeConstants;
        this.opMode = opMode;

		leftFront = hardwareMap.get(DcMotor.class, "left_front_wheel");
		rightFront = hardwareMap.get(DcMotor.class, "right_front_wheel");
		leftBack = hardwareMap.get(DcMotor.class, "left_back_wheel");
		rightBack = hardwareMap.get(DcMotor.class, "right_back_wheel");
    }
    
    private OPModeDriveHelper() {}
    
    // Drives with previously used settings
    public boolean drive(Double inches) {
    	return drive(inches, opModeConstants.getAutoSpeed(), opModeConstants.getDriveDirection());
    }

    // Drives horizontally
	public boolean driveHorizontal(Double inches, OPModeConstants.AutonomousSpeed speed, OPModeConstants.HorizontalDriveDirection direction) {
    	setAllStop();
    	resetDriveEncoders();
		double totalTicks = opModeConstants.horizontalTicksPerInch * inches;

		setHorizontalDriveDirection(direction);
		leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		leftFront.setPower(getPower(speed));
		rightFront.setPower(getPower(speed));
		leftFront.setTargetPosition((int) totalTicks);
		rightFront.setTargetPosition((int) totalTicks);

		while ((leftFront.isBusy() || rightFront.isBusy()) && !opMode.isStopRequested()) {
			leftBack.setPower(getPower(speed));
			rightBack.setPower(getPower(speed));
			telemetry.addData("Left Current Position -", leftFront.getCurrentPosition());
			telemetry.addData("Right Current Position -", rightFront.getCurrentPosition());
			telemetry.addData("Target positions: ", totalTicks);
			telemetry.update();
			sleep(10);
		}

		setAllStop();
		resetDriveEncoders();
    	return true;
	}

    // Drives in a line
    public boolean drive(Double inches, OPModeConstants.AutonomousSpeed speed, OPModeConstants.DriveDirection direction ) {
    	setAllStop();
    	resetDriveEncoders();
    	double totalTicks = opModeConstants.ticksPerInch * inches;

    	setDriveDirection(direction);
		leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		leftFront.setPower(getPower(speed));
		rightFront.setPower(getPower(speed));
		leftFront.setTargetPosition((int) totalTicks);
		rightFront.setTargetPosition((int) totalTicks);
        
        while ((leftFront.isBusy() || rightFront.isBusy()) && !opMode.isStopRequested()) {
        	leftBack.setPower(getPower(speed));
        	rightBack.setPower(getPower(speed));
            telemetry.addData("Left Current Position -", leftFront.getCurrentPosition());
            telemetry.addData("Right Current Position -", rightFront.getCurrentPosition());
			telemetry.addData("Target positions: ", totalTicks);
            telemetry.update();
            sleep(10);
        }

        setAllStop();
        resetDriveEncoders();
        return true;
    }

	// Turns the robot using encoders
    public boolean driveTurn(OPModeConstants.TurnDirection direction, OPModeConstants.AutonomousSpeed speed, double angle){
    	setAllStop();
    	resetDriveEncoders();

    	setTurnDirection(direction);
		while (angle > 360) {
			angle -= 360;
		}
		while (angle < 0) {
			angle += 360;
		}

		double totalTicks = angle * opModeConstants.degreesToInch * opModeConstants.ticksPerInch;

		leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		leftFront.setPower(getPower(speed));
		rightFront.setPower(getPower(speed));
		leftFront.setTargetPosition((int) totalTicks);
		rightFront.setTargetPosition((int) totalTicks);

		while((leftFront.isBusy() || rightFront.isBusy()) && !opMode.isStopRequested()) {
			leftBack.setPower(getPower(speed));
			rightBack.setPower(getPower(speed));
			telemetry.addData("Left Current Position -",leftFront.getCurrentPosition());
			telemetry.addData("Right Current Position -",rightFront.getCurrentPosition());
			telemetry.addData("Target positions: ", totalTicks);
			telemetry.update();
			sleep(10);
		}
		setAllStop();
		resetDriveEncoders();
		return true;
	}

    // Returns power for wheels
	private double getPower(OPModeConstants.AutonomousSpeed speed) {
		switch(speed) {
		case LOW:
			return 0.5;
		case MEDIUM:
			return 0.75;
		case HIGH:
			return 1.0;
		default:
			return 0.0;
		}
	}
	
	// Sets wheels to drive forward or backward
	private void setDriveDirection(OPModeConstants.DriveDirection direction) {
		switch(direction) {
			case FORWARD:
				rightFront.setDirection(DcMotor.Direction.FORWARD);
				rightBack.setDirection(DcMotor.Direction.FORWARD);
				leftFront.setDirection(DcMotor.Direction.FORWARD);
				leftBack.setDirection(DcMotor.Direction.FORWARD);
				break;
			case REVERSE:
				rightFront.setDirection(DcMotor.Direction.REVERSE);
				rightBack.setDirection(DcMotor.Direction.REVERSE);
				leftFront.setDirection(DcMotor.Direction.REVERSE);
				leftBack.setDirection(DcMotor.Direction.REVERSE);
				break;
		}
	}

	// Sets wheels to drive horizontally
	private void setHorizontalDriveDirection(OPModeConstants.HorizontalDriveDirection direction) {
    	switch (direction) {
			case LEFT:
				rightFront.setDirection(DcMotor.Direction.REVERSE);
				rightBack.setDirection(DcMotor.Direction.FORWARD);
				leftFront.setDirection(DcMotor.Direction.FORWARD);
				leftBack.setDirection(DcMotor.Direction.REVERSE);
				break;
			case RIGHT:
				rightFront.setDirection(DcMotor.Direction.FORWARD);
				rightBack.setDirection(DcMotor.Direction.REVERSE);
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
				leftFront.setDirection(DcMotor.Direction.REVERSE);
				leftBack.setDirection(DcMotor.Direction.REVERSE);
				break;
			case LEFT:
				rightFront.setDirection(DcMotor.Direction.REVERSE);
				rightBack.setDirection(DcMotor.Direction.REVERSE);
				leftFront.setDirection(DcMotor.Direction.FORWARD);
				leftBack.setDirection(DcMotor.Direction.FORWARD);
				break;
		}
	}

	// Resets the drive encoders
	private void resetDriveEncoders() {
		leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
	}
	
	// Stops the robot
    public void setAllStop() {
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
