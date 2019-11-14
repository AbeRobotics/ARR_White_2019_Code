package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Kyle Stang on 6-Mar-2019
 * Adapted from Akanksha Joshi
 */
public class Task_RaiseArm extends IOPModeTaskBase {

    private DcMotor armMotor;
    private TouchSensor armButton;
    private boolean taskComplete;
    private OPModeConstants opModeConstants;
    private LinearOpMode opMode;
    private ElapsedTime elapsedTime;

    public Task_RaiseArm(LinearOpMode opMode, HardwareMap hardwareMap, ElapsedTime elapsedTime, OPModeConstants opModeConstants){
        this.armMotor = hardwareMap.get(DcMotor.class, "arm");
        this.armButton = hardwareMap.get(TouchSensor.class, "button");
        this.opMode = opMode;
        this.opModeConstants = opModeConstants;
        this.elapsedTime = elapsedTime;
    }

    @Override
    public void init(){
        this.taskComplete = false;
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void performTask(){
        double startTime = elapsedTime.milliseconds();

        armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while(!opMode.isStopRequested() && !taskComplete){
            if (elapsedTime.milliseconds() > startTime + opModeConstants.armRaiseTimeMilli) {
                armMotor.setPower(0);
                opMode.telemetry.addData("status", "timeout");
                opMode.telemetry.update();
                taskComplete = true;
                break;
            }
            if(armButton.isPressed()){
                armMotor.setPower(0);
                opMode.telemetry.addData("Raise status", "done");
                opMode.telemetry.update();
                taskComplete = true;
                break;
            }
            else{
                armMotor.setPower(opModeConstants.armRaisePower);
                sleep(5);
            }
        }
    }

    @Override
    public boolean getTaskStatus(){
        return taskComplete;
    }

    @Override
    public void reset(){
        taskComplete = false;
    }
}
