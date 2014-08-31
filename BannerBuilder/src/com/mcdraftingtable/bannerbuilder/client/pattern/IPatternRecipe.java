package com.mcdraftingtable.bannerbuilder.client.pattern;

import com.mcdraftingtable.bannerbuilder.client.color.DyeColor;
import com.mcdraftingtable.bannerbuilder.client.recipe.Recipe;

public interface IPatternRecipe {
	public Recipe toRecipe(DyeColor dyeColor);
}
