package com.kjksoft.mcdesigner.client;

import java.util.HashMap;
import java.util.Map.Entry;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.kjksoft.mcdesigner.client.module.MaterialsList;
import com.kjksoft.mcdesigner.client.module.Palette;
import com.kjksoft.mcdesigner.client.module.TileArea;

public class MCDesigner implements EntryPoint {
	
	private final TileArea tileArea = new TileArea();
	private final DockLayoutPanel mainPanel = new DockLayoutPanel(Unit.EM);
	private final DockLayoutPanel sidePanel = new DockLayoutPanel(Unit.EM);
	private final ToolPanel toolPanel = new ToolPanel();
	private final Palette palette = new Palette();
	private final Label mouseCoords = new Label("x: ; z:");
	private final PopupPanel materialsPanel = new PopupPanel(true,true);
	private final MaterialsList materialsList = new MaterialsList();
	
	private final HashMap<Integer,HashMap<Point,String>> layerMap = new HashMap<Integer, HashMap<Point,String>>();
	private int currentLayer = 0;
	
	@Override
	public void onModuleLoad() {
		// Initialize the tile area
		tileArea.setTileSize(32);
		tileArea.setSize("100%", "100%");
		tileArea.setStyleName("tilearea");
		
		toolPanel.addStyleName("toolpanel");
		
		materialsPanel.setWidget(materialsList);
		
		mouseCoords.addStyleName("mouseCoords");
		
		sidePanel.addSouth(mouseCoords, 2);
		sidePanel.add(palette);
		
		mainPanel.addNorth(toolPanel, 2.2);
		mainPanel.addWest(sidePanel, 10);
		mainPanel.add(tileArea);
		
		// Add the tile area to the document
		//RootPanel.get("tilePanel").add(tileArea);
		RootLayoutPanel.get().add(mainPanel);
		
		// Add mouse handler
		new TileMouseHandler();
		tileArea.addMouseMoveHandler(new MouseMoveHandler() {
			@Override
			public void onMouseMove(MouseMoveEvent event) {
				mouseCoords.setText(toCoordString(tileArea.getCoordsFromMouseEvent(event)));
			}
			
			String toCoordString(Point p) {
				return "x:" + p.x + "; z: " + p.y; 
			}
		});
		
		// Handlers for tool panel buttons
		toolPanel.clearButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				tileArea.clearTiles();
			}
		});
		toolPanel.clearAllButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				tileArea.clearTiles();
				layerMap.clear();
			}
		});
		toolPanel.materialsButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				materialsList.clearMaterials();
				HashMap<String,Integer> tileCounts = tileArea.countTiles();
				for(Entry<String,Integer> tileCount : tileCounts.entrySet()) {
					for(Material m : Material.values()) {
						if (tileCount.getKey().endsWith(m.imgSrc)) {
							materialsList.addMaterial(m, tileCount.getValue());
						}
					}
				}
				materialsPanel.center();
			}
		});
		toolPanel.materialsAllButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				materialsList.clearMaterials();
				
				HashMap<String,Integer> tileCounts = tileArea.countTiles();
				for(Entry<Integer,HashMap<Point,String>> entry : layerMap.entrySet()) {
					if (entry.getKey() == currentLayer) continue;
					countLayerTiles(entry.getValue(), tileCounts);
				}
				
				for(Entry<String,Integer> tileCount : tileCounts.entrySet()) {
					for(Material m : Material.values()) {
						if (tileCount.getKey().endsWith(m.imgSrc)) {
							materialsList.addMaterial(m, tileCount.getValue());
						}
					}
				}
				materialsPanel.center();
			}
			
			void countLayerTiles(HashMap<Point,String> layer, HashMap<String,Integer> result) {
				for(Entry<Point,String> entry : layer.entrySet()) {
					String imgSrc = entry.getValue();
					Integer count = result.get(imgSrc);
					result.put(imgSrc, count == null ? 1 : count+1);
				}
			}
		});
		toolPanel.prevLayerButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				layerMap.put(currentLayer, tileArea.getTiles());
				toolPanel.setLayer(--currentLayer);
				tileArea.clearTiles();
				if (layerMap.containsKey(currentLayer)) {
					drawTiles(layerMap.get(currentLayer));
				}
			}
		});
		toolPanel.nextLayerButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				layerMap.put(currentLayer, tileArea.getTiles());
				toolPanel.setLayer(++currentLayer);
				tileArea.clearTiles();
				if (layerMap.containsKey(currentLayer)) {
					drawTiles(layerMap.get(currentLayer));
				}
			}
		});
		
//		for(Material m : Material.values()) {
//			palette.addMaterial(m);
//		}
		for(MaterialType type : MaterialType.values()) {
			palette.addMaterialType(type);
			for(Material m : type.getMaterials()) {
				palette.addMaterial(type,m);
			}
		}
		palette.addMaterial(null);
		
		palette.setPrimaryMaterial(Material.DIRT);
	}
	
	private void onTilePaint(Point p) {
		switch(toolPanel.getSelectedTool()) {
		case PENCIL:
			drawTile(p,palette.getPrimaryMaterial().imgSrc);
			break;
		case ERASER:
			drawTile(p,null);
			break;
			
		// The following operations are not paint operations
		case FILL:
			// Do nothing
		}
	}
	
	private void onTileClick(Point p) {
		switch(toolPanel.getSelectedTool()) {
		case PENCIL:
			drawTile(p,palette.getPrimaryMaterial().imgSrc);
			break;
		case ERASER:
			drawTile(p,null);
			break;
		case FILL:
			// TODO
		}
	}
	
	private void drawTile(Point p, String src) {
		if (src == null) {
			tileArea.clearTile(p);
		} else {
			tileArea.setTile(p, src);
		}
	}
	
	private void drawTiles(HashMap<Point,String> tileMap) {
		for(Entry<Point,String> entry : tileMap.entrySet()) {
			tileArea.setTile(entry.getKey(),entry.getValue());
		}
	}
	
	private class TileMouseHandler implements MouseDownHandler, MouseUpHandler, MouseMoveHandler {

		private boolean isMouseDown = false;
		private Point mouseDownPoint;
		
		public TileMouseHandler() {
			tileArea.addMouseUpHandler(this);
			tileArea.addMouseDownHandler(this);
			tileArea.addMouseMoveHandler(this);
		}
		
		@Override
		public void onMouseMove(MouseMoveEvent event) {
			if (isMouseDown) {
				Point p = tileArea.getCoordsFromMouseEvent(event);
				onTilePaint(p);
			}
		}

		@Override
		public void onMouseUp(MouseUpEvent event) {
			isMouseDown = false;
			if (mouseDownPoint != null && mouseDownPoint.equals(tileArea.getCoordsFromMouseEvent(event))) {
				onTileClick(mouseDownPoint);
			}
			mouseDownPoint = null;
		}

		@Override
		public void onMouseDown(MouseDownEvent event) {
			if (event.getNativeButton() == NativeEvent.BUTTON_LEFT) { 
				isMouseDown = true;
				mouseDownPoint = tileArea.getCoordsFromMouseEvent(event);
			}
		}
	}
}
