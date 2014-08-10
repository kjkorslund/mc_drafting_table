package com.mcdraftingtable.bannerbuilder.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class InstructionsOverlay extends Composite {

	private static InstructionsOverlayUiBinder uiBinder = GWT
			.create(InstructionsOverlayUiBinder.class);

	interface InstructionsOverlayUiBinder extends
			UiBinder<Widget, InstructionsOverlay> {
	}

	public InstructionsOverlay() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
