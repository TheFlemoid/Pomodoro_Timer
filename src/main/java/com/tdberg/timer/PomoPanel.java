/**
 * File:      PomoPanel.java
 * Author:    Franklyn Dahlberg
 * Created:   24 October, 2025
 * Copyright: 2025 (c) Franklyn Dahlberg
 */ 
package com.tdberg.timer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * JPanel that displays the timer digits
 */
public class PomoPanel extends JPanel {

    private static int IMAGE_WIDTH = 60;
    private static int IMAGE_HEIGHT = 100;
    private static int MINOR_SEP = 10;
    private static int MAJOR_SEP = 50;

    private DigitColor digitColor = DigitColor.GREEN;

    private int h0Value = 0;
    private int h1Value = 0;
    private int m0Value = 0;
    private int m1Value = 0;
    private int s0Value = 0;
    private int s1Value = 0;

    // Images for the hour, minute, and second positions
    private ImageIcon h0;
    private ImageIcon h1;
    private ImageIcon m0;
    private ImageIcon m1;
    private ImageIcon s0;
    private ImageIcon s1;

    private static final int H0_X = 10;
    private static final int H1_X = 10 + IMAGE_WIDTH + MINOR_SEP;
    private static final int M0_X = 10 + (IMAGE_WIDTH * 2) + MINOR_SEP + MAJOR_SEP;
    private static final int M1_X = 10 + (IMAGE_WIDTH * 3) + (MINOR_SEP * 2) + MAJOR_SEP;
    private static final int S0_X = 10 + (IMAGE_WIDTH * 4) + (MINOR_SEP * 2) + (MAJOR_SEP * 2);
    private static final int S1_X = 10 + (IMAGE_WIDTH * 5) + (MINOR_SEP * 3) + (MAJOR_SEP * 2);

    private static final String COLOR_PATH_TEMPLATE = "/digits/%s/%s.png";

    /**
     * Default constructor
     */    
    public PomoPanel() {
        super();
        setPreferredSize(new Dimension(500, 120));
        updateDigits();
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        g.drawImage(h0.getImage(), H0_X, 10, IMAGE_WIDTH, IMAGE_HEIGHT, null);
        g.drawImage(h1.getImage(), H1_X, 10, IMAGE_WIDTH, IMAGE_HEIGHT, null);
        g.drawImage(m0.getImage(), M0_X, 10, IMAGE_WIDTH, IMAGE_HEIGHT, null);
        g.drawImage(m1.getImage(), M1_X, 10, IMAGE_WIDTH, IMAGE_HEIGHT, null);
        g.drawImage(s0.getImage(), S0_X, 10, IMAGE_WIDTH, IMAGE_HEIGHT, null);
        g.drawImage(s1.getImage(), S1_X, 10, IMAGE_WIDTH, IMAGE_HEIGHT, null);
    }

    /**
     * Refresh the digits to the values of the global digitColor and value variables,
     * and repaint the panel.
     */
    private void updateDigits() {
        h0 = new ImageIcon(this.getClass().getResource(String.format(COLOR_PATH_TEMPLATE, digitColor.name().toLowerCase(), String.valueOf(h0Value))));
        h1 = new ImageIcon(this.getClass().getResource(String.format(COLOR_PATH_TEMPLATE, digitColor.name().toLowerCase(), String.valueOf(h1Value))));
        m0 = new ImageIcon(this.getClass().getResource(String.format(COLOR_PATH_TEMPLATE, digitColor.name().toLowerCase(), String.valueOf(m0Value))));
        m1 = new ImageIcon(this.getClass().getResource(String.format(COLOR_PATH_TEMPLATE, digitColor.name().toLowerCase(), String.valueOf(m1Value))));
        s0 = new ImageIcon(this.getClass().getResource(String.format(COLOR_PATH_TEMPLATE, digitColor.name().toLowerCase(), String.valueOf(s0Value))));
        s1 = new ImageIcon(this.getClass().getResource(String.format(COLOR_PATH_TEMPLATE, digitColor.name().toLowerCase(), String.valueOf(s1Value))));

        repaint();
    }

    /**
     * Sets the time that should be reflected on the panel
     *
     * @param showTime time to show in seconds
     */
    public void setTimeToShow(final int showTime) {
        int secondsRemaining = showTime % 60;
        int minutesRemaining = (showTime / 60) % 60;
        int hoursRemaining = showTime / 3600;

        s0Value = secondsRemaining / 10;
        s1Value = secondsRemaining % 10;
        m0Value = minutesRemaining / 10;
        m1Value = minutesRemaining % 10;
        h0Value = hoursRemaining / 10;
        h1Value = hoursRemaining % 10;

        updateDigits();
    }

    /**
     * Returns the current digit color
     *
     * @return the current digit color
     */
    public DigitColor getDigitColor() {
        return digitColor;
    }

    /**
     * Sets the digit color to the param value
     *
     * @param color DigitColor to set
     */
    public void setDigitColor(final DigitColor color) {
        digitColor = color;
        updateDigits();
    }
}
