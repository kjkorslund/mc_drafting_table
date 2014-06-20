package com.kjksoft.mcdesigner.client.materials;

import java.util.HashMap;

import com.kjksoft.mcdesigner.client.canvas.FadeTransformer;
import com.kjksoft.mcdesigner.client.canvas.ImageBuffer;

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
	
	private final HashMap<Material, ImageBuffer> textureMap = new HashMap<Material, ImageBuffer>();
	private final HashMap<Material, ImageBuffer> textureMap66 = new HashMap<Material, ImageBuffer>();
	private final HashMap<Material, ImageBuffer> textureMap33 = new HashMap<Material, ImageBuffer>();
	
	private TextureStore() { }
	
	/**
	 * Loads textures into the store. The loaded textures will replace existing
	 * textures; however, materials will retain their previous texture if the
	 * texture loader cannot load a texture for that material.
	 */
	public void loadTextures(ITextureLoader textureLoader) {
		for(Material material : Material.values()) {
			ImageBuffer texture = textureLoader.loadTextureFor(material);
			if (texture != null) {
				setTexture(material, texture);
			}
		}
	}
	
	public void setTexture(Material material, ImageBuffer texture) {
		textureMap.put(material, texture);
		textureMap66.put(material, createFadedTexture(texture, 0.66f));
		textureMap33.put(material, createFadedTexture(texture, 0.33f));
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
}
