package com.mcdraftingtable.bannerbuilder.client.pattern;

import com.mcdraftingtable.bannerbuilder.client.image.ImageBuffer;
import com.mcdraftingtable.bannerbuilder.client.recipe.IIngredient;

public class PatternIngredient implements IIngredient {

	private final String ingredientName;
	private final ImageBuffer imageBuffer = new ImageBuffer();
	
	public PatternIngredient(String ingredientName) {
		this.ingredientName = ingredientName;
		imageBuffer.loadFromImgSrc("res/image/items/" + ingredientName + ".png");
	}
	
	@Override
	public String getName() {
		return ingredientName;
	}

	@Override
	public ImageBuffer getImageBuffer() {
		return imageBuffer;
	}

}
