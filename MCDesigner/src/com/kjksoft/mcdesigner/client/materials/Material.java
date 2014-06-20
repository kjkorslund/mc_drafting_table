package com.kjksoft.mcdesigner.client.materials;


public enum Material {
	DIRT("dirt"),
	GRASS("grass"),
	COBBLESTONE("cobblestone"),
	OAK_PLANK("oak_plank"),
	BRICK("brick"),
	OBSIDIAN("obsidian"),
	;
	
	public final String textureName;
	
	private Material(String textureName) {
		this.textureName = textureName;
	}
}
