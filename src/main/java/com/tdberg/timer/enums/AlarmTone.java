/**
 * File:      AlarmTone.java
 * Author:    Franklyn Dahlberg
 * Created:   25 October, 2025
 * Copyright: 2025 (c) Franklyn Dahlberg
 */ 
package com.tdberg.timer.enums;

/**
 * Enumeration of all available alarm tones
 */
public enum AlarmTone {
    CLASSIC("/sfx/classic.wav", "classic.wav"),
    ROOSTER("/sfx/rooster.wav", "rooster.wav"),
    SLOT_MACHINE("/sfx/slot_machine.wav", "slot_machine.wav"),
    MUTE("", "");

    private String filepath;
    private String filename;

    AlarmTone(String filepath, String filename) {
        this.filepath = filepath;
        this.filename = filename;
    }

    /**
     * Returns this enums filename.
     *
     * @return the filename as a String
     */
    public String getFilename() {
        return filename;
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

