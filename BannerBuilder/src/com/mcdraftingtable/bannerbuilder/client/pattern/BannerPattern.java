package com.mcdraftingtable.bannerbuilder.client.pattern;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.Document;
import com.mcdraftingtable.bannerbuilder.client.image.ImageLoader;
import com.mcdraftingtable.bannerbuilder.client.image.ImageLoader.ImageLoadHandler;

public enum BannerPattern {
	// [kjk] This class may need to be refactored at some point, if I decide to
	// move to an XML-based data storage solution
	
	BORDER,
	BRICKS,
	CIRCLE,
	CREEPER,
	CROSS,
	CURLY_BORDER,
	DIAGONAL_LEFT,
	DIAGONAL_RIGHT,
	DIAGONAL_UP_LEFT,
	DIAGONAL_UP_RIGHT,
	FLOWER,
	GRADIENT,
	GRADIENT_UP,
	HALF_HORIZONTAL,
	HALF_HORIZONTAL_BOTTOM,
	HALF_VERTICAL,
	HALF_VERTICAL_RIGHT,
	MOJANG,
	RHOMBUS,
	SKULL,
	SMALL_STRIPES,
	SQUARE_BOTTOM_LEFT,
	SQUARE_BOTTOM_RIGHT,
	SQUARE_TOP_LEFT,
	SQUARE_TOP_RIGHT,
	STRAIGHT_CROSS,
	STRIPE_BOTTOM,
	STRIPE_CENTER,
	STRIPE_DOWNLEFT,
	STRIPE_DOWNRIGHT,
	STRIPE_LEFT,
	STRIPE_MIDDLE,
	STRIPE_RIGHT,
	STRIPE_TOP,
	TRIANGLE_BOTTOM,
	TRIANGLE_TOP,
	TRIANGLES_BOTTOM,
	TRIANGLES_TOP
	;
	
	public static void loadAllPatternData() {
		for(BannerPattern pattern : values()) {
			pattern.loadPatternData();
		}
	}
	
	private static boolean allPatternDataLoaded = false;
	public static boolean isAllPatternDataLoaded() {
		if (!allPatternDataLoaded) {
			for(BannerPattern pattern : values()) {
				if (!pattern.isPatternDataLoaded()) {
					return false;
				}
			}
			allPatternDataLoaded = true;
		}
		return true;
	}
	
	private CanvasElement patternData = null;
	
	public CanvasElement getPatternData() {
		return patternData;
	}
	
	public CanvasElement getPatternSwatchData() {
		if (isPatternDataLoaded()) {
			int width = patternData.getWidth()/10;
			int height = patternData.getHeight()/10;
			
			CanvasElement swatchData = Document.get().createCanvasElement();
			swatchData.setWidth(width);
			swatchData.setHeight(height);
			
			Context2d ctx = swatchData.getContext2d();
			ctx.drawImage(patternData, 0, 0, width, height);
			ctx.setGlobalCompositeOperation(Context2d.Composite.SOURCE_IN);
			ctx.setFillStyle(CssColor.make("black"));
			ctx.fillRect(0, 0, width, height);
			
			return swatchData;
		}
		
		return null;
	}
	
	public boolean isPatternDataLoaded() {
		return patternData != null;
	}

	private void loadPatternData() {
		if (!isPatternDataLoaded()) {
			String uri = "res/image/pattern/" + name().toLowerCase() + ".png";
			ImageLoader.load(uri, new ImageLoadHandler() {
				@Override
				public void onLoad(String imgSrc, CanvasElement imageData) {
					patternData = imageData;
				}
			});
		}
	}
}
