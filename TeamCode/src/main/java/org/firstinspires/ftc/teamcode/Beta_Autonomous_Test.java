package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Autonomous Beta", group="Autonomous Tests")
public class Beta_Autonomous_Test extends LinearOpMode {


    private ElapsedTime elapsedTime = new ElapsedTime();
    private OPModeConstants opModeConstants = OPModeConstants.getInstance();
    private OPModeDriveHelper opModeDriveHelper = OPModeDriveHelper.getInstance();

    @Override
    public void runOpMode() {
        opModeDriveHelper.init(telemetry, hardwareMap, opModeConstants, this);

        waitForStart();
        elapsedTime.reset();
        telemetry.addData("Status", "Started");
        telemetry.update();
        sleep(1000);

        opModeConstants.setAutoSpeed(OPModeConstants.AutonomousSpeed.LOW);
        opModeConstants.setDriveDirection(OPModeConstants.DriveDirection.FORWARD);

        // Constant is 1260ms to move one square forwards

        opModeDriveHelper.drive(12d);
        sleep(1000);
        opModeDriveHelper.drive(12d, OPModeConstants.AutonomousSpeed.HIGH, OPModeConstants.DriveDirection.REVERSE);
        sleep(1000);
        opModeDriveHelper.drive(12d, OPModeConstants.AutonomousSpeed.MEDIUM, OPModeConstants.DriveDirection.REVERSE);
    }
}
