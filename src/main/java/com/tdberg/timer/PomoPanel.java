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

public class PomoPanel extends JPanel {

    private static int IMAGE_WIDTH = 60;
    private static int IMAGE_HEIGHT = 100;
    private static int MINOR_SEP = 10;
    private static int MAJOR_SEP = 50;

    private Color digitColor = Color.RED;

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

    private String colorPathTemplate = "/digits/%s/%s.png";

    /**
     * Default constructor
     */    
    public PomoPanel() {
        super();

        h0 = new ImageIcon(this.getClass().getResource(String.format(colorPathTemplate, "red", "0")));
        h1 = new ImageIcon(this.getClass().getResource(String.format(colorPathTemplate, "red", "0")));
        m0 = new ImageIcon(this.getClass().getResource(String.format(colorPathTemplate, "red", "0")));
        m1 = new ImageIcon(this.getClass().getResource(String.format(colorPathTemplate, "red", "0")));
        s0 = new ImageIcon(this.getClass().getResource(String.format(colorPathTemplate, "red", "0")));
        s1 = new ImageIcon(this.getClass().getResource(String.format(colorPathTemplate, "red", "0")));

        setPreferredSize(new Dimension(500, 120));
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
     * Returns the current digit color
     *
     * @return the current digit color
     */
    public Color getDigitColor() {
        return digitColor;
    }

    /**
     * Sets the digit color to the param value
     *
     * @param Color to set
     */
    public void setDigitColor(final Color color) {
        digitColor = color;
    }
}
