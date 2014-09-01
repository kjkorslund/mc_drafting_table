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
	public static final DyedPatternRecipe CROSS = new DyedPatternRecipe(IRecipe.SOUTH,
		IRecipe.NORTHWEST, IRecipe.NORTHEAST, IRecipe.CENTER, IRecipe.SOUTHWEST, IRecipe.SOUTHEAST
	);
	public static final DyedPatternRecipe DIAGONAL_LEFT = new DyedPatternRecipe(IRecipe.SOUTH,
		IRecipe.NORTH, IRecipe.NORTHWEST, IRecipe.WEST
	);
	public static final DyedPatternRecipe DIAGONAL_RIGHT = new DyedPatternRecipe(IRecipe.SOUTH,
		IRecipe.NORTH, IRecipe.NORTHEAST, IRecipe.EAST
	);
	public static final DyedPatternRecipe DIAGONAL_UP_LEFT = new DyedPatternRecipe(IRecipe.NORTH,
		IRecipe.SOUTH, IRecipe.SOUTHWEST, IRecipe.WEST
	);
	public static final DyedPatternRecipe DIAGONAL_UP_RIGHT = new DyedPatternRecipe(IRecipe.NORTH,
		IRecipe.SOUTH, IRecipe.SOUTHEAST, IRecipe.EAST
	);
	public static final DyedPatternRecipe GRADIENT = new DyedPatternRecipe(IRecipe.NORTH,
		IRecipe.NORTHEAST, IRecipe.NORTHWEST, IRecipe.CENTER, IRecipe.SOUTH
	);
	public static final DyedPatternRecipe GRADIENT_UP = new DyedPatternRecipe(IRecipe.SOUTH,
		IRecipe.SOUTHEAST, IRecipe.SOUTHWEST, IRecipe.CENTER, IRecipe.NORTH
	);
	public static final DyedPatternRecipe HALF_HORIZONTAL = new DyedPatternRecipe(IRecipe.SOUTH,
		IRecipe.NORTHWEST, IRecipe.NORTH, IRecipe.NORTHEAST,
		IRecipe.WEST, IRecipe.CENTER, IRecipe.EAST
	);
	public static final DyedPatternRecipe HALF_HORIZONTAL_BOTTOM = new DyedPatternRecipe(IRecipe.NORTH,
			IRecipe.WEST, IRecipe.CENTER, IRecipe.EAST,
			IRecipe.SOUTHWEST, IRecipe.SOUTH, IRecipe.SOUTHEAST
	);
	public static final DyedPatternRecipe HALF_VERTICAL = new DyedPatternRecipe(IRecipe.EAST,
		IRecipe.NORTHWEST, IRecipe.WEST, IRecipe.SOUTHWEST,
		IRecipe.NORTH, IRecipe.CENTER, IRecipe.SOUTH
	);
	public static final DyedPatternRecipe HALF_VERTICAL_RIGHT = new DyedPatternRecipe(IRecipe.WEST,
		IRecipe.NORTH, IRecipe.CENTER, IRecipe.SOUTH,
		IRecipe.NORTHEAST, IRecipe.EAST, IRecipe.SOUTHEAST
	);
	public static final DyedPatternRecipe RHOMBUS = new DyedPatternRecipe(IRecipe.CENTER,
		IRecipe.NORTH, IRecipe.WEST, IRecipe.EAST, IRecipe.SOUTH
	);
	public static final DyedPatternRecipe SMALL_STRIPES = new DyedPatternRecipe(IRecipe.SOUTH,
		IRecipe.NORTHWEST, IRecipe.WEST, IRecipe.NORTHEAST, IRecipe.EAST
	);
	public static final DyedPatternRecipe SQUARE_BOTTOM_LEFT = new DyedPatternRecipe(IRecipe.SOUTH, IRecipe.SOUTHWEST);
	public static final DyedPatternRecipe SQUARE_BOTTOM_RIGHT = new DyedPatternRecipe(IRecipe.SOUTH, IRecipe.SOUTHEAST);
	public static final DyedPatternRecipe SQUARE_TOP_LEFT = new DyedPatternRecipe(IRecipe.SOUTH, IRecipe.NORTHWEST);
	public static final DyedPatternRecipe SQUARE_TOP_RIGHT = new DyedPatternRecipe(IRecipe.SOUTH, IRecipe.NORTHEAST);
	public static final DyedPatternRecipe STRAIGHT_CROSS = new DyedPatternRecipe(IRecipe.SOUTHWEST,
		IRecipe.NORTH, IRecipe.WEST, IRecipe.CENTER, IRecipe.EAST, IRecipe.SOUTH
	);
	public static final DyedPatternRecipe STRIPE_BOTTOM = new DyedPatternRecipe(IRecipe.CENTER,
		IRecipe.SOUTHWEST, IRecipe.SOUTH, IRecipe.SOUTHEAST
	);
	public static final DyedPatternRecipe STRIPE_CENTER = new DyedPatternRecipe(IRecipe.EAST,
		IRecipe.NORTH, IRecipe.CENTER, IRecipe.SOUTH
	);
	public static final DyedPatternRecipe STRIPE_DOWNLEFT = new DyedPatternRecipe(IRecipe.SOUTH,
		IRecipe.SOUTHWEST, IRecipe.CENTER, IRecipe.NORTHEAST
	);
	public static final DyedPatternRecipe STRIPE_DOWNRIGHT = new DyedPatternRecipe(IRecipe.SOUTH,
		IRecipe.NORTHWEST, IRecipe.CENTER, IRecipe.SOUTHEAST
	);
	public static final DyedPatternRecipe STRIPE_LEFT = new DyedPatternRecipe(IRecipe.SOUTH,
		IRecipe.NORTHWEST, IRecipe.WEST, IRecipe.SOUTHWEST
	);
	public static final DyedPatternRecipe STRIPE_MIDDLE = new DyedPatternRecipe(IRecipe.SOUTH,
		IRecipe.WEST, IRecipe.CENTER, IRecipe.EAST
	);
	public static final DyedPatternRecipe STRIPE_RIGHT = new DyedPatternRecipe(IRecipe.SOUTH,
		IRecipe.NORTHEAST, IRecipe.EAST, IRecipe.SOUTHEAST
	);
	public static final DyedPatternRecipe STRIPE_TOP = new DyedPatternRecipe(IRecipe.SOUTH,
		IRecipe.NORTHWEST, IRecipe.NORTH, IRecipe.NORTHEAST
	);
	public static final DyedPatternRecipe TRIANGLE_BOTTOM = new DyedPatternRecipe(IRecipe.SOUTH,
		IRecipe.SOUTHWEST, IRecipe.CENTER, IRecipe.SOUTHEAST
	);
	public static final DyedPatternRecipe TRIANGLE_TOP = new DyedPatternRecipe(IRecipe.SOUTH,
		IRecipe.NORTHWEST, IRecipe.CENTER, IRecipe.NORTHEAST
	);
	public static final DyedPatternRecipe TRIANGLES_BOTTOM = new DyedPatternRecipe(IRecipe.CENTER,
		IRecipe.WEST, IRecipe.SOUTH, IRecipe.EAST
	);
	public static final DyedPatternRecipe TRIANGLES_TOP = new DyedPatternRecipe(IRecipe.SOUTH,
		IRecipe.WEST, IRecipe.NORTH, IRecipe.EAST
	);
	
	public static final ItemPatternRecipe BRICKS = new ItemPatternRecipe(new FixedIngredient("barrier"));
	public static final ItemPatternRecipe CREEPER = new ItemPatternRecipe(new FixedIngredient("barrier"));
	public static final ItemPatternRecipe SKULL = new ItemPatternRecipe(new FixedIngredient("barrier"));
	public static final ItemPatternRecipe FLOWER = new ItemPatternRecipe(new FixedIngredient("barrier"));
	public static final ItemPatternRecipe MOJANG = new ItemPatternRecipe(new FixedIngredient("barrier"));
	public static final ItemPatternRecipe CURLY_BORDER = new ItemPatternRecipe(new FixedIngredient("barrier"));
	
	static {
		recipeMap.put(BannerPattern.BORDER, BORDER);
		recipeMap.put(BannerPattern.CIRCLE, CIRCLE);
		recipeMap.put(BannerPattern.CROSS, CROSS);
		recipeMap.put(BannerPattern.DIAGONAL_LEFT, DIAGONAL_LEFT);
		recipeMap.put(BannerPattern.DIAGONAL_RIGHT, DIAGONAL_RIGHT);
		recipeMap.put(BannerPattern.DIAGONAL_UP_LEFT, DIAGONAL_UP_LEFT);
		recipeMap.put(BannerPattern.DIAGONAL_UP_RIGHT, DIAGONAL_UP_RIGHT);
		recipeMap.put(BannerPattern.GRADIENT, GRADIENT);
		recipeMap.put(BannerPattern.GRADIENT_UP, GRADIENT_UP);
		recipeMap.put(BannerPattern.HALF_HORIZONTAL, HALF_HORIZONTAL);
		recipeMap.put(BannerPattern.HALF_HORIZONTAL_BOTTOM, HALF_HORIZONTAL_BOTTOM);
		recipeMap.put(BannerPattern.HALF_VERTICAL, HALF_VERTICAL);
		recipeMap.put(BannerPattern.HALF_VERTICAL_RIGHT, HALF_VERTICAL_RIGHT);
		recipeMap.put(BannerPattern.RHOMBUS, RHOMBUS);
		recipeMap.put(BannerPattern.SMALL_STRIPES, SMALL_STRIPES);
		recipeMap.put(BannerPattern.SQUARE_BOTTOM_LEFT, SQUARE_BOTTOM_LEFT);
		recipeMap.put(BannerPattern.SQUARE_BOTTOM_RIGHT, SQUARE_BOTTOM_RIGHT);
		recipeMap.put(BannerPattern.SQUARE_TOP_LEFT, SQUARE_TOP_LEFT);
		recipeMap.put(BannerPattern.SQUARE_TOP_RIGHT, SQUARE_TOP_RIGHT);
		recipeMap.put(BannerPattern.STRAIGHT_CROSS, STRAIGHT_CROSS);
		recipeMap.put(BannerPattern.STRIPE_BOTTOM, STRIPE_BOTTOM);
		recipeMap.put(BannerPattern.STRIPE_CENTER, STRIPE_CENTER);
		recipeMap.put(BannerPattern.STRIPE_DOWNLEFT, STRIPE_DOWNLEFT);
		recipeMap.put(BannerPattern.STRIPE_DOWNRIGHT, STRIPE_DOWNRIGHT);
		recipeMap.put(BannerPattern.STRIPE_LEFT, STRIPE_LEFT);
		recipeMap.put(BannerPattern.STRIPE_MIDDLE, STRIPE_MIDDLE);
		recipeMap.put(BannerPattern.STRIPE_RIGHT, STRIPE_RIGHT);
		recipeMap.put(BannerPattern.STRIPE_TOP, STRIPE_TOP);
		recipeMap.put(BannerPattern.TRIANGLE_BOTTOM, TRIANGLE_BOTTOM);
		recipeMap.put(BannerPattern.TRIANGLE_TOP, TRIANGLE_TOP);
		recipeMap.put(BannerPattern.TRIANGLES_BOTTOM, TRIANGLES_BOTTOM);
		recipeMap.put(BannerPattern.TRIANGLES_TOP, TRIANGLES_TOP);

		recipeMap.put(BannerPattern.BRICKS, BRICKS);
		recipeMap.put(BannerPattern.CREEPER, CREEPER);
		recipeMap.put(BannerPattern.SKULL, SKULL);
		recipeMap.put(BannerPattern.FLOWER, FLOWER);
		recipeMap.put(BannerPattern.MOJANG, MOJANG);
		recipeMap.put(BannerPattern.CURLY_BORDER, CURLY_BORDER);
	}
	
}
