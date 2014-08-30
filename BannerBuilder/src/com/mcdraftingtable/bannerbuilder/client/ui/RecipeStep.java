package com.mcdraftingtable.bannerbuilder.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class RecipeStep extends Composite {

	private static RecipeStepUiBinder uiBinder = GWT
			.create(RecipeStepUiBinder.class);

	interface RecipeStepUiBinder extends UiBinder<Widget, RecipeStep> {
	}
	
	@UiField Label stepLabel;
	@UiField RecipePanel recipePanel;
	
	private int stepNum;

	public RecipeStep() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public int getStepNum() {
		return stepNum;
	}

	public void setStepNum(int step) {
		this.stepNum = step;
		stepLabel.setText(Integer.toString(step));
	}

	public RecipePanel getRecipePanel() {
		return recipePanel;
	}

}
