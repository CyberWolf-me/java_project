package com.colorhunt.model;

import javafx.scene.paint.Color;

/**
 * Color constants and data for the game
 */
public class ColorData {
    public static final String[] COLOR_NAMES = {"RED", "BLUE", "GREEN", "YELLOW", "PURPLE", "ORANGE"};
    public static final Color[] COLORS = {
        Color.RED, Color.BLUE, Color.GREEN, 
        Color.YELLOW, Color.PURPLE, Color.ORANGE
    };
    
    /**
     * Get color name from Color object
     */
    public static String getColorName(Color color) {
        for (int i = 0; i < COLORS.length; i++) {
            if (COLORS[i].equals(color)) {
                return COLOR_NAMES[i];
            }
        }
        return "UNKNOWN";
    }
    
    /**
     * Get random color
     */
    public static Color getRandomColor() {
        return COLORS[(int) (Math.random() * COLORS.length)];
    }
    
    /**
     * Get random color name
     */
    public static String getRandomColorName() {
        return COLOR_NAMES[(int) (Math.random() * COLOR_NAMES.length)];
    }
}

