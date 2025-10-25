/**
 * File:      AlarmTone.java
 * Author:    Franklyn Dahlberg
 * Created:   25 October, 2025
 * Copyright: 2025 (c) Franklyn Dahlberg
 */ 
package com.tdberg.timer;

/**
 * Enumeration of all available alarm tones
 */
public enum AlarmTone {
    CLASSIC("sfx/classic.wav"),
    ROOSTER("sfx/rooster.wav"),
    SLOT_MACHINE("sfx/slot_machine.wav"),
    MUTE("");

    private String filepath;

    AlarmTone(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Returns this enums filepath
     *
     * @return this enums filepath
     */
    public String getFilePath() {
        return filepath;
    }
}

