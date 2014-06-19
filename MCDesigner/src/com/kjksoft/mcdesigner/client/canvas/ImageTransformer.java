package com.kjksoft.mcdesigner.client.canvas;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.ImageData;
import com.google.gwt.dom.client.CanvasElement;

public abstract class ImageTransformer {
	public void transform(ImageBuffer imageBuffer) {
		CanvasElement canvas = imageBuffer.getCanvas();
		
		Context2d ctx = canvas.getContext2d();
		ImageData imageData = ctx.getImageData(0, 0, canvas.getWidth(), canvas.getHeight());
		transformImpl(imageData);
		ctx.putImageData(imageData, 0, 0);
	}
	
	protected abstract void transformImpl(ImageData imageData);
}
