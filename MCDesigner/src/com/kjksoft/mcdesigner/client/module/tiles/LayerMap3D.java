package com.kjksoft.mcdesigner.client.module.tiles;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Data model to store 3D tile data for a model
 * 
 * @author Kevin
 * 
 * @param <T> tile data type
 */
public class LayerMap3D<T> {
	/*
	 * == Requirements for tile data model ==
	 *  - Track data in 3 dimensions (x,y,z)
	 *  - Efficient add/remove to any layer along any axis (painting)
	 *  - Efficient layer data retrieval
	 *  - Track min/max value along each axis (for iterating over layers
	 *      & possible UI purposes)
	 * 
	 * X coords: {y,z}
	 * Y coords: {x,z}
	 * Z coords: {x,y}
	 */
	
	HashMap<Integer, HashMap<Point,T>> xMap = new HashMap<Integer, HashMap<Point,T>>();
	HashMap<Integer, HashMap<Point,T>> yMap = new HashMap<Integer, HashMap<Point,T>>();
	HashMap<Integer, HashMap<Point,T>> zMap = new HashMap<Integer, HashMap<Point,T>>();
	
	Integer xMin, xMax;
	Integer yMin, yMax;
	Integer zMin, zMax;
	
	public boolean contains(Point3 p) {
		HashMap<Point,T> xLayer = xMap.get(p.x);
		if (xLayer != null) {
			return xLayer.containsKey(new Point(p.y,p.z));
		}
		return false;
	}
	
	public T get(Point3 p) {
		HashMap<Point,T> xLayer = xMap.get(p.x);
		if (xLayer != null) {
			return xLayer.get(new Point(p.y,p.z));
		}
		return null;
	}

	public Map<Point,T> getXLayer(int x) {
		HashMap<Point, T> xLayer = xMap.get(x);
		if (xLayer != null) return Collections.unmodifiableMap(xLayer);
		return null;
	}
	
	public Map<Point,T> getYLayer(int y) {
		HashMap<Point, T> yLayer = yMap.get(y);
		if (yLayer != null) return Collections.unmodifiableMap(yLayer);
		return null;
	}
	
	public Map<Point,T> getZLayer(int z) {
		HashMap<Point, T> zLayer = zMap.get(z);
		if (zLayer != null) return Collections.unmodifiableMap(zLayer);
		return null;
	}
	
	public T put(Point3 p, T val) {
		putX(p,val);
		putY(p,val);
		return putZ(p,val);
	}
	
	public T remove(Point3 p) {
		clearX(p);
		clearY(p);
		return clearZ(p);
	}
	
	public void clear() {
		xMap.clear();
		yMap.clear();
		zMap.clear();
		xMin = xMax = null;
		yMin = yMax = null;
		zMin = zMax = null;
	}
	
	public void clearXLayer(int layer) {
		xMap.remove(layer);
	}
	
	public void clearYLayer(int layer) {
		yMap.remove(layer);
	}
	
	public void clearZLayer(int layer) {
		zMap.remove(layer);
	}
	
	public Integer getXMin() {
		return xMin;
	}

	public Integer getXMax() {
		return xMax;
	}

	public Integer getYMin() {
		return yMin;
	}

	public Integer getYMax() {
		return yMax;
	}

	public Integer getZMin() {
		return zMin;
	}

	public Integer getZMax() {
		return zMax;
	}

	private T putX(Point3 p, T val) {
		HashMap<Point,T> xLayer = xMap.get(p.x);
		if (xLayer == null) {
			xLayer = new HashMap<Point, T>();
			xMap.put(p.x, xLayer);
			addXMinMax(p.x);
		}
		return xLayer.put(new Point(p.y,p.z), val);
	}
	
	private T putY(Point3 p, T val) {
		HashMap<Point,T> yLayer = yMap.get(p.y);
		if (yLayer == null) {
			yLayer = new HashMap<Point, T>();
			yMap.put(p.y, yLayer);
			addYMinMax(p.y);
		}
		return yLayer.put(new Point(p.x,p.z), val);
	}
	
	private T putZ(Point3 p, T val) {
		HashMap<Point,T> zLayer = zMap.get(p.z);
		if (zLayer == null) {
			zLayer = new HashMap<Point, T>();
			zMap.put(p.z, zLayer);
			addZMinMax(p.z);
		}
		return zLayer.put(new Point(p.x,p.y), val);
	}
	
	private T clearX(Point3 p) {
		HashMap<Point,T> xLayer = xMap.get(p.x);
		if (xLayer != null) {
			T result = xLayer.remove(new Point(p.y,p.z));
			if (xLayer.size() == 0) {
				xMap.remove(p.x);
				clearXMinMax(p.x);
			}
			return result;
		}
		return null;
	}
	
	private T clearY(Point3 p) {
		HashMap<Point,T> yLayer = yMap.get(p.y);
		if (yLayer != null) {
			T result = yLayer.remove(new Point(p.x,p.z));
			if (yLayer.size() == 0) {
				yMap.remove(p.y);
				clearYMinMax(p.y);
			}
			return result;
		}
		return null;
	}
	
	private T clearZ(Point3 p) {
		HashMap<Point,T> zLayer = zMap.get(p.z);
		if (zLayer != null) {
			T result = zLayer.remove(new Point(p.x,p.y));
			if (zLayer.size() == 0) {
				zMap.remove(p.z);
				clearZMinMax(p.z);
			}
			return result;
		}
		return null;
	}
	
	private void addXMinMax(int x) {
		if (xMin == null || x < xMin) xMin = x;
		if (xMax == null || x > xMax) xMax = x;
	}
	
	private void addYMinMax(int y) {
		if (yMin == null || y < yMin) yMin = y;
		if (yMax == null || y > yMax) yMax = y;
	}
	
	private void addZMinMax(int z) {
		if (zMin == null || z < zMin) zMin = z;
		if (zMax == null || z > zMax) zMax = z;
	}
	
	private void clearXMinMax(int x) {
		if (xMin != null && xMax != null) {
			if (x == xMin) {
				xMin = calcMin(xMap, xMin, xMax);
				if (xMin == null) xMax = null;
			}
			else if (x == xMax) {
				xMax = calcMax(xMap, xMin, xMax);
				if (xMax == null) xMin = null;
			}
		} else {
			xMin = null;
			xMax = null;
		}
	}
	
	private void clearYMinMax(int y) {
		if (yMin != null && yMax != null) {
			if (y == yMin) {
				yMin = calcMin(yMap, yMin, yMax);
				if (yMin == null) yMax = null;
			}
			else if (y == yMax) {
				yMax = calcMax(yMap, yMin, yMax);
				if (yMax == null) yMin = null;
			}
		} else {
			yMin = null;
			yMax = null;
		}
	}
	
	private void clearZMinMax(int z) {
		if (zMin != null && zMax != null) {
			if (z == zMin) {
				zMin = calcMin(zMap, zMin, zMax);
				if (zMin == null) zMax = null;
			}
			else if (z == zMax) {
				zMax = calcMax(zMap, zMin, zMax);
				if (zMax == null) zMin = null;
			}
		} else {
			zMin = null;
			zMax = null;
		}
	}
	
	private Integer calcMin(HashMap<Integer,HashMap<Point,T>> map, int min, int max) {
		for(int i=min; i<=max; i++) {
			if (!map.containsKey(i)) continue;
			return i;
		}
		return null;
	}
	
	private Integer calcMax(HashMap<Integer,HashMap<Point,T>> map, int min, int max) {
		for(int i=max; i>=min; i--) {
			if (!map.containsKey(i)) continue;
			return i;
		}
		return null;
	}
}
