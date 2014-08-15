package com.mcdraftingtable.bannerbuilder.client.color;

import com.google.gwt.canvas.dom.client.CssColor;

public class RGB {
	public final int r;
	public final int g;
	public final int b;

	public RGB(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public String toCssString() {
		return "rgb(" + r + "," + g + "," + b + ")";
	}
	
	public CssColor toCssColor() {
		return CssColor.make(toCssString());
	}
}
