package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Kyle Stang on 14-Feb-2019
 * Adapted from Akanksha Joshi
 */
public class Task_RaiseNoEncoder extends IOPModeTaskBase {

    private DcMotor armMotor;
    private boolean taskComplete;
    private OPModeConstants opModeConstants;
    private LinearOpMode opMode;
    private ElapsedTime elapsedTime;

    public Task_RaiseNoEncoder(LinearOpMode opMode, HardwareMap hardwareMap, ElapsedTime elapsedTime, OPModeConstants opModeConstants){
        this.armMotor = hardwareMap.get(DcMotor.class, "arm");
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
                opMode.telemetry.addData("status", "timeout");
                opMode.telemetry.update();
                taskComplete = true;
                break;
            }
            if(armMotor.isBusy()){
                opMode.telemetry.addData("Arm position", armMotor.getCurrentPosition());
                opMode.telemetry.update();
                sleep(10);
            }
            else{
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
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
