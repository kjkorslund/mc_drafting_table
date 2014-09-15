package com.mcdraftingtable.bannerbuilder.client.ui;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Widget;
import com.mcdraftingtable.bannerbuilder.client.image.ImageBuffer;

public class MaterialsPanel extends Composite {
	private static final int MATERIALS_LIST_NCOLS = 5;

	private static MaterialsPanelUiBinder uiBinder = GWT
			.create(MaterialsPanelUiBinder.class);

	interface MaterialsPanelUiBinder extends UiBinder<Widget, MaterialsPanel> {
	}
	
	@UiField Grid materialsList;
	
	public MaterialsPanel() {
		initWidget(uiBinder.createAndBindUi(this));

		ArrayList<MaterialCount> testMaterialCounts = new ArrayList<>();
		for(int i=0; i<12; i++) {
			MaterialCount materialCount = createTestMaterialCount(i+5);
			testMaterialCounts.add(materialCount);
		}
		setMaterialsListContents(testMaterialCounts);
	}
	
	public void setMaterialsListContents(Collection<MaterialCount> materialCounts) {
		resizeMaterialsList(materialCounts.size());
		int row = 0;
		int col = 0;
		for(MaterialCount materialCount : materialCounts) {
			materialsList.setWidget(row, col, materialCount);
			if (++col >= MATERIALS_LIST_NCOLS) {
				col = 0;
				row++;
			}
		}
	}
	
	private void resizeMaterialsList(int cellCount) {
		int nRows = cellCount / MATERIALS_LIST_NCOLS;
		if (cellCount % MATERIALS_LIST_NCOLS > 0) nRows++;
		materialsList.resize(nRows, MATERIALS_LIST_NCOLS);
	}
	
	private static MaterialCount createTestMaterialCount(int count) {
		MaterialCount materialCount = new MaterialCount();
		
		ImageBuffer testIcon = new ImageBuffer();
		String uri = "res/image/items/barrier.png";
		testIcon.loadFromImgSrc(uri);
		materialCount.setMaterialCount(testIcon, count);
		materialCount.getElement().getStyle().setMarginRight(8.0, Unit.PX);
		materialCount.getElement().getStyle().setMarginBottom(2.0, Unit.PX);
		
		return materialCount;
	}

}
