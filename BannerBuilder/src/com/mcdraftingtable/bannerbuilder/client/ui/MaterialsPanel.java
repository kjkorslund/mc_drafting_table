package com.mcdraftingtable.bannerbuilder.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.mcdraftingtable.bannerbuilder.client.image.ImageBuffer;

public class MaterialsPanel extends Composite {

	private static MaterialsPanelUiBinder uiBinder = GWT
			.create(MaterialsPanelUiBinder.class);

	interface MaterialsPanelUiBinder extends UiBinder<Widget, MaterialsPanel> {
	}
	
	@UiField FlowPanel materialsList;

	public MaterialsPanel() {
		initWidget(uiBinder.createAndBindUi(this));

		// FIXME [kjk] The problem with FlowPanel is that the cells don't line
		// up vertically due to varying cell widths. It may be more complex to
		// implement, but I think it has to be a grid.
		for(int i=0; i<12; i++) {
			MaterialCount materialCount = new MaterialCount();
			
			ImageBuffer testIcon = new ImageBuffer();
			String uri = "res/image/items/barrier.png";
			testIcon.loadFromImgSrc(uri);
			materialCount.setMaterialCount(testIcon, i+5);
			materialCount.getElement().getStyle().setMarginRight(8.0, Unit.PX);
			materialCount.getElement().getStyle().setMarginBottom(2.0, Unit.PX);
			
			materialsList.add(materialCount);
		}
	}

}
