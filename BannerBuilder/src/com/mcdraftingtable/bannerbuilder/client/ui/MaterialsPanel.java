package com.mcdraftingtable.bannerbuilder.client.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Widget;
import com.mcdraftingtable.bannerbuilder.client.image.ImageBuffer;
import com.mcdraftingtable.bannerbuilder.client.recipe.IIngredient;

public class MaterialsPanel extends Composite {
	private static final int MATERIALS_LIST_NCOLS = 5;

	private static MaterialsPanelUiBinder uiBinder = GWT
			.create(MaterialsPanelUiBinder.class);

	interface MaterialsPanelUiBinder extends UiBinder<Widget, MaterialsPanel> {
	}
	
	@UiField Grid materialsList;
	
	public MaterialsPanel() {
		initWidget(uiBinder.createAndBindUi(this));

		HashMap<IIngredient, Integer> testIngredientCounts = new HashMap<>();
		for(int i=0; i<12; i++) {
			testIngredientCounts.put(createTestIngredient(), i+100);
		}
		setIngredientCounts(testIngredientCounts);
	}
	
	public void setIngredientCounts(HashMap<IIngredient, Integer> ingredientCounts) {
		ArrayList<MaterialCount> materialCounts = new ArrayList<>();
		for(IIngredient ingredient : ingredientCounts.keySet()) {
			ImageBuffer ingredientIcon = ingredient.getImageBuffer();
			int count = ingredientCounts.get(ingredient);
			
			MaterialCount materialCount = new MaterialCount();
			materialCount.setMaterialCount(ingredientIcon, count);
			materialCounts.add(materialCount);
		}
		setMaterialsListContents(materialCounts);
	}
	
	private void setMaterialsListContents(Collection<MaterialCount> materialCounts) {
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
	
	private static IIngredient createTestIngredient() {
		final ImageBuffer testIcon = new ImageBuffer();
		String uri = "res/image/items/barrier.png";
		testIcon.loadFromImgSrc(uri);
		
		return new IIngredient() {
			@Override
			public String getName() {
				return "Test ingredient";
			}
			
			@Override
			public ImageBuffer getImageBuffer() {
				return testIcon;
			}
		};
	}

}
