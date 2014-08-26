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
import com.mcdraftingtable.bannerbuilder.client.pattern.BannerPattern;

public class PatternSwatch extends Composite {
	private static final int SIZE_PX = 48;

	private static ColorSwatchUiBinder uiBinder = GWT
			.create(ColorSwatchUiBinder.class);

	interface ColorSwatchUiBinder extends UiBinder<Widget, PatternSwatch> {
	}
	
	interface SwatchStyle extends CssResource {
		String swatch();
	}
	
	@UiField FocusPanel swatchPanel;
	@UiField CanvasElement swatch;

	private final HashSet<PatternChangeListener> patternChangeListeners = new HashSet<>();
	private BannerPattern pattern = null;

	public PatternSwatch() {
		initWidget(uiBinder.createAndBindUi(this));
		swatchPanel.addClickHandler(new SwatchClickHandler());
		swatch.setWidth(SIZE_PX);
		swatch.setHeight(SIZE_PX);
	}
	
	public void addPatternChangeListener(PatternChangeListener patternChangeListener) {
		patternChangeListeners.add(patternChangeListener);
	}

	public void removePatternChangeListener(PatternChangeListener patternChangeListener) {
		patternChangeListeners.remove(patternChangeListener);
	}
	
	public BannerPattern getPattern() {
		return pattern;
	}

	public void setPattern(BannerPattern pattern) {
		this.pattern = pattern;
		drawPattern(swatch, pattern);
		for(PatternChangeListener patternChangeListener : patternChangeListeners) {
			patternChangeListener.onPatternChange();
		}
	}
	
	private void drawPattern(CanvasElement destination, BannerPattern pattern) {
		CanvasElement patternSwatchData = pattern.getPatternSwatchData().getImageData();
		if (patternSwatchData != null) {
			destination.setWidth(patternSwatchData.getWidth());
			destination.setHeight(patternSwatchData.getHeight());
			
			Context2d ctx = destination.getContext2d();
			ctx.clearRect(0, 0, patternSwatchData.getWidth(), patternSwatchData.getHeight());
			ctx.drawImage(patternSwatchData, 0, 0);
		}
	}
	
	private class SwatchClickHandler implements ClickHandler {
		private final PatternChooser patternChooser = new PatternChooser();
		
		public SwatchClickHandler() {
			for(BannerPattern pattern : BannerPattern.values()) {
				patternChooser.addPattern(pattern);
			}
		}
		
		@Override
		public void onClick(ClickEvent event) {
			patternChooser.showRelativeTo(swatchPanel);
			
			patternChooser.addCloseHandler(new CloseHandler<PopupPanel>() {
				@Override
				public void onClose(CloseEvent<PopupPanel> event) {
					setPattern(patternChooser.getChosenPattern());
				}
			});
		}
	}
	
	public static interface PatternChangeListener {
		public void onPatternChange();
	}
}
