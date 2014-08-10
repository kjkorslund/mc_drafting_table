package com.mcdraftingtable.bannerbuilder.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class AbstractChooser extends Composite {

	private static AbstractChooserUiBinder uiBinder = GWT
			.create(AbstractChooserUiBinder.class);

	interface AbstractChooserUiBinder extends UiBinder<Widget, AbstractChooser> {
	}
	
	interface ChooserStyle extends CssResource {
		String choice();
	}

	@UiField ChooserStyle style;
	@UiField FlowPanel choicesPanel;
	
	public AbstractChooser() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void addChoice(Widget widget) {
		widget.addStyleName(style.choice());
		choicesPanel.add(widget);
	}
}
