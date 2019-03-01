package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Kyle Stang on 1-Mar-2019
 */
public class Task_ArmRelease extends IOPModeTaskBase {

    private LinearOpMode opMode;
    private HardwareMap hardwareMap;
    private ElapsedTime elapsedTime;
    private OPModeConstants opModeConstants;

    private boolean taskComplete;
    private Servo armBrake;

    public Task_ArmRelease(LinearOpMode opMode, HardwareMap hardwareMap, ElapsedTime elapsedTime, OPModeConstants opModeConstants){
        this.opMode = opMode;
        this.hardwareMap = hardwareMap;
        this.elapsedTime = elapsedTime;
        this.opModeConstants = opModeConstants;
    }

    @Override
    public void init() {
        taskComplete = false;
        armBrake = hardwareMap.servo.get("arm_brake");
    }

    @Override
    public void performTask() {
        double startTime = elapsedTime.milliseconds();
        while(taskComplete == false && !opMode.isStopRequested()){
            if(elapsedTime.milliseconds() > startTime + opModeConstants.armBrakeTimeMilli){
                opMode.telemetry.addData("status", "timeout");
                opMode.telemetry.update();
                taskComplete = true;
                break;
            }
            armBrake.setPosition(0);
            if(armBrake.getPosition() == 0){
                taskComplete = true;
            }
        }
    }

    @Override
    public boolean getTaskStatus() {
        return taskComplete;
    }

    @Override
    public void reset() {
        taskComplete = false;
    }
}
