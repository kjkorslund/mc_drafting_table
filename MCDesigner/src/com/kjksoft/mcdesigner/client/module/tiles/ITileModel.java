package com.kjksoft.mcdesigner.client.module.tiles;

import java.util.Collection;
import java.util.List;


public interface ITileModel<T> {
	public void addTileListener(TileListener listener);
	public void removeTileListener(TileListener listener);
	
	public boolean hasTile(Point p);
	public T getTile(Point p);
	public List<T> getTiles(Collection<Point> points);
}
