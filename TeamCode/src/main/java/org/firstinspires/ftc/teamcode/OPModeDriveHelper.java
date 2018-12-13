package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class OPModeDriveHelper {
	private static OPModeDriveHelper instance;
    private  OPModeConstants opModeConstants;
    private  Telemetry telemetry;
    private  HardwareMap hardwareMap;
    private OPModeGyroHelper gyroHelper;
    DcMotor leftWheel;
    DcMotor rightWheel;

    public void Init(Telemetry telemetry, HardwareMap hardwareMap) {
    	this.telemetry = telemetry;
    	this.hardwareMap = hardwareMap;
        opModeConstants = OPModeConstants.getInstance();
    	
        leftWheel = hardwareMap.dcMotor.get("left_wheel");
        rightWheel = hardwareMap.dcMotor.get("right_wheel");
    }
    
    private OPModeDriveHelper() {}
    
    public boolean MoveForward(Double inches)
}
