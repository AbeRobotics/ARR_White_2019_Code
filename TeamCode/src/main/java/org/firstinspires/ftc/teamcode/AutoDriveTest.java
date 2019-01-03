package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Auto Drive Test", group="Autonomous Tests")
public class AutoDriveTest extends LinearOpMode{

    private ElapsedTime elapsedTime = new ElapsedTime();
    private OPModeConstants opModeConstants = OPModeConstants.getInstance();
    private OPModeDriveHelper opModeDriveHelper = OPModeDriveHelper.getInstance();

    @Override
    public void runOpMode() {

        opModeConstants.setRuntimeLimitMilliseconds(30000d);
        opModeDriveHelper.init(telemetry, hardwareMap, opModeConstants, elapsedTime);

        waitForStart();
        elapsedTime.reset();
        telemetry.addData("Status", "Started");
        telemetry.update();
        sleep(1000);

        opModeConstants.setAutoSpeed(OPModeConstants.AutonomousSpeed.LOW);
        opModeConstants.setDriveDirection(OPModeConstants.DriveDirection.FORWARD);

        opModeDriveHelper.drive(36d);
        sleep(1000);
        opModeDriveHelper.drive(24.0, OPModeConstants.AutonomousSpeed.HIGH, OPModeConstants.DriveDirection.REVERSE);
        sleep(1000);
        opModeDriveHelper.drive(12d, OPModeConstants.AutonomousSpeed.MEDIUM, OPModeConstants.DriveDirection.REVERSE);
    }

}
