package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Test Op Mode", group="Linear Opmode")
//@Disabled
public class TestOpMode extends LinearOpMode {
	
	// Constants
	// Use speed multiplier to set top speed, 1 means no change
	private double SPEED_MULTIPLIER = 0.75;
	// Sets how quickly the power to the wheels changes as a percent of the difference goal speed and current speed (max 1)
	private double ACCELERATION_MULTIPIER = (1/60);

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftDrive  = hardwareMap.get(DcMotor.class, "left_wheel");
        rightDrive = hardwareMap.get(DcMotor.class, "right_wheel");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double goalLeftPower;
            double goalRightPower;
            
            double leftPower;
            double rightPower;

            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            goalLeftPower  = -gamepad1.left_stick_y * SPEED_MULTIPLIER;
            goalRightPower = -gamepad1.right_stick_y * SPEED_MULTIPLIER;
            
            //Set the wheel power to the difference between the desired and actual power times the acceleration multiplier.
            leftPower = leftPower + ((goalLeftPower - leftPower) * ACCELERATION_MULTIPLIER);
            rightPower = rightPower + ((goalRightPower - rightPower) * ACCELERATION_MULTIPLIER);

            // Send calculated power to wheels
            leftDrive.setPower(rightPower);
            rightDrive.setPower(leftPower);

            // Show the elapsed game time, goal wheel power, and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f), goal left (%.2f), goal right (%.2f)",
            		leftPower, rightPower, goalLeftPower, goalRightPower);
            telemetry.update();
        }
    }
}
