/**
 * File:      CountdownTimer.java
 * Author:    Franklyn Dahlberg
 * Created:   25 October, 2025
 * Copyright: 2025 (c) Franklyn Dahlberg
 */ 
package com.tdberg.timer;

import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimer {
    private int timerSetTimeSeconds = 1500;  // Default set time is 25 minutes
    private int remainingTime = timerSetTimeSeconds;
    private PomoPanel parent;

    private Timer timer;

    /**
     * Default constructor
     *
     * @param parent PomoPanel parent of this timer that should reflect the time
     */
    public CountdownTimer(final PomoPanel parent) {
        this.parent = parent;

        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (remainingTime >= 0) {
                    System.out.println(String.valueOf(remainingTime));
                    remainingTime--;
                } else {
                    timer.cancel();
                }
            }
        }, 0, 1000);
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

