package com.kjksoft.mcdesigner.client.module.tiles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.kjksoft.mcdesigner.client.canvas.FadeTransformer;
import com.kjksoft.mcdesigner.client.canvas.ImageBuffer;
import com.kjksoft.mcdesigner.client.materials.Material;
import com.kjksoft.mcdesigner.client.texture.Texture;

public class TileModel3D extends AbstractTileModel<ImageBuffer> {
	// TODO consider rewriting this class to use GWT Lightweight Collections,
	// for performance purposes
	Axis currentAxis = Axis.Z;
	int currentLayer = 0;
	
	final LayerMap3D<Material> materialMap = new LayerMap3D<Material>();
	
	final HashMap<Point,Material> currentLayerMap = new HashMap<Point, Material>();
	final HashMap<Point,Material> prevLayerMap = new HashMap<Point, Material>();
	final HashMap<Point,Material> lowerLayersMap = new HashMap<Point, Material>();
	
	final HashMap<Texture, ImageBuffer> prevLayerImageMap = new HashMap<Texture, ImageBuffer>();
	final HashMap<Texture, ImageBuffer> lowerLayersImageMap = new HashMap<Texture, ImageBuffer>();

	@Override
	public boolean hasTile(Point p) {
		return currentLayerMap.containsKey(p)
				|| prevLayerMap.containsKey(p)
				|| lowerLayersMap.containsKey(p);
	}

	@Override
	public ImageBuffer getTile(Point p) {
		Material m = currentLayerMap.get(p);
		if (m != null) return m.texture.getImgBuffer();
		
		m = prevLayerMap.get(p);
		if (m != null) return getPrevLayerImage(m.texture);
		
		m = lowerLayersMap.get(p);
		if (m != null) return getLowerLayersImage(m.texture);
		
		return null;
	}
	
	private ImageBuffer getPrevLayerImage(Texture texture) {
		ImageBuffer result = prevLayerImageMap.get(texture);
		if (result == null) {
			result = new ImageBuffer();
			result.loadFromImageBuffer(texture.getImgBuffer());
			FadeTransformer transformer = new FadeTransformer();
			transformer.setStrength(0.66f);
			transformer.transform(result);
			prevLayerImageMap.put(texture, result);
		}
		return result;
	}
	
	private ImageBuffer getLowerLayersImage(Texture texture) {
		ImageBuffer result = lowerLayersImageMap.get(texture);
		if (result == null) {
			result = new ImageBuffer();
			result.loadFromImageBuffer(texture.getImgBuffer());
			FadeTransformer transformer = new FadeTransformer();
			transformer.setStrength(0.33f);
			transformer.transform(result);
			lowerLayersImageMap.put(texture, result);
		}
		return result;
	}

	@Override
	public List<ImageBuffer> getTiles(Collection<Point> points) {
		ArrayList<ImageBuffer> results = new ArrayList<ImageBuffer>();
		for (Point p : points) {
			results.add(getTile(p));
		}
		return results;
	}
	
	public void putTile(Point p, Material m) {
		putTile(currentLayer,p,m);
	}
	
	public void putTile(int layer, Point p, Material m) {
		if (_putTile(point3(layer,p), m)) {
			recalcLowerLayers();
		}
		onTileUpdate(p);
	}
	
	public void putTiles(int layer, Map<Point,Material> tileMap) {
		boolean recalcLowerLayers = false;
		for(Point p : tileMap.keySet()) {
			Material m = tileMap.get(p);
			
			if (!_putTile(point3(layer,p),m)) continue;
			recalcLowerLayers = true;
		}
		
		if (recalcLowerLayers) {
			recalcLowerLayers();
		}
		onTilesUpdate(tileMap.keySet());
	}
	
	public void putTiles(Map<Point3,Material> tileMap) {
		boolean recalcLowerLayers = false;
		for(Point3 p : tileMap.keySet()) {
			Material m = tileMap.get(p);
			
			if (!_putTile(p,m)) continue;
			recalcLowerLayers = true;
		}
		
		if (recalcLowerLayers) {
			recalcLowerLayers();
		}
		HashSet<Point> points = new HashSet<Point>();
		for(Point3 p3 : tileMap.keySet()) {
			points.add(point2(p3));
		}
		onTilesUpdate(points);
	}
	
	public void removeTile(Point p) {
		removeTile(currentLayer,p);
	}
	
	public void removeTile(int layer, Point p) {
		if (_removeTile(point3(layer,p))) {
			recalcLowerLayers();
		}
		onTileUpdate(p);
	}
	
	public void removeTiles(Iterable<Point3> tiles) {
		boolean recalcLowerLayers = false;
		for(Point3 p : tiles) {
			if (!_removeTile(p)) continue;
			recalcLowerLayers = true;
		}
		
		if (recalcLowerLayers) {
			recalcLowerLayers();
		}
		HashSet<Point> points = new HashSet<Point>();
		for(Point3 p3 : tiles) {
			points.add(point2(p3));
		}
		onTilesUpdate(points);
	}
	
	public void clearCurrentLayer() {
		clearCurrentMapLayer();

		HashSet<Point> points = new HashSet<Point>(currentLayerMap.keySet());
		currentLayerMap.clear();
		onTilesUpdate(points);
	}
	
	public void clearAll() {
		materialMap.clear();
		
		HashSet<Point> points = new HashSet<Point>(currentLayerMap.keySet());
		points.addAll(prevLayerMap.keySet());
		points.addAll(lowerLayersMap.keySet());
		
		currentLayerMap.clear();
		prevLayerMap.clear();
		lowerLayersMap.clear();
		
		onTilesUpdate(points);
	}
	
	public Map<Material,Integer> getCurrentLayerMaterialCount() {
		Map<Material,Integer> materialCounts = new HashMap<Material, Integer>();
		addLayerMaterialCounts(materialCounts, currentAxis, currentLayer);
		return materialCounts;
	}
	
	public Map<Material,Integer> getTotalMaterialCount() {
		Map<Material,Integer> materialCounts = new HashMap<Material, Integer>();
		for(Entry<Integer,HashMap<Point,Material>> entry : materialMap.xMap.entrySet()) {
			addLayerMaterialCounts(materialCounts, Axis.X, entry.getKey());
		}
		return materialCounts;
	}
	
	void addLayerMaterialCounts(Map<Material,Integer> map, Axis axis, int layer) {
		Map<Point, Material> currentMapLayer = getMapLayer(axis,layer);
		if (currentMapLayer != null) {
			for(Entry<Point,Material> entry : currentMapLayer.entrySet()) {
				Material m = entry.getValue();
				if (map.containsKey(m)) {
					map.put(m, map.get(m)+1);
					continue;
				}
				map.put(m,1);
			}
		}
	}
	
	public int getCurrentLayer() {
		return currentLayer;
	}
	
	public int setCurrentLayer(int layer) {
		int result = currentLayer;
		currentLayer = layer;
		recalcAllLayers();
		return result;
	}
	
	public Axis getCurrentAxis() {
		return currentAxis;
	}
	
	public Axis setCurrentAxis(Axis axis) {
		Axis result = currentAxis;
		currentAxis = axis;
		recalcAllLayers();
		return result;
	}
	
	void recalcAllLayers() {
		HashSet<Point> points = new HashSet<Point>(currentLayerMap.keySet());
		points.addAll(prevLayerMap.keySet());
		points.addAll(lowerLayersMap.keySet());
		
		currentLayerMap.clear();
		populateMap(currentLayerMap,currentLayer);
		prevLayerMap.clear();
		populateMap(prevLayerMap,currentLayer-1);
		recalcLowerLayers();
		
		points.addAll(currentLayerMap.keySet());
		points.addAll(prevLayerMap.keySet());
		points.addAll(lowerLayersMap.keySet());
		onTilesUpdate(points);
	}
	
	void recalcLowerLayers() {
		lowerLayersMap.clear();
		Integer layerMin = mapLayerMin();
		if (layerMin != null) {
			for(int layer = layerMin; layer<currentLayer-1; layer++) {
				populateMap(lowerLayersMap,layer);
			}
		}
	}
	
	void populateMap(Map<Point,Material> map, int layer) {
		Map<Point, Material> mapLayer = getMapLayer(currentAxis,layer);
		if (mapLayer != null) {
			map.putAll(mapLayer);
		}
	}
	
	boolean _putTile(Point3 p, Material m) {
		materialMap.put(p,m);
		int layer = layer(p);
		
		if (layer == currentLayer) {
			currentLayerMap.put(point2(p), m);
			return false;
		}
		
		if (layer < currentLayer) {
			if (layer < currentLayer-1) {
				return true;
			}
			prevLayerMap.put(point2(p), m);
			return false;
		}
		
		return false;
	}
	
	/**
	 * @param p
	 * @return true if lower layers need to be recalculated; false otherwise
	 */
	boolean _removeTile(Point3 p) {
		materialMap.remove(p);
		int layer = layer(p);
		
		if (layer == currentLayer) {
			currentLayerMap.remove(point2(p));
			return false;
		}
		
		if (layer < currentLayer) {
			if (layer < currentLayer-1) {
				return true;
			}
			prevLayerMap.remove(point2(p));
			return false;
		}
		
		return false;
	}
	
	Point3 point3(int layer, Point p) {
		switch(currentAxis) {
		case X:
			return new Point3(currentLayer,p.x,p.y);
		case Y:
			return new Point3(p.x,currentLayer,p.y);
		case Z:
			return new Point3(p.x,p.y,currentLayer);
		}
		throw new IllegalArgumentException("current axis is invalid");
	}
	
	Point point2(Point3 p) {
		switch(currentAxis) {
		case X:
			return new Point(p.y,p.z);
		case Y:
			return new Point(p.x,p.z);
		case Z:
			return new Point(p.x,p.y);
		}
		throw new IllegalArgumentException("current axis is invalid");
	}
	
	int layer(Point3 p) {
		switch(currentAxis) {
		case X:
			return p.x;
		case Y:
			return p.y;
		case Z:
			return p.z;
		}
		throw new IllegalArgumentException("current axis is invalid");
	}
	
	Integer mapLayerMin() {
		switch(currentAxis) {
		case X:
			return materialMap.getXMin();
		case Y:
			return materialMap.getYMin();
		case Z:
			return materialMap.getZMin();
		}
		throw new IllegalArgumentException("current axis is invalid");
	}
	
	Map<Point,Material> getMapLayer(Axis axis, int layer) {
		switch(axis) {
		case X:
			return materialMap.getXLayer(layer);
		case Y:
			return materialMap.getYLayer(layer);
		case Z:
			return materialMap.getZLayer(layer);
		}
		throw new IllegalArgumentException("current axis is invalid");
	}
	
	void clearCurrentMapLayer() {
		switch(currentAxis) {
		case X:
			materialMap.clearXLayer(currentLayer);
			return;
		case Y:
			materialMap.clearYLayer(currentLayer);
			return;
		case Z:
			materialMap.clearZLayer(currentLayer);
			return;
		}
		throw new IllegalArgumentException("current axis is invalid");
	}
}
