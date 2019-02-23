package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

/**
 * Created by Kyle Stang on 15-Feb-2019
 * Adapted from Akanksha Joshi
 * and org.firstinspires.ftc
 */
public class Task_FindGold extends IOPModeTaskBase {

    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
    private static final String VUFORIA_KEY = "AfJ/NgH/////AAABmf3lER8noEwfiY17SFpoG3eMTTw5Nzdz1TM9af2/+C5KuTgWbedb9QWWBNGvtxHegA+NVoQdwpd9XPKRAXsqFoyZOg66k/wCHWZf3+IS5S126NXAJOgIwRxLdhyArNlDR4I+MKwiT3ykNQBIS752I/bKshDPjwVa7Q+PRXhKfrkYyIDwJozr8oD+nN7mZWQrW5b33RDmRvpYjTBhjppIfM603G4kCFewU8rYcqtyEWtmfQJtNXfn4AA30x9ZBa9asBgmy9hujH0Gy2J9ERUbBuI43l063ONRkHWpAwMDyIQ4z+wOBFSDCcj4AUaBUNBJzoYAUozXS+DIkmtnITrUmfZq6wkyeh63xKxU+Q5lJBfx";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    private HardwareMap hardwareMap;
    private LinearOpMode opMode;
    private OPModeConstants opModeConstants;
    private ElapsedTime elapsedTime;

    private boolean taskComplete = false;

    public Task_FindGold(LinearOpMode opMode, HardwareMap hardwareMap, OPModeConstants opModeConstants, ElapsedTime elapsedTime){
        this.opMode = opMode;
        this.hardwareMap = hardwareMap;
        this.opModeConstants = opModeConstants;
        this.elapsedTime = elapsedTime;
    }

    @Override
    public void init() {
        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            opMode.telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }
    }

    @Override
    public void performTask() {
        double startTime = elapsedTime.milliseconds();
        if (tfod != null) {
            tfod.activate();
        }
        while (!taskComplete && !opMode.isStopRequested() && elapsedTime.milliseconds() < startTime + opModeConstants.findGoldTimeMilli){
            if (tfod != null) {
                // getUpdatedRecognitions() will return null if no new information is available since
                // the last time that call was made.
                List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                if (updatedRecognitions != null) {
                    opMode.telemetry.addData("# Object Detected", updatedRecognitions.size());
                    if (updatedRecognitions.size() == 3) {
                        int goldMineralX = -1;
                        int silverMineral1X = -1;
                        int silverMineral2X = -1;
                        for (Recognition recognition : updatedRecognitions) {
                            if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                                goldMineralX = (int) recognition.getLeft();
                            } else if (silverMineral1X == -1) {
                                silverMineral1X = (int) recognition.getLeft();
                            } else {
                                silverMineral2X = (int) recognition.getLeft();
                            }
                        }
                        if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                            if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                                opMode.telemetry.addData("Gold Mineral Position", "Left");
                                opModeConstants.setGoldLocation(OPModeConstants.GoldLocation.LEFT);
                            } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                                opMode.telemetry.addData("Gold Mineral Position", "Right");
                                opModeConstants.setGoldLocation(OPModeConstants.GoldLocation.RIGHT);
                            } else {
                                opMode.telemetry.addData("Gold Mineral Position", "Center");
                                opModeConstants.setGoldLocation(OPModeConstants.GoldLocation.MIDDLE);
                            }
                            taskComplete = true;
                        }
                    }
                    opMode.telemetry.update();
                }
            }
        }

        if (tfod != null) {
        tfod.shutdown();
        }
    }


    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    }

    private void initTfod(){
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }

    @Override
    public boolean getTaskStatus(){
        return taskComplete;
    }

    @Override
    public void reset(){
        taskComplete = false;
    }
}
