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

public class DesignOverlay extends Composite {
	// FIXME [kjk] The logic for showing/hiding the up/down buttons is messed up
	// right now. It's too complicated as well. There's got to be a better
	// way... 
	
	private static final int MAX_LAYERS = 6;

	private static DesignOverlayUiBinder uiBinder = GWT
			.create(DesignOverlayUiBinder.class);

	interface DesignOverlayUiBinder extends
			UiBinder<Widget, DesignOverlay> {
	}
	
	@UiField VerticalPanel mainPanel;
	@UiField HorizontalPanel addLayerRow;
	@UiField Button addLayerButton;

	int layerCount = 0;

	public DesignOverlay() {
		initWidget(uiBinder.createAndBindUi(this));
		addLayerButton.addClickHandler(new AddLayerClickHandler());
	}

	public LayerConfiguration getLayerAt(int index) {
		int startIndex = mainPanel.getWidgetIndex(addLayerRow) - layerCount;
		return (LayerConfiguration) mainPanel.getWidget(startIndex + index);
	}
	
	private class AddLayerClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			LayerConfiguration newLayerConfig = new LayerConfiguration();
			newLayerConfig.setLayerID(layerCount+1);
			new LayerHandlers(newLayerConfig);
			
			int index = mainPanel.getWidgetIndex(addLayerRow);
			mainPanel.insert(newLayerConfig, index);
			
			layerCount++;
			if (layerCount >= MAX_LAYERS) {
				addLayerRow.setVisible(false);
			}
		}
	}
	
	private class LayerHandlers {
		private final LayerConfiguration layerConfig;
		private final ClickHandler removeButtonHandler = new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				removeLayer();
			}
		};
		private final ClickHandler moveUpButtonHandler = new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				moveLayerUp();
			}
		};
		private final ClickHandler moveDownButtonHandler = new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				moveLayerDown();
			}
		};

		public LayerHandlers(LayerConfiguration layerConfig) {
			this.layerConfig = layerConfig;
			layerConfig.getRemoveButton().addClickHandler(removeButtonHandler);
			layerConfig.getMoveUpButton().addClickHandler(moveUpButtonHandler);
			layerConfig.getMoveDownButton().addClickHandler(moveDownButtonHandler);
		}
		
		private void removeLayer() {
			mainPanel.remove(layerConfig);
			
			layerCount--;
			if (layerCount < MAX_LAYERS) {
				addLayerRow.setVisible(true);
			}
			
			// [kjk] Relabel existing layers
			for(int i = layerConfig.getLayerID()-1; i<layerCount; i++) {
				getLayerAt(i).setLayerID(i+1);
			}
		}

		private void moveLayerUp() {
			promoteLayer(layerConfig);
		}
		
		private void moveLayerDown() {
			int index = layerConfig.getLayerID()-1;
			promoteLayer(getLayerAt(index+1));
		}
		
		private void promoteLayer(LayerConfiguration layerConfig) {
			int index = layerConfig.getLayerID()-1;
			int newIndex = index-1;
			mainPanel.insert(layerConfig, newIndex);
			layerConfig.setLayerID(newIndex+1);
			layerConfig.getMoveDownButton().setEnabled(true);
			if (newIndex == 0) {
				layerConfig.getMoveUpButton().setEnabled(false);
			}
			
			LayerConfiguration otherLayerConfig = getLayerAt(index);
			otherLayerConfig.setLayerID(index+1);
			otherLayerConfig.getMoveUpButton().setEnabled(true);
			if (index == layerCount-1) {
				otherLayerConfig.getMoveDownButton().setEnabled(false);
			}
		}

	}

}
