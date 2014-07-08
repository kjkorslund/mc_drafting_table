package com.kjksoft.mcdesigner.client.materials;

import java.util.HashMap;
import java.util.HashSet;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.Command;
import com.kjksoft.mcdesigner.client.canvas.FadeTransformer2;
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
	
	public void setTexture(final Material material, ImageBuffer texture) {
		textureMap.put(material, texture);

		// TODO [kjk] Deferring the faded texture creation commands helped a
		// little with responsiveness, but the overall delay is still
		// unacceptable. Investigate (a) improving the faded texture creation
		// algorithm performance, (b) using web workers to perform the operation
		// as a thread, and (c) displaying a progress bar to the user
		FadedTextureCreator generator66 = new FadedTextureCreator(texture, 0.66f) {
			@Override
			protected void onCreated(ImageBuffer result) {
				textureMap66.put(material, result);
			}
		};
		Scheduler.get().scheduleDeferred(generator66);
		
		FadedTextureCreator generator33 = new FadedTextureCreator(texture, 0.33f) {
			@Override
			protected void onCreated(ImageBuffer result) {
				textureMap33.put(material, result);
			}
		};
		Scheduler.get().scheduleDeferred(generator33);
		
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
	
	public static interface TextureUpdateListener {
		public void onTextureUpdate(Material material);
	}
	
	private static abstract class FadedTextureCreator implements Command {
		private final ImageBuffer source;
		private final float strength;

		public FadedTextureCreator(ImageBuffer source, float strength) {
			this.source = source;
			this.strength = strength;
		}
		
		@Override
		public void execute() {
			onCreated(createFadedTexture());
		}
		
		protected abstract void onCreated(ImageBuffer result);
		
		private ImageBuffer createFadedTexture() {
			ImageBuffer result = new ImageBuffer();
			result.loadFromImageBuffer(source);
			
			FadeTransformer2 fader = new FadeTransformer2();
			fader.setStrength(strength);
			fader.transform(result);
			
			return result;
		}
	}
}
