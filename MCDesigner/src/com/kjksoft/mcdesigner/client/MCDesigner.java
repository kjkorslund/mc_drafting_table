package com.kjksoft.mcdesigner.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.core.client.EntryPoint;
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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.kjksoft.mcdesigner.client.module.MaterialsList;
import com.kjksoft.mcdesigner.client.module.Palette;
import com.kjksoft.mcdesigner.client.module.tiles.Point;
import com.kjksoft.mcdesigner.client.module.tiles.TileModel3D;
import com.kjksoft.mcdesigner.client.module.tiles.TileView;

public class MCDesigner implements EntryPoint {
	
	private final TileView tileView = new TileView();
	//private final LayeredMaterialsModel layeredMaterialsModel = new LayeredMaterialsModel();
	private final TileModel3D tileModel = new TileModel3D();
	private final DockLayoutPanel mainPanel = new DockLayoutPanel(Unit.EM);
	private final DockLayoutPanel sidePanel = new DockLayoutPanel(Unit.EM);
	private final ToolPanel toolPanel = new ToolPanel();
	private final Palette palette = new Palette();
	private final Label mouseCoords = new Label("x: ; z:");
	private final PopupPanel materialsPanel = new PopupPanel(true,true);
	private final MaterialsList materialsList = new MaterialsList();
	
	@Override
	public void onModuleLoad() {
		// Initialize the tile area
		tileView.setTileSize(32);
		tileView.setSize("100%", "100%");
		tileView.setStyleName("tilearea");
		tileView.setModel(tileModel);
		
		toolPanel.addStyleName("toolpanel");
		
		materialsPanel.setWidget(materialsList);
		
		mouseCoords.addStyleName("mouseCoords");
		
		sidePanel.addSouth(mouseCoords, 2);
		sidePanel.add(palette);
		
		mainPanel.addNorth(toolPanel, 2.2);
		mainPanel.addWest(sidePanel, 10);
		mainPanel.add(tileView);
		
		// Add the tile area to the document
		//RootPanel.get("tilePanel").add(tileArea);
		RootLayoutPanel.get().add(mainPanel);
		
		// Add mouse handler
		new TileMouseHandler();
		tileView.addMouseMoveHandler(new MouseMoveHandler() {
			@Override
			public void onMouseMove(MouseMoveEvent event) {
				mouseCoords.setText(toCoordString(tileView.getCoordsFromMouseEvent(event)));
			}
			
			String toCoordString(Point p) {
				return "x:" + p.x + "; z: " + p.y; 
			}
		});
		
		// Handlers for tool panel buttons
		toolPanel.clearButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				tileModel.clearCurrentLayer();
			}
		});
		toolPanel.clearAllButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				tileModel.clearAll();
			}
		});
		toolPanel.materialsButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				materialsList.clearMaterials();
				Map<Material,Integer> materialCounts = tileModel.getCurrentLayerMaterialCount();
				for(Entry<Material,Integer> entry : materialCounts.entrySet()) {
					materialsList.addMaterial(entry.getKey(), entry.getValue());
				}
				materialsPanel.center();
			}
		});
		toolPanel.materialsAllButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				materialsList.clearMaterials();
				Map<Material,Integer> materialCounts = tileModel.getTotalMaterialCount();
				for(Entry<Material,Integer> entry : materialCounts.entrySet()) {
					materialsList.addMaterial(entry.getKey(), entry.getValue());
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
				tileModel.setCurrentLayer(tileModel.getCurrentLayer()-1);
				toolPanel.currentLayer.setInnerText("Layer " + 
						Integer.toString(tileModel.getCurrentLayer()));
			}
		});
		toolPanel.nextLayerButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				tileModel.setCurrentLayer(tileModel.getCurrentLayer()+1);
				toolPanel.currentLayer.setInnerText("Layer " + 
						Integer.toString(tileModel.getCurrentLayer()));
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
			drawTile(p,palette.getPrimaryMaterial());
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
			drawTile(p,palette.getPrimaryMaterial());
			break;
		case ERASER:
			drawTile(p,null);
			break;
		case FILL:
			// TODO
		}
	}
	
	private void drawTile(Point p, Material m) {
		if (m == null) {
			tileModel.removeTile(p);
		} else {
			tileModel.putTile(p, m);
		}
	}
	
	private void drawTiles(HashMap<Point,Material> tileMap) {
		tileModel.putTiles(tileModel.getCurrentLayer(),tileMap);
	}
	
	private class TileMouseHandler implements MouseDownHandler, MouseUpHandler, MouseMoveHandler {

		private boolean isMouseDown = false;
		private Point mouseDownPoint;
		
		public TileMouseHandler() {
			tileView.addMouseUpHandler(this);
			tileView.addMouseDownHandler(this);
			tileView.addMouseMoveHandler(this);
		}
		
		@Override
		public void onMouseMove(MouseMoveEvent event) {
			if (isMouseDown) {
				Point p = tileView.getCoordsFromMouseEvent(event);
				onTilePaint(p);
			}
		}

		@Override
		public void onMouseUp(MouseUpEvent event) {
			isMouseDown = false;
			if (mouseDownPoint != null && mouseDownPoint.equals(tileView.getCoordsFromMouseEvent(event))) {
				onTileClick(mouseDownPoint);
			}
			mouseDownPoint = null;
		}

		@Override
		public void onMouseDown(MouseDownEvent event) {
			if (event.getNativeButton() == NativeEvent.BUTTON_LEFT) { 
				isMouseDown = true;
				mouseDownPoint = tileView.getCoordsFromMouseEvent(event);
			}
		}
	}
}
