package com.mcdraftingtable.bannerbuilder.client.color;

import com.google.gwt.user.client.Random;

public enum DyeColor {
	BLACK(0,0,0),
	RED(153,51,51),
	GREEN(102,127,51),
	BROWN(102,76,51),
	BLUE(51,76,178),
	PURPLE(127,63,178),
	CYAN(76,127,153),
	LIGHT_GRAY(153,153,153),
	GRAY(76,76,76),
	PINK(242,127,165),
	LIME(127,204,25),
	YELLOW(229,229,51),
	LIGHT_BLUE(102,153,216),
	MAGENTA(178,76,216),
	ORANGE(216, 127, 51),
	WHITE(255, 255, 255),
	;
	
	public static DyeColor random() {
		DyeColor[] colors = DyeColor.values();
		return colors[Random.nextInt(colors.length)];
	}
	
	public final RGB rgb;
	
	private DyeColor(int r, int g, int b) {
		rgb = new RGB(r, g, b);
	}
}
