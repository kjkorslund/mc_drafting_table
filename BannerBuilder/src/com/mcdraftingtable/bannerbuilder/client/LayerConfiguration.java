package com.mcdraftingtable.bannerbuilder.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class LayerConfiguration extends Composite {

	private static LayerConfigurationUiBinder uiBinder = GWT
			.create(LayerConfigurationUiBinder.class);

	interface LayerConfigurationUiBinder extends UiBinder<Widget, LayerConfiguration> {
	}
	
	@UiField Label layerLabel;
	@UiField Image patternSwatch;
	@UiField Image colorSwatch;
	@UiField Button moveUpButton;
	@UiField Button moveDownButton;
	@UiField Button removeButton;
	
	private int id;

	public LayerConfiguration() {
		initWidget(uiBinder.createAndBindUi(this));
		patternSwatch.addClickHandler(new PatternClickHandler());
		colorSwatch.addClickHandler(new ColorClickHandler());

		// [kk] This is temporary, for testing the add/remove layer dynamics
		colorSwatch.getElement().getStyle().setBackgroundColor(randomColor());
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

	private class ColorClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			// TODO proper color selection dialog
			Window.alert("Choose a color");
		}
	}
	
	private class PatternClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			// TODO proper patter selection dialog
			Window.alert("Choose a pattern");
		}
	}
	
	private String randomColor() {
		int r = Random.nextInt(256);
		int g = Random.nextInt(256);
		int b = Random.nextInt(256);
		return "rgb(" + r + "," + g + "," + b + ")";
	}
}
