package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Kyle Stang on 24-Oct-2018
 */
@TeleOp(name="Advanced Tele Op", group="Linear Opmode")
public class Abe_Teleop_Main extends LinearOpMode {
	
	// Constants
	// Use speed multiplier to set top speed, 1 means no change
	private double SPEED_MULTIPLIER = 0.75;
	private  double ARM_MULTIPLIER = 0.3;
    // Sets how quickly the power to the wheels changes as a percent of the difference goal speed and current speed (max 1)
	private double ACCELERATION_MULTIPLIER = (0.5);

    // Declare OpMode members.
    private OPModeConstants opModeConstants = OPModeConstants.getInstance();
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor armDrive = null;
    private DcMotor collectionDrive = null;
//    private Task_RaiseArm raiseArm = new Task_RaiseArm(hardwareMap,this, opModeConstants);
//    private Task_LowerArm lowerArm = new Task_LowerArm(hardwareMap,this, opModeConstants);

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftDrive  = hardwareMap.get(DcMotor.class, "left_wheel");
        rightDrive = hardwareMap.get(DcMotor.class, "right_wheel");
        armDrive = hardwareMap.get(DcMotor.class, "arm");
        collectionDrive = hardwareMap.get(DcMotor.class, "collector");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        armDrive.setDirection(DcMotor.Direction.FORWARD);
        collectionDrive.setDirection(DcMotor.Direction.FORWARD);
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        collectionDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        collectionDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        collectionDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

//        raiseArm.init();
//        lowerArm.init();


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        double armPower = 0;
        double leftPower = 0;
        double rightPower = 0;
        double collectorPower = 0;



        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double goalLeftPower;
            double goalRightPower;
            


            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            goalLeftPower  = gamepad1.left_stick_y * SPEED_MULTIPLIER;
            goalRightPower = gamepad1.right_stick_y * SPEED_MULTIPLIER;
            armPower = (gamepad1.right_trigger - gamepad1.left_trigger) * ARM_MULTIPLIER;
            
            //Set the wheel power to the difference between the desired and actual power times the acceleration multiplier.
            leftPower = leftPower + ((goalLeftPower - leftPower) * ACCELERATION_MULTIPLIER);
            rightPower = rightPower + ((goalRightPower - rightPower) * ACCELERATION_MULTIPLIER);

            if(gamepad1.dpad_up){
 //               raiseArm.performTask();
            }

            if(gamepad1.dpad_down){
//                lowerArm.performTask();
            }

            if(gamepad1.b){
                collectorPower = 0;
            }

            if(gamepad1.a){
                collectorPower = 0.75;
            }

            if(gamepad1.y){
                collectorPower = -0.75;
            }

            // Send calculated power to wheels
            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);
            armDrive.setPower(armPower);
            collectionDrive.setPower(collectorPower);

            // Show the elapsed game time, goal wheel power, and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f), goal left (%.2f), goal right (%.2f)",
            		leftPower, rightPower, goalLeftPower, goalRightPower);
            telemetry.addData("Right encoder", rightDrive.getCurrentPosition());
            telemetry.addData("Left encoder", leftDrive.getCurrentPosition());
            telemetry.addData("arm encoder", armDrive.getCurrentPosition());
            telemetry.update();
        }
    }
}
