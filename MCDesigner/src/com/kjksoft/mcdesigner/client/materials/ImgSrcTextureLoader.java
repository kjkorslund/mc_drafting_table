package com.kjksoft.mcdesigner.client.materials;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ImageElement;
import com.kjksoft.mcdesigner.client.canvas.ImageBuffer;
import com.kjksoft.mcdesigner.client.gwt.event.NativeEventHandler;
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
	
	private final class ImageLoadHandler implements NativeEventHandler {
		private final ImageElement img;
		private final TextureLoadRequest loadRequest;

		public ImageLoadHandler(ImageElement img, TextureLoadRequest loadRequest) {
			this.img = img;
			this.loadRequest = loadRequest;
		}
		
		@Override
		public void onLoad() {
//			System.out.println("Processing load request for material " + loadRequest.getMaterial());
			
			ImageBuffer texture = new ImageBuffer();
			texture.loadFromImg(img);
			
			TextureLoadHandler loadHandler = loadRequest.getLoadHandler();
			if (loadHandler != null) {
				loadHandler.onLoad(loadRequest.getMaterial(), texture);
			}
		}
	}

}
