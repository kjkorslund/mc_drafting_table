package com.kjksoft.mcdesigner.client.tool;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.kjksoft.mcdesigner.client.module.tiles.Point;
import com.kjksoft.mcdesigner.client.module.tiles.TileView;

/**
 * Abstract representation of a tool handler that operates on the vector between
 * the mousedown location and mouseup location (such as line and shape tools)
 * 
 * @author Kevin
 * 
 */
public abstract class VectorMouseHandler extends ToolMouseHandler {
	
	private Point start = null;
	private Point end = null;

	@Override
	protected void onMouseDown(TileView tileView, MouseDownEvent event) {
		start = tileView.getCoordsFromMouseEvent(event);
		end = start;
		onVectorUpdate(tileView, start, end);
	}

	@Override
	protected void onMouseUp(TileView tileView, MouseUpEvent event) {
		Point p = tileView.getCoordsFromMouseEvent(event);
		onFinalVectorUpdate(tileView, start, p);
		start = null;
		end = null;
	}

	@Override
	protected void onMouseMove(TileView tileView, MouseMoveEvent event) {
		if (isMouseDown()) {
			Point p = tileView.getCoordsFromMouseEvent(event);
			if (!end.equals(p)) {
				onVectorUpdate(tileView, start, p);
				end = p;
			}
		}
	}

	/**
	 * Called whenever the vector coordinates change while the mouse is still down.
	 * 
	 * @param tileView
	 * @param isFinal
	 *            true if this is the final update (mouse was released); false
	 *            otherwise
	 */
	protected abstract void onVectorUpdate(TileView tileView, Point start, Point end);
	
	/**
	 * Called when the mouse is released, producing the final vector coordinates
	 * 
	 * @param tileView
	 */
	protected abstract void onFinalVectorUpdate(TileView tileView, Point start, Point end);
}
