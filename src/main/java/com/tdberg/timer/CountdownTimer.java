/**
 * File:      CountdownTimer.java
 * Author:    Franklyn Dahlberg
 * Created:   25 October, 2025
 * Copyright: 2025 (c) Franklyn Dahlberg
 */ 
package com.tdberg.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Settable countdown timer that can be started, stopped, and reset.
 */
public class CountdownTimer {
    //private int timerSetTimeSeconds = 1500;  // Default set time is 25 minutes
    private int timerSetTimeSeconds = 5;  // Default set time is 25 minutes
    private int remainingTime = timerSetTimeSeconds;
    private PomoTimer pomoTimer;

    private Timer timer;

    /**
     * Default constructor
     *
     * @param pomoTimer PomoPanel parent of this timer
     */
    public CountdownTimer(final PomoTimer pomoTimer) {
        this.pomoTimer = pomoTimer;
        pomoTimer.getDigitPanel().setTimeToShow(remainingTime);
    }

    /**
     * Starts the timer
     */
    public void start() {
        timer = new Timer();

        if (remainingTime == 0) {
            return;
        }

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (remainingTime > 0) {
                    remainingTime--;
                } else {
                    timer.cancel();
                    pomoTimer.timerFinished();
                }
                pomoTimer.getDigitPanel().setTimeToShow(remainingTime);
            }
        }, 0, 1000);
    }

    /**
     * Stops the timer without touching the remaining time
     */
    public void stop() {
        if (timer == null) {
            return;
        }

        timer.cancel();
        pomoTimer.getDigitPanel().setTimeToShow(remainingTime);
    }

    /**
     * Stops the timer and resets remaining time to the set time
     */
    public void reset() {
        if (timer == null) {
            return;
        }

        timer.cancel();
        remainingTime = timerSetTimeSeconds;
        pomoTimer.getDigitPanel().setTimeToShow(remainingTime);
    }

    /**
     * Returns the remaining timer time in seconds
     *
     * @return the remaining timer time in seconds
     */
    public int getRemainingTime() {
        return remainingTime;
    }

    /**
     * Sets the timerSetTime (time that the timer should start at)
     *
     * @param setTime time to set in seconds
     */
    public void setTimerSetTime(final int setTime) {
        timer.cancel();
        timerSetTimeSeconds = setTime;
        remainingTime = setTime;
    }

    /**
     * Returns the timerSetTime in seconds
     *
     * @return the timerSetTime (time that the timer should start at) in seconds
     */
    public int getTimerSetTime() {
        return timerSetTimeSeconds;
    }
}

