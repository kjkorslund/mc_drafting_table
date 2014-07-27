package com.mcdraftingtable.bannerbuilder.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.mcdraftingtable.bannerbuilder.client.LayerSummary.RemoveLayerHandler;

public class DesignOverlay extends Composite {
	
	private static final int MAX_LAYERS = 6;

	private static DesignOverlayUiBinder uiBinder = GWT
			.create(DesignOverlayUiBinder.class);

	interface DesignOverlayUiBinder extends
			UiBinder<Widget, DesignOverlay> {
	}
	
	@UiField VerticalPanel mainPanel;
	@UiField HorizontalPanel addLayerRow;
	@UiField Button addLayerButton;

	final RemoveLayerHandlerImpl removeLayerHandler = new RemoveLayerHandlerImpl();
	int layerCount = 0;

	public DesignOverlay() {
		initWidget(uiBinder.createAndBindUi(this));
		addLayerButton.addClickHandler(new AddLayerClickHandler());
	}

	public LayerSummary getLayerAt(int index) {
		int startIndex = mainPanel.getWidgetIndex(addLayerRow) - layerCount;
		return (LayerSummary) mainPanel.getWidget(startIndex + index);
	}
	
	private class AddLayerClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			LayerSummary newLayerSummary = new LayerSummary();
			newLayerSummary.setLayerID(layerCount+1);
			newLayerSummary.setRemoveLayerHandler(removeLayerHandler);
			
			int index = mainPanel.getWidgetIndex(addLayerRow);
			mainPanel.insert(newLayerSummary, index);
			
			layerCount++;
			if (layerCount >= MAX_LAYERS) {
				addLayerRow.setVisible(false);
			}
		}
	}
	
	private class RemoveLayerHandlerImpl implements RemoveLayerHandler {

		@Override
		public void onRemoveLayer(LayerSummary layer) {
			mainPanel.remove(layer);
			
			layerCount--;
			if (layerCount < MAX_LAYERS) {
				addLayerRow.setVisible(true);
			}
			
			// [kjk] Relabel existing layers
			for(int i = layer.getLayerID()-1; i<layerCount; i++) {
				getLayerAt(i).setLayerID(i+1);
			}
		}
		
	}

}
