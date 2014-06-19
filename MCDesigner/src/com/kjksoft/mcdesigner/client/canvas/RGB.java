package com.kjksoft.mcdesigner.client.canvas;

/**
 * Simple RGB class for use with color transforms
 * @author Kevin
 *
 */
public class RGB {
	public final int value;
	
	public RGB(int value) {
		this.value = value;
	}
	
	public RGB(int r, int g, int b) {
		this.value =
				((r & 0xFF) << 16) |
                ((g & 0xFF) << 8)  |
                ((b & 0xFF) << 0);
	}
	
	public int getRed() {
        return (value >> 16) & 0xFF;
    }

    public int getGreen() {
        return (value >> 8) & 0xFF;
    }

    public int getBlue() {
        return (value >> 0) & 0xFF;
    }
}
