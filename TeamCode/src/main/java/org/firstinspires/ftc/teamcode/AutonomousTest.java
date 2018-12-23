package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="AutonomousTest", group="Autonomous")
public class AutonomousTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
    	
    	OPModeConstants opModeConstants = OPModeConstants.getInstance();
    	OPModeDriveHelper driver = OPModeDriveHelper.getInstance();
    	
    	driver.init(telemetry, hardwareMap, opModeConstants);
    	
    	waitForStart();
    	
    	opModeConstants.setAutoSpeed(OPModeConstants.AutonomousSpeed.LOW);
    	opModeConstants.setDriveDirection(OPModeConstants.DriveDirection.FORWARD);
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
