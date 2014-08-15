package com.mcdraftingtable.bannerbuilder.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.mcdraftingtable.bannerbuilder.client.DesignOverlay.ConfigurationData;
import com.mcdraftingtable.bannerbuilder.client.ui.BannerDisplay;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class BannerBuilder extends Composite implements EntryPoint {

	interface BannerBuilderUiBinder extends UiBinder<Widget, BannerBuilder> {
	}
	
	static interface BannerBuilderStyle extends CssResource {
		String bannerBackground();
		String overlayPanel();
	}

	private static BannerBuilderUiBinder uiBinder = GWT
			.create(BannerBuilderUiBinder.class);
	
	@UiField InstructionsOverlay instructionsOverlay;
	@UiField DesignOverlay designOverlay;
	@UiField BannerDisplay bannerDisplay;
	@UiField BannerBuilderStyle style;
	
	public BannerBuilder() {
		initWidget(uiBinder.createAndBindUi(this));
		
		designOverlay.addUpdateListener(new DesignOverlay.ConfigurationUpdateListener() {
			@Override
			public void onConfigurationUpdate(ConfigurationData configData) {
				bannerDisplay.setBaseColor(configData.getBaseColor());
			}
		});
	}
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootLayoutPanel.get().add(this);
	}
}
