package com.kjksoft.mcdesigner.client.module.tiles;

import com.kjksoft.mcdesigner.client.materials.Material;

public class TileData3D {
	private final Material material;
	private final LayerType layerType;
	
	public TileData3D(Material material, LayerType layerType) {
		this.material = material;
		this.layerType = layerType;
	}
}