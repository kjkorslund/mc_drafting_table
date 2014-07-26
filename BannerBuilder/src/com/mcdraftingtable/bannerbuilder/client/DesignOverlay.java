package com.mcdraftingtable.bannerbuilder.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class DesignOverlay extends Composite {

	private static DesignOverlayUiBinder uiBinder = GWT
			.create(DesignOverlayUiBinder.class);

	interface DesignOverlayUiBinder extends
			UiBinder<Widget, DesignOverlay> {
	}
	
	@UiField LayerSummary layerRow1;
	@UiField LayerSummary layerRow2;
	@UiField LayerSummary layerRow3;
	@UiField LayerSummary layerRow4;
	@UiField LayerSummary layerRow5;
	@UiField LayerSummary layerRow6;
	@UiField HorizontalPanel addLayerRow;
	@UiField Button addLayerButton;

	private int layerCount = 0;
	
	public DesignOverlay() {
		initWidget(uiBinder.createAndBindUi(this));
		addLayerButton.addClickHandler(new AddLayerClickHandler());
		// TODO rework this class to dynamically create the layer rows, so
		// layers can be easily removed
	}

	public int getLayerCount() {
		return layerCount;
	}

	public void setLayerCount(int layerCount) {
		if (layerCount > 6) layerCount = 6;
		this.layerCount = layerCount;
		
		for(int i=0; i < 6; i++) {
			int layerID = i+1;
			LayerSummary layerRow = getLayerByID(layerID);
			layerRow.setVisible(i < layerCount);
		}
		addLayerRow.setVisible(layerCount < 6);
	}
	
	private LayerSummary getLayerByID(int layerID) {
		switch(layerID) {
		case 1:
			return layerRow1;
		case 2:
			return layerRow2;
		case 3:
			return layerRow3;
		case 4:
			return layerRow4;
		case 5:
			return layerRow5;
		case 6:
			return layerRow6;
		}
		throw new IllegalArgumentException("layerID must be in the range [1,6]");
	}
	
	private class AddLayerClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			setLayerCount(layerCount+1);
		}
		
	}

}
