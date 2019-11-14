package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Kyle Stang on 22-Feb-2019
 */
public class Task_DropFlag extends IOPModeTaskBase {

    private LinearOpMode opMode;
    private HardwareMap hardwareMap;
    private ElapsedTime elapsedTime;
    private OPModeConstants opModeConstants;

    private boolean taskComplete;
    private Servo flagHolder;

    public Task_DropFlag(LinearOpMode opMode, HardwareMap hardwareMap, ElapsedTime elapsedTime, OPModeConstants opModeConstants){
        this.opMode = opMode;
        this.hardwareMap = hardwareMap;
        this.elapsedTime = elapsedTime;
        this.opModeConstants = opModeConstants;
    }

    @Override
    public void init() {
        taskComplete = false;
        flagHolder = hardwareMap.servo.get("flag_holder");
    }

    @Override
    public void performTask(){
        double startTime = elapsedTime.milliseconds();
        while(taskComplete == false && !opMode.isStopRequested()) {
            if (elapsedTime.milliseconds() > startTime + opModeConstants.dropFlagTimeMilli) {
                opMode.telemetry.addData("status", "timeout");
                opMode.telemetry.update();
                taskComplete = true;
                break;
            }
            flagHolder.setPosition(0.7);
            if (flagHolder.getPosition() == 0.7) {
                taskComplete = true;
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
