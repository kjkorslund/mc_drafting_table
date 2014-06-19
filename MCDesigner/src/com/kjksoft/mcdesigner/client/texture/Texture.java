package com.kjksoft.mcdesigner.client.texture;

import com.kjksoft.mcdesigner.client.canvas.ImageBuffer;


/**
 * Class representing a Minecraft texture. A texture is the visual
 * representation of a material, and can be found in resource packs.
 * 
 * @author Kevin
 * 
 */
public class Texture {
	private final String imgSrc;
	private ImageBuffer imgBuffer = null;
	
	public Texture(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public ImageBuffer getImgBuffer() {
		if (imgBuffer == null) {
			imgBuffer = new ImageBuffer();
			imgBuffer.loadFromImgSrc(imgSrc);
		}
		return imgBuffer;
	}
	
	
}
