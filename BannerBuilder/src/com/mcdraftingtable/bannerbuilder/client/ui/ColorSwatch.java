package com.mcdraftingtable.bannerbuilder.client.ui;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.mcdraftingtable.bannerbuilder.client.color.DyeColor;
import com.mcdraftingtable.bannerbuilder.client.color.RGB;

public class ColorSwatch extends Composite {
	private static final int SIZE_PX = 48;

	private static ColorSwatchUiBinder uiBinder = GWT
			.create(ColorSwatchUiBinder.class);

	interface ColorSwatchUiBinder extends UiBinder<Widget, ColorSwatch> {
	}
	
	interface SwatchStyle extends CssResource {
		String swatch();
	}
	
	@UiField FocusPanel swatchPanel;
	@UiField CanvasElement swatch;

	private RGB color = null;

	public ColorSwatch() {
		initWidget(uiBinder.createAndBindUi(this));
		swatchPanel.addClickHandler(new ColorClickHandler());
		swatch.setWidth(SIZE_PX);
		swatch.setHeight(SIZE_PX);
	}
	
	public RGB getColor() {
		return color;
	}

	public void setColor(RGB color) {
		this.color = color;
		
		Context2d context2d = swatch.getContext2d();
		context2d.setFillStyle(CssColor.make(color.toCssString()));
		context2d.fillRect(0, 0, SIZE_PX, SIZE_PX);
	}
	
	private class ColorClickHandler implements ClickHandler {
		private final ColorChooser colorChooser = new ColorChooser();
		
		public ColorClickHandler() {
			for(DyeColor dyeColor : DyeColor.values()) {
				colorChooser.addColor(dyeColor.rgb);
			}
		}
		
		@Override
		public void onClick(ClickEvent event) {
			colorChooser.showRelativeTo(swatchPanel);
			
			colorChooser.addCloseHandler(new CloseHandler<PopupPanel>() {
				@Override
				public void onClose(CloseEvent<PopupPanel> event) {
					setColor(colorChooser.getChosenColor());
				}
			});
		}
	}
}
