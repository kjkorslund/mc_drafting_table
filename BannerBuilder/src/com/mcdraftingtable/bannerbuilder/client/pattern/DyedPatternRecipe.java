package com.mcdraftingtable.bannerbuilder.client.pattern;

import com.mcdraftingtable.bannerbuilder.client.color.DyeColor;
import com.mcdraftingtable.bannerbuilder.client.recipe.Recipe;

public class DyedPatternRecipe implements IPatternRecipe {
	
	private final PatternRecipe patternRecipe = new PatternRecipe();
	
	public DyedPatternRecipe(int bannerSlot, int... dyeSlots) {
		patternRecipe.setPatternIngredient(PatternIngredient.BANNER, bannerSlot);
		patternRecipe.setPatternIngredient(PatternIngredient.DYE, dyeSlots);
	}

	@Override
	public Recipe toRecipe(DyeColor dyeColor) {
		return patternRecipe.toRecipe(dyeColor);
	}

}
