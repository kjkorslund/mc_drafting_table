package com.mcdraftingtable.bannerbuilder.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.mcdraftingtable.bannerbuilder.client.recipe.IRecipe;

public class RecipePanel extends Composite {

	private static RecipePanelUiBinder uiBinder = GWT
			.create(RecipePanelUiBinder.class);

	interface RecipePanelUiBinder extends UiBinder<Widget, RecipePanel> {
	}
	
	@UiField IngredientSlot northWest;
	@UiField IngredientSlot north;
	@UiField IngredientSlot northEast;
	@UiField IngredientSlot west;
	@UiField IngredientSlot center;
	@UiField IngredientSlot east;
	@UiField IngredientSlot southWest;
	@UiField IngredientSlot south;
	@UiField IngredientSlot southEast;

	public RecipePanel() {
		initWidget(uiBinder.createAndBindUi(this));
		
		initSlot(northWest);
		initSlot(north);
		initSlot(northEast);
		initSlot(west);
		initSlot(center);
		initSlot(east);
		initSlot(southWest);
		initSlot(south);
		initSlot(southEast);
	}
	
	private void initSlot(IngredientSlot slot) {
		slot.canvas.setWidth(32);
		slot.canvas.setHeight(32);
	}
	
	public void showRecipe(IRecipe recipe) {
		northWest.setIngredient(recipe.getIngredient(IRecipe.NORTHWEST));
		north.setIngredient(recipe.getIngredient(IRecipe.NORTH));
		northEast.setIngredient(recipe.getIngredient(IRecipe.NORTHEAST));
		west.setIngredient(recipe.getIngredient(IRecipe.WEST));
		center.setIngredient(recipe.getIngredient(IRecipe.CENTER));
		east.setIngredient(recipe.getIngredient(IRecipe.EAST));
		southWest.setIngredient(recipe.getIngredient(IRecipe.SOUTHWEST));
		south.setIngredient(recipe.getIngredient(IRecipe.SOUTH));
		southEast.setIngredient(recipe.getIngredient(IRecipe.SOUTHEAST));
	}
}
