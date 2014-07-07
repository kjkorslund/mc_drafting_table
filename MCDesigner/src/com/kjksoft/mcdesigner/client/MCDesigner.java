package com.kjksoft.mcdesigner.client;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.kjksoft.mcdesigner.client.lib.zipjs.JsZip;
import com.kjksoft.mcdesigner.client.materials.ImgSrcTextureLoader;
import com.kjksoft.mcdesigner.client.materials.Material;
import com.kjksoft.mcdesigner.client.materials.MaterialType;
import com.kjksoft.mcdesigner.client.materials.ResourcePackTextureLoader;
import com.kjksoft.mcdesigner.client.materials.TextureStore;
import com.kjksoft.mcdesigner.client.module.MaterialsList;
import com.kjksoft.mcdesigner.client.module.Palette;
import com.kjksoft.mcdesigner.client.module.tiles.Point;
import com.kjksoft.mcdesigner.client.module.tiles.TileModel3D;
import com.kjksoft.mcdesigner.client.module.tiles.TileView;
import com.kjksoft.mcdesigner.client.tool.PaintMouseHandler;
import com.kjksoft.mcdesigner.client.tool.ToolMouseHandler;

public class MCDesigner implements EntryPoint {
	
	private final TileView tileView = new TileView();
	//private final LayeredMaterialsModel layeredMaterialsModel = new LayeredMaterialsModel();
	private final TileModel3D tileModel = new TileModel3D();
	private final DockLayoutPanel mainPanel = new DockLayoutPanel(Unit.EM);
	private final DockLayoutPanel sidePanel = new DockLayoutPanel(Unit.EM);
	private final ToolPanel toolPanel = new ToolPanel();
	private final Palette palette = new Palette();
	private final Label mouseCoords = new Label("x: ; z:");
	private final PopupPanel materialsPanel = new PopupPanel(true, false);
	private final MaterialsList materialsList = new MaterialsList();
	
	private final PaintMouseHandler pencilMouseHandler = new PaintMouseHandler() {
		@Override
		protected void onTilePaint(TileView tileView, Point p) {
			drawTile(p,palette.getPrimaryMaterial());
		}
	};
	
	private final PaintMouseHandler eraserMouseHandler = new PaintMouseHandler() {
		@Override
		protected void onTilePaint(TileView tileView, Point p) {
			drawTile(p,null);
		}
	};
	
	private ToolMouseHandler scrollMouseHandler = new ToolMouseHandler() {
		@Override
		protected void onMouseUp(TileView tileView, MouseUpEvent event) {
			// Do nothing
		}
		
		@Override
		protected void onMouseMove(TileView tileView, MouseMoveEvent event) {
			if (isMouseDown()) {
				Point p = tileView.getCoordsFromMouseEvent(event);
				int dx = p.x - getMouseDownPoint().x;
				int dy = p.y - getMouseDownPoint().y;
				
				if (dx != 0 || dy != 0) {
					Point pScroll = tileView.getScrollOffset();
					pScroll = new Point(
						pScroll.x - dx,
						pScroll.y - dy
					);
					tileView.setScrollOffset(pScroll);
				}
			}
		}
		
		@Override
		protected void onMouseDown(TileView tileView, MouseDownEvent event) {
			// Do nothing
		}
	};
	
	@Override
	public void onModuleLoad() {
		JsZip.initWorkerScriptsPath("/mcdesigner/zipjs/");
		
		// [kjk] Inject theme-override styles
		Resources.INSTANCE.css().ensureInjected();
		
		// Load default material textures
		ImgSrcTextureLoader textureLoader = new ImgSrcTextureLoader("images/textures/");
		TextureStore.getInstance().loadTextures(textureLoader);
		
		// Initialize the tile area
		tileView.setSize("100%", "100%");
		tileView.setStyleName("tilearea");
		tileView.setModel(tileModel);
		
		toolPanel.addStyleName("toolpanel");
		
		materialsPanel.setWidget(materialsList);
		
		mouseCoords.addStyleName("mouseCoords");
		
		sidePanel.addSouth(mouseCoords, 2);
		sidePanel.add(palette);
		
		mainPanel.addNorth(toolPanel, 2.25);
		mainPanel.addWest(sidePanel, 12);
		mainPanel.add(tileView);
		
		// Add the tile area to the document
		//RootPanel.get("tilePanel").add(tileArea);
		RootLayoutPanel.get().add(mainPanel);
		
		// Add mouse handler
		//new TileMouseHandler();
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
		toolPanel.zoomInButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				tileView.zoomIn();
				toolPanel.zoomInButton.setEnabled(tileView.canZoomIn());
				toolPanel.zoomOutButton.setEnabled(tileView.canZoomOut());
			}
		});
		toolPanel.zoomOutButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				tileView.zoomOut();
				toolPanel.zoomInButton.setEnabled(tileView.canZoomIn());
				toolPanel.zoomOutButton.setEnabled(tileView.canZoomOut());
			}
		});
		
		toolPanel.importResPackButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ResourcePackTextureLoader loader = new ResourcePackTextureLoader();
				loader.setUrl("res/polishedcraft64x-17.zip");
				TextureStore.getInstance().loadTextures(loader);
				
//				final PopupPanel popupPanel = new PopupPanel(true);
//				final HTMLPanel htmlPanel =  new HTMLPanel("<div><b>Loaded textures:</b></div>");
//				popupPanel.add(htmlPanel);
//				
//				htmlPanel.add(new Image("images/material/grass.png"));
//				htmlPanel.add(new Image("images/material/dirt.png"));
//				
//				
//				JsZip.getInstance().createZipReader("res/test.zip", new JsZipReader.CreateCallback() {
//					@Override
//					public void onCreate(final JsZipReader zipReader) {
//						Window.alert("Successfully opened zip file!");
//						zipReader.getEntries(new JsZipEntry.Callback() {
//							@Override
//							public void onCreate(JsArray<JsZipEntry> entries) {
//								Window.alert("Successfully read zip file entries!");
//								for(int i=0; i < entries.length(); i++) {
//									JsZipEntry zipEntry = entries.get(i);
//									if (i < 10) {
////										Window.alert("Read entry #" + i + ": " + zipEntry.getFilename());
//									}
//								}
//								zipReader.close(new JsZipReader.CloseCallback() {
//									@Override
//									public void onClose() {
//										Window.alert("Successfully closed zip file");
//										popupPanel.center();
//									}
//								});
//							}
//						});
//					}
//				}, new ErrorCallback() {
//					@Override
//					public void onError(String error) {
//						Window.alert("ERROR: " + error);
//					}
//				});
			}
		});
		
		toolPanel.toolBox.addToolSelectionHandler(new ToolSelectionHandler() {
			@Override
			public void onToolSelection(Tool newTool) {
				pencilMouseHandler.removeFrom(tileView);
				eraserMouseHandler.removeFrom(tileView);
				scrollMouseHandler.removeFrom(tileView);
				switch(newTool) {
				case ERASER:
					eraserMouseHandler.installOn(tileView);
					break;
				case PENCIL:
					pencilMouseHandler.installOn(tileView);
					break;
				case SCROLL:
					scrollMouseHandler.installOn(tileView);
					break;
				default:
					throw new UnsupportedOperationException();
				}
			}
		});
		toolPanel.toolBox.selectTool(Tool.PENCIL);
		
		// Create palette material type groups
		for(MaterialType type : MaterialType.values()) {
			palette.addMaterialType(type);
		}
		
		// Add materials to the palette
		for(Material material : Material.values()) {
			Set<MaterialType> types = material.getTypes();
			if (types != null && types.size() > 0) {
				for(MaterialType type : types) {
					palette.addMaterial(type,material);
				}
			} else {
				palette.addMaterial(material);
			}
		}
		
		palette.setPrimaryMaterial(Material.DIRT);
		palette.setMaterialType(MaterialType.NATURAL);
	}
	
	private void drawTile(Point p, Material m) {
		if (m == null) {
			tileModel.removeTile(p);
		} else {
			tileModel.putTile(p, m);
		}
	}
}
