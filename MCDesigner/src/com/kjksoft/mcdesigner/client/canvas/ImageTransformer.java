package com.kjksoft.mcdesigner.client.canvas;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.ImageData;
import com.google.gwt.core.client.Duration;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.CanvasElement;
import com.kjksoft.mcdesigner.client.gwt.time.TimeUtil;

public abstract class ImageTransformer {
	public void transform(ImageBuffer imageBuffer) {
		Duration d = new Duration();
		CanvasElement canvas = imageBuffer.getCanvas();
		GWT.log(TimeUtil.elapsedStr(d) + " Getting Context2d");
		Context2d ctx = canvas.getContext2d();
		GWT.log(TimeUtil.elapsedStr(d) + " Getting ImageData");
		ImageData imageData = ctx.getImageData(0, 0, canvas.getWidth(), canvas.getHeight());
		GWT.log(TimeUtil.elapsedStr(d) + " Running transform");
		transformImpl(imageData);
		GWT.log(TimeUtil.elapsedStr(d) + " Putting ImageData");
		ctx.putImageData(imageData, 0, 0);
		GWT.log(TimeUtil.elapsedStr(d) + " Done");
	}
	
	protected abstract void transformImpl(ImageData imageData);
}
