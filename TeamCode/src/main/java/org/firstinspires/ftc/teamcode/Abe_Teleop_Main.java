package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Kyle Stang on 24-Oct-2018
 */
@TeleOp(name="Advanced Tele Op", group="Linear Opmode")
public class Abe_Teleop_Main extends LinearOpMode {
	
	// Constants
	// Use speed multiplier to set top speed, 1 means no change
	private double FORWARD_SPEED_MULTIPLIER = 0.75;
	private double HORIZONTAL_SPEED_MULTIPLIER = 0.75;
    // Sets how quickly the power to the wheels changes as a percent of the difference goal speed and current speed (max 1)
	private double ACCELERATION_MULTIPLIER = 0.5;

    // Declare OpMode members.
    private ElapsedTime elapsedTime = new ElapsedTime();
    private DcMotor leftFront = null;
    private DcMotor leftBack = null;
    private DcMotor rightFront = null;
    private DcMotor rightBack = null;


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


        // Reverse the motor that runs backwards when connected directly to the battery
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        elapsedTime.reset();

        double leftPower = 0;
        double rightPower = 0;

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double goalLeftPower;
            double goalRightPower;

            // Tank Mode uses one stick to control each wheel.
            goalLeftPower  = gamepad1.left_stick_y * FORWARD_SPEED_MULTIPLIER;
            goalRightPower = gamepad1.right_stick_y * FORWARD_SPEED_MULTIPLIER;


            // used with omni-wheels in order to make the robot move sideways to the right
            if (gamepad1.left_stick_y == 0 && gamepad1.right_stick_y == 0 && gamepad1.right_trigger > 0) {
                leftBack.setPower(-gamepad1.right_trigger * HORIZONTAL_SPEED_MULTIPLIER);
                leftFront.setPower(gamepad1.right_trigger * HORIZONTAL_SPEED_MULTIPLIER);
                rightFront.setPower(-gamepad1.right_trigger * HORIZONTAL_SPEED_MULTIPLIER);
                rightBack.setPower(gamepad1.right_trigger * HORIZONTAL_SPEED_MULTIPLIER);
            }

            // used with omni-wheels in order to make the robot move sideways to the left
            if (gamepad1.left_stick_y == 0 && gamepad1.right_stick_y == 0 && gamepad1.left_trigger > 0) {
                leftBack.setPower(gamepad1.right_trigger * HORIZONTAL_SPEED_MULTIPLIER);
                leftFront.setPower(-gamepad1.right_trigger * HORIZONTAL_SPEED_MULTIPLIER);
                rightFront.setPower(gamepad1.right_trigger * HORIZONTAL_SPEED_MULTIPLIER);
                rightBack.setPower(-gamepad1.right_trigger * HORIZONTAL_SPEED_MULTIPLIER);
            }
            
            //Set the wheel power to the difference between the desired and actual power times the acceleration multiplier.
            leftPower = leftPower + ((goalLeftPower - leftPower) * ACCELERATION_MULTIPLIER);
            rightPower = rightPower + ((goalRightPower - rightPower) * ACCELERATION_MULTIPLIER);

            // Send calculated power to motors
            leftBack.setPower(leftPower);
            leftFront.setPower(leftPower);
            rightBack.setPower(rightPower);
            rightFront.setPower(rightPower);

            // Show the elapsed game time, goal wheel power, and wheel power.
            telemetry.addData("Status", "Run Time: " + elapsedTime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f), goal left (%.2f), goal right (%.2f)",
            		leftPower, rightPower, goalLeftPower, goalRightPower);
            telemetry.addData("Right encoder", rightBack.getCurrentPosition());
            telemetry.addData("Left encoder", leftBack.getCurrentPosition());
            telemetry.update();
        }
    }
}
