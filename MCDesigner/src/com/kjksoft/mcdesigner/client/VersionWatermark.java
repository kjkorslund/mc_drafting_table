package com.kjksoft.mcdesigner.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;

public class VersionWatermark extends Composite {

	private static VersionWatermarkUiBinder uiBinder = GWT
			.create(VersionWatermarkUiBinder.class);
	@UiField Label versionLabel;
	private String versionString;

	interface VersionWatermarkUiBinder extends
			UiBinder<Widget, VersionWatermark> {
	}

	public VersionWatermark() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public String getVersionString() {
		return versionString;
	}

	public void setVersionString(String versionString) {
		this.versionString = versionString;
		versionLabel.setText(versionString);
	}

}
