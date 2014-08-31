package com.mcdraftingtable.bannerbuilder.client.pattern;


public abstract class PatternIngredient {
	public static final PatternIngredient DYE = new DyeIngredient();
	public static final PatternIngredient DYED_WOOL = new DyedWoolIngredient();
	public static final FixedIngredient BANNER = new FixedIngredient("banner");
	
	public static final class FixedIngredient extends PatternIngredient {
		private final String name;

		public FixedIngredient(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}
	
	private static final class DyeIngredient extends PatternIngredient {
		private DyeIngredient() { }
	}
	
	private static final class DyedWoolIngredient extends PatternIngredient {
	}
}
