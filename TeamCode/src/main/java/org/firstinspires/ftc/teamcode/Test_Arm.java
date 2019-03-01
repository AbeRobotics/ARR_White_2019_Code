package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Kyle Stang on 1-Mar-2019
 */
@Autonomous(name="Arm Test", group="Autonomous Tests")
public class Test_Arm extends LinearOpMode {

    private ElapsedTime elapsedTime = new ElapsedTime();
    private OPModeConstants opModeConstants = OPModeConstants.getInstance();

    private Task_ArmRelease armRelease;
    private Task_RaiseArm raiseArm;
    private Task_LowerArm lowerArm;
    private Task_ArmBrake armBrake;

    @Override
    public void runOpMode(){
        armRelease = new Task_ArmRelease(this, hardwareMap, elapsedTime, opModeConstants);
        raiseArm = new Task_RaiseArm(this, hardwareMap, elapsedTime, opModeConstants);
        lowerArm = new Task_LowerArm(this, hardwareMap, elapsedTime, opModeConstants);
        armBrake = new Task_ArmBrake(this, hardwareMap, elapsedTime, opModeConstants);

        armRelease.init();
        armRelease.performTask();
        while (!armRelease.getTaskStatus() && !isStopRequested()){
            sleep(10);
        }

        sleep(1000);

        raiseArm.init();
        raiseArm.reset();
        raiseArm.performTask();
        while (!raiseArm.getTaskStatus() && !isStopRequested()){
            sleep(10);
        }

        sleep(1000);

        lowerArm.init();
        lowerArm.performTask();
        while (!lowerArm.getTaskStatus() && !isStopRequested()){
            sleep(10);
        }

        sleep(1000);

        armBrake.init();
        armBrake.performTask();
        while (!armBrake.getTaskStatus() && !isStopRequested()){
            sleep(10);
        }

        telemetry.addData("status", "finished");
        telemetry.update();

        sleep(5000);

        requestOpModeStop();
    }
}
