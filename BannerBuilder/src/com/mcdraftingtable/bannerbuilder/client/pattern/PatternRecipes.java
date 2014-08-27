package com.mcdraftingtable.bannerbuilder.client.pattern;

import com.mcdraftingtable.bannerbuilder.client.pattern.PatternIngredient.DyeIngredient;
import com.mcdraftingtable.bannerbuilder.client.pattern.PatternIngredient.DyedWoolIngredient;
import com.mcdraftingtable.bannerbuilder.client.pattern.PatternIngredient.FixedIngredient;
import com.mcdraftingtable.bannerbuilder.client.recipe.IRecipe;

public class PatternRecipes {
	public static PatternRecipe forPattern(BannerPattern pattern) {
		switch(pattern) {
		case BORDER: return BORDER;
		default: return null;
		}
	}
	
	public static final PatternRecipe BANNER_BASE = new PatternRecipe();
	static {
		BANNER_BASE.setPatternIngredient(new FixedIngredient("stick"), IRecipe.SOUTH);
		BANNER_BASE.setPatternIngredient(DyedWoolIngredient.INSTANCE,
			IRecipe.NORTHWEST, IRecipe.NORTH, IRecipe.NORTHEAST,
			IRecipe.WEST, IRecipe.CENTER, IRecipe.EAST
		);
	}
	
	public static final PatternRecipe BORDER = new PatternRecipe();
	static {
		BORDER.setPatternIngredient(new FixedIngredient("banner"), IRecipe.CENTER);
		BORDER.setPatternIngredient(DyeIngredient.INSTANCE,
			IRecipe.NORTHWEST, IRecipe.NORTH, IRecipe.NORTHEAST,
			IRecipe.WEST, IRecipe.EAST,
			IRecipe.SOUTHWEST, IRecipe.SOUTH, IRecipe.SOUTHEAST
		);
	}
}
