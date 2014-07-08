package com.kjksoft.mcdesigner.client.canvas;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.Context2d.Composite;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.core.client.Duration;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.CanvasElement;
import com.kjksoft.mcdesigner.client.gwt.time.TimeUtil;

/**
 * 'Fades' an image by converting the color data to HSB and reducing the
 * saturation value.
 * 
 * @author Kevin
 * 
 */
public class FadeTransformer {
	
	private float strength = 1.0f;

	public float getStrength() {
		return strength;
	}

	/**
	 * @param strength
	 *            value ranging from 0.0f (complete whiteout) to 1.0f (no
	 *            change)
	 */
	public void setStrength(float strength) {
		this.strength = strength;
	}
	
	public void transform(ImageBuffer imageBuffer) {
		Duration d = new Duration();
		CanvasElement canvas = imageBuffer.getCanvas();
		GWT.log(TimeUtil.elapsedStr(d) + " FT2: Getting Context2d");
		Context2d ctx = canvas.getContext2d();
		GWT.log(TimeUtil.elapsedStr(d) + " FT2: Setting context global settings");
		ctx.setGlobalCompositeOperation(Composite.SOURCE_OVER);
		ctx.setGlobalAlpha(1.0 - strength);
		ctx.setFillStyle(CssColor.make("white"));
		GWT.log(TimeUtil.elapsedStr(d) + " FT2: Filling rectangle");
		ctx.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		GWT.log(TimeUtil.elapsedStr(d) + " FT2: Done");
	}

}
