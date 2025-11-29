package com.colorhunt.util;

import javafx.scene.paint.Color;

/**
 * Utility class for color operations
 */
public class ColorUtils {
    
    /**
     * Convert Color to hex string
     */
    public static String toHexString(Color color) {
        return String.format("#%02X%02X%02X",
            (int) (color.getRed() * 255),
            (int) (color.getGreen() * 255),
            (int) (color.getBlue() * 255)
        );
    }
    
    /**
     * Darken a hex color for border
     */
    public static String darkenColor(String hex) {
        // Simple darkening by reducing RGB values
        hex = hex.substring(1); // Remove #
        int r = Integer.parseInt(hex.substring(0, 2), 16);
        int g = Integer.parseInt(hex.substring(2, 4), 16);
        int b = Integer.parseInt(hex.substring(4, 6), 16);
        
        r = Math.max(0, r - 40);
        g = Math.max(0, g - 40);
        b = Math.max(0, b - 40);
        
        return String.format("#%02X%02X%02X", r, g, b);
    }
}

