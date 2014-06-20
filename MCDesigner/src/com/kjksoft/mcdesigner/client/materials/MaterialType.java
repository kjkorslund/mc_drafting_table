package com.kjksoft.mcdesigner.client.materials;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

public enum MaterialType {
	NATURAL("Natural"
		, Material.DIRT, Material.GRASS, Material.COBBLESTONE
		, Material.OBSIDIAN
		),
	CRAFTED("Crafted"
		, Material.OAK_PLANK, Material.BRICK
		),
	WOOL("Wool"),
	;
	
	private final String title;
	private final LinkedHashSet<Material> materials = new LinkedHashSet<Material>();

	private MaterialType(String title, Material... materials) {
		this.title = title;
		this.materials.addAll(Arrays.asList(materials));
	}
	
	public HashSet<Material> getMaterials() {
		return materials;
	}

	@Override
	public String toString() {
		return title;
	}
}
