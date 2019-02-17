package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/*
Created by Kyle Stang on 14-Feb-2019
Adapted from Akanksha Joshi
 */
public class Task_RaiseArm extends IOPModeTaskBase {

    private DcMotor armMotor;
    private boolean taskComplete;
    private OPModeConstants opModeConstants;
    private LinearOpMode opMode;

    public Task_RaiseArm(HardwareMap hardwareMap, LinearOpMode opMode, OPModeConstants opModeConstants){
        this.armMotor = hardwareMap.get(DcMotor.class, "arm");
        this.opMode = opMode;
        this.opModeConstants = opModeConstants;
    }

    @Override
    public void init(){
        this.taskComplete = false;
    }

    @Override
    public boolean getTaskStatus(){
        return taskComplete;
    }

    @Override
    public void performTask(){
        double startTime = opMode.time;
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armMotor.setTargetPosition((int)opModeConstants.armRaiseTicks);
        armMotor.setPower(-0.2);

        while(!opMode.isStopRequested() && !taskComplete && opMode.time < startTime + opModeConstants.armRaiseTimeMilli){
            if(!armMotor.isBusy()){
                taskComplete = true;
                opMode.telemetry.addData("Arm position", armMotor.getCurrentPosition());
                opMode.telemetry.update();
            }
            sleep(10);
        }

    }

    @Override
    public void reset(){
        taskComplete = false;
    }
}
