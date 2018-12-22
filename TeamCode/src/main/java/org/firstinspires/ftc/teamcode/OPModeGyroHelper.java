package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class OPModeGyroHelper {
	private BNO055IMU imu;
	private Orientation angles;
	
	public double getGyroAngle(Telemetry telemetry, HardwareMap hardwareMap) {
		if(imu == null) {
			BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
			parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
			parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
			parameters.calibrationDataFile = "BNO055IMUCalibration.json";
			parameters.loggingEnabled = true;
            parameters.loggingTag = "Gyro";
            parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
            
            //sensor type is ADA Fruit
            // name it 'Gyro' on hardware config
            imu = hardwareMap.get(BNO055IMU.class, "Gyro");
            imu.initialize(parameters);
		}
	}
}
