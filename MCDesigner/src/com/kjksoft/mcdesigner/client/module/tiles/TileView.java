package com.kjksoft.mcdesigner.client.module.tiles;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.CanvasElement;
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
	private final int[] zoomLevels = new int[] { 8,16,32,48 };
	private int iZoomLevel = 2;
	
	private final HashMap<String, ImageElement> imgSourceMap = new HashMap<String, ImageElement>();
	private final HashMap<Point,Tile> tileMap = new HashMap<Point, Tile>();
	private final DivElement tileContainer;
	private Point scrollOffset = new Point(0,0);
	
	private ITileModel model;
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
		
		refreshZoom();
	}
	
	public ITileModel getModel() {
		return model;
	}

	public void setModel(ITileModel tileModel) {
		if (model != null) {
			model.removeTileListener(tileListener);
		}
		model = tileModel;
		model.addTileListener(tileListener);
	}
	
	public void zoomIn() {
		if (!canZoomIn()) throw new RuntimeException("cannot zoom in");
		iZoomLevel++;
		refreshZoom();
	}
	
	public void zoomOut() {
		if (!canZoomOut()) throw new RuntimeException("cannot zoom out");
		iZoomLevel--;
		refreshZoom();
	}
	
	private void refreshZoom() {
		int tileSize = tileSize();
		tileContainer.getStyle().setBackgroundImage("url('images/grid_" + tileSize + ".png')");
		tileContainer.getStyle().setProperty("backgroundSize", tileSize, Unit.PX);
		tileContainer.getStyle().setProperty("backgroundRepeat", "repeat");
		updateAllTileCoords();
	}
	
	private int tileSize() {
		return zoomLevels[iZoomLevel];
	}

	public boolean canZoomIn() {
		return iZoomLevel < zoomLevels.length - 1;
	}
	
	public boolean canZoomOut() {
		return iZoomLevel > 0;
	}

	private void clearTile(Point p) {
		Tile tile = tileMap.remove(p);
		if (tile != null) {
			tileContainer.removeChild(tile.getCanvas());
		}
	}
	
	public Point getScrollOffset() {
		return scrollOffset;
	}
	
	public void setScrollOffset(Point offset) {
		this.scrollOffset = offset;
		updateAllTileCoords();
	}
	
	private void setTile(Point p, String imgSrc) {
		Tile tile = tileMap.get(p);
		if (tile == null) {
			tile = new Tile();
			tileContainer.appendChild(tile.getCanvas());
			tileMap.put(p, tile);
		}

		ImageElement img = getSourceImage(imgSrc);
		tile.setSourceImage(img);
		
		tile.draw(p);
	}
	
	private ImageElement getSourceImage(String imgSrc) {
		ImageElement result = imgSourceMap.get(imgSrc);
		if (result == null) {
			result = Document.get().createImageElement();
			result.setSrc(imgSrc);
		}
		return result;
	}
	
	private void updateAllTileCoords() {
		for(Entry<Point,Tile> entry : tileMap.entrySet()) {
			Point p = entry.getKey();
			Tile tile = entry.getValue();
			tile.draw(p);
		}
	}
	
	public <H extends EventHandler> Point getCoordsFromMouseEvent(MouseEvent<H> event) {
		Point p = new Point(
				event.getRelativeX(getElement()),
				event.getRelativeY(getElement())
			);
		int tileSize = tileSize();
		return new Point(p.x/tileSize + scrollOffset.x,p.y/tileSize + scrollOffset.y);
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
	
	private final class Tile {
		private final CanvasElement canvas;
		private ImageElement sourceImage;
		
		public Tile() {
			this.canvas = Document.get().createCanvasElement();
			this.canvas.getStyle().setPosition(Position.ABSOLUTE);
			this.canvas.getStyle().setZIndex(-1);
		}

		public ImageElement getSourceImage() {
			return sourceImage;
		}

		public void setSourceImage(ImageElement sourceImage) {
			this.sourceImage = sourceImage;
		}

		public CanvasElement getCanvas() {
			return canvas;
		}
		
		public void draw(Point p) {
			int tileSize = tileSize();
			canvas.getStyle().setProperty("left", Integer.toString((p.x - scrollOffset.x)*tileSize) + "px");
			canvas.getStyle().setProperty("top", Integer.toString((p.y - scrollOffset.y)*tileSize) + "px");
			canvas.setWidth(tileSize);
			canvas.setHeight(tileSize);
			
			if (sourceImage != null) {
				Context2d ctx = canvas.getContext2d();
				ctx.drawImage(sourceImage, 0, 0, tileSize, tileSize);
			}
		}
	}
}
