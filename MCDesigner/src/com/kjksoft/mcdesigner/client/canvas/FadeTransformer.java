package com.kjksoft.mcdesigner.client.canvas;

import java.awt.Color;

import com.google.gwt.canvas.dom.client.ImageData;

/**
 * 'Fades' an image by converting the color data to HSB and reducing the
 * saturation value.
 * 
 * @author Kevin
 * 
 */
public class FadeTransformer extends CanvasTransformer {
	
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
	protected void transformImpl(ImageData imageData) {
		float[] hsbvals = new float[3];
		for(int x = 0; x < imageData.getWidth(); x++) {
			for(int y = 0; y < imageData.getHeight(); y++) {
				int r = imageData.getRedAt(x, y);
				int g = imageData.getGreenAt(x, y);
				int b = imageData.getBlueAt(x, y);
				Color.RGBtoHSB(r, g, b, hsbvals);
				hsbvals[1] = hsbvals[1]*strength;
				
				Color result = Color.getHSBColor(hsbvals[0], hsbvals[1], hsbvals[2]);
				imageData.setRedAt(result.getRed(), x, y);
				imageData.setGreenAt(result.getGreen(), x, y);
				imageData.setBlueAt(result.getBlue(), x, y);
			}
		}
	}

}
