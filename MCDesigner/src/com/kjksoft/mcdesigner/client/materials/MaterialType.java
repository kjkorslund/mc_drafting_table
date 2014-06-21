package com.kjksoft.mcdesigner.client.materials;


public enum MaterialType {
	NATURAL("Natural"),
	CRAFTED("Crafted"),
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
