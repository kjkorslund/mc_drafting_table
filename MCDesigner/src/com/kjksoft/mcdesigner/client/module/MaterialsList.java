package com.kjksoft.mcdesigner.client.module;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.kjksoft.mcdesigner.client.Material;

public class MaterialsList extends Composite {

	private static MaterialsListUiBinder uiBinder = GWT
			.create(MaterialsListUiBinder.class);

	interface MaterialsListUiBinder extends UiBinder<Widget, MaterialsList> {
	}

	public MaterialsList() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField UListElement ulMaterials;

	public MaterialsList(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public void clearMaterials() {
		while(ulMaterials.hasChildNodes()) {
			ulMaterials.removeChild(ulMaterials.getLastChild());
		}
	}

	public void addMaterial(Material material, int count) {
		LIElement li = Document.get().createLIElement();
		ImageElement img = Document.get().createImageElement();
		img.setSrc(material.texture.getImgSrc());
		img.setAlt(material.textureName);
		
		li.appendChild(img);
		li.appendChild(Document.get().createTextNode(" " + formatMaterialsCount(count)));
		
		ulMaterials.appendChild(li);
	}
	
	private String formatMaterialsCount(int count) {
		StringBuilder sb = new StringBuilder(Integer.toString(count));
		
		int stacks = count / 64;
		if (stacks > 0) {
			int blocks = count % 64;
			int largechests = stacks / 54;
			
			sb.append(" (");
			if (largechests > 0) {
				stacks = stacks % 54;
				
				sb.append(largechests);
				sb.append(" LChest");
				if (largechests > 1) sb.append('s');
				if (stacks > 0 || blocks > 0) {
					sb.append(" + ");
				}
			}
			if (stacks > 0) {
				sb.append(stacks);
				sb.append(" stack");
				if (stacks > 1) sb.append('s');
				if (blocks > 0) {
					sb.append(" + ");
				}
			}
			if (blocks > 0) {
				sb.append(blocks);
				sb.append(" block");
				if (blocks > 1) sb.append('s');
			}
			sb.append(')');
		}
		
		return sb.toString();
	}
}
