package com.kjksoft.mcdesigner.client.module;

import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.kjksoft.mcdesigner.client.Material;
import com.kjksoft.mcdesigner.client.MaterialType;

public class Palette extends Composite {

	private static PaletteUiBinder uiBinder = GWT.create(PaletteUiBinder.class);

	interface PaletteUiBinder extends UiBinder<Widget, Palette> {
	}
	
	@UiField ImageElement primaryBlock;
	@UiField ImageElement secondaryBlock;
	@UiField HTMLPanel paletteSwatches;
	@UiField StackLayoutPanel materialTypesPanel;
	
	private final HashMap<MaterialType, HTMLPanel> materialTypeSwatchPanels = new HashMap<MaterialType, HTMLPanel>();
	
	private Material primaryMaterial;
	private Material secondaryMaterial;
	
	public Palette() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public Material getPrimaryMaterial() {
		return primaryMaterial;
	}

	public void setPrimaryMaterial(Material primaryMaterial) {
		this.primaryMaterial = primaryMaterial;
		this.primaryBlock.setSrc(primaryMaterial.imgSrc);
	}

	public Material getSecondaryMaterial() {
		return secondaryMaterial;
	}

	public void setSecondaryMaterial(Material secondaryMaterial) {
		this.secondaryMaterial = secondaryMaterial;
		this.primaryBlock.setSrc(secondaryMaterial.imgSrc);
	}

	public void addMaterial(Material material) {
		Image img = (material == null) ? new Image() : new Image(material.imgSrc);
		img.addClickHandler(new PaletteClickHandler(material));
		paletteSwatches.add(img);
	}
	
	public void addMaterial(MaterialType type, Material material) {
		Image img = (material == null) ? new Image() : new Image(material.imgSrc);
		img.addClickHandler(new PaletteClickHandler(material));
		
		addMaterialType(type);
		materialTypeSwatchPanels.get(type).add(img);
	}
	
	public void addMaterialType(MaterialType type) {
		if (!materialTypeSwatchPanels.containsKey(type)) {
			HTMLPanel typeSwatchPanel = new HTMLPanel("");
			materialTypeSwatchPanels.put(type, typeSwatchPanel);
			materialTypesPanel.insert(typeSwatchPanel,type.toString(),2.5,materialTypesPanel.getWidgetCount()-1);
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

}
