package com.kjksoft.mcdesigner.client.materials;


public enum MaterialType {
	NATURAL("Natural"),
	CRAFTED("Crafted"),
	PLANT_TREE("Plants - Trees"),
	PLANT_DECORATIVE("Plants - Decorative"),
	PLANT_EDIBLE("Plants - Farmed"),
	REDSTONE("Redstone"),
	ORE("Ore"),
	GLASS("Glass"),
	HARDENED_CLAY("Hardened Clay"),
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
