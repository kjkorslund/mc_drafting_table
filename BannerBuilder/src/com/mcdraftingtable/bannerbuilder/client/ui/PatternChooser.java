package com.mcdraftingtable.bannerbuilder.client.ui;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.PopupPanel;
import com.mcdraftingtable.bannerbuilder.client.pattern.BannerTestPattern;

public class PatternChooser extends PopupPanel {
	private static final int SIZE_PX = 32;
	
	final AbstractChooser chooser = new AbstractChooser();
	private BannerTestPattern chosenPattern = null;
	
	public PatternChooser() {
		super(true, true);
		getElement().getStyle().setBorderColor("gray");
		
		int chooserWidth = (SIZE_PX+6)*4;
		chooser.setWidth(chooserWidth + "px");
		setWidget(chooser);
	}
	
	public void addPattern(BannerTestPattern pattern) {
		Canvas canvas = Canvas.createIfSupported();
		canvas.getCanvasElement().setWidth(SIZE_PX);
		canvas.getCanvasElement().setHeight(SIZE_PX);
		
		pattern.drawOn(canvas.getCanvasElement());
		
		canvas.addClickHandler(new PatternClickHandler(pattern));
		
		chooser.addChoice(canvas);
	}
	
	public BannerTestPattern getChosenPattern() {
		return chosenPattern;
	}

	private class PatternClickHandler implements ClickHandler {
		
		private final BannerTestPattern pattern;

		public PatternClickHandler(BannerTestPattern pattern) {
			this.pattern = pattern;
		}

		@Override
		public void onClick(ClickEvent event) {
			PatternChooser.this.chosenPattern = this.pattern;
			PatternChooser.this.hide();
		}
		
	}
}
