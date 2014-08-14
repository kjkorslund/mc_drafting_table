package com.mcdraftingtable.bannerbuilder.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.mcdraftingtable.bannerbuilder.client.image.CanvasLoader;

public class BannerDisplay extends Composite {

	private static BannerDisplayUiBinder uiBinder = GWT
			.create(BannerDisplayUiBinder.class);

	interface BannerDisplayUiBinder extends UiBinder<Widget, BannerDisplay> {
	}
	
	@UiField CanvasElement displayCanvas;

	public BannerDisplay() {
		initWidget(uiBinder.createAndBindUi(this));
		
		CanvasLoader.loadImgSrc(displayCanvas, "res/image/banner_white.png");
	}

}
