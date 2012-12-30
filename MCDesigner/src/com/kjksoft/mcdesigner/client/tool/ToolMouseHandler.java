package com.kjksoft.mcdesigner.client.tool;

import java.util.HashMap;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.kjksoft.mcdesigner.client.module.tiles.Point;
import com.kjksoft.mcdesigner.client.module.tiles.TileView;

public abstract class ToolMouseHandler {
	Point mouseDownPoint = null;
	boolean isMouseDown = false;
	boolean isMouseDragged = false;

	private final HashMap<TileView, HandlerRegistration> mouseUpRegistrations = new HashMap<TileView, HandlerRegistration>();
	private final HashMap<TileView, HandlerRegistration> mouseDownRegistrations = new HashMap<TileView, HandlerRegistration>();
	private final HashMap<TileView, HandlerRegistration> mouseMoveRegistrations = new HashMap<TileView, HandlerRegistration>();
	
	public void installOn(TileView tileView) {
		InternalMouseHandler imh = new InternalMouseHandler(tileView);
		mouseUpRegistrations.put(tileView, tileView.addMouseUpHandler(imh));
		mouseDownRegistrations.put(tileView, tileView.addMouseDownHandler(imh));
		mouseMoveRegistrations.put(tileView, tileView.addMouseMoveHandler(imh));
	}
	
	public void removeFrom(TileView tileView) {
		HandlerRegistration registration = mouseUpRegistrations.remove(tileView);
		if (registration != null) registration.removeHandler();
		
		registration = mouseDownRegistrations.remove(tileView);
		if (registration != null) registration.removeHandler();
		
		registration = mouseMoveRegistrations.remove(tileView);
		if (registration != null) registration.removeHandler();
	}
	
	protected boolean isMouseDown() {
		return isMouseDown;
	}
	
	protected boolean isMouseDragged() {
		return isMouseDragged;
	}
	
	protected Point getMouseDownPoint() {
		return mouseDownPoint;
	}
	
	protected abstract void onMouseDown(TileView tileView, MouseDownEvent event);
	protected abstract void onMouseUp(TileView tileView, MouseUpEvent event);
	protected abstract void onMouseMove(TileView tileView, MouseMoveEvent event);
	
	private class InternalMouseHandler implements MouseDownHandler, MouseUpHandler, MouseMoveHandler {
		private TileView tileView;

		public InternalMouseHandler(TileView tileView) {
			this.tileView = tileView;
		}
		
		@Override
		public void onMouseMove(MouseMoveEvent event) {
			ToolMouseHandler.this.onMouseMove(tileView, event);
		}

		@Override
		public void onMouseUp(MouseUpEvent event) {
			isMouseDown = false;
			ToolMouseHandler.this.onMouseUp(tileView, event);
			mouseDownPoint = null;
			isMouseDragged = false;
		}

		@Override
		public void onMouseDown(MouseDownEvent event) {
			isMouseDown = true;
			mouseDownPoint = tileView.getCoordsFromMouseEvent(event);
			ToolMouseHandler.this.onMouseDown(tileView, event);
		}
	}
}
