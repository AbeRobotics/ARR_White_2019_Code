package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Beta Op Mode", group="Linear Opmode")
public class Beta_Teleop_Main extends LinearOpMode {

    // Use speed multiplier to set top speed of motors, 1 means no change
    private double FORWARD_SPEED_MULTIPLIER = 1;
    private double HORIZONTAL_SPEED_MULTIPLIER = 1;
    private double ARM_LIFT_SPEED_MULTIPLIER = 1;
    private double WRIST_LIFT_SPEED_MULTIPLIER = 1;
    // Sets how quickly the power to the wheels changes as a percent of the difference goal speed and current speed (max 1)
    private double ACCELERATION_MULTIPLIER = 1;

    private ElapsedTime elapsedTime = new ElapsedTime();
    private DcMotor leftFront = null;
    private DcMotor leftBack = null;
    private DcMotor rightFront = null;
    private DcMotor rightBack = null;
    private DcMotor armLift = null;
    private DcMotor wristLift = null;
    private Servo claw = null;
    private Servo rightPlatformGrabber = null;
    private Servo leftPlatformGrabber = null;

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

        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        leftPlatformGrabber.setDirection(Servo.Direction.REVERSE);

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



            if (gamepad1.left_stick_x != 0 && gamepad1.right_stick_x != 0) {
                if (gamepad1.right_stick_x > 0 && gamepad1.left_stick_x > 0) {
                    // used with omni-wheels in order to make the robot move sideways to the right
                    goalLeftBackPower = ((gamepad1.right_stick_x + gamepad1.left_stick_x) / 2) * -HORIZONTAL_SPEED_MULTIPLIER;
                    goalLeftFrontPower = ((gamepad1.right_stick_x + gamepad1.left_stick_x) / 2) * HORIZONTAL_SPEED_MULTIPLIER;
                    goalRightFrontPower = ((gamepad1.right_stick_x + gamepad1.left_stick_x) / 2) * -HORIZONTAL_SPEED_MULTIPLIER;
                    goalRightBackPower = ((gamepad1.right_stick_x + gamepad1.left_stick_x) / 2) * HORIZONTAL_SPEED_MULTIPLIER;
                } else if (gamepad1.right_stick_x < 0 && gamepad1.left_stick_x < 0) {
                    // used with omni-wheels in order to make the robot move sideways to the left
                    goalLeftBackPower = ((gamepad1.right_stick_x + gamepad1.left_stick_x) / 2) * -HORIZONTAL_SPEED_MULTIPLIER;
                    goalLeftFrontPower = ((gamepad1.right_stick_x + gamepad1.left_stick_x) / 2) * HORIZONTAL_SPEED_MULTIPLIER;
                    goalRightFrontPower = ((gamepad1.right_stick_x + gamepad1.left_stick_x) / 2) * -HORIZONTAL_SPEED_MULTIPLIER;
                    goalRightBackPower = ((gamepad1.right_stick_x + gamepad1.left_stick_x) / 2) * HORIZONTAL_SPEED_MULTIPLIER;
                }
            } else {
                // tank mode uses one joystick per side
                goalLeftBackPower = gamepad1.left_stick_y * FORWARD_SPEED_MULTIPLIER;
                goalLeftFrontPower = gamepad1.left_stick_y * FORWARD_SPEED_MULTIPLIER;
                goalRightFrontPower = gamepad1.right_stick_y * FORWARD_SPEED_MULTIPLIER;
                goalRightBackPower = gamepad1.right_stick_y * FORWARD_SPEED_MULTIPLIER;
            }

            if (gamepad1.a) {
                rightPlatformGrabber.setPosition(1);
                leftPlatformGrabber.setPosition(1);
            } else if (gamepad1.b) {
                rightPlatformGrabber.setPosition(0);
                leftPlatformGrabber.setPosition(0);
            }

            if (gamepad2.x){
                claw.setPosition(1);
            } else if (gamepad2.y) {
                claw.setPosition(0);
            }

            armLiftPower = gamepad2.right_stick_y * ARM_LIFT_SPEED_MULTIPLIER;
            wristLiftPower = gamepad2.left_stick_y * WRIST_LIFT_SPEED_MULTIPLIER;

            //Set the wheel power to the difference between the desired and actual power times the acceleration multiplier.
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

            // Show the elapsed game time, goal wheel power, and wheel power.
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

