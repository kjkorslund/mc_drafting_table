package com.mcdraftingtable.bannerbuilder.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.mcdraftingtable.bannerbuilder.client.color.DyeColor;
import com.mcdraftingtable.bannerbuilder.client.ui.ColorChooser;
import com.mcdraftingtable.bannerbuilder.client.ui.ColorSwatch;
import com.mcdraftingtable.bannerbuilder.client.ui.PatternSwatch;

public class LayerConfiguration extends Composite {

	private static LayerConfigurationUiBinder uiBinder = GWT
			.create(LayerConfigurationUiBinder.class);

	interface LayerConfigurationUiBinder extends UiBinder<Widget, LayerConfiguration> {
	}
	
	@UiField Label layerLabel;
	@UiField PatternSwatch patternSwatch;
	@UiField ColorSwatch colorSwatch;
	@UiField Button moveUpButton;
	@UiField Button moveDownButton;
	@UiField Button removeButton;
	
	private ColorChooser colorChooser = new ColorChooser();
	
	private int id;

	public LayerConfiguration() {
		initWidget(uiBinder.createAndBindUi(this));

		// [kk] This is temporary, for testing the add/remove layer dynamics
		colorSwatch.getElement().getStyle().setBackgroundColor(DyeColor.random().rgb.toCssString());
		
		for(DyeColor dyeColor : DyeColor.values()) {
			colorChooser.addColor(dyeColor.rgb);
		}
	}
	
	public void setLayerID(int id) {
		this.id = id;
		layerLabel.setText("Layer " + id + ": ");
	}
	
	public int getLayerID() {
		return this.id;
	}
	
	public Button getMoveUpButton() {
		return moveUpButton;
	}

	public Button getMoveDownButton() {
		return moveDownButton;
	}

	public Button getRemoveButton() {
		return removeButton;
	}
	
}
