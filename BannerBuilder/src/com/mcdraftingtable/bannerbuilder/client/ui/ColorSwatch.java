package com.mcdraftingtable.bannerbuilder.client.ui;

import java.util.HashSet;

import com.google.gwt.canvas.dom.client.Context2d;
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

	private final HashSet<ColorChangeListener> colorChangeListeners = new HashSet<>();
	private DyeColor color = null;

	public ColorSwatch() {
		initWidget(uiBinder.createAndBindUi(this));
		swatchPanel.addClickHandler(new ColorClickHandler());
		swatch.setWidth(SIZE_PX);
		swatch.setHeight(SIZE_PX);
	}
	
	public void addColorChangeListener(ColorChangeListener colorChangeListener) {
		colorChangeListeners.add(colorChangeListener);
	}

	public void removeColorChangeListener(ColorChangeListener colorChangeListener) {
		colorChangeListeners.remove(colorChangeListener);
	}
	
	public DyeColor getColor() {
		return color;
	}

	public void setColor(DyeColor color) {
		this.color = color;
		
		Context2d context2d = swatch.getContext2d();
		context2d.setFillStyle(color.rgb.toCssColor());
		context2d.fillRect(0, 0, SIZE_PX, SIZE_PX);
		
		for(ColorChangeListener colorChangeListener : colorChangeListeners) {
			colorChangeListener.onColorChange();
		}
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
					int index = colorChooser.getChosenIndex();
					setColor(DyeColor.values()[index]);
				}
			});
		}
	}
	
	public static interface ColorChangeListener {
		public void onColorChange();
	}
}
