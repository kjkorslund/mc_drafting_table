package com.mcdraftingtable.bannerbuilder.client.pattern;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.Context2d.TextAlign;
import com.google.gwt.canvas.dom.client.Context2d.TextBaseline;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.dom.client.CanvasElement;

/**
 * This enum is used only for testing and development of the pattern chooser.
 * The actual pattern class will be swapped in once it's ready.
 * 
 * @author Kevin
 * 
 */
public enum BannerTestPattern {
	ONE,
	TWO,
	THREE,
	FOUR,
	FIVE,
	SIX,
	SEVEN,
	EIGHT,
	NINE,
	TEN,
	ELEVEN,
	TWELVE,
	THIRTEEN,
	FOURTEEN,
	FIFTEEN,
	SIXTEEN
	;
	
	public void drawOn(CanvasElement canvas) {
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		
		Context2d context2d = canvas.getContext2d();
		
		context2d.setFillStyle(CssColor.make("white"));
		context2d.fillRect(0, 0, width, height);
		
		context2d.setFillStyle(CssColor.make("black"));
		context2d.setTextAlign(TextAlign.CENTER);
		context2d.setTextBaseline(TextBaseline.MIDDLE);
		context2d.fillText(this.name(), width/2, height/2, width);
	}
}
