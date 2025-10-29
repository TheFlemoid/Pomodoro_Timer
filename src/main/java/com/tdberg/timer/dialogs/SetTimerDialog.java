/**
 * File:      SetTimerDialog.java
 * Author:    Franklyn Dahlberg
 * Created:   29 October, 2025
 * Copyright: 2025 (c) Franklyn Dahlberg
 */
package com.tdberg.timer.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Contains the "Set Timer" dialog, that allows the user to
 * set the "Work" and "Rest" timers for the Pomodoro Timer.
 */
public class SetTimerDialog extends JDialog {

    JTextField workHoursTextField;
    JTextField workMinutesTextField;
    JTextField workSecondsTextField;
    JTextField breakHoursTextField;
    JTextField breakMinutesTextField;
    JTextField breakSecondsTextField;

    JButton confirmButton;

    JLabel hoursLabel = new JLabel("Hours:");
    JLabel minutesLabel = new JLabel("Minutes:");
    JLabel secondsLabel = new JLabel("Seconds:");

    final int workTimerSeconds;
    final int breakTimerSeconds;

    final int initWorkTimerHours;
    final int initWorkTimerMinutes;
    final int initWorkTimerSeconds;
    final int initBreakTimerHours;
    final int initBreakTimerMinutes;
    final int initBreakTimerSeconds;

    /**
     * Default constructor
     *
     * @param owner JFrame owner of this dialog
     */
    public SetTimerDialog(final JFrame owner, final int workTimerSeconds,
                          final int breakTimerSeconds) {

        super(owner, "Set Timer", true);
        this.workTimerSeconds = workTimerSeconds;
        this.breakTimerSeconds = breakTimerSeconds;

        initWorkTimerSeconds = workTimerSeconds % 60;
        initWorkTimerMinutes = (workTimerSeconds / 60) % 60;
        initWorkTimerHours = workTimerSeconds / 3600;
        initBreakTimerSeconds = breakTimerSeconds % 60;
        initBreakTimerMinutes = (breakTimerSeconds / 60) % 60;
        initBreakTimerHours = breakTimerSeconds / 3600;

        initializeUI();
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(owner);
    }

    /**
     * Initialize all UI elements
     */
    private void initializeUI() {
        // Main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Create timer panels
        JPanel timersPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        timersPanel.add(createWorkTimerPanel());
        timersPanel.add(createBreakTimerPanel());

        // Confirmation button
        confirmButton = new JButton("Confirm");
        confirmButton.setPreferredSize(new Dimension(100, 35));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(confirmButton);

        // Add to main panel
        mainPanel.add(timersPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
    }

    /**
     * Create the work timer JPanel (sub to the main JPanel)
     *
     * @return the work timer JPanel
     */
    private JPanel createWorkTimerPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Work Timer"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;

        hoursLabel = new JLabel("Hours:");
        minutesLabel = new JLabel("Minutes:");
        secondsLabel = new JLabel("Seconds:");

        workHoursTextField = new JTextField(String.valueOf(initWorkTimerHours), 3);
        workMinutesTextField = new JTextField(String.valueOf(initWorkTimerMinutes), 3);
        workSecondsTextField = new JTextField(String.valueOf(initWorkTimerSeconds), 3);

        // Row layout: Hours, Minutes, Seconds horizontally
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(hoursLabel, gbc);

        gbc.gridx = 1;
        panel.add(workHoursTextField, gbc);

        gbc.gridx = 2;
        panel.add(minutesLabel, gbc);

        gbc.gridx = 3;
        panel.add(workMinutesTextField, gbc);

        gbc.gridx = 4;
        panel.add(secondsLabel, gbc);

        gbc.gridx = 5;
        panel.add(workSecondsTextField, gbc);

        return panel;
    }

    /**
     * Create the break (rest) JPanel (sub to the main JPanel)
     *
     * @return the break (rest) timer JPanel
     */
    private JPanel createBreakTimerPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Rest Timer"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;

        hoursLabel = new JLabel("Hours:");
        minutesLabel = new JLabel("Minutes:");
        secondsLabel = new JLabel("Seconds:");

        breakHoursTextField = new JTextField(String.valueOf(initBreakTimerHours), 3);
        breakMinutesTextField = new JTextField(String.valueOf(initBreakTimerMinutes), 3);
        breakSecondsTextField = new JTextField(String.valueOf(initBreakTimerSeconds), 3);

        // Row layout: Hours, Minutes, Seconds horizontally
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(hoursLabel, gbc);

        gbc.gridx = 1;
        panel.add(breakHoursTextField, gbc);

        gbc.gridx = 2;
        panel.add(minutesLabel, gbc);

        gbc.gridx = 3;
        panel.add(breakMinutesTextField, gbc);

        gbc.gridx = 4;
        panel.add(secondsLabel, gbc);

        gbc.gridx = 5;
        panel.add(breakSecondsTextField, gbc);

        return panel;
    }
}
