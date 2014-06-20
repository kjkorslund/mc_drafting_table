package com.kjksoft.mcdesigner.client.materials;

import com.kjksoft.mcdesigner.client.canvas.ImageBuffer;

/**
 * Interface for a class that is capable of loading material textures
 * 
 * @author Kevin
 * 
 */
public interface ITextureLoader {
	/**
	 * @param material
	 *            material to load the texture for
	 * @return loaded texture, or <code>null</code> if no texture was found for
	 *         that material
	 */
	public ImageBuffer loadTextureFor(Material material);
}
