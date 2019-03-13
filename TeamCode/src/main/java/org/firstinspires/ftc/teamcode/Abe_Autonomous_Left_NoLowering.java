package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Kyle Stang on 08-Mar-2019
 */
@Autonomous(name="Competition Left No Lowering", group="Game Opmodes")
public class Abe_Autonomous_Left_NoLowering extends LinearOpMode{

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
        Task_FindGold findGold = new Task_FindGold(this, hardwareMap, opModeConstants, elapsedTime);
        Task_DropFlag dropFlag = new Task_DropFlag(this, hardwareMap, elapsedTime, opModeConstants);
        Task_MoveCenterGold moveCenterGold = new Task_MoveCenterGold(this, hardwareMap, elapsedTime, opModeConstants, driveHelper);
        Task_MoveLeftGold moveLeftGold = new Task_MoveLeftGold(this, hardwareMap, elapsedTime, opModeConstants, driveHelper);
        Task_MoveRightGold moveRightGold = new Task_MoveRightGold(this, hardwareMap, elapsedTime, opModeConstants, driveHelper);

        waitForStart();

        elapsedTime.reset();

        telemetry.addData("Status", "Running");
        telemetry.update();

        // Start of game actions
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

        // Drive to depot
        driveHelper.driveTurn(OPModeConstants.TurnDirection.LEFT, OPModeConstants.AutonomousSpeed.LOW, 40.0);
        sleep(opModeConstants.restTimeMilli);
        driveHelper.drive(40.0, OPModeConstants.AutonomousSpeed.MEDIUM, OPModeConstants.DriveDirection.FORWARD);
        sleep(opModeConstants.restTimeMilli);
        driveHelper.driveTurn(OPModeConstants.TurnDirection.LEFT, OPModeConstants.AutonomousSpeed.LOW, 75.0);
        sleep(opModeConstants.restTimeMilli);
        driveHelper.drive(60.5, OPModeConstants.AutonomousSpeed.MEDIUM, OPModeConstants.DriveDirection.FORWARD);
        sleep(opModeConstants.restTimeMilli);
        driveHelper.driveTurn(OPModeConstants.TurnDirection.LEFT, OPModeConstants.AutonomousSpeed.LOW, 180-+.0);

        // Drop flag
        dropFlag.init();
        dropFlag.performTask();
        while (dropFlag.getTaskStatus() == false && !isStopRequested()){
            sleep(10);
        }

        // Drive to crater
        driveHelper.drive(75.0, OPModeConstants.AutonomousSpeed.MEDIUM, OPModeConstants.DriveDirection.FORWARD);

        requestOpModeStop();
    }

}
