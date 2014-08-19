package com.mcdraftingtable.bannerbuilder.client.recipe;

public interface IRecipe {
	public static final int NORTHWEST	= 0;
	public static final int NORTH		= 1;
	public static final int NORTHEAST	= 2;
	public static final int WEST		= 3;
	public static final int CENTER		= 4;
	public static final int EAST		= 5;
	public static final int SOUTHWEST	= 6;
	public static final int SOUTH		= 7;
	public static final int SOUTHEAST	= 8;
	
	public IIngredient getIngredient(int slot);
}
