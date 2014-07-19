package com.kjksoft.mcdesigner.client;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.kjksoft.mcdesigner.client.lib.filereader.JsFile;
import com.kjksoft.mcdesigner.client.lib.zipjs.JsZip;
import com.kjksoft.mcdesigner.client.materials.ImgSrcTextureLoader;
import com.kjksoft.mcdesigner.client.materials.Material;
import com.kjksoft.mcdesigner.client.materials.MaterialType;
import com.kjksoft.mcdesigner.client.materials.ResourcePackTextureLoader;
import com.kjksoft.mcdesigner.client.materials.TextureStore;
import com.kjksoft.mcdesigner.client.materials.TextureStore.TextureUpdateListener;
import com.kjksoft.mcdesigner.client.module.MaterialsList;
import com.kjksoft.mcdesigner.client.module.tiles.Point;
import com.kjksoft.mcdesigner.client.module.tiles.TileModel3D;
import com.kjksoft.mcdesigner.client.module.tiles.TileView;
import com.kjksoft.mcdesigner.client.tool.PaintMouseHandler;
import com.kjksoft.mcdesigner.client.tool.ToolMouseHandler;
import com.kjksoft.mcdesigner.client.tool.VectorMouseHandler;
import com.kjksoft.mcdesigner.client.widgets.FileUploader;
import com.kjksoft.mcdesigner.client.widgets.FileUploader.SelectionHandler;

public class MCDesigner implements EntryPoint {
	
	//private final LayeredMaterialsModel layeredMaterialsModel = new LayeredMaterialsModel();
	private final TileModel3D tileModel = new TileModel3D();
	
	private final MainPanel mainPanel = new MainPanel();
//	private final DockLayoutPanel mainPanel = new DockLayoutPanel(Unit.EM);
//	private final DockLayoutPanel sidePanel = new DockLayoutPanel(Unit.EM);
//	private final ToolPanel toolPanel = new ToolPanel();
//	private final TileView tileView = new TileView();
//	private final Palette palette = new Palette();
//	private final Label mouseCoords = new Label("x: ; z:");
	private final PopupPanel materialsPanel = new PopupPanel(true, false);
	private DialogBox resourcePackPopup = new DialogBox(true);
	private final MaterialsList materialsList = new MaterialsList();
	
	private final PaintMouseHandler pencilMouseHandler = new PaintMouseHandler() {
		@Override
		protected void onTilePaint(TileView tileView, Point p) {
			drawTile(p,mainPanel.getPalette().getPrimaryMaterial());
		}
	};
	
	private final PaintMouseHandler eraserMouseHandler = new PaintMouseHandler() {
		@Override
		protected void onTilePaint(TileView tileView, Point p) {
			drawTile(p,null);
		}
	};
	
	private final VectorMouseHandler lineMouseHandler = new VectorMouseHandler() {
		@Override
		protected void onVectorUpdate(TileView tileView, Point start, Point end) {
			GWT.log("Line vector: " + start + " --> " + end);
		}

		@Override
		protected void onFinalVectorUpdate(TileView tileView, Point start,
				Point end) {
			GWT.log("Line vector: " + start + " --> " + end + " (FINAL)");
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
				Point p = mainPanel.getTileView().getCoordsFromMouseEvent(event);
				int dx = p.x - getMouseDownPoint().x;
				int dy = p.y - getMouseDownPoint().y;
				
				if (dx != 0 || dy != 0) {
					Point pScroll = mainPanel.getTileView().getScrollOffset();
					pScroll = new Point(
						pScroll.x - dx,
						pScroll.y - dy
					);
					mainPanel.getTileView().setScrollOffset(pScroll);
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
		JsZip.initWorkerScriptsPath(GWT.getModuleBaseForStaticFiles() + "zipjs/");
		
		// [kjk] Inject theme-override styles
		Resources.INSTANCE.css().ensureInjected();
		
		// Load default material textures
		ImgSrcTextureLoader textureLoader = new ImgSrcTextureLoader("images/textures/");
		TextureStore.getInstance().loadTextures(textureLoader);
		
		// Initialize the tile area
//		mainPanel.getTileView().setSize("100%", "100%");
//		mainPanel.getTileView().setStyleName("tilearea");
		mainPanel.getTileView().setModel(tileModel);
		
		mainPanel.getToolPanel().addStyleName("toolpanel");
		
		materialsPanel.setWidget(materialsList);
		
		resourcePackPopup.setText("Import textures from a resource pack");
		resourcePackPopup.setGlassEnabled(true);
		
//		mouseCoords.addStyleName("mouseCoords");
		
//		mainPanel.getSidePanel().addSouth(mouseCoords, 2);
		
		// Add the tile area to the document
		//RootPanel.get("tilePanel").add(tileArea);
		RootLayoutPanel.get().add(mainPanel);
		
		// Add mouse handler
		//new TileMouseHandler();
		mainPanel.getTileView().addMouseMoveHandler(new MouseMoveHandler() {
			@Override
			public void onMouseMove(MouseMoveEvent event) {
				mainPanel.getMouseCoords().setText(toCoordString(mainPanel.getTileView().getCoordsFromMouseEvent(event)));
			}
			
			String toCoordString(Point p) {
				return "x:" + p.x + "; z: " + p.y; 
			}
		});
		
		// Handlers for tool panel buttons
		mainPanel.getToolPanel().clearButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				tileModel.clearCurrentLayer();
			}
		});
		mainPanel.getToolPanel().clearAllButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				tileModel.clearAll();
			}
		});
		mainPanel.getToolPanel().materialsButton.addClickHandler(new ClickHandler() {
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
		mainPanel.getToolPanel().materialsAllButton.addClickHandler(new ClickHandler() {
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
		mainPanel.getToolPanel().prevLayerButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				tileModel.setCurrentLayer(tileModel.getCurrentLayer()-1);
				mainPanel.getToolPanel().currentLayer.setInnerText("Layer " + 
						Integer.toString(tileModel.getCurrentLayer()));
			}
		});
		mainPanel.getToolPanel().nextLayerButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				tileModel.setCurrentLayer(tileModel.getCurrentLayer()+1);
				mainPanel.getToolPanel().currentLayer.setInnerText("Layer " + 
						Integer.toString(tileModel.getCurrentLayer()));
			}
		});
		mainPanel.getToolPanel().zoomInButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mainPanel.getTileView().zoomIn();
				mainPanel.getToolPanel().zoomInButton.setEnabled(mainPanel.getTileView().canZoomIn());
				mainPanel.getToolPanel().zoomOutButton.setEnabled(mainPanel.getTileView().canZoomOut());
			}
		});
		mainPanel.getToolPanel().zoomOutButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mainPanel.getTileView().zoomOut();
				mainPanel.getToolPanel().zoomInButton.setEnabled(mainPanel.getTileView().canZoomIn());
				mainPanel.getToolPanel().zoomOutButton.setEnabled(mainPanel.getTileView().canZoomOut());
			}
		});
		
		mainPanel.getToolPanel().importResPackButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				FileUploader fileUploader = new FileUploader();
				fileUploader.setHeaderText("Choose a resource pack to upload: ");
				fileUploader.setSelectionHandler(new SelectionHandler() {
					@Override
					public void onFileSelection(JsFile file) {
						// TODO Auto-generated method stub
						ResourcePackTextureLoader loader = new ResourcePackTextureLoader();
						loader.setFile(file);
						TextureStore.getInstance().loadTextures(loader);
						resourcePackPopup.hide();
					}
				});
				
				resourcePackPopup.setWidget(fileUploader);
				resourcePackPopup.center();
			}
		});
		
		TextureStore.getInstance().addUpdateListener(new TextureUpdateListener() {
			@Override public void onTextureUpdate(Material material) { }

			@Override
			public void onTextureUpdateDeferred() {
				mainPanel.tileView.updateAllTileSources();
			}
		});
		
		mainPanel.getToolPanel().toolBox.addToolSelectionHandler(new ToolSelectionHandler() {
			@Override
			public void onToolSelection(Tool newTool) {
				pencilMouseHandler.removeFrom(mainPanel.getTileView());
				eraserMouseHandler.removeFrom(mainPanel.getTileView());
				lineMouseHandler.removeFrom(mainPanel.getTileView());
				scrollMouseHandler.removeFrom(mainPanel.getTileView());
				switch(newTool) {
				case ERASER:
					eraserMouseHandler.installOn(mainPanel.getTileView());
					break;
				case PENCIL:
					pencilMouseHandler.installOn(mainPanel.getTileView());
					break;
				case LINE:
					lineMouseHandler.installOn(mainPanel.getTileView());
					break;
				case SCROLL:
					scrollMouseHandler.installOn(mainPanel.getTileView());
					break;
				default:
					throw new UnsupportedOperationException();
				}
			}
		});
		mainPanel.getToolPanel().toolBox.selectTool(Tool.PENCIL);
		
		// Create palette material type groups
		for(MaterialType type : MaterialType.values()) {
			mainPanel.getPalette().addMaterialType(type);
		}
		
		// Add materials to the palette
		for(Material material : Material.values()) {
			Set<MaterialType> types = material.getTypes();
			if (types != null && types.size() > 0) {
				for(MaterialType type : types) {
					mainPanel.getPalette().addMaterial(type,material);
				}
			} else {
				mainPanel.getPalette().addMaterial(material);
			}
		}
		
		mainPanel.getPalette().setPrimaryMaterial(Material.DIRT);
		mainPanel.getPalette().setMaterialType(MaterialType.NATURAL);
	}
	
	private void drawTile(Point p, Material m) {
		if (m == null) {
			tileModel.removeTile(p);
		} else {
			tileModel.putTile(p, m);
		}
	}
}
