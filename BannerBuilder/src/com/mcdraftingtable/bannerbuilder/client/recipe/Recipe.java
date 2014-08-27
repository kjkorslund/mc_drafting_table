package com.mcdraftingtable.bannerbuilder.client.recipe;


public class Recipe implements IRecipe {
	
	private final Ingredient[] ingredients = new Ingredient[SLOT_COUNT];
	
	@Override
	public Ingredient getIngredient(int slot) {
		return ingredients[slot];
	}

	public void setIngredient(Ingredient ingredient, int... slots) {
		for (int slot : slots) {
			if (slot < 0 || slot >= ingredients.length) {
				throw new IllegalArgumentException("invalid slot index");
			}
			ingredients[slot] = ingredient;
		}
	}
}
