package com.kjksoft.mcdesigner.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.kjksoft.mcdesigner.client.module.Palette;
import com.kjksoft.mcdesigner.client.module.tiles.TileView;

public class MainPanel extends Composite {
	interface MainPanelUiBinder extends UiBinder<Widget, MainPanel> {
	}

	private static MainPanelUiBinder uiBinder = GWT
			.create(MainPanelUiBinder.class);
	
	@UiField ToolPanel toolPanel;
	@UiField Palette palette;
	@UiField TileView tileView;
	@UiField Label mouseCoords;

	public MainPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public ToolPanel getToolPanel() {
		return toolPanel;
	}

	public Palette getPalette() {
		return palette;
	}

	public TileView getTileView() {
		return tileView;
	}

	public Label getMouseCoords() {
		return mouseCoords;
	}

}
