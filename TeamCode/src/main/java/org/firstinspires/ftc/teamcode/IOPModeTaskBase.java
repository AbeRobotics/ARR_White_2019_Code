package org.firstinspires.ftc.teamcode;

/**
 * Created by Akanksha.Joshi on 25-Dec-2017.
 * Modified By Kyle Stang on 13-Feb-2019.
 */

public abstract class IOPModeTaskBase {
    public abstract boolean getTaskStatus();
    public abstract void performTask();
    public abstract void reset();
    public final void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public void log (String key, String value)
    {
        //todo - Implement call to telemetery so our implementers can easily log whats going on
    }
}