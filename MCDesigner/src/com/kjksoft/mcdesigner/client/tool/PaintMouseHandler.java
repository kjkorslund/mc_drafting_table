package com.kjksoft.mcdesigner.client.tool;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.kjksoft.mcdesigner.client.module.tiles.Point;
import com.kjksoft.mcdesigner.client.module.tiles.TileView;

public abstract class PaintMouseHandler extends ToolMouseHandler {
	
	@Override
	protected void onMouseDown(TileView tileView, MouseDownEvent event) {
		// [kjk] Do nothing
	}

	@Override
	protected void onMouseMove(TileView tileView, MouseMoveEvent event) {
		if (isMouseDown()) {
			Point p = tileView.getCoordsFromMouseEvent(event);
			onTilePaint(tileView,p);
		}
	}
	
	@Override
	protected void onMouseUp(TileView tileView, MouseUpEvent event) {
		if (!isMouseDragged()) {
			// [kjk] The mouse was never moved, so we still need to paint the
			// clicked tile
			Point p = tileView.getCoordsFromMouseEvent(event);
			onTilePaint(tileView,p);
		}
	}
	
	protected abstract void onTilePaint(TileView tileView, Point p);
}
