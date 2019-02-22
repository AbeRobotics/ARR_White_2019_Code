package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

// Designed to work with only one encoder on the right wheel
public class OPModeDriveHelperV2 {

    private static OPModeDriveHelperV2 instance;
    private  OPModeConstants opModeConstants;
    private  Telemetry telemetry;
    private  HardwareMap hardwareMap;
    private LinearOpMode opMode;
    private DcMotor leftWheel;
    private DcMotor rightWheel;

    // Returns instance or creates a new one if null
    public static OPModeDriveHelperV2 getInstance() {
        if(instance==null) {
            instance = new OPModeDriveHelperV2();
        }
        return instance;
    }

    public void init(Telemetry telemetry, HardwareMap hardwareMap, OPModeConstants opModeConstants, LinearOpMode opMode) {
        this.telemetry = telemetry;
        this.hardwareMap = hardwareMap;
        this.opModeConstants = opModeConstants;
        this.opMode = opMode;

        leftWheel = hardwareMap.dcMotor.get("left_wheel");
        rightWheel = hardwareMap.dcMotor.get("right_wheel");
    }

    private OPModeDriveHelperV2(){}

    public boolean drive(Double inches) {
        return drive(inches, opModeConstants.getAutoSpeed(), opModeConstants.getDriveDirection());
    }

    public boolean drive(Double inches, OPModeConstants.AutonomousSpeed speed, OPModeConstants.DriveDirection direction) {
        setAllStop();
        rightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        double totalTicks = opModeConstants.ticksPerInch * inches;

        setDriveDirection(direction);
        leftWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (!onPosition(totalTicks) && !opMode.isStopRequested()){
            double power = getPower(speed) * Range.clip(opModeConstants.slowdownMultiplier * Math.sqrt(Math.abs(totalTicks) - Math.abs(rightWheel.getCurrentPosition())), 0, 1);
            rightWheel.setPower(power);
            leftWheel.setPower(power);
        }

        setAllStop();
        rightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        return true;
    }

    public boolean driveTurn(OPModeConstants.TurnDirection direction, OPModeConstants.AutonomousSpeed speed, double angle) {
        setAllStop();
        rightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        setTurnDirection(direction);
        while (angle > 360) {
            angle -= 360;
        }
        while (angle < 0) {
            angle += 360;
        }

        double totalTicks = angle * opModeConstants.degreesToInch * opModeConstants.ticksPerInch;
        leftWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (!onPosition(totalTicks) && !opMode.isStopRequested()){
            double power = getPower(speed) * Range.clip(opModeConstants.slowdownMultiplier * Math.sqrt(Math.abs(totalTicks) - Math.abs(rightWheel.getCurrentPosition())), 0, 1);
            rightWheel.setPower(power);
            leftWheel.setPower(power);
        }

        setAllStop();
        rightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        return true;
    }

    private boolean onPosition(double position){
        if(Math.abs(rightWheel.getCurrentPosition()) < Math.abs(position) - opModeConstants.driveErrorThreshold){
            return false;
        }
        else{
            return true;
        }
    }

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

    private void setDriveDirection(OPModeConstants.DriveDirection direction) {
        switch(direction) {
            case FORWARD:
                rightWheel.setDirection(DcMotor.Direction.FORWARD);
                leftWheel.setDirection(DcMotor.Direction.REVERSE);
                break;
            case REVERSE:
                rightWheel.setDirection(DcMotor.Direction.REVERSE);
                leftWheel.setDirection(DcMotor.Direction.FORWARD);
                break;
        }
    }

    private void setTurnDirection(OPModeConstants.TurnDirection direction) {
        switch(direction) {
            case RIGHT:
                rightWheel.setDirection(DcMotor.Direction.REVERSE);
                leftWheel.setDirection(DcMotor.Direction.REVERSE);
                break;
            case LEFT:
                rightWheel.setDirection(DcMotor.Direction.FORWARD);
                leftWheel.setDirection(DcMotor.Direction.FORWARD);
                break;
        }
    }

    public void setAllStop(){
        rightWheel.setPower(0);
        leftWheel.setPower(0);
    }
}
