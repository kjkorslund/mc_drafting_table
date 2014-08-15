package com.mcdraftingtable.bannerbuilder.client;

import java.util.HashSet;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.mcdraftingtable.bannerbuilder.client.color.DyeColor;
import com.mcdraftingtable.bannerbuilder.client.pattern.LayerDefinition;
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
	
	private final HashSet<LayerConfigurationChangeListener> changeListeners = new HashSet<>();
	private ColorChooser colorChooser = new ColorChooser();
	
	private int id;

	public LayerConfiguration() {
		initWidget(uiBinder.createAndBindUi(this));

		// [kk] This is temporary, for testing the add/remove layer dynamics
		colorSwatch.setColor(DyeColor.random());
		
		for(DyeColor dyeColor : DyeColor.values()) {
			colorChooser.addColor(dyeColor.rgb);
		}
		
		patternSwatch.addPatternChangeListener(new PatternSwatch.PatternChangeListener() {
			@Override
			public void onPatternChange() {
				fireChangeListener();
			}
		});
		colorSwatch.addColorChangeListener(new ColorSwatch.ColorChangeListener() {
			@Override
			public void onColorChange() {
				fireChangeListener();
			}
		});
	}
	
	public void addChangeListener(LayerConfigurationChangeListener changeListener) {
		changeListeners.add(changeListener);
	}
	
	public void removeChangeListener(LayerConfigurationChangeListener changeListener) {
		changeListeners.remove(changeListener);
	}
	
	private void fireChangeListener() {
		for(LayerConfigurationChangeListener changeListener : changeListeners) {
			changeListener.onConfigurationChange();
		}
	}
	
	public LayerDefinition getLayerDefinition() {
		return new LayerDefinition(patternSwatch.getPattern(),
				colorSwatch.getColor());
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
	
	public static interface LayerConfigurationChangeListener {
		public void onConfigurationChange();
	}
	
}
