package com.mcdraftingtable.bannerbuilder.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class LayerSummary extends Composite {

	private static LayerSummaryUiBinder uiBinder = GWT
			.create(LayerSummaryUiBinder.class);

	interface LayerSummaryUiBinder extends UiBinder<Widget, LayerSummary> {
	}
	
	@UiField Label layerLabel;
	@UiField Image patternSwatch;
	@UiField Image colorSwatch;
	@UiField Button moveUpButton;
	@UiField Button moveDownButton;
	@UiField Button removeButton;
	
	private int id;

	public LayerSummary() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public void setLayerID(int id) {
		this.id = id;
		layerLabel.setText("Layer " + id + ": ");
	}
	
	public int getLayerID() {
		return this.id;
	}

}
