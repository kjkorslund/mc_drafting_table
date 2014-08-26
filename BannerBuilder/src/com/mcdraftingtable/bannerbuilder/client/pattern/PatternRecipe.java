package com.mcdraftingtable.bannerbuilder.client.pattern;

import com.mcdraftingtable.bannerbuilder.client.recipe.IRecipe;

public class PatternRecipe implements IRecipe {
	
	private final PatternIngredient[] ingredients = new PatternIngredient[IRecipe.SLOT_COUNT];
	
	@Override
	public PatternIngredient getIngredient(int slot) {
		return ingredients[slot];
	}

	public void setIngredient(PatternIngredient ingredient, int... slots) {
		for (int slot : slots) {
			if (slot < 0 || slot >= ingredients.length) {
				throw new IllegalArgumentException("invalid slot index");
			}
			ingredients[slot] = ingredient;
		}
	}
}
