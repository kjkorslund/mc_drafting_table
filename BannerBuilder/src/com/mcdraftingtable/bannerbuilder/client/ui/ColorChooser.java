package com.mcdraftingtable.bannerbuilder.client.ui;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.PopupPanel;
import com.mcdraftingtable.bannerbuilder.client.color.RGB;

public class ColorChooser extends PopupPanel {
	private static final int SIZE_PX = 32;
	
	final AbstractChooser chooser = new AbstractChooser();
	private RGB chosenColor = null;
	
	public ColorChooser() {
		super(true, true);
		getElement().getStyle().setBorderColor("gray");
		
		int chooserWidth = (SIZE_PX+6)*4;
		chooser.setWidth(chooserWidth + "px");
		setWidget(chooser);
	}
	
	public void addColor(RGB rgb) {
		Canvas canvas = Canvas.createIfSupported();
		canvas.getCanvasElement().setWidth(SIZE_PX);
		canvas.getCanvasElement().setHeight(SIZE_PX);

		Context2d context2d = canvas.getContext2d();
		context2d.setFillStyle(rgb.toCssColor());
		context2d.fillRect(0, 0, SIZE_PX, SIZE_PX);
		
		canvas.addClickHandler(new ColorClickHandler(rgb));
		
		chooser.addChoice(canvas);
	}
	
	public RGB getChosenColor() {
		return chosenColor;
	}

	private class ColorClickHandler implements ClickHandler {
		
		private final RGB rgb;

		public ColorClickHandler(RGB rgb) {
			this.rgb = rgb;
		}

		@Override
		public void onClick(ClickEvent event) {
			ColorChooser.this.chosenColor = this.rgb;
			ColorChooser.this.hide();
		}
		
	}
}
