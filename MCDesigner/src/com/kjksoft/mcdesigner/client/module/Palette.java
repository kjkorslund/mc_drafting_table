package com.kjksoft.mcdesigner.client.module;

import java.util.HashMap;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.kjksoft.mcdesigner.client.canvas.ImageBuffer;
import com.kjksoft.mcdesigner.client.materials.Material;
import com.kjksoft.mcdesigner.client.materials.MaterialType;
import com.kjksoft.mcdesigner.client.materials.TextureStore;
import com.kjksoft.mcdesigner.client.materials.TextureStore.TextureUpdateListener;

public class Palette extends Composite {

	private static PaletteUiBinder uiBinder = GWT.create(PaletteUiBinder.class);

	interface PaletteUiBinder extends UiBinder<Widget, Palette> {
	}
	
	@UiField CanvasElement primaryBlock;
	@UiField CanvasElement secondaryBlock;
	@UiField HTMLPanel paletteSwatches;
	@UiField StackLayoutPanel materialTypesPanel;
	
	private final HashMap<MaterialType, HTMLPanel> materialTypeSwatchPanels = new HashMap<MaterialType, HTMLPanel>();
	
	private Material primaryMaterial;
	private Material secondaryMaterial;
	private final TextureUpdateListener textureUpdateListener = new TextureUpdateListener() {
		@Override
		public void onTextureUpdate(Material material) {
			if (material == primaryMaterial) {
				ImageBuffer texture = TextureStore.getInstance().getTexture(material);
				drawTexture(primaryBlock, texture);
			} else if (material == secondaryMaterial) {
				ImageBuffer texture = TextureStore.getInstance().getTexture(material);
				drawTexture(secondaryBlock, texture);
			}
		}
	};
	
	public Palette() {
		initWidget(uiBinder.createAndBindUi(this));
		TextureStore.getInstance().addUpdateListener(textureUpdateListener);
	}
	
	public Material getPrimaryMaterial() {
		return primaryMaterial;
	}

	public void setPrimaryMaterial(Material primaryMaterial) {
		this.primaryMaterial = primaryMaterial;
		ImageBuffer texture = TextureStore.getInstance().getTexture(primaryMaterial);
		drawTexture(primaryBlock, texture);
	}

	public Material getSecondaryMaterial() {
		return secondaryMaterial;
	}

	public void setSecondaryMaterial(Material secondaryMaterial) {
		this.secondaryMaterial = secondaryMaterial;
		ImageBuffer texture = TextureStore.getInstance().getTexture(secondaryMaterial);
		drawTexture(secondaryBlock, texture);
	}

	public void addMaterial(Material material) {
		Canvas swatch = createSwatch(material);
		paletteSwatches.add(swatch);
	}
	
	public void addMaterial(MaterialType type, Material material) {
		addMaterialType(type);
		
		Canvas swatch = createSwatch(material);
		materialTypeSwatchPanels.get(type).add(swatch);
	}
	
	private Canvas createSwatch(Material material) {
		final Canvas canvas = Canvas.createIfSupported();
		if (material != null) {
			canvas.setTitle(material.toString());
			
			ImageBuffer texture = TextureStore.getInstance().getTexture(material);
			drawTexture(canvas.getCanvasElement(), texture);
			
			final Material swatchMaterial = material;
			final TextureUpdateListener textureUpdateListener = new TextureUpdateListener() {
				@Override
				public void onTextureUpdate(Material material) {
					if (material == swatchMaterial) {
						ImageBuffer texture = TextureStore.getInstance().getTexture(material);
						drawTexture(canvas.getCanvasElement(), texture);
					}
				}
			};
			
			// TODO: swatches should be properly tracked so the texture update
			// listener can be removed if necessary
			TextureStore.getInstance().addUpdateListener(textureUpdateListener);
		}
		canvas.addClickHandler(new PaletteClickHandler(material));
		
		return canvas;
	}
	
	public void addMaterialType(MaterialType type) {
		if (!materialTypeSwatchPanels.containsKey(type)) {
			HTMLPanel typeSwatchPanel = new HTMLPanel("");
			materialTypeSwatchPanels.put(type, typeSwatchPanel);
			materialTypesPanel.insert(typeSwatchPanel,type.toString(),1.8,materialTypesPanel.getWidgetCount()-1);
		}
	}
	
//	public void removeSwatch(String imgSrc) {
//		for(int i=0; i<paletteSwatches.getWidgetCount(); i++) {
//			Image img = (Image) paletteSwatches.getWidget(i);
//			if (imgSrc.equals(img.getUrl())) {
//				// This needs to be fixed if this method is to be used...
//				paletteSwatches.removeChild(img);
//				return;
//			}
//		}
//	}
	
	private class PaletteClickHandler implements ClickHandler {
		private final Material material;

		public PaletteClickHandler(Material material) {
			this.material = material;
		}
		
		@Override
		public void onClick(ClickEvent event) {
			switch(event.getNativeButton()) {
			case NativeEvent.BUTTON_LEFT:
				setPrimaryMaterial(material);
				break;
			case NativeEvent.BUTTON_RIGHT:
				// TODO: this doesn't work.  See:
				//    http://stackoverflow.com/questions/3832155/how-to-implement-both-right-click-and-left-click-with-clickhandler-on-a-flextabl
				setSecondaryMaterial(material);
				break;
			default:
				// ignore middle-click
			}
		}
	}
	
	private static void drawTexture(CanvasElement canvas, ImageBuffer texture) {
		if (texture != null) {
			canvas.setWidth(texture.getWidth());
			canvas.setHeight(texture.getHeight());
			Context2d ctx = canvas.getContext2d();
			ctx.drawImage(texture.getCanvas(), 0, 0, texture.getWidth(), texture.getHeight());
		}
	}

}
