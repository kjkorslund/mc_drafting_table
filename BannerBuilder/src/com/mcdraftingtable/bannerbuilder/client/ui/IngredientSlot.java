package com.mcdraftingtable.bannerbuilder.client.ui;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.mcdraftingtable.bannerbuilder.client.image.ImageBuffer;
import com.mcdraftingtable.bannerbuilder.client.recipe.IIngredient;

public class IngredientSlot extends Composite {

	private static IngredientSlotUiBinder uiBinder = GWT
			.create(IngredientSlotUiBinder.class);

	interface IngredientSlotUiBinder extends UiBinder<Widget, IngredientSlot> {
	}
	
	@UiField CanvasElement canvas;
	
	private IIngredient ingredient = null;

	public IngredientSlot() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public IIngredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(IIngredient ingredient) {
		this.ingredient = ingredient;
		updateCanvas();
	}

	private void updateCanvas() {
		if (ingredient != null) {
			ingredient.getImageBuffer().runOrScheduleJob(new ImageBuffer.ImageJob() {
				@Override
				public void run(CanvasElement imageData) {
					int width = canvas.getWidth();
					int height = canvas.getHeight();
					
					Context2d ctx = canvas.getContext2d();
					ctx.clearRect(0, 0, width, height);
					ctx.drawImage(imageData, 0, 0, width, height);
				}
			});
			canvas.setTitle(ingredient.getName());
		} else {
			Context2d ctx = canvas.getContext2d();
			ctx.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			canvas.setTitle(null);
		}
	}
	
}
