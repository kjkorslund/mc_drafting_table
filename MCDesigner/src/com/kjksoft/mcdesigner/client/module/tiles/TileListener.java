package com.kjksoft.mcdesigner.client.module.tiles;

import java.util.Collection;


public interface TileListener<T> {
	public void onTileUpdate(ITileModel<T> model, Point p);
	public void onTilesUpdate(ITileModel<T> model, Collection<Point> points);
}
