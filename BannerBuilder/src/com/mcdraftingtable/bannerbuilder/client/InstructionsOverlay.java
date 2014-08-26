package com.mcdraftingtable.bannerbuilder.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.mcdraftingtable.bannerbuilder.client.pattern.BannerPattern;
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
		
		recipePanel.showRecipe(BannerPattern.BORDER.getRecipe());
	}

}
