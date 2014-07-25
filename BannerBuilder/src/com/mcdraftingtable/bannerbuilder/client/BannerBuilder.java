package com.mcdraftingtable.bannerbuilder.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class BannerBuilder extends Composite implements EntryPoint {

	interface BannerBuilderUiBinder extends UiBinder<Widget, BannerBuilder> {
	}
	
	static interface BannerBuilderStyle extends CssResource {
		String bannerBackground();
		String overlayPanel();
		String overlayPanelShadow();
	}

	private static BannerBuilderUiBinder uiBinder = GWT
			.create(BannerBuilderUiBinder.class);
	
	@UiField LayoutPanel layoutPanel;
	@UiField HTMLPanel instructionsOverlay;
	@UiField HTMLPanel designOverlay;
	@UiField BannerBuilderStyle style;
	
	public BannerBuilder() {
		initWidget(uiBinder.createAndBindUi(this));

		// [kjk] This is a bit of a hack. The box shadow only works if it's
		// applied to the parent div for the overlay panels.
		designOverlay.getElement().getParentElement().addClassName(
				style.overlayPanelShadow());
		instructionsOverlay.getElement().getParentElement().addClassName(
				style.overlayPanelShadow());
	}
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootLayoutPanel.get().add(this);
	}
}
