/**
 * File:      PomoTimer.java
 * Author:    Franklyn Dahlberg
 * Created:   24 October, 2025
 * Copyright: 2025 (c) Franklyn Dahlberg
 */ 
package com.tdberg.timer;

import com.tdberg.timer.alarm.AlarmPlayer;
import com.tdberg.timer.dialogs.AboutDialog;
import com.tdberg.timer.dialogs.SetTimerDialog;
import com.tdberg.timer.dialogs.SetVolumeDialog;
import com.tdberg.timer.enums.AlarmTone;
import com.tdberg.timer.enums.DigitColor;
import com.tdberg.timer.enums.TimerType;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

/**
 * Parent object of timer application.  Extends JFrame.
 */
public class PomoTimer extends JFrame implements ActionListener {

    private static final int FRAME_WIDTH = 510;
    private static final int FRAME_HEIGHT = 220;

    private int workTimeSeconds = 1500;
    private int breakTimeSeconds = 300;
    private TimerType timerType = TimerType.POMO;
    private boolean onBreak = false;

    SetTimerDialog setTimerDialog;
    SetVolumeDialog setVolumeDialog;
    AboutDialog aboutDialog;

    BorderLayout borderLayout = new BorderLayout();
    DigitPanel digitPanel = new DigitPanel();
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
    JMenuItem exitItem, setTimeItem, aboutItem, setVolumeItem;
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
        countdownTimer.setTimerSetTime(workTimeSeconds);

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
        setVolumeItem = new JMenuItem("Set Volume");
        exitItem.addActionListener(this);
        setTimeItem.addActionListener(this);
        aboutItem.addActionListener(this);
        setVolumeItem.addActionListener(this);

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
        alarmMenu.addSeparator();
        alarmMenu.add(setVolumeItem);

        // Setup color options
        blue = new JRadioButtonMenuItem("Blue");
        green = new JRadioButtonMenuItem("Green");
        orange = new JRadioButtonMenuItem("Orange");
        pink = new JRadioButtonMenuItem("Pink");
        red = new JRadioButtonMenuItem("Red");
        green.setSelected(true);

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
                setTimerDialog = new SetTimerDialog(this, workTimeSeconds, breakTimeSeconds);
                setTimerDialog.setVisible(true);
                break;
            case "About":
                aboutDialog = new AboutDialog(this);
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
            case "Mute":
                alarmPlayer.setActiveAlarm(AlarmTone.MUTE);
                break;
            case "Slot Machine":
                alarmPlayer.setActiveAlarm(AlarmTone.SLOT_MACHINE);
                break;
            case "Set Volume":
                setVolumeDialog = new SetVolumeDialog(this, alarmPlayer);
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
                timerType = TimerType.POMO;
                countdownTimer.stop();
                countdownTimer.setTimerSetTime(workTimeSeconds);
                countdownTimer.reset();
                startPauseButton.setText("Start");
                break;
            case "Timer":
                timerType = TimerType.TIMER;
                countdownTimer.stop();
                countdownTimer.setTimerSetTime(workTimeSeconds);
                countdownTimer.reset();
                startPauseButton.setText("Start");
                break;
            case "Exit":
                System.exit(0);
                break;
            default:
                break;
        }
    }

    /**
     * Called when the timer completes it's countdown to zero
     */
    public void timerFinished() {
        if (alarmPlayer.getActiveAlarm() != AlarmTone.MUTE) {
            alarmPlayer.playAlarm();
        }

        if (timerType == TimerType.POMO) {
            if (!onBreak) {
                onBreak = true;
                countdownTimer.setTimerSetTime(breakTimeSeconds);
            } else {
                onBreak = false;
                countdownTimer.setTimerSetTime(workTimeSeconds);
            }
        }else {
            countdownTimer.setTimerSetTime(workTimeSeconds);
        }

        startPauseButton.setText("Start");
        countdownTimer.reset();
    }

    /**
     * Returns the digit JPanel for this runtime
     */
    public DigitPanel getDigitPanel() {
        return digitPanel;
    }
}
