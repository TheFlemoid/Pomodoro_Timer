/**
 * File:      PomoTimer.java
 * Author:    Franklyn Dahlberg
 * Created:   25 October, 2025
 * Copyright: 2025 (c) Franklyn Dahlberg
 */ 
package com.tdberg.timer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URISyntaxException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Minimal audio player to play alarm tones
 */
public class AlarmPlayer {
    AlarmTone activeAlarm = AlarmTone.CLASSIC;
    Clip audioClip;
    AudioInputStream audioInputStream;

    public AlarmPlayer() {
        setActiveAlarm(AlarmTone.CLASSIC);
    }

    public void playAlarm() {
        if (this.activeAlarm == AlarmTone.MUTE) {
            return;
        }

        audioClip.setMicrosecondPosition(0);
        audioClip.start();
    }

    /**
     * Returns the active alarm tone
     */
    public AlarmTone getActiveAlarm() {
        return activeAlarm;
    }

    /**
     * Sets the active alarm tone to the param value
     *
     * @param alarmTone AlarmTone to set
     */
    public void setActiveAlarm(final AlarmTone activeAlarm) {
        this.activeAlarm = activeAlarm;

        if (this.activeAlarm == AlarmTone.MUTE) {
            return;
        }

        if (audioClip != null) {
            audioClip.stop();
            audioClip.close();
        }

        InputStream inputStream = getClass().getResourceAsStream(activeAlarm.getFilePath());

        try {
            audioInputStream = AudioSystem.getAudioInputStream(inputStream);
            audioClip = AudioSystem.getClip();
            audioClip.open(audioInputStream);
        }catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
}

