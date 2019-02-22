package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

// Gets orientation using gyrosocope on robot controller phone
@TeleOp(name="PhoneGyroTest", group="Linear Opmode")
public class PhoneGyroTest extends LinearOpMode{

    OPModeGyroHelperV2 gyroHelper = new OPModeGyroHelperV2();

    // Express the updated rotation matrix as three orientation angles.
    float[] orientation = new float[3];

    @Override
    public void runOpMode(){
        while (!isStopRequested()) {
            orientation = gyroHelper.getPhoneOrientation();
            telemetry.addData("Z axis:", orientation[0]);
            telemetry.addData("X axis:", orientation[1]);
            telemetry.addData("Y axis:", orientation[2]);
            sleep(100);
        }
    }
}
