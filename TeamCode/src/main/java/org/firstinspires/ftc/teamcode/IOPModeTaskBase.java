package org.firstinspires.ftc.teamcode;

public abstract class IOPModeTaskBase {
    public abstract void init();
    public abstract void performTask();
    public abstract boolean getTaskStatus();
    public abstract void reset();
    public final void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
