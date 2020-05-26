package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Platform Right Wall", group="Autonomous")
public class Auto_Platform_Right_Wall extends LinearOpMode {

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

        Task_ReleasePlatform releasePlatform = new Task_ReleasePlatform(this, hardwareMap, elapsedTime, opModeConstants);
        releasePlatform.init();
        releasePlatform.performTask();

        opModeDriveHelper.driveHorizontal(6, OPModeConstants.HorizontalDriveDirection.RIGHT);

        sleep(200);

        opModeDriveHelper.drive(17, OPModeConstants.DriveDirection.REVERSE);

        sleep(200);

        Task_GrabPlatform grabPlatform = new Task_GrabPlatform(this, hardwareMap, elapsedTime, opModeConstants);
        grabPlatform.init();
        grabPlatform.performTask();

        sleep(200);

        opModeDriveHelper.drive(10, OPModeConstants.DriveDirection.FORWARD);

        sleep(200);

        opModeDriveHelper.driveTurn( 125, OPModeConstants.TurnDirection.LEFT);

        sleep(200);

        opModeDriveHelper.driveHorizontal(5, OPModeConstants.HorizontalDriveDirection.RIGHT);

        sleep(200);

        opModeDriveHelper.driveTurn( 125, OPModeConstants.TurnDirection.LEFT);

        sleep(200);

        opModeDriveHelper.driveHorizontal(5, OPModeConstants.HorizontalDriveDirection.RIGHT);

        sleep(200);

        opModeDriveHelper.driveTurn( 110, OPModeConstants.TurnDirection.LEFT);

        sleep(200);

        opModeDriveHelper.driveHorizontal(5, OPModeConstants.HorizontalDriveDirection.RIGHT);

        sleep(200);

        opModeDriveHelper.drive(16, OPModeConstants.DriveDirection.REVERSE);

        sleep(200);

        Task_ReleasePlatform releasePlatform1 = new Task_ReleasePlatform(this, hardwareMap, elapsedTime, opModeConstants);
        releasePlatform1.init();
        releasePlatform1.performTask();

        sleep(200);

        opModeDriveHelper.driveHorizontal(20, OPModeConstants.HorizontalDriveDirection.RIGHT);

        sleep(200);

        opModeDriveHelper.drive(8, OPModeConstants.DriveDirection.REVERSE);

        sleep(200);

        opModeDriveHelper.driveHorizontal(8, OPModeConstants.HorizontalDriveDirection.RIGHT);
    }
}
