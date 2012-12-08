package com.kjksoft.mcdesigner.client.module.tiles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.kjksoft.mcdesigner.client.Material;

public class TileModel3D extends AbstractTileModel {
	Axis currentAxis = Axis.Z;
	int currentLayer = 0;
	
	final LayerMap3D<Material> materialMap = new LayerMap3D<Material>();
	
	final HashMap<Point,Material> currentLayerMap = new HashMap<Point, Material>();
	final HashMap<Point,Material> prevLayerMap = new HashMap<Point, Material>();
	final HashMap<Point,Material> lowerLayersMap = new HashMap<Point, Material>();

	@Override
	public boolean hasTile(Point p) {
		return currentLayerMap.containsKey(p)
				|| prevLayerMap.containsKey(p)
				|| lowerLayersMap.containsKey(p);
	}

	@Override
	public String getTile(Point p) {
		Material m = currentLayerMap.get(p);
		if (m != null) return m.imgSrc;
		
		m = prevLayerMap.get(p);
		if (m != null) return m.imgSrc_66;
		
		m = lowerLayersMap.get(p);
		if (m != null) return m.imgSrc_33;
		
		return null;
	}

	@Override
	public List<String> getTiles(Collection<Point> points) {
		ArrayList<String> results = new ArrayList<String>();
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
		if (!_removeTile(point3(layer,p))) return;
		recalcLowerLayers();
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
		currentLayerMap.clear();
		currentLayerMap.putAll(getMapLayer(currentLayer));
		prevLayerMap.clear();
		prevLayerMap.putAll(getMapLayer(currentLayer));
		recalcLowerLayers();
	}
	
	void recalcLowerLayers() {
		lowerLayersMap.clear();
		for(int layer=mapLayerMin(); layer<currentLayer-1; layer++) {
			lowerLayersMap.putAll(getMapLayer(layer));
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
	
	int mapLayerMin() {
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
	
	Map<Point,Material> getMapLayer(int layer) {
		switch(currentAxis) {
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
		case Y:
			materialMap.clearYLayer(currentLayer);
		case Z:
			materialMap.clearZLayer(currentLayer);
		}
		throw new IllegalArgumentException("current axis is invalid");
	}
}
