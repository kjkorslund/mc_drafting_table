package com.kjksoft.mcdesigner.client.materials;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ImageElement;
import com.kjksoft.mcdesigner.client.gwt.event.NativeEvents;


public class ImgSrcTextureLoader extends TextureLoader {
	private final String base;

	public ImgSrcTextureLoader(String base) {
		this.base = base;
	}

	public String getBase() {
		return base;
	}

	@Override
	public void postLoadRequest(TextureLoadRequest loadRequest) {
		Material material = loadRequest.getMaterial();
		
//		System.out.println("Posting request for material " + material);
		ImageElement img = Document.get().createImageElement();
		ImageLoadHandler loadHandler = new ImageLoadHandler(img, loadRequest);
		NativeEvents.register(img, loadHandler);
		
//		String imgPath = URL.encode(base + material.textureName + ".png");
		String imgPath = base + material.textureName + ".png";
		img.setSrc(imgPath);
	}

}
