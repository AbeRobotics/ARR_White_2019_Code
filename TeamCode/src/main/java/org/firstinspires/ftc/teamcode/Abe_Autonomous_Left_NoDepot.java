package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Kyle Stang on 22-Feb-2019
 */
@Autonomous(name="Competition Left No Depot", group="Game Opmodes")
public class Abe_Autonomous_Left_NoDepot extends LinearOpMode{

    private OPModeConstants opModeConstants;
    private ElapsedTime elapsedTime;
    private Helper_OPModeDriverV3 driveHelper;

    @Override
    public void runOpMode(){

        elapsedTime = new ElapsedTime();
        opModeConstants = OPModeConstants.getInstance();
        driveHelper = Helper_OPModeDriverV3.getInstance();

        driveHelper.init(telemetry, hardwareMap, opModeConstants, this);
        telemetry.setAutoClear(false);

        // Create tasks
        Task_ArmRelease armRelease = new Task_ArmRelease(this, hardwareMap, elapsedTime, opModeConstants);
        Task_RaiseArm raiseArm = new Task_RaiseArm(this, hardwareMap, elapsedTime, opModeConstants);
        Task_FindGold findGold = new Task_FindGold(this, hardwareMap, opModeConstants, elapsedTime);
        Task_MoveCenterGold moveCenterGold = new Task_MoveCenterGold(this, hardwareMap, elapsedTime, opModeConstants, driveHelper);
        Task_MoveLeftGold moveLeftGold = new Task_MoveLeftGold(this, hardwareMap, elapsedTime, opModeConstants, driveHelper);
        Task_MoveRightGold moveRightGold = new Task_MoveRightGold(this, hardwareMap, elapsedTime, opModeConstants, driveHelper);

        waitForStart();

        elapsedTime.reset();

        telemetry.addData("Status", "Running");
        telemetry.update();

        // Start of game actions
        // Release brake
        armRelease.init();
        armRelease.performTask();
        while (armRelease.getTaskStatus() == false && !isStopRequested()){
            sleep(10);
        }

        sleep(opModeConstants.restTimeMilli);

        // Lower robot
        raiseArm.init();
        raiseArm.performTask();
        while (raiseArm.getTaskStatus() == false && !isStopRequested()){
            sleep(5);
        }

        sleep(opModeConstants.restTimeMilli);

        driveHelper.drive(4.0, OPModeConstants.AutonomousSpeed.MEDIUM, OPModeConstants.DriveDirection.REVERSE);

        sleep(opModeConstants.restTimeMilli);

        // Make robot face forward
        driveHelper.driveTurn(OPModeConstants.TurnDirection.RIGHT, OPModeConstants.AutonomousSpeed.LOW, 180.0);

        sleep(opModeConstants.restTimeMilli);

        // Find gold block
        findGold.init();
        findGold.performTask();
        while (findGold.getTaskStatus() == false && !isStopRequested()){
            sleep(10);
        }

        // Move gold block
        if(opModeConstants.getGoldLocation() == OPModeConstants.GoldLocation.CENTER){
            moveCenterGold.init();
            moveCenterGold.performTask();
            while (moveCenterGold.getTaskStatus() == false && !isStopRequested()){
                sleep(10);
            }
        }
        else if(opModeConstants.getGoldLocation() == OPModeConstants.GoldLocation.LEFT){
            moveLeftGold.init();
            moveLeftGold.performTask();
            while (moveLeftGold.getTaskStatus() == false && !isStopRequested()){
                sleep(10);
            }
        }
        else if(opModeConstants.getGoldLocation() == OPModeConstants.GoldLocation.RIGHT){
            moveRightGold.init();
            moveRightGold.performTask();
            while (moveRightGold.getTaskStatus() == false && !isStopRequested()){
                sleep(10);
            }
        }

        sleep(opModeConstants.restTimeMilli);

        // Drive to crater
        driveHelper.driveTurn(OPModeConstants.TurnDirection.RIGHT, OPModeConstants.AutonomousSpeed.LOW, 40.0);

        sleep(opModeConstants.restTimeMilli);

        driveHelper.drive(47.0, OPModeConstants.AutonomousSpeed.MEDIUM, OPModeConstants.DriveDirection.FORWARD);

        sleep(opModeConstants.restTimeMilli);

        driveHelper.driveTurn(OPModeConstants.TurnDirection.RIGHT, OPModeConstants.AutonomousSpeed.LOW, 75.0);

        driveHelper.drive(6.0, OPModeConstants.AutonomousSpeed.MEDIUM, OPModeConstants.DriveDirection.REVERSE);

        requestOpModeStop();
    }

}
