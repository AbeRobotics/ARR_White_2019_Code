package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Kyle Stang on 20-Feb-2019
 */
@Autonomous(name="Image Recognition Test", group="Autonomous Tests")
public class Test_ImageRecognition extends LinearOpMode{

    private ElapsedTime elapsedTime = new ElapsedTime();
    private OPModeConstants opModeConstants = OPModeConstants.getInstance();
    private Task_FindGold findGold;

    @Override
    public void runOpMode() {
        findGold = new Task_FindGold(this, this.hardwareMap, opModeConstants, elapsedTime);

        findGold.init();

        waitForStart();
        elapsedTime.reset();
        telemetry.addData("Status", "Started");
        telemetry.update();
        sleep(1000);

        findGold.performTask();

        telemetry.addData("Gold position", opModeConstants.getGoldLocation());
        telemetry.update();
        sleep(2000);
    }

}
