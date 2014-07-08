package com.kjksoft.mcdesigner.client.canvas;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.Context2d.Composite;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.canvas.dom.client.ImageData;
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
public class FadeTransformer2 extends ImageTransformer {
	
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
	
	@Override
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

	@Override
	protected void transformImpl(ImageData imageData) {
//		for(int x = 0; x < imageData.getWidth(); x++) {
//			for(int y = 0; y < imageData.getHeight(); y++) {
//				int r = imageData.getRedAt(x, y);
//				int g = imageData.getGreenAt(x, y);
//				int b = imageData.getBlueAt(x, y);
//				RGB rgb = new RGB(r,g,b);
//				float[] hslvals = ColorUtil.RGBtoHSL(rgb);
//				hslvals[1] = hslvals[1]*strength;
//				hslvals[2] = 100.0f - (100.0f - hslvals[2])*strength;
//				
//				RGB result = ColorUtil.HSLtoRGB(hslvals[0], hslvals[1], hslvals[2]);
//				imageData.setRedAt(result.getRed(), x, y);
//				imageData.setGreenAt(result.getGreen(), x, y);
//				imageData.setBlueAt(result.getBlue(), x, y);
//			}
//		}
	}

}
