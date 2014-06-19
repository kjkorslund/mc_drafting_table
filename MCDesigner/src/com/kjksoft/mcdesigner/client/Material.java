package com.kjksoft.mcdesigner.client;

import com.kjksoft.mcdesigner.client.texture.Texture;

public enum Material {
	DIRT(base(),"dirt","png"),
	GRASS(base(),"grass","png"),
	COBBLESTONE(base(),"cobblestone","png"),
	OAK_PLANK(base(),"oak_plank","png"),
	BRICK(base(),"brick","png"),
	OBSIDIAN(base(),"obsidian","png"),
	;
	
	private static final String base() {
		return "images/material/";
	};
	
	public final String imgSrc;
	public final String imgSrc_66;
	public final String imgSrc_33;
	
	public Texture texture;

	private Material(String base, String name, String extension) {
		this.imgSrc = base + name + "." + extension;
		this.imgSrc_66 = base + name + "_75." + extension;
		this.imgSrc_33 = base + name + "_50." + extension;
		this.texture = new Texture(imgSrc);
	}
}
