/**
 * File:      PomoTimer.java
 * Author:    Franklyn Dahlberg
 * Created:   25 October, 2025
 * Copyright: 2025 (c) Franklyn Dahlberg
 */ 
package com.tdberg.timer;

import org.freedesktop.gstreamer.Gst;
import org.freedesktop.gstreamer.Pipeline;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
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
    private static final String PIPELINE_TEMPLATE = "filesrc location=%s ! decodebin ! audioconvert " +
                                                    "! volume volume=%s ! autoaudiosink";
    AlarmTone activeAlarm = AlarmTone.CLASSIC;
    private String jarPath;
    private String sfxPath;
    private String volume = "0.2";

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
            sfxPath = jarPath + "/../../sfx/";
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

        String pipeSpec = String.format(PIPELINE_TEMPLATE, activeAlarm.getFilename(), volume);
        Pipeline pipeline = (Pipeline) Gst.parseLaunch(pipeSpec);
        pipeline.play();

        //Thread alarmThread = new Thread(new Runnable() {
        //    @Override
        //    public void run() {
        //        InputStream inputStream = getClass().getResourceAsStream(activeAlarm.getFilePath());

        //        try {
        //            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
        //            Clip audioClip = AudioSystem.getClip();
        //            audioClip.open(audioInputStream);
        //            audioClip.setMicrosecondPosition(0);
        //            audioClip.start();
        //            Thread.sleep(6000); // Clip start is non-blocking for some silly reason
        //            audioClip.close();
        //        }catch (UnsupportedAudioFileException | LineUnavailableException |
        //                IOException | InterruptedException e) {
        //            e.printStackTrace();
        //        }
        //    }
        //});
        //alarmThread.start();
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

