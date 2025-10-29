/**
 * File:      SetTimerDialog.java
 * Author:    Franklyn Dahlberg
 * Created:   29 October, 2025
 * Copyright: 2025 (c) Franklyn Dahlberg
 */
package com.tdberg.timer.dialogs;

import com.tdberg.timer.alarm.AlarmPlayer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JSlider;

/**
 * Incorporates a "Set Volume" dialog, used to set the alarm tone
 * volume for the application.
 */
public class SetVolumeDialog extends JDialog {
    private static final int MIN_VOL = 0;
    private static final int MAX_VOL = 100;

    JSlider volumeSlider;
    JButton confirmButton;

    final int volumeDialogWidth = 310;
    final int volumeDialogHeight = 140;

    /**
     * Default constructor
     *
     * @param owner JFrame owner of this dialog
     * @param alarmPlayer AlarmPlayer object to set the volume of
     */
    public SetVolumeDialog(final JFrame owner, final AlarmPlayer alarmPlayer) {
        super(owner, "Set Volume", true);

        JDialog volumeDialog = this;
        BorderLayout borderLayout = new BorderLayout();
        setLocationRelativeTo(owner);
        setLayout(borderLayout);

        // Volume is stored in the alarm player as a float, as Gstreamer needs
        // this for the volume plugin, but we'd like to show it as a % to the
        // user.  Hence the conversion here.
        float currentVolume = alarmPlayer.getVolume() * 100;
        int volumeInt = Math.round(currentVolume);

        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final float volumeToSet = volumeSlider.getValue() / 100f;
                alarmPlayer.setVolume(volumeToSet);
                volumeDialog.dispose();
            }
        });

        volumeSlider = new JSlider(JSlider.HORIZONTAL, MIN_VOL, MAX_VOL, volumeInt);
        volumeSlider.setMajorTickSpacing(10);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);

        add(volumeSlider, BorderLayout.CENTER);
        add(confirmButton, BorderLayout.SOUTH);

        setSize(volumeDialogWidth, volumeDialogHeight);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
