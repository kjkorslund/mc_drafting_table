package com.mcdraftingtable.bannerbuilder.client;

import java.util.ArrayList;
import java.util.HashSet;

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
import com.mcdraftingtable.bannerbuilder.client.color.DyeColor;
import com.mcdraftingtable.bannerbuilder.client.ui.ColorSwatch;

public class DesignOverlay extends Composite {
	
	private static final int MAX_LAYERS = 6;

	private static DesignOverlayUiBinder uiBinder = GWT
			.create(DesignOverlayUiBinder.class);

	interface DesignOverlayUiBinder extends
			UiBinder<Widget, DesignOverlay> {
	}
	
	@UiField VerticalPanel mainPanel;
	@UiField ColorSwatch baseColorSwatch;
	@UiField HorizontalPanel addLayerRow;
	@UiField Button addLayerButton;

	private final HashSet<ConfigurationUpdateListener> updateListeners = new HashSet<>();
	private final ArrayList<LayerConfiguration> layerConfigurations = new ArrayList<>(MAX_LAYERS);
	private final int startIndex;

	public DesignOverlay() {
		initWidget(uiBinder.createAndBindUi(this));
		addLayerButton.addClickHandler(new AddLayerClickHandler());
		startIndex = mainPanel.getWidgetIndex(addLayerRow);
		
		baseColorSwatch.addColorChangeListener(new ColorSwatch.ColorChangeListener() {
			@Override
			public void onColorChange() {
				doConfigurationUpdate();
			}
		});
	}

	public LayerConfiguration getLayerAt(int index) {
		return (LayerConfiguration) mainPanel.getWidget(startIndex + index);
	}
	
	public void addUpdateListener(ConfigurationUpdateListener updateListener) {
		updateListeners.add(updateListener);
	}
	
	public void removeUpdateListener(ConfigurationUpdateListener updateListener) {
		updateListeners.remove(updateListener);
	}
	
	private void doConfigurationUpdate() {
		ConfigurationData configData = createConfigurationData();
		for (ConfigurationUpdateListener updateListener : updateListeners) {
			updateListener.onConfigurationUpdate(configData);
		}
	}
	
	private ConfigurationData createConfigurationData() {
		ConfigurationDataImpl result = new ConfigurationDataImpl();
		result.baseColor = baseColorSwatch.getColor();
		return result;
	}

	private class AddLayerClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			int index = layerConfigurations.size();

			LayerConfiguration newLayerConfig = new LayerConfiguration();
			new LayerHandlers(newLayerConfig);

			layerConfigurations.add(newLayerConfig);
			updateLayerConfigurationAt(layerConfigurations.size() - 1);
			if (layerConfigurations.size() > 1) {
				// [kjk] This update is to add the 'move down' button for the
				// previous row
				updateLayerConfigurationAt(layerConfigurations.size() - 2);
			}
			
			mainPanel.insert(newLayerConfig, startIndex + index);

			if (layerConfigurations.size() >= MAX_LAYERS) {
				addLayerRow.setVisible(false);
			}
		}
	}
	
	private void updateLayerConfigurationAt(int index) {
		LayerConfiguration layerConfig = layerConfigurations.get(index);
		layerConfig.setLayerID(index+1);
		layerConfig.getMoveUpButton().setVisible(index > 0);
		layerConfig.getMoveDownButton().setVisible(index < (layerConfigurations.size() - 1));
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
			int index = layerConfigurations.indexOf(layerConfig);
			layerConfigurations.remove(index);
			mainPanel.remove(startIndex + index);
			
			if (layerConfigurations.size() < MAX_LAYERS) {
				addLayerRow.setVisible(true);
			}
			
			// [kjk] Relabel existing layers
			for(int i = index; i<layerConfigurations.size(); i++) {
				updateLayerConfigurationAt(i);
			}
		}

		private void moveLayerUp() {
			int index = layerConfigurations.indexOf(layerConfig);
			promoteLayer(index);
		}
		
		private void moveLayerDown() {
			int index = layerConfigurations.indexOf(layerConfig);
			promoteLayer(index + 1);
		}
		
		private void promoteLayer(int index) {
			int newIndex = index - 1;
			
			LayerConfiguration config = layerConfigurations.get(index);
			layerConfigurations.set(index, layerConfigurations.get(newIndex));
			layerConfigurations.set(newIndex, config);
			
			mainPanel.insert(config, startIndex + newIndex);
			
			updateLayerConfigurationAt(index);
			updateLayerConfigurationAt(newIndex);
		}

	}
	
	public static interface ConfigurationUpdateListener {
		public void onConfigurationUpdate(ConfigurationData configData);
	}
	
	public static interface ConfigurationData {
		public DyeColor getBaseColor();
	}
	
	private static class ConfigurationDataImpl implements ConfigurationData {
		
		DyeColor baseColor;

		@Override
		public DyeColor getBaseColor() {
			return baseColor;
		}
		
	}

}
