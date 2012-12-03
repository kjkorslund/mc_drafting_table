package com.kjksoft.mcdesigner.client.module.tiles;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasAllMouseHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

public class TileView extends Widget implements HasAllMouseHandlers, HasClickHandlers {
	private final HashMap<Point,ImageElement> tileMap = new HashMap<Point, ImageElement>();
	private final DivElement tileContainer;
	
	private ITileModel model;
	private int tileSize;
	private final TileListener tileListener = new TileListener() {
		@Override
		public void onTileUpdate(ITileModel model, Point p) {
			updateTile(p,model.getTile(p));
		}

		@Override
		public void onTilesUpdate(ITileModel model, Collection<Point> points) {
			Iterator<Point> pointIter = points.iterator();
			Iterator<String> imgSrcIter = model.getTiles(points).iterator();
			while(pointIter.hasNext()) {
				updateTile(pointIter.next(),imgSrcIter.next());
			}
		}
		
		void updateTile(Point p, String imgSrc) {
			if (imgSrc == null) {
				clearTile(p);
			} else {
				setTile(p,imgSrc);
			}
		}
	};
	
	public TileView() {
		setElement(Document.get().createDivElement());
		//getElement().getStyle().setOverflow(Overflow.HIDDEN);
		
		tileContainer = Document.get().createDivElement();
		tileContainer.getStyle().setPosition(Position.RELATIVE);
		tileContainer.getStyle().setZIndex(-2);
		tileContainer.getStyle().setWidth(100, Unit.PCT);
		tileContainer.getStyle().setHeight(100, Unit.PCT);
		getElement().appendChild(tileContainer);
		
		setTileSize(32);
	}
	
	public ITileModel getModel() {
		return model;
	}

	public void setModel(ITileModel tileModel) {
		this.model.removeTileListener(tileListener);
		this.model = tileModel;
		this.model.addTileListener(tileListener);
	}

	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
		tileContainer.getStyle().setBackgroundImage("url('images/grid_32.png')");
		tileContainer.getStyle().setProperty("backgroundSize", tileSize, Unit.PX);
		tileContainer.getStyle().setProperty("backgroundRepeat", "repeat");
	}

	public HashMap<Point,String> getTiles() {
		HashMap<Point, String> result = new HashMap<Point, String>();
		for(Entry<Point,ImageElement> entry : tileMap.entrySet()) {
			result.put(entry.getKey(), entry.getValue().getSrc());
		}
		return result;
	}
	
	public HashMap<String,Integer> countTiles() {
		HashMap<String,Integer> result = new HashMap<String, Integer>();
		for(Entry<Point,ImageElement> entry : tileMap.entrySet()) {
			String imgSrc = entry.getValue().getSrc();
			Integer count = result.get(imgSrc);
			result.put(imgSrc, count == null ? 1 : count+1);
		}
		return result;
	}
	
	private void clearTile(Point p) {
		ImageElement img = tileMap.remove(p);
		if (img != null) {
			tileContainer.removeChild(img);
		}
	}
	
	private void setTile(Point p, String imgSrc) {
		ImageElement img = tileMap.get(p);
		if (img == null) {
			img = Document.get().createImageElement();
			img.getStyle().setPosition(Position.ABSOLUTE);
			img.getStyle().setZIndex(-1);
			tileContainer.appendChild(img);
			tileMap.put(p, img);
		}
		img.setSrc(imgSrc);
		updateTile(p,img);
	}
	
	private void updateTile(Point p, ImageElement img) {
		img.getStyle().setProperty("left", Integer.toString(p.x*tileSize) + "px");
		img.getStyle().setProperty("top", Integer.toString(p.y*tileSize) + "px");
		img.getStyle().setProperty("width", Integer.toString(tileSize) + "px");
		img.getStyle().setProperty("height", Integer.toString(tileSize) + "px");
	}
	
	public <H extends EventHandler> Point getCoordsFromMouseEvent(MouseEvent<H> event) {
		Point p = new Point(
				event.getRelativeX(getElement()),
				event.getRelativeY(getElement())
			);
		return new Point(p.x/tileSize,p.y/tileSize);
	}

	@Override
	public HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {
		return addDomHandler(handler, MouseDownEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
		return addDomHandler(handler, MouseMoveEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
		return addDomHandler(handler, MouseOutEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
		return addDomHandler(handler, MouseOverEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseUpHandler(MouseUpHandler handler) {
		return addDomHandler(handler, MouseUpEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseWheelHandler(MouseWheelHandler handler) {
		return addDomHandler(handler, MouseWheelEvent.getType());
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return addDomHandler(handler, ClickEvent.getType());
	}
}
