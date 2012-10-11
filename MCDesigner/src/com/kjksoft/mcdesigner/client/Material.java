package com.kjksoft.mcdesigner.client;

public enum Material {
	DIRT("images/material/dirt.png"),
	GRASS("images/material/grass.png"),
	COBBLESTONE("images/material/cobblestone.png"),
	OAK_PLANK("images/material/oak_plank.png"),
	BRICK("images/material/brick.png"),
	OBSIDIAN("images/material/obsidian.png"),
	;
	
	public final String imgSrc;

	private Material(String imgSrc) {
		this.imgSrc = imgSrc;
	}
}
