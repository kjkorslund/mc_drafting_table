package com.kjksoft.mcdesigner.client.module.tiles;

import java.util.Collection;
import java.util.HashSet;

public abstract class AbstractTileModel<T> implements ITileModel<T> {
	private final HashSet<TileListener> listeners = new HashSet<TileListener>();
	
	@Override
	public void addTileListener(TileListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeTileListener(TileListener listener) {
		listeners.remove(listener);
	}
	
	protected void onTileUpdate(Point p) {
		for(TileListener listener : listeners) {
			listener.onTileUpdate(this, p);
		}
	}
	
	protected void onTilesUpdate(Collection<Point> points) {
		for(TileListener listener : listeners) {
			listener.onTilesUpdate(this, points);
		}
	}

}
