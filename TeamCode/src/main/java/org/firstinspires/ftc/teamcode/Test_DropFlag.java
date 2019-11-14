package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Kyle Stang on 21-Feb-2019
 */
@Autonomous(name="Drop Flag Test", group="Autonomous Tests")
public class Test_DropFlag extends LinearOpMode{

    private ElapsedTime elapsedTime = new ElapsedTime();
    private OPModeConstants opModeConstants = OPModeConstants.getInstance();

    private Task_DropFlag dropFlag;

    @Override
    public void runOpMode() {
        dropFlag = new Task_DropFlag(this, hardwareMap, elapsedTime, opModeConstants);

        dropFlag.init();

        waitForStart();
        elapsedTime.reset();
        telemetry.addData("Status", "Started");
        telemetry.update();
        sleep(1000);

        dropFlag.performTask();
        while (dropFlag.getTaskStatus() == false){
            sleep(10);
        }


        telemetry.addData("Status", dropFlag.getTaskStatus());
        telemetry.update();
        sleep(10000);
    }

}
