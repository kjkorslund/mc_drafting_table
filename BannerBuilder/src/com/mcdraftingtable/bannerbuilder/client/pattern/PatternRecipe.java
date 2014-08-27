package com.mcdraftingtable.bannerbuilder.client.pattern;

import com.mcdraftingtable.bannerbuilder.client.color.DyeColor;
import com.mcdraftingtable.bannerbuilder.client.pattern.PatternIngredient.DyeIngredient;
import com.mcdraftingtable.bannerbuilder.client.pattern.PatternIngredient.DyedWoolIngredient;
import com.mcdraftingtable.bannerbuilder.client.pattern.PatternIngredient.FixedIngredient;
import com.mcdraftingtable.bannerbuilder.client.recipe.IRecipe;
import com.mcdraftingtable.bannerbuilder.client.recipe.Ingredient;
import com.mcdraftingtable.bannerbuilder.client.recipe.Recipe;

public class PatternRecipe {
	
	private final PatternIngredient[] patternIngredients = new PatternIngredient[IRecipe.SLOT_COUNT];
	
	public PatternIngredient getPatternIngredient(int slot) {
		return patternIngredients[slot];
	}

	public void setPatternIngredient(PatternIngredient patternIngredient, int... slots) {
		for (int slot : slots) {
			if (slot < 0 || slot >= patternIngredients.length) {
				throw new IllegalArgumentException("invalid slot index");
			}
			patternIngredients[slot] = patternIngredient;
		}
	}
	
	public Recipe toRecipe(DyeColor dyeColor) {
		Recipe recipe = new Recipe();
		for(int i=0; i<patternIngredients.length; i++) {
			PatternIngredient patternIngredient = patternIngredients[i];
			String ingredientName = null;
			if (patternIngredient instanceof DyeIngredient) {
				ingredientName = getDyeName(dyeColor);
			}
			else if (patternIngredient instanceof DyedWoolIngredient) {
				ingredientName = getDyedWoolName(dyeColor);
			}
			else if (patternIngredient instanceof FixedIngredient){
				ingredientName = ((FixedIngredient)patternIngredient).getName();
			}
			recipe.setIngredient(new Ingredient(ingredientName), i);
		}
		return recipe;
	}
	
	private String getDyeName(DyeColor dyeColor) {
		switch(dyeColor) {
		case RED: return "dye_powder_red";
		case ORANGE: return "dye_powder_orange";
		case YELLOW: return "dye_powder_yellow";
		case GREEN: return "dye_powder_green";
		case BLUE: return "dye_powder_blue";
		case LIGHT_BLUE: return "dye_powder_light_blue";
		case MAGENTA: return "dye_powder_magenta";
		case PINK: return "dye_powder_pink";
		case WHITE: return "dye_powder_white";
		case LIGHT_GRAY: return "dye_powder_silver";
		case BLACK: return "dye_powder_black";
		case BROWN: return "dye_powder_brown";
		case CYAN: return "dye_powder_cyan";
		case PURPLE: return "dye_powder_purple";
		case GRAY: return "dye_powder_gray";
		case LIME: return "dye_powder_lime";
		default: return null;
		}
	}
	
	private String getDyedWoolName(DyeColor dyeColor) {
		switch(dyeColor) {
		case RED: return "wool_colored_red";
		case ORANGE: return "wool_colored_orange";
		case YELLOW: return "wool_colored_yellow";
		case GREEN: return "wool_colored_green";
		case BLUE: return "wool_colored_blue";
		case LIGHT_BLUE: return "wool_colored_light_blue";
		case MAGENTA: return "wool_colored_magenta";
		case PINK: return "wool_colored_pink";
		case WHITE: return "wool_colored_white";
		case LIGHT_GRAY: return "wool_colored_silver";
		case BLACK: return "wool_colored_black";
		case BROWN: return "wool_colored_brown";
		case CYAN: return "wool_colored_cyan";
		case PURPLE: return "wool_colored_purple";
		case GRAY: return "wool_colored_gray";
		case LIME: return "wool_colored_lime";
		default: return null;
		}
	}
}
