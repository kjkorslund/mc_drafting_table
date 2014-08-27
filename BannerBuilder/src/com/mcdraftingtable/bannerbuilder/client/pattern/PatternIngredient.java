package com.mcdraftingtable.bannerbuilder.client.pattern;


public abstract class PatternIngredient {
	
	public static final class FixedIngredient extends PatternIngredient {
		private final String name;

		public FixedIngredient(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}
	
	public static final class DyeIngredient extends PatternIngredient {
		public static final DyeIngredient INSTANCE = new DyeIngredient();
		
		private DyeIngredient() { }
	}
	
	public static final class DyedWoolIngredient extends PatternIngredient {
		public static final DyedWoolIngredient INSTANCE = new DyedWoolIngredient();
		
		private DyedWoolIngredient() { }
	}
}
