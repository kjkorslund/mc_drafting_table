package com.kjksoft.mcdesigner.client.materials;


public enum MaterialType {
	NATURAL("Natural"),
	CRAFTED("Crafted"),
	PLANT("Plant"),
	REDSTONE("Redstone"),
	ORE("Ore"),
	WOOL("Wool"),
	APPARATUS("Apparatus"),
	;
	
	private final String title;

	private MaterialType(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return title;
	}
}
