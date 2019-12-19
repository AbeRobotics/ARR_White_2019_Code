package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Autonomous Test", group="Autonomous Tests")
public class AutonomousTest extends LinearOpMode {

	private ElapsedTime runTime = new ElapsedTime();

    @Override
    public void runOpMode() {

    	OPModeConstants opModeConstants = OPModeConstants.getInstance();
    	OPModeDriveHelper driver = OPModeDriveHelper.getInstance();

    	driver.init(telemetry, hardwareMap, opModeConstants, this);

    	waitForStart();
		telemetry.addData("Status: ", "Started");
		telemetry.update();
		sleep(1000);
    	runTime.reset();


		opModeConstants.setAutoSpeed(OPModeConstants.AutonomousSpeed.MEDIUM);
		opModeConstants.setDriveDirection(OPModeConstants.DriveDirection.FORWARD);
		driver.drive(20.0, opModeConstants.getAutoSpeed(), opModeConstants.getDriveDirection());
    	sleep(1000);

		opModeConstants.setTurnDirection(OPModeConstants.TurnDirection.RIGHT);
		driver.driveTurn(opModeConstants.getTurnDirection(), opModeConstants.getAutoSpeed(), 90);
		sleep(10000);

		opModeConstants.setHorizontalDriveDirection(OPModeConstants.HorizontalDriveDirection.RIGHT);
		driver.driveHorizontal(20.0, opModeConstants.getAutoSpeed(), opModeConstants.getHorizontalDriveDirection());
		sleep(1000);
    }
}
