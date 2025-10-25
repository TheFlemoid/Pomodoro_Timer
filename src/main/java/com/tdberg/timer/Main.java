/**
 * File:      Main.java
 * Author:    Franklyn Dahlberg
 * Created:   24 October, 2025
 * Copyright: 2025 (c) Franklyn Dahlberg
 */ 
package com.tdberg.timer;

import com.formdev.flatlaf.FlatDarculaLaf;

/**
 * Application main
 */
public class Main {

    /**
     * Application main method
     *
     * @param args String array of arguments to this runtime
     */
    public static void main(String[] args) {
        FlatDarculaLaf.setup();
        
        PomoTimer pomoTimer = new PomoTimer();
    }
}
