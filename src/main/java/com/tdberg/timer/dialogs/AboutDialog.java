/**
 * File:      AboutDialog.java
 * Author:    Franklyn Dahlberg
 * Created:   29 October, 2025
 * Copyright: 2025 (c) Franklyn Dahlberg
 */
package com.tdberg.timer.dialogs;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Incorporates an `About` dialog, with information about this application
 */
public class AboutDialog extends JDialog {
    final static int ABOUT_DIALOG_WIDTH = 340;
    final static int ABOUT_DIALOG_HEIGHT = 135;
    final static String ABOUT_STRING = "<html><b><center><u>Pomodoro Timer v1.0.0</u>" +
                                      "<br>Made By: Franklyn Dahlberg in October, 2025." +
                                      "<br>GitHub: TheFlemoid" +
                                      "<br>License: MIT";

    /**
     * Default constructor
     *
     * @param owner JFrame owner of this dialog
     */
    public AboutDialog(final JFrame owner) {
        super(owner, "About", true);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        JLabel aboutLabel = new JLabel(ABOUT_STRING, SwingConstants.CENTER);
        add(aboutLabel, BorderLayout.CENTER);

        setSize(ABOUT_DIALOG_WIDTH, ABOUT_DIALOG_HEIGHT);
        setResizable(false);
        setVisible(true);
    }
}
