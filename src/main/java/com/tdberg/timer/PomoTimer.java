/**
 * File:      PomoTimer.java
 * Author:    Franklyn Dahlberg
 * Created:   24 October, 2025
 * Copyright: 2025 (c) Franklyn Dahlberg
 */ 
package com.tdberg.timer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingConstants;

/**
 * Parent object of timer application.  Extends JFrame.
 */
public class PomoTimer extends JFrame implements ActionListener {

    private static int FRAME_WIDTH = 510;
    private static int FRAME_HEIGHT = 220;

    BorderLayout borderLayout = new BorderLayout();
    PomoPanel digitPanel = new PomoPanel();
    JPanel buttonPanel = new JPanel();
    CountdownTimer countdownTimer = new CountdownTimer(this);
    AlarmPlayer alarmPlayer = new AlarmPlayer();

    JButton startPauseButton = new JButton("Start");
    JButton resetButton = new JButton("Reset");

    JMenu timerMenu = new JMenu("Timer");
    JMenu colorMenu = new JMenu("Color");
    JMenu modeMenu = new JMenu("Mode");
    JMenu alarmMenu = new JMenu("Alarm");
    JMenuBar menuBar = new JMenuBar();
    JMenuItem exitItem, setTimeItem, aboutItem;
    JRadioButtonMenuItem blue, green, orange, pink, red;
    JRadioButtonMenuItem pomoMode, timerMode;
    JRadioButtonMenuItem classic, rooster, slotMachine, mute;
    ButtonGroup colorOptionGroup;
    ButtonGroup alarmToneOptionGroup;
    ButtonGroup modeOptionGroup;

    /**
     * Default constructor
     */
    public PomoTimer() {
        super("Pomodoro Timer");

        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(borderLayout);

        initializeFrameElements();

        this.setVisible(true);
    }

    /**
     * Sets up and initializes all the UI components within the JFrame.
     */
    private void initializeFrameElements() {
        // Setup digit panel
        this.getContentPane().add(digitPanel, BorderLayout.NORTH);

        // Setup bottom buttons
        startPauseButton.addActionListener(this);
        resetButton.addActionListener(this);
        buttonPanel.add(startPauseButton);
        buttonPanel.add(resetButton);
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Setup menu bar
        exitItem = new JMenuItem("Exit");
        setTimeItem = new JMenuItem("Set Time");
        aboutItem = new JMenuItem("About");
        exitItem.addActionListener(this);
        setTimeItem.addActionListener(this);
        aboutItem.addActionListener(this);

        // Setup mode options
        pomoMode = new JRadioButtonMenuItem("Pomodoro");
        timerMode = new JRadioButtonMenuItem("Timer");

        pomoMode.addActionListener(this);
        timerMode.addActionListener(this);
        pomoMode.setSelected(true);

        modeOptionGroup = new ButtonGroup();
        modeOptionGroup.add(pomoMode);
        modeOptionGroup.add(timerMode);

        modeMenu.add(pomoMode);
        modeMenu.add(timerMode);

        // Setup alarm tone options
        classic = new JRadioButtonMenuItem("Classic");
        rooster = new JRadioButtonMenuItem("Rooster");
        slotMachine = new JRadioButtonMenuItem("Slot Machine");
        mute = new JRadioButtonMenuItem("Mute");
        classic.setSelected(true);

        classic.addActionListener(this);
        rooster.addActionListener(this);
        slotMachine.addActionListener(this);
        mute.addActionListener(this);

        alarmToneOptionGroup = new ButtonGroup();
        alarmToneOptionGroup.add(classic);
        alarmToneOptionGroup.add(rooster);
        alarmToneOptionGroup.add(slotMachine);
        alarmToneOptionGroup.add(mute);

        alarmMenu.add(classic);
        alarmMenu.add(rooster);
        alarmMenu.add(slotMachine);
        alarmMenu.add(mute);

        // Setup color options
        blue = new JRadioButtonMenuItem("Blue");
        green = new JRadioButtonMenuItem("Green");
        orange = new JRadioButtonMenuItem("Orange");
        pink = new JRadioButtonMenuItem("Pink");
        red = new JRadioButtonMenuItem("Red");
        red.setSelected(true);

        blue.addActionListener(this);
        green.addActionListener(this);
        orange.addActionListener(this);
        pink.addActionListener(this);
        red.addActionListener(this);

        colorOptionGroup = new ButtonGroup();
        colorOptionGroup.add(red);
        colorOptionGroup.add(blue);
        colorOptionGroup.add(green);
        colorOptionGroup.add(orange);
        colorOptionGroup.add(pink);

        colorMenu.add(red);
        colorMenu.add(blue);
        colorMenu.add(green);
        colorMenu.add(orange);
        colorMenu.add(pink);

        timerMenu.add(setTimeItem);
        timerMenu.add(aboutItem);
        timerMenu.addSeparator();
        timerMenu.add(exitItem);

        menuBar.add(timerMenu);
        menuBar.add(modeMenu);
        menuBar.add(alarmMenu);
        menuBar.add(colorMenu);
        this.setJMenuBar(menuBar);
    }

    /**
     * Implementation of ActionListener for UI components
     * NOTE: Having every UI component run through this single listener
     *       is very gross and should probably be fixed, but this is
     *       quick and dirty.
     *
     * @param event ActionEvent that called this handler
     */
    public void actionPerformed(ActionEvent event) {
        String action = event.getActionCommand();

        switch(action) {
            case "Set Time":
                break;
            case "About":
                showAboutDialog();
                break;
            case "Start":
                startPauseButton.setText("Pause");
                countdownTimer.start();
                break;
            case "Pause":
                startPauseButton.setText("Start");
                countdownTimer.stop();
                break;
            case "Reset":
                startPauseButton.setText("Start");
                countdownTimer.reset();
                break;
            case "Classic":
                alarmPlayer.setActiveAlarm(AlarmTone.CLASSIC);
                break;
            case "Rooster":
                alarmPlayer.setActiveAlarm(AlarmTone.ROOSTER);
                break;
            case "Slot Machine":
                alarmPlayer.setActiveAlarm(AlarmTone.SLOT_MACHINE);
                break;
            case "Mute":
                alarmPlayer.setActiveAlarm(AlarmTone.MUTE);
                break;
            case "Blue":
                digitPanel.setDigitColor(DigitColor.BLUE);
                break;
            case "Green":
                digitPanel.setDigitColor(DigitColor.GREEN);
                break;
            case "Orange":
                digitPanel.setDigitColor(DigitColor.ORANGE);
                break;
            case "Pink":
                digitPanel.setDigitColor(DigitColor.PINK);
                break;
            case "Red":
                digitPanel.setDigitColor(DigitColor.RED);
                break;
            case "Pomodoro":
                break;
            case "Timer":
                break;
            case "Exit":
                System.exit(0);
                break;
            default:
                break;
        }

        System.out.println(action);
    }

    public void timerFinished() {
        if (alarmPlayer.getActiveAlarm() != AlarmTone.MUTE) {
            alarmPlayer.playAlarm();
        }

        startPauseButton.setText("Start");
        countdownTimer.reset();
    }

    /**
     * Displays the 'About' dialog
     */
    private void showAboutDialog() {
        final int aboutDialogWidth = 310;
        final int aboutDialogHeight = 130;

        JDialog aboutDialog = new JDialog(this, "About");

        String aboutString = "<html><center>Pomodoro Timer v1.0.0" +
                              "<br>Made by Franklyn Dahlberg in October, 2025." +
                              "<br>GitHub: TheFlemoid" +
                              "<br>License: MIT";

        JLabel aboutLabel = new JLabel(aboutString, SwingConstants.CENTER);

        aboutDialog.add(aboutLabel);

        aboutDialog.setSize(aboutDialogWidth, aboutDialogHeight);
        aboutDialog.setResizable(false);
        aboutDialog.setVisible(true);
    }

    public PomoPanel getDigitPanel() {
        return digitPanel;
    }
}
