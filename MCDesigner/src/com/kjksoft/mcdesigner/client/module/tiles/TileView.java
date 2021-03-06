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
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.FontStyle;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.TextAlign;
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
import com.kjksoft.mcdesigner.client.canvas.ImageBuffer;

public class TileView extends Widget implements HasAllMouseHandlers, HasClickHandlers {
	private final int[] zoomLevels = new int[] { 8,16,32,48 };
	private int iZoomLevel = 2;
	
	private final HashMap<Point,Tile> tileMap = new HashMap<Point, Tile>();
	private final DivElement tileContainer;
	private Point scrollOffset = new Point(0,0);
	
	private ITileModel<ImageBuffer> model;
	private final TileListener<ImageBuffer> tileListener = new TileListener<ImageBuffer>() {
		@Override
		public void onTileUpdate(ITileModel<ImageBuffer> model, Point p) {
			updateTile(p,model.getTile(p));
		}

		@Override
		public void onTilesUpdate(ITileModel<ImageBuffer> model, Collection<Point> points) {
			Iterator<Point> pointIter = points.iterator();
			Iterator<ImageBuffer> imgSrcIter = model.getTiles(points).iterator();
			while(pointIter.hasNext()) {
				updateTile(pointIter.next(),imgSrcIter.next());
			}
		}
		
		void updateTile(Point p, ImageBuffer imgBuffer) {
			if (imgBuffer == null) {
				clearTile(p);
			} else {
				setTile(p,imgBuffer);
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
		
		// TODO: figure out a way to add a widget here
//		VersionWatermark watermark = new VersionWatermark();
		
		ImageElement watermark = Document.get().createImageElement();
		watermark.getStyle().setDisplay(Display.BLOCK);
		watermark.setSrc("res/draftingtable_watermark.png");
		
		SpanElement version = Document.get().createSpanElement();
		version.getStyle().setColor("darkgray");
		version.getStyle().setFontStyle(FontStyle.ITALIC);
		version.getStyle().setDisplay(Display.BLOCK);
		version.getStyle().setTextAlign(TextAlign.RIGHT);
		version.getStyle().setPaddingRight(1.0, Unit.EM);
		version.setInnerText("Version: preview-20140714");
		
		DivElement watermarkContainer = Document.get().createDivElement();
		watermarkContainer.getStyle().setPosition(Position.ABSOLUTE);
		watermarkContainer.getStyle().setRight(1.0, Unit.EM);
		watermarkContainer.getStyle().setBottom(1.0, Unit.EM);
		watermarkContainer.getStyle().setZIndex(-3);
		
		watermarkContainer.appendChild(watermark);
		watermarkContainer.appendChild(version);
		tileContainer.appendChild(watermarkContainer);
		
		
		refreshZoom();
	}
	
	public ITileModel<ImageBuffer> getModel() {
		return model;
	}

	public void setModel(ITileModel<ImageBuffer> tileModel) {
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
	
	private void setTile(Point p, ImageBuffer imgSource) {
		Tile tile = tileMap.get(p);
		if (tile == null) {
			tile = new Tile();
			tileContainer.appendChild(tile.getCanvas());
			tileMap.put(p, tile);
		}

		tile.setSource(imgSource);
		
		tile.draw(p);
	}
	
	public void updateAllTileSources() {
		for(Entry<Point,Tile> entry : tileMap.entrySet()) {
			Point p = entry.getKey();
			Tile tile = entry.getValue();
			ImageBuffer imgSource = model.getTile(p);
			tile.setSource(imgSource);
			tile.draw(p);
		}
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
		private ImageBuffer source;
		
		public Tile() {
			this.canvas = Document.get().createCanvasElement();
			this.canvas.getStyle().setPosition(Position.ABSOLUTE);
			this.canvas.getStyle().setZIndex(-1);
		}

		public ImageBuffer getSource() {
			return source;
		}

		public void setSource(ImageBuffer source) {
			this.source = source;
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
			
			if (source != null) {
				Context2d ctx = canvas.getContext2d();
				ctx.drawImage(source.getCanvas(), 0, 0, tileSize, tileSize);
			}
		}
	}
}
