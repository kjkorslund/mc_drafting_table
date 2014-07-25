package com.mcdraftingtable.bannerbuilder.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class BannerBuilder extends Composite implements EntryPoint {

	interface BannerBuilderUiBinder extends UiBinder<Widget, BannerBuilder> {
	}

	private static BannerBuilderUiBinder uiBinder = GWT
			.create(BannerBuilderUiBinder.class);
	
	public BannerBuilder() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootLayoutPanel.get().add(this);
	}
}
