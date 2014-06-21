package com.kjksoft.mcdesigner.client.materials;

import com.kjksoft.mcdesigner.client.canvas.ImageBuffer;

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
}
