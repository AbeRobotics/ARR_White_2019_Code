package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Beta Op Mode", group="Linear Opmode")
public class Teleop_Main extends LinearOpMode {

    // Use speed multiplier to set top speed of motors, 1 means no change
    final private double FORWARD_SPEED_MULTIPLIER = 1;
    final private double HORIZONTAL_SPEED_MULTIPLIER = 1;
    final private double ARM_LIFT_SPEED_MULTIPLIER = 0.6;
    final private double WRIST_LIFT_SPEED_MULTIPLIER = 0.4;
    final private double ACCELERATION_MULTIPLIER = 1;



    private ElapsedTime elapsedTime = new ElapsedTime();
    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor rightFront;
    private DcMotor rightBack;
    private DcMotor armLift;
    private DcMotor wristLift;
    private Servo claw;
    private Servo rightPlatformGrabber;
    private Servo leftPlatformGrabber;

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
        armLift = hardwareMap.get(DcMotor.class, "arm_lift");
        wristLift = hardwareMap.get(DcMotor.class, "wrist_lift");
        claw = hardwareMap.get(Servo.class, "claw");
        rightPlatformGrabber = hardwareMap.get(Servo.class, "right_grabber");
        leftPlatformGrabber = hardwareMap.get(Servo.class, "left_grabber");

        // set initial positions of motors
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        armLift.setDirection(DcMotorSimple.Direction.REVERSE);
        wristLift.setDirection(DcMotorSimple.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        elapsedTime.reset();

        double leftBackPower;
        double leftFrontPower;
        double rightBackPower;
        double rightFrontPower;
        double armLiftPower;
        double wristLiftPower;

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double goalLeftBackPower = 0;
            double goalLeftFrontPower = 0;
            double goalRightFrontPower = 0;
            double goalRightBackPower = 0;

            // omni wheels move horizontally when left and right joystick are pushed to the same side
            if (gamepad1.left_stick_x != 0 && gamepad1.right_stick_x != 0) {
                if (gamepad1.right_stick_x > 0 && gamepad1.left_stick_x > 0) {
                    // inverts the left front and right back wheel in order to move horizontally using omni wheels
                    goalLeftBackPower = ((gamepad1.right_stick_x + gamepad1.left_stick_x) / 2) * HORIZONTAL_SPEED_MULTIPLIER;
                    goalLeftFrontPower = ((gamepad1.right_stick_x + gamepad1.left_stick_x) / 2) * -HORIZONTAL_SPEED_MULTIPLIER;
                    goalRightFrontPower = ((gamepad1.right_stick_x + gamepad1.left_stick_x) / 2) * HORIZONTAL_SPEED_MULTIPLIER;
                    goalRightBackPower = ((gamepad1.right_stick_x + gamepad1.left_stick_x) / 2) * -HORIZONTAL_SPEED_MULTIPLIER;
                } else if (gamepad1.right_stick_x < 0 && gamepad1.left_stick_x < 0) {
                    // inverts the left front and right back wheel in order to move horizontally using omni wheels
                    goalLeftBackPower = ((gamepad1.right_stick_x + gamepad1.left_stick_x) / 2) * HORIZONTAL_SPEED_MULTIPLIER;
                    goalLeftFrontPower = ((gamepad1.right_stick_x + gamepad1.left_stick_x) / 2) * -HORIZONTAL_SPEED_MULTIPLIER;
                    goalRightFrontPower = ((gamepad1.right_stick_x + gamepad1.left_stick_x) / 2) * HORIZONTAL_SPEED_MULTIPLIER;
                    goalRightBackPower = ((gamepad1.right_stick_x + gamepad1.left_stick_x) / 2) * -HORIZONTAL_SPEED_MULTIPLIER;
                }
            } else {
                // tank mode uses one joystick per side
                if (rightFront.getDirection() == DcMotorSimple.Direction.FORWARD) {
                    goalLeftBackPower = gamepad1.right_stick_y * FORWARD_SPEED_MULTIPLIER;
                    goalLeftFrontPower = gamepad1.right_stick_y * FORWARD_SPEED_MULTIPLIER;
                    goalRightFrontPower = gamepad1.left_stick_y * FORWARD_SPEED_MULTIPLIER;
                    goalRightBackPower = gamepad1.left_stick_y * FORWARD_SPEED_MULTIPLIER;
                } else {
                    goalLeftBackPower = gamepad1.left_stick_y * FORWARD_SPEED_MULTIPLIER;
                    goalLeftFrontPower = gamepad1.left_stick_y * FORWARD_SPEED_MULTIPLIER;
                    goalRightFrontPower = gamepad1.right_stick_y * FORWARD_SPEED_MULTIPLIER;
                    goalRightBackPower = gamepad1.right_stick_y * FORWARD_SPEED_MULTIPLIER;
                }
            }


            // reverses the motors for the wheels upon button press
            if (gamepad1.x) {
                rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
                rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
                leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
                leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
            } else if (gamepad1.y) {
                rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
                rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
                leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
                leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
            }

            // set to grab platform
            if (gamepad1.a) {
                rightPlatformGrabber.setPosition(1);
                leftPlatformGrabber.setPosition(0.7);
            } else if (gamepad1.b) {
                rightPlatformGrabber.setPosition(0.5);
                leftPlatformGrabber.setPosition(0.3);
            }

            // set to close claw and grab blocks
            if (gamepad2.a) {
                claw.setPosition(1);
            } else if (gamepad2.b) {
                claw.setPosition(0);
            }

            // reverses the motors of the arm with button press
            if (gamepad2.x) {
                armLift.setDirection(DcMotorSimple.Direction.FORWARD);
                wristLift.setDirection(DcMotorSimple.Direction.FORWARD);
            } else if (gamepad2.y) {
                armLift.setDirection(DcMotorSimple.Direction.REVERSE);
                wristLift.setDirection(DcMotorSimple.Direction.REVERSE);
            }

            // set the arm wrist and lift power to the joystick value multiplied by their respective speed multiplier.
            armLiftPower = gamepad2.right_stick_y * ARM_LIFT_SPEED_MULTIPLIER;
            wristLiftPower = gamepad2.left_stick_y * WRIST_LIFT_SPEED_MULTIPLIER;

            // set the wheel power to the desired power times the acceleration multiplier.
            leftBackPower = goalLeftBackPower * ACCELERATION_MULTIPLIER;
            leftFrontPower = goalLeftFrontPower * ACCELERATION_MULTIPLIER;
            rightBackPower = goalRightBackPower * ACCELERATION_MULTIPLIER;
            rightFrontPower = goalRightFrontPower * ACCELERATION_MULTIPLIER;

            // Send calculated power to motors
            leftBack.setPower(leftBackPower);
            leftFront.setPower(leftFrontPower);
            rightBack.setPower(rightBackPower);
            rightFront.setPower(rightFrontPower);
            armLift.setPower(armLiftPower);
            wristLift.setPower(wristLiftPower);

            // Show the elapsed game time, wheel power, servo positions, and arm motors power
            telemetry.addData("Status", "Run Time: " + elapsedTime.toString());
            telemetry.addData("Motors",
                    "left_back (%.2f), right_back (%.2f), left_front (%.2f), right_front (%.2f)," +
                            " claw position (%.2f), right grabber position (%.2f), left grabber position (%.2f)," +
                            " arm lift motor power (%.2f), wrist lift motor power (%.2f)",
                    leftBackPower, rightBackPower, leftFrontPower, rightFrontPower, claw.getPosition(), rightPlatformGrabber.getPosition(),
                    leftPlatformGrabber.getPosition(), armLiftPower, wristLiftPower);
            telemetry.addData("Right encoder", rightBack.getCurrentPosition());
            telemetry.addData("Left encoder", leftBack.getCurrentPosition());
            telemetry.update();
        }
    }
}

