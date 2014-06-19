package com.kjksoft.mcdesigner.client;

import com.kjksoft.mcdesigner.client.texture.Texture;

public enum Material {
	DIRT("dirt"),
	GRASS("grass"),
	COBBLESTONE("cobblestone"),
	OAK_PLANK("oak_plank"),
	BRICK("brick"),
	OBSIDIAN("obsidian"),
	;
	
	private static final String base() {
		return "images/material/";
	};
	
	public final String textureName;
	
	public Texture texture;

	private Material(String textureName) {
		this.textureName = textureName;
		this.texture = new Texture(base() + textureName + ".png");
	}
}
