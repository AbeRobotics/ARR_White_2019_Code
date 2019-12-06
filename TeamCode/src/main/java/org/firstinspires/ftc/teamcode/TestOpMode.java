package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Test Op Mode", group="Linear Opmode")
//@Disabled
public class TestOpMode extends LinearOpMode {

    // Declare OpMode members.
    // Constants
    // Use speed multiplier to set top speed, 1 means no change
    private double FORWARD_SPEED_MULTIPLIER = 1;
    private double HORIZONTAL_SPEED_MULTIPLIER = 1;
    // Sets how quickly the power to the wheels changes as a percent of the difference goal speed and current speed (max 1)
    private double ACCELERATION_MULTIPLIER = 1;

    private ElapsedTime runtime = new ElapsedTime();
    private ElapsedTime elapsedTime = new ElapsedTime();
    private DcMotor leftFront = null;
    private DcMotor leftBack = null;
    private DcMotor rightFront = null;
    private DcMotor rightBack = null;
    private DcMotor arm = null;

    private Servo leftClaw = null;
    private Servo rightClaw = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. The strings used here as parameters
        // must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftFront = hardwareMap.get(DcMotor.class, "left_front_wheel");
        rightFront = hardwareMap.get(DcMotor.class, "right_front_wheel");
        leftBack = hardwareMap.get(DcMotor.class, "left_back_wheel");
        rightBack = hardwareMap.get(DcMotor.class, "right_back_wheel");

        arm = hardwareMap.get(DcMotor.class, "arm");

        leftClaw = hardwareMap.get(Servo.class, "left_claw");
        rightClaw = hardwareMap.get(Servo.class, "right_claw");

        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        rightClaw.setDirection(Servo.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        elapsedTime.reset();

        double leftBackPower = 0;
        double leftFrontPower = 0;
        double rightBackPower = 0;
        double rightFrontPower = 0;

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double goalLeftBackPower;
            double goalLeftFrontPower;
            double goalRightFrontPower;
            double goalRightBackPower;

            if (gamepad1.a) {
                leftClaw.setPosition(0);
                rightClaw.setPosition(0);
            }

            if (gamepad1.b) {
                leftClaw.setPosition(1);
                rightClaw.setPosition(1);
            }

            if (gamepad1.dpad_up) {
                arm.setPower(0.5);
            } else if (gamepad1.dpad_down) {
                arm.setPower(-0.2);
            } else {
                arm.setPower(0);
            }

            if (gamepad1.right_trigger > 0 || gamepad1.left_trigger > 0) {
                if (gamepad1.right_trigger > 0) {
                    // used with omni-wheels in order to make the robot move sideways to the right
                    goalLeftBackPower = gamepad1.right_trigger * -HORIZONTAL_SPEED_MULTIPLIER;
                    goalLeftFrontPower = gamepad1.right_trigger * HORIZONTAL_SPEED_MULTIPLIER;
                    goalRightFrontPower = gamepad1.right_trigger * -HORIZONTAL_SPEED_MULTIPLIER;
                    goalRightBackPower = gamepad1.right_trigger * HORIZONTAL_SPEED_MULTIPLIER;
                } else {
                    // used with omni-wheels in order to make the robot move sideways to the left
                    goalLeftBackPower = gamepad1.left_trigger * HORIZONTAL_SPEED_MULTIPLIER;
                    goalLeftFrontPower = gamepad1.left_trigger * -HORIZONTAL_SPEED_MULTIPLIER;
                    goalRightFrontPower = gamepad1.left_trigger * HORIZONTAL_SPEED_MULTIPLIER;
                    goalRightBackPower = gamepad1.left_trigger * -HORIZONTAL_SPEED_MULTIPLIER;
                }
            } else {
                // tank mode uses one joystick per side
                goalLeftBackPower = gamepad1.left_stick_y * FORWARD_SPEED_MULTIPLIER;
                goalLeftFrontPower = gamepad1.left_stick_y * FORWARD_SPEED_MULTIPLIER;
                goalRightFrontPower = gamepad1.right_stick_y * FORWARD_SPEED_MULTIPLIER;
                goalRightBackPower = gamepad1.right_stick_y * FORWARD_SPEED_MULTIPLIER;
            }

            //Set the wheel power to the difference between the desired and actual power times the acceleration multiplier.
            leftBackPower = leftBackPower + ((goalLeftBackPower - leftBackPower) * ACCELERATION_MULTIPLIER);
            leftFrontPower = leftFrontPower + ((goalLeftFrontPower - leftFrontPower) * ACCELERATION_MULTIPLIER);
            rightBackPower = rightBackPower + ((goalRightBackPower - rightBackPower) * ACCELERATION_MULTIPLIER);
            rightFrontPower = rightFrontPower + ((goalRightFrontPower - rightFrontPower) * ACCELERATION_MULTIPLIER);

            // Send calculated power to motors
            leftBack.setPower(leftBackPower);
            leftFront.setPower(leftFrontPower);
            rightBack.setPower(rightBackPower);
            rightFront.setPower(rightFrontPower);

            // Show the elapsed game time, goal wheel power, and wheel power.
            telemetry.addData("Status", "Run Time: " + elapsedTime.toString());
            telemetry.addData("Motors",
                    "left_back (%.2f), right_back (%.2f), left_front (%.2f), right_front (%.2f)," +
                            " goal left_back (%.2f), goal right_back (%.2f), goal left_front (%.2f), goal right_front (%.2f)",
                    leftBackPower, rightBackPower, leftFrontPower, rightFrontPower,
                    goalLeftBackPower, goalRightBackPower, goalLeftFrontPower, goalRightFrontPower);
            telemetry.addData("Right encoder", rightBack.getCurrentPosition());
            telemetry.addData("Left encoder", leftBack.getCurrentPosition());
            telemetry.update();
        }
    }
}
