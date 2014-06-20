package com.kjksoft.mcdesigner.client.materials;

import java.util.HashMap;

import com.kjksoft.mcdesigner.client.texture.Texture;

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
	
	private final HashMap<Material, Texture> textureMap = new HashMap<Material, Texture>();
	
	private TextureStore() { }
	
	public void setTexture(Material material, Texture texture) {
		textureMap.put(material, texture);
	}
	
	public Texture getTexture(Material material) {
		return textureMap.get(material);
	}
}
