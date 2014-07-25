package com.mcdraftingtable.bannerbuilder.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DesignOverlay extends Composite {

	private static DesignOverlayUiBinder uiBinder = GWT
			.create(DesignOverlayUiBinder.class);

	interface DesignOverlayUiBinder extends
			UiBinder<Widget, DesignOverlay> {
	}

	public DesignOverlay() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
