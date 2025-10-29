/**
 * File:      PomoTimer.java
 * Author:    Franklyn Dahlberg
 * Created:   25 October, 2025
 * Copyright: 2025 (c) Franklyn Dahlberg
 */ 
package com.tdberg.timer.alarm;

import com.tdberg.timer.enums.AlarmTone;
import org.freedesktop.gstreamer.Gst;
import org.freedesktop.gstreamer.Pipeline;

import java.io.File;
import java.net.URISyntaxException;

/**
 * Minimal audio player to play alarm tones.
 * NOTE: Currently having big problems getting Java to play out of bluetooth devices,
 *       probably need to replace this with gstreamer.
 */
public class AlarmPlayer {
    private static final String PIPELINE_TEMPLATE = "filesrc location=%s ! decodebin ! audioconvert " +
                                                    "! volume volume=%s ! autoaudiosink";
    AlarmTone activeAlarm = AlarmTone.CLASSIC;
    private String jarPath;
    private String sfxPath;
    private float volume = 0.1f;

    /**
     * Default constructor
     */
    public AlarmPlayer() {
        Gst.init();

        try {
            // Don't know of a way of playing files with Gst that are inside the JAR file
            // so right now we include them with the distribution, and then locate them by
            // going off of the absolute path of the JAR file.  This is gross and I'd like
            // to fix it, but not sure how right now.
            // This also means that running the applicaiton with gradle run can't play
            // alerts, as the pathing works differently then when it's packaged for dist.
            jarPath = new File(AlarmPlayer.class.getProtectionDomain()
                    .getCodeSource().getLocation().toURI()).getPath();

            // Getting the path to the JAR file (lopping off the JAR filename)
            String[] jarPathArray = jarPath.split("/");
            jarPath = "";
            for (int i = 0; i < jarPathArray.length - 1; i++) {
                jarPath = jarPath + jarPathArray[i] + "/";
            }

            sfxPath = jarPath + "../sfx/";
        }catch (URISyntaxException e) {
            System.out.println("URISyntaxException when getting JAR path.");
            e.printStackTrace();
        }

        setActiveAlarm(AlarmTone.CLASSIC);
    }

    public void playAlarm() {
        if (this.activeAlarm == AlarmTone.MUTE) {
            return;
        }

        String volumeString = String.format(java.util.Locale.US, "%.2f", volume); 

        String filePath = sfxPath + activeAlarm.getFilename();
        String pipeSpec = String.format(PIPELINE_TEMPLATE, filePath, volumeString);
        Pipeline pipeline = (Pipeline) Gst.parseLaunch(pipeSpec);
        pipeline.play();
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

    public float getVolume() {
        return volume;
    }

    public void setVolume(final float volume) {
        this.volume = volume;
    }
}

