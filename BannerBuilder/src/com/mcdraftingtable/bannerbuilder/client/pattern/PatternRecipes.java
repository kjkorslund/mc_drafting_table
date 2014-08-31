package com.mcdraftingtable.bannerbuilder.client.pattern;

import java.util.HashMap;

import com.mcdraftingtable.bannerbuilder.client.pattern.PatternIngredient.FixedIngredient;
import com.mcdraftingtable.bannerbuilder.client.recipe.IRecipe;

public class PatternRecipes {
	
	public static final PatternRecipe BANNER_BASE = new PatternRecipe();
	static {
		BANNER_BASE.setPatternIngredient(new FixedIngredient("stick"), IRecipe.SOUTH);
		BANNER_BASE.setPatternIngredient(PatternIngredient.DYED_WOOL,
			IRecipe.NORTHWEST, IRecipe.NORTH, IRecipe.NORTHEAST,
			IRecipe.WEST, IRecipe.CENTER, IRecipe.EAST
		);
	}
	
	private static final HashMap<BannerPattern, IPatternRecipe> recipeMap = new HashMap<>();
	public static IPatternRecipe forPattern(BannerPattern pattern) {
		return recipeMap.get(pattern);
	}

	public static final DyedPatternRecipe BORDER = new DyedPatternRecipe(IRecipe.CENTER,
		IRecipe.NORTHWEST, IRecipe.NORTH, IRecipe.NORTHEAST,
		IRecipe.WEST, IRecipe.EAST,
		IRecipe.SOUTHWEST, IRecipe.SOUTH, IRecipe.SOUTHEAST
	);
	public static final DyedPatternRecipe CIRCLE = new DyedPatternRecipe(IRecipe.SOUTH, IRecipe.CENTER);

	public static final ItemPatternRecipe BRICKS = new ItemPatternRecipe(new FixedIngredient("barrier"));
	public static final ItemPatternRecipe CREEPER = new ItemPatternRecipe(new FixedIngredient("barrier"));
	public static final ItemPatternRecipe SKULL = new ItemPatternRecipe(new FixedIngredient("barrier"));
	public static final ItemPatternRecipe FLOWER = new ItemPatternRecipe(new FixedIngredient("barrier"));
	public static final ItemPatternRecipe MOJANG = new ItemPatternRecipe(new FixedIngredient("barrier"));

	static {
		recipeMap.put(BannerPattern.BORDER, BORDER);
		recipeMap.put(BannerPattern.CIRCLE, CIRCLE);

		recipeMap.put(BannerPattern.BRICKS, BRICKS);
		recipeMap.put(BannerPattern.CREEPER, CREEPER);
		recipeMap.put(BannerPattern.SKULL, SKULL);
		recipeMap.put(BannerPattern.FLOWER, FLOWER);
		recipeMap.put(BannerPattern.MOJANG, MOJANG);
	}

	public static final PatternRecipe CROSS = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.CROSS, CROSS);
	}
	
	public static final PatternRecipe CURLY_BORDER = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.CURLY_BORDER, CURLY_BORDER);
	}
	
	public static final PatternRecipe DIAGONAL_LEFT = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.DIAGONAL_LEFT, DIAGONAL_LEFT);
	}
	
	public static final PatternRecipe DIAGONAL_RIGHT = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.DIAGONAL_RIGHT, DIAGONAL_RIGHT);
	}
	
	public static final PatternRecipe DIAGONAL_UP_LEFT = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.DIAGONAL_UP_LEFT, DIAGONAL_UP_LEFT);
	}
	
	public static final PatternRecipe DIAGONAL_UP_RIGHT = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.DIAGONAL_UP_RIGHT, DIAGONAL_UP_RIGHT);
	}
	
	public static final PatternRecipe GRADIENT = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.GRADIENT, GRADIENT);
	}
	
	public static final PatternRecipe GRADIENT_UP = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.GRADIENT_UP, GRADIENT_UP);
	}
	
	public static final PatternRecipe HALF_HORIZONTAL = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.HALF_HORIZONTAL, HALF_HORIZONTAL);
	}
	
	public static final PatternRecipe HALF_HORIZONTAL_BOTTOM = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.HALF_HORIZONTAL_BOTTOM, HALF_HORIZONTAL_BOTTOM);
	}
	
	public static final PatternRecipe HALF_VERTICAL = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.HALF_VERTICAL, HALF_VERTICAL);
	}
	
	public static final PatternRecipe HALF_VERTICAL_RIGHT = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.HALF_VERTICAL_RIGHT, HALF_VERTICAL_RIGHT);
	}
	
	public static final PatternRecipe RHOMBUS = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.RHOMBUS, RHOMBUS);
	}
	
	public static final PatternRecipe SMALL_STRIPES = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.SMALL_STRIPES, SMALL_STRIPES);
	}
	
	public static final PatternRecipe SQUARE_BOTTOM_LEFT = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.SQUARE_BOTTOM_LEFT, SQUARE_BOTTOM_LEFT);
	}
	
	public static final PatternRecipe SQUARE_BOTTOM_RIGHT = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.SQUARE_BOTTOM_RIGHT, SQUARE_BOTTOM_RIGHT);
	}
	
	public static final PatternRecipe SQUARE_TOP_LEFT = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.SQUARE_TOP_LEFT, SQUARE_TOP_LEFT);
	}
	
	public static final PatternRecipe SQUARE_TOP_RIGHT = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.SQUARE_TOP_RIGHT, SQUARE_TOP_RIGHT);
	}
	
	public static final PatternRecipe STRAIGHT_CROSS = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.STRAIGHT_CROSS, STRAIGHT_CROSS);
	}
	
	public static final PatternRecipe STRIPE_BOTTOM = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.STRIPE_BOTTOM, STRIPE_BOTTOM);
	}
	
	public static final PatternRecipe STRIPE_CENTER = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.STRIPE_CENTER, STRIPE_CENTER);
	}
	
	public static final PatternRecipe STRIPE_DOWNLEFT = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.STRIPE_DOWNLEFT, STRIPE_DOWNLEFT);
	}
	
	public static final PatternRecipe STRIPE_DOWNRIGHT = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.STRIPE_DOWNRIGHT, STRIPE_DOWNRIGHT);
	}
	
	public static final PatternRecipe STRIPE_LEFT = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.STRIPE_LEFT, STRIPE_LEFT);
	}
	
	public static final PatternRecipe STRIPE_MIDDLE = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.STRIPE_MIDDLE, STRIPE_MIDDLE);
	}
	
	public static final PatternRecipe STRIPE_RIGHT = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.STRIPE_RIGHT, STRIPE_RIGHT);
	}
	
	public static final PatternRecipe STRIPE_TOP = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.STRIPE_TOP, STRIPE_TOP);
	}
	
	public static final PatternRecipe TRIANGLE_BOTTOM = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.TRIANGLE_BOTTOM, TRIANGLE_BOTTOM);
	}
	
	public static final PatternRecipe TRIANGLE_TOP = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.TRIANGLE_TOP, TRIANGLE_TOP);
	}
	
	public static final PatternRecipe TRIANGLES_BOTTOM = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.TRIANGLES_BOTTOM, TRIANGLES_BOTTOM);
	}
	
	public static final PatternRecipe TRIANGLES_TOP = new PatternRecipe();
	static {
		recipeMap.put(BannerPattern.TRIANGLES_TOP, TRIANGLES_TOP);
	}
}
