package com.kjksoft.mcdesigner.client.materials;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;


public enum Material {
	DIRT("dirt", MaterialType.NATURAL),
	GRASS("grass_top", MaterialType.NATURAL),
	COBBLESTONE("cobblestone", MaterialType.NATURAL),
	OAK_PLANK("planks_oak", MaterialType.CRAFTED),
	BRICK("brick", MaterialType.CRAFTED),
	OBSIDIAN("obsidian", MaterialType.NATURAL),
	;
	
	public final String textureName;
	private final Set<MaterialType> types;
	
	private Material(String textureName, MaterialType... materialTypes) {
		this.textureName = textureName;
		
		this.types = new LinkedHashSet<MaterialType>(Arrays.asList(materialTypes));
	}
	public Set<MaterialType> getTypes() {
		return Collections.unmodifiableSet(types);
	}
}
