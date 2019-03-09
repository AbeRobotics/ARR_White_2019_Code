package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Kyle Stang on 22-Feb-2019
 */
public class Task_MoveLeftGold extends IOPModeTaskBase {

    private LinearOpMode opMode;
    private boolean taskComplete;
    private HardwareMap hardwareMap;
    private Helper_OPModeDriverV3 driveHelper;
    private ElapsedTime elapsedTime;
    private OPModeConstants opModeConstants;

    public Task_MoveLeftGold(LinearOpMode opMode, HardwareMap hardwareMap, ElapsedTime elapsedTime, OPModeConstants opModeConstants, Helper_OPModeDriverV3 driveHelper){
        this.opMode = opMode;
        this.hardwareMap = hardwareMap;
        this.driveHelper = driveHelper;
        this.elapsedTime = elapsedTime;
        this.opModeConstants = opModeConstants;
    }

    @Override
    public void init() {
        taskComplete = false;
    }

    @Override
    public void performTask(){
        driveHelper.driveTurn(OPModeConstants.TurnDirection.LEFT, OPModeConstants.AutonomousSpeed.LOW, opModeConstants.mineralAngleDegrees);

        sleep(opModeConstants.restTimeMilli);

        driveHelper.drive(38.0, OPModeConstants.AutonomousSpeed.MEDIUM, OPModeConstants.DriveDirection.FORWARD);

        sleep(opModeConstants.restTimeMilli);

        driveHelper.drive(38.0, OPModeConstants.AutonomousSpeed.MEDIUM, OPModeConstants.DriveDirection.REVERSE);

        sleep(opModeConstants.restTimeMilli);

        driveHelper.driveTurn(OPModeConstants.TurnDirection.RIGHT, OPModeConstants.AutonomousSpeed.LOW, opModeConstants.mineralAngleDegrees);

        taskComplete = true;
    }

    @Override
    public boolean getTaskStatus(){
        return taskComplete;
    }

    @Override
    public void reset(){
        taskComplete = false;
    }
}