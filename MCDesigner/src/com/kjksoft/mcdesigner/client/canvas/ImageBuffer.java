package com.kjksoft.mcdesigner.client.canvas;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ImageElement;

/**
 * Class representing a buffer for storing image data. Currently this is just a
 * wrapper around a {@link CanvasElement}.
 * 
 * @author Kevin
 * 
 */
public class ImageBuffer {
	private final CanvasElement canvas = Document.get().createCanvasElement();
	
	public final int getHeight() {
		return canvas.getHeight();
	}

	public final int getWidth() {
		return canvas.getWidth();
	}

	public CanvasElement getCanvas() {
		return canvas;
	}
	
	public void loadFromImageBuffer(ImageBuffer source) {
		CanvasElement sourceCanvas = source.getCanvas();
		
		canvas.setWidth(sourceCanvas.getWidth());
		canvas.setHeight(sourceCanvas.getHeight());
		Context2d ctx = canvas.getContext2d();
		ctx.drawImage(sourceCanvas, 0, 0, sourceCanvas.getWidth(), sourceCanvas.getHeight());
	}

	public void loadFromImgSrc(String imgSrc) {
		ImageElement img = Document.get().createImageElement();
		img.setSrc(imgSrc);
		
		canvas.setWidth(img.getWidth());
		canvas.setHeight(img.getHeight());
		Context2d ctx = canvas.getContext2d();
		ctx.drawImage(img, 0, 0, img.getWidth(), img.getHeight());
	}
}
