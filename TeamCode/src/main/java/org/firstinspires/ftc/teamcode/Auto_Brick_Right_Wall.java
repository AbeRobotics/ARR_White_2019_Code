package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Auto Brick Right Wall", group="Autonomous")
public class Auto_Brick_Right_Wall extends LinearOpMode {

    private ElapsedTime elapsedTime = new ElapsedTime();
    private OPModeConstants opModeConstants = OPModeConstants.getInstance();
    private OPModeDriveHelper opModeDriveHelper = OPModeDriveHelper.getInstance();

    @Override
    public void runOpMode() {
        opModeDriveHelper.init(hardwareMap, opModeConstants);

        waitForStart();
        elapsedTime.reset();
        telemetry.addData("Status", "Started");
        telemetry.update();

        opModeDriveHelper.driveHorizontal(23, OPModeConstants.HorizontalDriveDirection.RIGHT);
    }
}
