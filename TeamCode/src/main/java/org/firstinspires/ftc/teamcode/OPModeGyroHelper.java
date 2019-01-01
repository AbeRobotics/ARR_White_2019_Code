package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.util.ReadWriteFile;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

public class OPModeGyroHelper {
	private BNO055IMU imu;
	private Orientation angles;
	
	// returns robot angle
	public double getGyroAngle(Telemetry telemetry, HardwareMap hardwareMap, ElapsedTime elapsedTime) {
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
		telemetry.addData("Gyro Calibration: ", imu.isGyroCalibrated());
		telemetry.addData("Accel Calibration: ", imu.isAccelerometerCalibrated());
		telemetry.update();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) { }
		angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);
        double currentAngle = angles.firstAngle;
        telemetry.addData("Elapsed time: ", elapsedTime.toString());
        telemetry.addData("Gyro Angle", currentAngle);
        telemetry.update();
        return currentAngle;
	}
}
