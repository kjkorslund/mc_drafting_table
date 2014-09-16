package com.mcdraftingtable.bannerbuilder.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.mcdraftingtable.bannerbuilder.client.recipe.IIngredient;
import com.mcdraftingtable.bannerbuilder.client.recipe.IRecipe;
import com.mcdraftingtable.bannerbuilder.client.ui.MaterialsPanel;
import com.mcdraftingtable.bannerbuilder.client.ui.RecipeStep;

public class InstructionsOverlay extends Composite {

	private static InstructionsOverlayUiBinder uiBinder = GWT
			.create(InstructionsOverlayUiBinder.class);

	interface InstructionsOverlayUiBinder extends
			UiBinder<Widget, InstructionsOverlay> {
	}
	
	@UiField VerticalPanel stepsPanel;
	@UiField MaterialsPanel materialsPanel;
	
	private final ArrayList<RecipeStep> recipeSteps = new ArrayList<>();

	public InstructionsOverlay() {
		initWidget(uiBinder.createAndBindUi(this));
		
//		setRecipeStepCount(1);
//		recipeSteps.get(0).getRecipePanel().showRecipe(
//			PatternRecipes.forPattern(BannerPattern.BORDER).toRecipe(DyeColor.GREEN)
//		);
	}
	
	public void setRecipeSteps(List<IRecipe> recipes) {
		setRecipeStepCount(recipes.size());
		for(int i=0; i < recipes.size(); i++) {
			recipeSteps.get(i).getRecipePanel().showRecipe(recipes.get(i));
		}
	}
	
	private void setRecipeStepCount(int count) {
		while(recipeSteps.size() < count) {
			RecipeStep newStep = new RecipeStep();
			newStep.setStepNum(recipeSteps.size() + 1);
			recipeSteps.add(newStep);
			stepsPanel.add(newStep);
		}
		while(recipeSteps.size() > count) {
			RecipeStep step = recipeSteps.remove(recipeSteps.size() - 1);
			stepsPanel.remove(step);
		}
	}

	public void setIngredientCounts(HashMap<IIngredient, Integer> ingredientCounts) {
		materialsPanel.setIngredientCounts(ingredientCounts);
	}
	
}
