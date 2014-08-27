package com.mcdraftingtable.bannerbuilder.client.recipe;

import com.mcdraftingtable.bannerbuilder.client.image.ImageBuffer;

public class Ingredient implements IIngredient {

	private final ImageBuffer imageBuffer = new ImageBuffer();
	private final String ingredientName;
	
	public Ingredient(String name) {
		this.ingredientName = name;
		if (name != null) {
			imageBuffer.loadFromImgSrc("res/image/items/" + name + ".png");
		}
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
