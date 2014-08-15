package com.mcdraftingtable.bannerbuilder.client.pattern;

import com.mcdraftingtable.bannerbuilder.client.color.DyeColor;

public class LayerDefinition {
	public final BannerPattern pattern;
	public final DyeColor color;

	public LayerDefinition(BannerPattern pattern, DyeColor color) {
		this.pattern = pattern;
		this.color = color;
	}
}
