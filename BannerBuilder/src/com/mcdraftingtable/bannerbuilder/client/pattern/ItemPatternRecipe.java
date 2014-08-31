package com.mcdraftingtable.bannerbuilder.client.pattern;

import com.mcdraftingtable.bannerbuilder.client.color.DyeColor;
import com.mcdraftingtable.bannerbuilder.client.pattern.PatternIngredient.FixedIngredient;
import com.mcdraftingtable.bannerbuilder.client.recipe.IRecipe;
import com.mcdraftingtable.bannerbuilder.client.recipe.Recipe;

public class ItemPatternRecipe implements IPatternRecipe {
	
	private final PatternRecipe dyelessRecipe = new PatternRecipe();
	private final PatternRecipe dyedRecipe = new PatternRecipe();
	
	public ItemPatternRecipe(FixedIngredient itemIngredient) {
		dyelessRecipe.setPatternIngredient(itemIngredient, IRecipe.WEST);
		dyelessRecipe.setPatternIngredient(PatternIngredient.BANNER, IRecipe.CENTER);
		
		dyedRecipe.setPatternIngredient(itemIngredient, IRecipe.WEST);
		dyedRecipe.setPatternIngredient(PatternIngredient.DYE, IRecipe.CENTER);
		dyedRecipe.setPatternIngredient(PatternIngredient.BANNER, IRecipe.SOUTH);
	}

	@Override
	public Recipe toRecipe(DyeColor dyeColor) {
		PatternRecipe patternRecipe = (dyeColor == DyeColor.BLACK)
				? dyelessRecipe : dyedRecipe;
		return patternRecipe.toRecipe(dyeColor);
	}

}
