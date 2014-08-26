package com.mcdraftingtable.bannerbuilder.client.pattern;

import com.mcdraftingtable.bannerbuilder.client.recipe.IRecipe;

public class PatternRecipes {
	public static final PatternRecipe BORDER = new PatternRecipe();
	static {
		BORDER.setIngredient(new PatternIngredient("barrier"),
				IRecipe.NORTHWEST, IRecipe.CENTER, IRecipe.SOUTHEAST);
	}
}
