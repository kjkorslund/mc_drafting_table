package com.mcdraftingtable.bannerbuilder.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.mcdraftingtable.bannerbuilder.client.image.ImageBuffer;
import com.mcdraftingtable.bannerbuilder.client.recipe.IIngredient;
import com.mcdraftingtable.bannerbuilder.client.recipe.IRecipe;
import com.mcdraftingtable.bannerbuilder.client.ui.RecipePanel;

public class InstructionsOverlay extends Composite {

	private static InstructionsOverlayUiBinder uiBinder = GWT
			.create(InstructionsOverlayUiBinder.class);

	interface InstructionsOverlayUiBinder extends
			UiBinder<Widget, InstructionsOverlay> {
	}
	
	@UiField RecipePanel recipePanel;

	public InstructionsOverlay() {
		initWidget(uiBinder.createAndBindUi(this));
		
		IRecipe testRecipe = new IRecipe() {
			IIngredient testIngredient = new IIngredient() {
				
				private ImageBuffer imageBuffer = new ImageBuffer();
				{
					imageBuffer.loadFromImgSrc("res/image/items/barrier.png");
				}
				
				@Override
				public String getName() {
					return "Unobtanium";
				}
				
				@Override
				public ImageBuffer getImageBuffer() {
					return imageBuffer;
				}
			};
			
			@Override
			public IIngredient getIngredient(int slot) {
				switch(slot) {
				case 0: case 1: case 2:
					return testIngredient;
				default:
					return null;
				}
			}
		};
		
		recipePanel.showRecipe(testRecipe);
	}

}
