package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Kyle Stang on 22-Feb-2019
 */
@Autonomous(name="Competition Left", group="Game Opmodes")
public class Abe_Autonomous_Left extends LinearOpMode{

    private OPModeConstants opModeConstants;
    private ElapsedTime elapsedTime = new ElapsedTime();
    private Helper_OPModeDriverV3 driveHelper;

    @Override
    public void runOpMode(){
        opModeConstants = OPModeConstants.getInstance();
        driveHelper = Helper_OPModeDriverV3.getInstance();
        elapsedTime.reset();
        telemetry.setAutoClear(false);
        telemetry.addData("Status", "Running");
        telemetry.update();

        Task_RaiseArm raiseArm = new Task_RaiseArm(hardwareMap, this, opModeConstants, elapsedTime);
        raiseArm.init();
        raiseArm.performTask();
        while (raiseArm.getTaskStatus() == false){
            sleep(10);
        }

/*      Only needed if the robot starts backwards.
        driveHelper.driveTurn(OPModeConstants.TurnDirection.RIGHT, OPModeConstants.AutonomousSpeed.LOW, 180);
*/
        Task_FindGold findGold = new Task_FindGold(this, hardwareMap, opModeConstants, elapsedTime);
        findGold.init();
        findGold.performTask();
        while (findGold.getTaskStatus() == false){
            sleep(10);
        }

        if(opModeConstants.getGoldLocation() == OPModeConstants.GoldLocation.RIGHT){
            Task_MoveRightGold moveRightGold = new Task_MoveRightGold(this, hardwareMap, driveHelper, elapsedTime, opModeConstants);
            moveRightGold.init();
            moveRightGold.performTask();
            while (moveRightGold.getTaskStatus() == false){
                sleep(10);
            }
        }

        else if(opModeConstants.getGoldLocation() == OPModeConstants.GoldLocation.LEFT){
            Task_MoveLeftGold moveLeftGold = new Task_MoveLeftGold(this, hardwareMap, driveHelper, elapsedTime, opModeConstants);
            moveLeftGold.init();
            moveLeftGold.performTask();
            while (moveLeftGold.getTaskStatus() == false){
                sleep(10);
            }
        }

        else{
            Task_MoveCenterGold moveCenterGold = new Task_MoveCenterGold(this, hardwareMap, driveHelper, elapsedTime, opModeConstants);
            moveCenterGold.init();
            moveCenterGold.performTask();
            while (moveCenterGold.getTaskStatus() == false){
                sleep(10);
            }
        }

        driveHelper.driveTurn(OPModeConstants.TurnDirection.LEFT, OPModeConstants.AutonomousSpeed.LOW, 45.0);

        driveHelper.drive(50.92, OPModeConstants.AutonomousSpeed.MEDIUM, OPModeConstants.DriveDirection.FORWARD);

        driveHelper.driveTurn(OPModeConstants.TurnDirection.LEFT, OPModeConstants.AutonomousSpeed.LOW, 90.0);

        driveHelper.drive(74.42, OPModeConstants.AutonomousSpeed.MEDIUM, OPModeConstants.DriveDirection.FORWARD);

        Task_DropFlag dropFlag = new Task_DropFlag(this, hardwareMap, elapsedTime, opModeConstants);
        dropFlag.init();
        dropFlag.performTask();
        while (dropFlag.getTaskStatus() == false){
            sleep(10);
        }

        driveHelper.drive(92.0, OPModeConstants.AutonomousSpeed.MEDIUM, OPModeConstants.DriveDirection.REVERSE);

        requestOpModeStop();
    }

}
