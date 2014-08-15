package com.mcdraftingtable.bannerbuilder.client.pattern;

import com.google.gwt.dom.client.CanvasElement;
import com.mcdraftingtable.bannerbuilder.client.image.ImageLoader;
import com.mcdraftingtable.bannerbuilder.client.image.ImageLoader.ImageLoadHandler;

public enum BannerPattern {
	// [kjk] This class may need to be refactored at some point, if I decide to
	// move to an XML-based data storage solution
	
	BRICKS,
	BORDER,
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
