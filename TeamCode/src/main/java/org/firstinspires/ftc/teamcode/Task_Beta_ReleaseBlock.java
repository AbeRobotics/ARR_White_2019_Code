package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Task_Beta_ReleaseBlock extends IOPModeTaskBase {

    private LinearOpMode opMode;
    private HardwareMap hardwareMap;
    private ElapsedTime elapsedTime;
    private OPModeConstants opModeConstants;

    private boolean taskComplete;
    private Servo claw;

    public Task_Beta_ReleaseBlock(LinearOpMode opMode, HardwareMap hardwareMap, ElapsedTime elapsedTime, OPModeConstants opModeConstants){
        this.opMode = opMode;
        this.hardwareMap = hardwareMap;
        this.elapsedTime = elapsedTime;
        this.opModeConstants = opModeConstants;
    }

    @Override
    public void init() {
        taskComplete = false;
        claw = hardwareMap.servo.get("claw");
    }

    @Override
    public void performTask(){
        double startTime = elapsedTime.milliseconds();

        while(!taskComplete && !opMode.isStopRequested()) {
            if (elapsedTime.milliseconds() > startTime + opModeConstants.dropFlagTimeMilli) {
                opMode.telemetry.addData("status", "timeout");
                opMode.telemetry.update();
                taskComplete = true;
                break;
            }
            claw.setPosition(0);
            if (claw.getPosition() == 0) {
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
