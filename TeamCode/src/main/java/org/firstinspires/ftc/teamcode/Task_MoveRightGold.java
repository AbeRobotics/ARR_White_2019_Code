package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Kyle Stang on 22-Feb-2019
 */
public class Task_MoveRightGold extends IOPModeTaskBase {

    private LinearOpMode opMode;
    private boolean taskComplete;
    private HardwareMap hardwareMap;
    private Helper_OPModeDriverV3 driveHelper;
    private ElapsedTime elapsedTime;
    private OPModeConstants opModeConstants;
    private DcMotor collector;

    public Task_MoveRightGold(LinearOpMode opMode, HardwareMap hardwareMap, ElapsedTime elapsedTime, OPModeConstants opModeConstants, Helper_OPModeDriverV3 driveHelper){
        this.opMode = opMode;
        this.hardwareMap = hardwareMap;
        this.driveHelper = driveHelper;
        this.elapsedTime = elapsedTime;
        this.opModeConstants = opModeConstants;
    }

    @Override
    public void init() {
        taskComplete = false;
        collector = hardwareMap.dcMotor.get("collector");
    }

    @Override
    public void performTask(){
        driveHelper.driveTurn(OPModeConstants.TurnDirection.RIGHT, OPModeConstants.AutonomousSpeed.LOW, opModeConstants.mineralAngleDegrees);

        sleep(opModeConstants.restTimeMilli);

        collector.setPower(-0.75);

        driveHelper.drive(38.0, OPModeConstants.AutonomousSpeed.MEDIUM, OPModeConstants.DriveDirection.FORWARD);

        sleep(opModeConstants.restTimeMilli);

        driveHelper.drive(333.0, OPModeConstants.AutonomousSpeed.MEDIUM, OPModeConstants.DriveDirection.REVERSE);

        collector.setPower(0);

        sleep(opModeConstants.restTimeMilli);

        driveHelper.driveTurn(OPModeConstants.TurnDirection.LEFT, OPModeConstants.AutonomousSpeed.LOW, opModeConstants.mineralAngleDegrees);

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
