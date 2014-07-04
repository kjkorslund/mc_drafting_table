package com.kjksoft.mcdesigner.client.materials;

import com.google.gwt.dom.client.ImageElement;
import com.kjksoft.mcdesigner.client.canvas.ImageBuffer;
import com.kjksoft.mcdesigner.client.gwt.event.NativeEventHandler;

/**
 * Interface for a class that is capable of loading texture data for materials.
 * Due to the asynchronous nature of networks, texture retrieval is implemented
 * as an event-driven interface.
 * 
 * @author Kevin
 * 
 */
public abstract class TextureLoader {
	
	/**
	 * Posts a load request to this texture loader for the given material
	 * 
	 * @param material
	 *            material to load the texture for
	 */
	public abstract void postLoadRequest(TextureLoadRequest loadRequest);
	
	public static class TextureLoadRequest {
		private final Material material;
		private final TextureLoadHandler loadHandler;

		public TextureLoadRequest(Material material, TextureLoadHandler loadHandler) {
			this.material = material;
			this.loadHandler = loadHandler;
		}

		public Material getMaterial() {
			return material;
		}

		public TextureLoadHandler getLoadHandler() {
			return loadHandler;
		}
	}
	
	public static interface TextureLoadHandler {
		public void onLoad(Material material, ImageBuffer texture);
	}
	
	public static class ImageLoadHandler implements NativeEventHandler {
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


