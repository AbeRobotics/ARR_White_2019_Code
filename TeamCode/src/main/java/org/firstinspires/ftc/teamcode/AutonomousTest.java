package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Autonomous Test", group="Autonomous Tests")
public class AutonomousTest extends LinearOpMode {

	private ElapsedTime runTime = new ElapsedTime();
	private double runtimeLimitMilli = 30000;

    @Override
    public void runOpMode() {
    	
    	OPModeConstants opModeConstants = OPModeConstants.getInstance();
    	OPModeDriveHelper driver = OPModeDriveHelper.getInstance();

    	opModeConstants.setRuntimeLimitMilliseconds(30000d);
    	driver.init(telemetry, hardwareMap, opModeConstants, runTime);
    	
    	waitForStart();
		telemetry.addData("Status: ", "Started");
		telemetry.update();
		sleep(1000);
    	runTime.reset();
    	
    	opModeConstants.setAutoSpeed(OPModeConstants.AutonomousSpeed.LOW);
    	opModeConstants.setDriveDirection(OPModeConstants.DriveDirection.FORWARD);
    	telemetry.addData("Runtime: ", runTime.toString());
    	telemetry.update();
    	sleep(1000);
    	driver.drive(24.0, opModeConstants.getAutoSpeed(), opModeConstants.getDriveDirection());
    	sleep(2000);
    	
    	opModeConstants.setTurnDirection(OPModeConstants.TurnDirection.RIGHT);
    	driver.gyroTurn(opModeConstants.getTurnDirection(), opModeConstants.getAutoSpeed(), 360.0);
    	sleep(2000);
    	
    	opModeConstants.setTurnDirection(OPModeConstants.TurnDirection.LEFT);
    	opModeConstants.setAutoSpeed(OPModeConstants.AutonomousSpeed.HIGH);;
    	driver.gyroTurn(opModeConstants.getTurnDirection(), opModeConstants.getAutoSpeed(), 360.0);
    	sleep(2000);
    	
    	opModeConstants.setDriveDirection(OPModeConstants.DriveDirection.REVERSE);
    	driver.drive(24.0, opModeConstants.getAutoSpeed(), opModeConstants.getDriveDirection());
    }
}
