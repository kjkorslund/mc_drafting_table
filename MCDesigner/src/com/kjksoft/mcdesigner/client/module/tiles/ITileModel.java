package com.kjksoft.mcdesigner.client.module.tiles;

import java.util.Collection;
import java.util.List;


public interface ITileModel {
	public void addTileListener(TileListener listener);
	public void removeTileListener(TileListener listener);
	
	public boolean hasTile(Point p);
	public String getTile(Point p);
	public List<String> getTiles(Collection<Point> points);
}
