package com.kjksoft.mcdesigner.client.materials;

import java.util.HashMap;
import java.util.HashSet;

import com.kjksoft.mcdesigner.client.canvas.FadeTransformer;
import com.kjksoft.mcdesigner.client.canvas.ImageBuffer;
import com.kjksoft.mcdesigner.client.materials.TextureLoader.TextureLoadHandler;
import com.kjksoft.mcdesigner.client.materials.TextureLoader.TextureLoadRequest;

/**
 * Class to track the texture associated with various materials. This is
 * essentially just a singleton wrapper around a HashMap.
 * 
 * @author Kevin
 * 
 */
public class TextureStore {
	
	private static final TextureStore INSTANCE = new TextureStore();
	public static final TextureStore getInstance() {
		return INSTANCE;
	}
	
	private final HashSet<TextureUpdateListener> updateListeners = new HashSet<TextureUpdateListener>();
	
	private final HashMap<Material, ImageBuffer> textureMap = new HashMap<Material, ImageBuffer>();
	private final HashMap<Material, ImageBuffer> textureMap66 = new HashMap<Material, ImageBuffer>();
	private final HashMap<Material, ImageBuffer> textureMap33 = new HashMap<Material, ImageBuffer>();
	
	private TextureStore() { }
	
	/**
	 * Loads textures into the store. The loaded textures will replace existing
	 * textures; however, materials will retain their previous texture if the
	 * texture loader cannot load a texture for that material.
	 */
	public void loadTextures(TextureLoader textureLoader) {
		final TextureLoadHandler loadHandler = new TextureLoadHandler() {
			@Override
			public void onLoad(Material material, ImageBuffer texture) {
				setTexture(material, texture);
			}
		};
		
		for(Material material : Material.values()) {
			TextureLoadRequest loadRequest = new TextureLoadRequest(material, loadHandler);
			textureLoader.postLoadRequest(loadRequest);
		}
	}
	
	public void addUpdateListener(TextureUpdateListener listener) {
		updateListeners.add(listener);
	}

	public void removeUpdateListener(TextureUpdateListener listener) {
		updateListeners.remove(listener);
	}
	
	public void setTexture(Material material, ImageBuffer texture) {
		textureMap.put(material, texture);
		textureMap66.put(material, createFadedTexture(texture, 0.66f));
		textureMap33.put(material, createFadedTexture(texture, 0.33f));
		
		for(TextureUpdateListener listener : updateListeners) {
			listener.onTextureUpdate(material);
		}
	}
	
	public ImageBuffer getTexture(Material material) {
		return textureMap.get(material);
	}
	
	public ImageBuffer getTexture66(Material material) {
		return textureMap66.get(material);
	}
	
	public ImageBuffer getTexture33(Material material) {
		return textureMap33.get(material);
	}
	
	private ImageBuffer createFadedTexture(ImageBuffer source, float strength) {
		ImageBuffer result = new ImageBuffer();
		result.loadFromImageBuffer(source);
		
		FadeTransformer fader = new FadeTransformer();
		fader.setStrength(strength);
		fader.transform(result);
		
		return result;
	}
	
	public static interface TextureUpdateListener {
		public void onTextureUpdate(Material material);
	}
}
