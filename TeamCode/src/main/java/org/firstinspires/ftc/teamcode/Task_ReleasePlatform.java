package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Task_ReleasePlatform extends IOPModeTaskBase {

    private LinearOpMode opMode;
    private HardwareMap hardwareMap;
    private ElapsedTime elapsedTime;
    private OPModeConstants opModeConstants;

    private boolean taskComplete = false;
    private Servo leftPlatformGrabber;
    private Servo rightPlatformGrabber;

    Task_ReleasePlatform(LinearOpMode opMode, HardwareMap hardwareMap, ElapsedTime elapsedTime, OPModeConstants opModeConstants){
        this.opMode = opMode;
        this.hardwareMap = hardwareMap;
        this.elapsedTime = elapsedTime;
        this.opModeConstants = opModeConstants;
    }

    public void init() {
        rightPlatformGrabber = hardwareMap.get(Servo.class, "right_grabber");
        leftPlatformGrabber = hardwareMap.get(Servo.class, "left_grabber");
    }

    public void performTask() {
        double startTime = elapsedTime.milliseconds();
        while (!taskComplete && !opMode.isStopRequested()) {
            if (elapsedTime.milliseconds() > startTime + opModeConstants.servoTimeLimit) {
                opMode.telemetry.addData("status", "timeout");
                opMode.telemetry.update();
                taskComplete = true;
                break;
            }

            leftPlatformGrabber.setPosition(0);
            rightPlatformGrabber.setPosition(0);

            if (leftPlatformGrabber.getPosition() == 0 && rightPlatformGrabber.getPosition() == 0) {
                taskComplete = true;
            }
        }
    }

    public boolean getTaskStatus() { return taskComplete; }
    public void reset() { taskComplete = false; }
}