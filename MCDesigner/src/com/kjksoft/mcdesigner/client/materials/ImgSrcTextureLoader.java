package com.kjksoft.mcdesigner.client.materials;

import java.util.HashMap;
import java.util.HashSet;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.kjksoft.mcdesigner.client.canvas.ImageBuffer;


public class ImgSrcTextureLoader extends TextureLoader {
	
	private final HashSet<ImageLoadHandler> activeHandlers = new HashSet<ImageLoadHandler>();
	
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
		
		System.out.println("Posting request for material " + material);
		ImageElement img = Document.get().createImageElement();
		ImageLoadHandler loadHandler = new ImageLoadHandler(img, loadRequest);
		activeHandlers.add(loadHandler);
		
//		Image image = Image.wrap(img);
//		image.addLoadHandler(new ImageLoadHandler(img, loadRequest));
		Event.setEventListener(img, loadHandler);
		
//		String imgPath = URL.encode(base + material.textureName + ".png");
		String imgPath = base + material.textureName + ".png";
		img.setSrc(imgPath);
	}
	
	private final class ImageLoadHandler implements LoadHandler, EventListener {
		private final ImageElement img;
		private final TextureLoadRequest loadRequest;

		public ImageLoadHandler(ImageElement img, TextureLoadRequest loadRequest) {
			this.img = img;
			this.loadRequest = loadRequest;
		}
		
		@Override
		public void onLoad(LoadEvent event) {
			onLoad();
		}

		@Override
		public void onBrowserEvent(Event event) {
			 if(Event.ONLOAD == event.getTypeInt()) {
				 System.out.println("Processing load request for material " + loadRequest.getMaterial());
				 onLoad();
             }
		}
		
		private void onLoad() {
			ImageBuffer texture = new ImageBuffer();
			texture.loadFromImg(img);
			
			TextureLoadHandler loadHandler = loadRequest.getLoadHandler();
			if (loadHandler != null) {
				loadHandler.onLoad(loadRequest.getMaterial(), texture);
			}
		}
	}

}
