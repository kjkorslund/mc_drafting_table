package com.kjksoft.mcdesigner.client.materials;


public enum MaterialType {
	NATURAL("Terrain"),
	MASONRY("Doors and Masonry"),
	CRAFTED("Crafted Objects"),
	PLANT_TREE("Trees and Mushrooms"),
	PLANT_DECORATIVE("Decorative Plants"),
	PLANT_FARMED("Farm Plants"),
	REDSTONE("Rail and Redstone"),
	ORE("Ores"),
	GLASS("Glass"),
	HARDENED_CLAY("Hardened Clay"),
	WOOL("Wool"),
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
