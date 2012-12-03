package com.kjksoft.mcdesigner.client.module.tiles;

import java.util.Collection;


public interface TileListener {
	public void onTileUpdate(ITileModel model, Point p);
	public void onTilesUpdate(ITileModel model, Collection<Point> points);
}
