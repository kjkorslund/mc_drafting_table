package com.kjksoft.mcdesigner.client.canvas;

import com.google.gwt.canvas.dom.client.ImageData;

/**
 * 'Fades' an image by converting the color data to HSB and reducing the
 * saturation value.
 * 
 * @author Kevin
 * 
 */
public class FadeTransformer extends ImageTransformer {
	
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
		for(int x = 0; x < imageData.getWidth(); x++) {
			for(int y = 0; y < imageData.getHeight(); y++) {
				int r = imageData.getRedAt(x, y);
				int g = imageData.getGreenAt(x, y);
				int b = imageData.getBlueAt(x, y);
				RGB rgb = new RGB(r,g,b);
				float[] hslvals = ColorUtil.RGBtoHSL(rgb);
				hslvals[1] = hslvals[1]*strength;
				hslvals[2] = 100.0f - (100.0f - hslvals[2])*strength;
				
				RGB result = ColorUtil.HSLtoRGB(hslvals[0], hslvals[1], hslvals[2]);
				imageData.setRedAt(result.getRed(), x, y);
				imageData.setGreenAt(result.getGreen(), x, y);
				imageData.setBlueAt(result.getBlue(), x, y);
			}
		}
	}

}
