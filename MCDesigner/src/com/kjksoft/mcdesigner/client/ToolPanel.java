package com.kjksoft.mcdesigner.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.kjksoft.mcdesigner.client.module.ToolBox;

public class ToolPanel extends Composite {

	private static ToolPanel2UiBinder uiBinder = GWT
			.create(ToolPanel2UiBinder.class);

	interface ToolPanel2UiBinder extends UiBinder<Widget, ToolPanel> {
	}

	@UiField Button clearButton;
	@UiField Button clearAllButton;
	@UiField Button materialsButton;
	@UiField Button materialsAllButton;
	@UiField ToolBox toolBox;
	@UiField Button prevLayerButton;
	@UiField SpanElement currentLayer;
	@UiField Button nextLayerButton;
	@UiField Button zoomInButton;
	@UiField Button zoomOutButton;
	@UiField Button importResPackButton;
	
	public ToolPanel() {
		initWidget(uiBinder.createAndBindUi(this));
		setStyleName("toolpanel");
	}
	
	public Tool getSelectedTool() {
		return toolBox.getSelectedTool();
	}
	
	public void setLayer(int layer) {
		currentLayer.setInnerText("Layer " + layer);
	}
}
