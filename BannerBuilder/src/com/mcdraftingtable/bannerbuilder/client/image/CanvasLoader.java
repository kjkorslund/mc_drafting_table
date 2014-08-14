package com.mcdraftingtable.bannerbuilder.client.image;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class CanvasLoader {
	public static void loadImgSrc(final CanvasElement canvasElement, final String imgSrc) {
		final Image image = new Image();
		image.setVisible(false);
		RootPanel.get().add(image);
		
		image.addLoadHandler(new LoadHandler() {
			@Override
			public void onLoad(LoadEvent event) {
				GWT.log("[CanvasLoader] onLoad");
				ImageElement imageElement = image.getElement().cast();

				canvasElement.setWidth(image.getWidth());
				canvasElement.setHeight(image.getHeight());
				canvasElement.getContext2d().drawImage(imageElement, 0, 0);
			}
		});
		
		GWT.log("[CanvasLoader] Setting image URL");
		image.setUrl(imgSrc);
	}
}
