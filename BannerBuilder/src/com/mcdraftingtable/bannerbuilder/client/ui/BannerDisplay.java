package com.mcdraftingtable.bannerbuilder.client.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.mcdraftingtable.bannerbuilder.client.color.DyeColor;
import com.mcdraftingtable.bannerbuilder.client.image.ImageLoader;
import com.mcdraftingtable.bannerbuilder.client.image.ImageLoader.ImageLoadHandler;
import com.mcdraftingtable.bannerbuilder.client.pattern.BannerPattern;
import com.mcdraftingtable.bannerbuilder.client.pattern.LayerDefinition;

public class BannerDisplay extends Composite {

	private static BannerDisplayUiBinder uiBinder = GWT
			.create(BannerDisplayUiBinder.class);

	interface BannerDisplayUiBinder extends UiBinder<Widget, BannerDisplay> {
	}
	
	@UiField CanvasElement displayCanvas;
	
	CanvasElement poleData = null;
	CanvasElement flagTextureData = null;
	
	DyeColor baseColor = DyeColor.WHITE;
	ArrayList<LayerDefinition> layerDefinitions = new ArrayList<>();

	public BannerDisplay() {
		initWidget(uiBinder.createAndBindUi(this));
		
		BannerPattern.loadAllPatternData();
		ImageLoader.load("res/image/banner_pole.png", new ImageLoadHandler() {
			@Override
			public void onLoad(String imgSrc, CanvasElement imageData) {
				poleData = imageData;
				updateDisplayCanvas();
			}
		});
		ImageLoader.load("res/image/banner_front_texture.png", new ImageLoadHandler() {
			@Override
			public void onLoad(String imgSrc, CanvasElement imageData) {
				flagTextureData = imageData;
				updateDisplayCanvas();
			}
		});
		
		// FIXME [kjk] test code
//		layerDefinitions.add(new LayerDefinition(BannerPattern.BRICKS, DyeColor.LIME));
	}
	
	public DyeColor getBaseColor() {
		return baseColor;
	}

	public void setBaseColor(DyeColor baseColor) {
		this.baseColor = baseColor;
		updateDisplayCanvas();
	}
	
	public List<LayerDefinition> getLayerDefinitions() {
		return Collections.unmodifiableList(layerDefinitions);
	}

	public void setLayerDefinitions(Collection<LayerDefinition> layerDefinitions) {
		this.layerDefinitions = new ArrayList<>(layerDefinitions);
		updateDisplayCanvas();
	}

	private void updateDisplayCanvas() {
		if (poleData == null || flagTextureData == null) {
			// [kjk] Do nothing because the base data isn't loaded yet
			return;
		}

		displayCanvas.setWidth(poleData.getWidth());
		displayCanvas.setHeight(poleData.getHeight());
		
		Context2d ctx = displayCanvas.getContext2d();
		ctx.clearRect(0, 0, displayCanvas.getWidth(), displayCanvas.getHeight());
		ctx.drawImage(poleData, 0, 0);
		
		// Draw the base color for the flag
		ctx.setFillStyle(baseColor.rgb.toCssColor());
		ctx.fillRect(0, 0, flagTextureData.getWidth(), flagTextureData.getHeight());
		
		// Now draw the layer colors
		if (BannerPattern.isAllPatternDataLoaded()) {
			for (LayerDefinition layerDef : layerDefinitions) {
				CanvasElement layerData = createLayerOverlay(layerDef);
				ctx.drawImage(layerData, 0, 0);
			}
		}
		
		// Last step is to draw the flag texture on top of everything
		ctx.setGlobalCompositeOperation(Context2d.Composite.SOURCE_ATOP);
		ctx.setGlobalAlpha(1.0);
		ctx.drawImage(flagTextureData, 0, 0);
	}
	
	private CanvasElement createLayerOverlay(LayerDefinition layerDef) {
		CanvasElement canvas = Document.get().createCanvasElement();
		canvas.setWidth(flagTextureData.getWidth());
		canvas.setHeight(flagTextureData.getHeight());
		
		Context2d ctx = canvas.getContext2d();
		
		ctx.setFillStyle(layerDef.color.rgb.toCssColor());
		ctx.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		ctx.setGlobalCompositeOperation(Context2d.Composite.DESTINATION_IN);
		ctx.setGlobalAlpha(1.0);
		ctx.drawImage(layerDef.pattern.getPatternData(), 0, 0);
		
		return canvas;
	}

}
