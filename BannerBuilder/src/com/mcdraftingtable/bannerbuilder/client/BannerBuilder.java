package com.mcdraftingtable.bannerbuilder.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.mcdraftingtable.bannerbuilder.client.DesignOverlay.ConfigurationData;
import com.mcdraftingtable.bannerbuilder.client.pattern.IPatternRecipe;
import com.mcdraftingtable.bannerbuilder.client.pattern.LayerDefinition;
import com.mcdraftingtable.bannerbuilder.client.pattern.PatternRecipes;
import com.mcdraftingtable.bannerbuilder.client.recipe.IRecipe;
import com.mcdraftingtable.bannerbuilder.client.ui.BannerDisplay;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class BannerBuilder extends Composite implements EntryPoint {

	interface BannerBuilderUiBinder extends UiBinder<Widget, BannerBuilder> {
	}
	
	static interface BannerBuilderStyle extends CssResource {
		String bannerBackground();
		String overlayPanel();
	}

	private static BannerBuilderUiBinder uiBinder = GWT
			.create(BannerBuilderUiBinder.class);
	
	@UiField InstructionsOverlay instructionsOverlay;
	@UiField DesignOverlay designOverlay;
	@UiField BannerDisplay bannerDisplay;
	@UiField BannerBuilderStyle style;
	
	public BannerBuilder() {
		initWidget(uiBinder.createAndBindUi(this));
		
		designOverlay.addUpdateListener(new DesignOverlay.ConfigurationUpdateListener() {
			@Override
			public void onConfigurationUpdate(ConfigurationData configData) {
				bannerDisplay.setBaseColor(configData.getBaseColor());
				bannerDisplay.setLayerDefinitions(configData.getLayerDefinitions());
//				instructionsOverlay.setIngredientCounts(getIngredientCounts(configData));
				instructionsOverlay.setRecipeSteps(getRecipes(configData));
			}
		});
	}
	
//	private HashMap<IIngredient, Integer> getIngredientCounts(ConfigurationData configData) {
//		
//	}
	
	private List<IRecipe> getRecipes(ConfigurationData configData) {
		ArrayList<IRecipe> recipes = new ArrayList<>();
		IRecipe baseRecipe = PatternRecipes.BANNER_BASE.toRecipe(configData.getBaseColor());
		recipes.add(baseRecipe);
		
		for(LayerDefinition layerDefinition : configData.getLayerDefinitions()) {
			IRecipe recipe = getRecipe(layerDefinition);
			if (recipe != null) {
				recipes.add(recipe);
			}
		}
		return recipes;
	}
	
	private IRecipe getRecipe(LayerDefinition layerDefinition) {
		IPatternRecipe patternRecipe = PatternRecipes.forPattern(layerDefinition.pattern);
		return patternRecipe == null ? null : patternRecipe.toRecipe(layerDefinition.color);
	}
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootLayoutPanel.get().add(this);
	}
}
