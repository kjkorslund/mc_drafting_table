package com.mcdraftingtable.bannerbuilder.client.image;

import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class ImageLoader {
	public static void load(final String imgSrc, final ImageLoadHandler handler) {
		final Image image = new Image();
		image.setVisible(false);
		RootPanel.get().add(image);
		
		image.addLoadHandler(new LoadHandler() {
			@Override
			public void onLoad(LoadEvent event) {
				ImageElement imageElement = image.getElement().cast();

				CanvasElement imageData = Document.get().createCanvasElement();
				imageData.setWidth(image.getWidth());
				imageData.setHeight(image.getHeight());
				imageData.getContext2d().drawImage(imageElement, 0, 0);
				
				handler.onLoad(imgSrc, imageData);
				
				image.removeFromParent();
			}
		});
		
		image.setUrl(imgSrc);
	}
	
	public static interface ImageLoadHandler {
		public void onLoad(String imgSrc, CanvasElement imageData);
	}
}
