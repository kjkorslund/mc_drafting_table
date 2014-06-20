package com.kjksoft.mcdesigner.client.materials;

import com.kjksoft.mcdesigner.client.canvas.ImageBuffer;


public class ImgSrcTextureLoader implements ITextureLoader {
	
	private final String base;

	public ImgSrcTextureLoader(String base) {
		this.base = base;
	}

	public String getBase() {
		return base;
	}

	@Override
	public ImageBuffer loadTextureFor(Material material) {
		ImageBuffer texture = new ImageBuffer();
		texture.loadFromImgSrc(base + material.textureName + ".png");
		return texture;
	}
	
}
