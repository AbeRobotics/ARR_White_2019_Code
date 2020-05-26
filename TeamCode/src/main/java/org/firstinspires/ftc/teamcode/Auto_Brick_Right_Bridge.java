package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Auto Brick Right Bridge", group="Autonomous")
public class Auto_Brick_Right_Bridge extends LinearOpMode {

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

        opModeDriveHelper.drive(11, OPModeConstants.DriveDirection.REVERSE);

        sleep(200);

        opModeDriveHelper.driveHorizontal(23, OPModeConstants.HorizontalDriveDirection.RIGHT);


    }
}
