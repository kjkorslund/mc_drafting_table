package com.mcdraftingtable.bannerbuilder.client.ui;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.PopupPanel;
import com.mcdraftingtable.bannerbuilder.client.pattern.BannerPattern;

public class PatternChooser extends PopupPanel {
	private static final int SIZE_PX = 32;
	
	final AbstractChooser chooser = new AbstractChooser();
	private BannerPattern chosenPattern = null;
	
	public PatternChooser() {
		super(true, true);
		getElement().getStyle().setBorderColor("gray");
		
		int chooserWidth = (SIZE_PX+6)*4;
		chooser.setWidth(chooserWidth + "px");
		setWidget(chooser);
	}
	
	public void addPattern(BannerPattern pattern) {
		Canvas canvas = Canvas.createIfSupported();
		canvas.getCanvasElement().setWidth(SIZE_PX);
		canvas.getCanvasElement().setHeight(SIZE_PX);
		
		drawPattern(canvas.getCanvasElement(), pattern);
		
		canvas.addClickHandler(new PatternClickHandler(pattern));
		
		chooser.addChoice(canvas);
	}
	
	private void drawPattern(CanvasElement destination, BannerPattern pattern) {
		CanvasElement patternSwatchData = pattern.getPatternSwatchData();
		if (patternSwatchData != null) {
			destination.setWidth(patternSwatchData.getWidth());
			destination.setHeight(patternSwatchData.getHeight());
			destination.getContext2d().drawImage(patternSwatchData, 0, 0);
		}
	}
	
	public BannerPattern getChosenPattern() {
		return chosenPattern;
	}

	private class PatternClickHandler implements ClickHandler {
		
		private final BannerPattern pattern;

		public PatternClickHandler(BannerPattern pattern) {
			this.pattern = pattern;
		}

		@Override
		public void onClick(ClickEvent event) {
			PatternChooser.this.chosenPattern = this.pattern;
			PatternChooser.this.hide();
		}
		
	}
}
