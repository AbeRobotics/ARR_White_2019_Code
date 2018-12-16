package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class OPModeDriveHelper {
	private static OPModeDriveHelper instance;
    private  OPModeConstants opModeConstants;
    private  Telemetry telemetry;
    private  HardwareMap hardwareMap;
    DcMotor leftWheel;
    DcMotor rightWheel;

    public static OPModeDriveHelper getInstance() {
        if(instance==null) {
            instance = new OPModeDriveHelper();
        }
        return instance;
    }
    
    public void init(Telemetry telemetry, HardwareMap hardwareMap) {
    	this.telemetry = telemetry;
    	this.hardwareMap = hardwareMap;
        opModeConstants = OPModeConstants.getInstance();
    	
        leftWheel = hardwareMap.dcMotor.get("left_wheel");
        rightWheel = hardwareMap.dcMotor.get("right_wheel");
    }
    
    private OPModeDriveHelper(){}
    
    public boolean moveForward(Double inches) {
    	return moveForward(inches, opModeConstants.getAutoSpeed());
    }
    
    // TODO It seems redundant to copy MoveForward for MoveBackward. Will probably just create function that
    // multiplies the input by -1 and calls MoveForward or create a single move function for MoveForward and MoveBackward.
    public boolean moveForward(Double inches, OPModeConstants.AutonomousSpeed speed) {
    	resetDriveEncoders();
    	//SetForwardSteering();		TODO What is the point of this method? What advantage over SetAllStop()?
    	double totalTicks = opModeConstants.ticksPerInch * inches;
    	
    	leftWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftWheel.setPower(GetPower(speed));
        rightWheel.setPower(GetPower(speed));
        leftWheel.setTargetPosition((int)totalTicks);
        rightWheel.setTargetPosition((int)(totalTicks*-1));
        while(leftWheel.isBusy()) {
            telemetry.addData("Left Current Position -",leftWheel.getCurrentPosition());
            telemetry.addData("Right Current Position -",rightWheel.getCurrentPosition());
            telemetry.update();
            sleep(10);
        }
        setAllStop();
        resetDriveEncoders();
        return true;
    }
    
    public boolean gyroTurn(OPModeConstants.AutonomousSpeed speed, double angle) {
    	leftWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    	rightWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    	
    	
    }

	private double getPower(OPModeConstants.AutonomousSpeed speed) {
		switch(speed) {
			case LOW:
				return 0.5;
				break;
			
			case MEDIUM:
				return 0.75;
				break;
			
			case HIGH:
				return 1.0;
				break;
		}
	}

	private void resetDriveEncoders() {
		leftWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		rightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
	}
	
    public void setAllStop() {
        leftWheel.setPower(0);
        rightWheel.setPower(0);
    }
    
    public final void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
