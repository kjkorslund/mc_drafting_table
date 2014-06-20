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
//		String imgPath = URL.encode(base + material.textureName + ".png");
		String imgPath = base + material.textureName + ".png";
//		RequestBuilder rb = new RequestBuilder(RequestBuilder.HEAD, imgPath);
//		Request r = rb.send();
//		r.
		ImageBuffer texture = new ImageBuffer();
		texture.loadFromImgSrc(imgPath);
		return texture;
	}
	
}
