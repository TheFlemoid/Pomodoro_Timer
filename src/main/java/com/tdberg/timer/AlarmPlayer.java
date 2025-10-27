/**
 * File:      PomoTimer.java
 * Author:    Franklyn Dahlberg
 * Created:   25 October, 2025
 * Copyright: 2025 (c) Franklyn Dahlberg
 */ 
package com.tdberg.timer;

import org.freedesktop.gstreamer.Gst;
import org.freedesktop.gstreamer.Pipeline;

import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Minimal audio player to play alarm tones.
 * NOTE: Currently having big problems getting Java to play out of bluetooth devices,
 *       probably need to replace this with gstreamer.
 */
public class AlarmPlayer {
    AlarmTone activeAlarm = AlarmTone.CLASSIC;

    public AlarmPlayer() {
        setActiveAlarm(AlarmTone.CLASSIC);
    }

    public void playAlarm() {
        if (this.activeAlarm == AlarmTone.MUTE) {
            return;
        }

        Thread alarmThread = new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream inputStream = getClass().getResourceAsStream(activeAlarm.getFilePath());

                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
                    Clip audioClip = AudioSystem.getClip();
                    audioClip.open(audioInputStream);
                    audioClip.setMicrosecondPosition(0);
                    audioClip.start();
                    Thread.sleep(6000); // Clip start is non-blocking for some silly reason
                    audioClip.close();
                }catch (UnsupportedAudioFileException | LineUnavailableException |
                        IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        alarmThread.start();
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
    }
}

