package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Kyle Stang on 22-Feb-2019
 */
@Autonomous(name="Blue Team Left", group="Game Opmodes")
public class Abe_Autonomous_BlueTeam_Left extends LinearOpMode{

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
        raiseArm.performTask();
        while (raiseArm.getTaskStatus() == false){
            sleep(10);
        }

        driveHelper.driveTurn(OPModeConstants.TurnDirection.RIGHT, OPModeConstants.AutonomousSpeed.LOW, 180);

        Task_FindGold findGold = new Task_FindGold(this, hardwareMap, opModeConstants, elapsedTime);
        findGold.performTask();
        while (findGold.getTaskStatus() == false){
            sleep(10);
        }


    }

}
