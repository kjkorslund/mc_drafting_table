package com.kjksoft.mcdesigner.client.materials;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;


public enum Material {
	BEACON("beacon"),
	BEDROCK("bedrock", MaterialType.NATURAL),
	BOOKSHELF("bookshelf"),
	BREWING_STAND("brewing_stand", MaterialType.APPARATUS),
	BRICK("brick", MaterialType.CRAFTED),
	CACTUS("cactus_side", MaterialType.PLANT),
	CAKE("cake_side"),
	CARROTS("carrots_stage_3", MaterialType.PLANT),
	CAULDRON("cauldron_side", MaterialType.APPARATUS),
	CLAY("clay", MaterialType.NATURAL),
	COAL_BLOCK("coal_block", MaterialType.ORE),
	COAL_ORE("coal_ore", MaterialType.ORE),
	COBBLESTONE_MOSSY("cobblestone_mossy", MaterialType.NATURAL),
	COBBLESTONE("cobblestone", MaterialType.NATURAL),
	COCOA("cocoa_stage_2",MaterialType.PLANT),
	COMMAND_BLOCK("command_block"),
	COMPARATOR("comparator_off", MaterialType.REDSTONE),
	CRAFTING_TABLE("crafting_table_front", MaterialType.APPARATUS),
	DAYLIGHT_DETECTOR("daylight_detector_top", MaterialType.REDSTONE),
	DEAD_BUSH("deadbush", MaterialType.PLANT),
	DIAMOND_BLOCK("diamond_block", MaterialType.ORE),
	DIAMOND_ORE("diamond_ore", MaterialType.ORE),
	DIRT_PODZOL("dirt_podzol_top", MaterialType.NATURAL),
	DIRT("dirt", MaterialType.NATURAL),
	DISPENSER("dispenser_front_horizontal", MaterialType.APPARATUS),
	DOOR_IRON("door_iron_upper", MaterialType.CRAFTED),
	DOOR_WOOD("door_wood_upper", MaterialType.CRAFTED),
	DROPPER("dropper_front_horizontal", MaterialType.APPARATUS),
	EMERALD_BLOCK("emerald_block", MaterialType.ORE),
	EMERALD_ORE("emerald_ore", MaterialType.ORE),
	ENCHANTING_TABLE("enchanting_table_side", MaterialType.APPARATUS),
	END_STONE("end_stone", MaterialType.NATURAL),
	END_FRAME("endframe_top", MaterialType.NATURAL),
	FARMLAND("farmland_dry", MaterialType.NATURAL),
	FERN("fern", MaterialType.PLANT),
	FLOWER_BLUE_ORCHID("flower_blue_orchid", MaterialType.PLANT),
	FLOWER_DANDELION("flower_dandelion", MaterialType.PLANT),
	FLOWER_HOUSTONIA("flower_houstonia", MaterialType.PLANT),
	FLOWER_OXEYE_DAISY("flower_oxeye_daisy", MaterialType.PLANT),
	FLOWER_PAEONIA("flower_paeonia", MaterialType.PLANT),
	FLOWER_ROSE("flower_rose", MaterialType.PLANT),
	FLOWER_TULIP_ORANGE("flower_tulip_orange", MaterialType.PLANT),
	FLOWER_TULIP_PINK("flower_tulip_pink", MaterialType.PLANT),
	FLOWER_TULIP_RED("flower_tulip_red", MaterialType.PLANT),
	FLOWER_TULIP_WHITE("flower_tulip_white", MaterialType.PLANT),
	FLOWERPOT("flower_pot", MaterialType.CRAFTED),
	FURNACE("furnace_front_off", MaterialType.APPARATUS),
	GLASS_BLACK("glass_black", MaterialType.CRAFTED),
	GLASS_BLUE("glass_blue", MaterialType.CRAFTED),
	GLASS_BROWN("glass_brown", MaterialType.CRAFTED),
	GLASS_CYAN("glass_cyan", MaterialType.CRAFTED),
	GLASS_GRAY("glass_gray", MaterialType.CRAFTED),
	GLASS_GREEN("glass_green", MaterialType.CRAFTED),
	GLASS_LIGHT_BLUE("glass_light_blue", MaterialType.CRAFTED),
	GLASS_LIME("glass_lime", MaterialType.CRAFTED),
	GLASS_MAGENTA("glass_magenta", MaterialType.CRAFTED),
	GLASS_ORANGE("glass_orange", MaterialType.CRAFTED),
	GLASS_PINK("glass_pink", MaterialType.CRAFTED),
	GLASS_PURPLE("glass_purple", MaterialType.CRAFTED),
	GLASS_RED("glass_red", MaterialType.CRAFTED),
	GLASS_SILVER("glass_silver", MaterialType.CRAFTED),
	GLASS_WHITE("glass_white", MaterialType.CRAFTED),
	GLASS_YELLOW("glass_yellow", MaterialType.CRAFTED),
	GLASS("glass", MaterialType.CRAFTED),
	GLOWSTONE("glowstone", MaterialType.CRAFTED),
	GOLD_BLOCK("gold_block", MaterialType.ORE),
	GOLD_ORE("gold_ore", MaterialType.ORE),
	GRASS("grass_top", MaterialType.NATURAL),
	GRAVEL("gravel", MaterialType.NATURAL),
	// TODO: add 'natural' tag to appropriate clay types
	HARDENED_CLAY_BLACK("hardened_clay_stained_black", MaterialType.CRAFTED),
	HARDENED_CLAY_BLUE("hardened_clay_stained_blue", MaterialType.CRAFTED),
	HARDENED_CLAY_BROWN("hardened_clay_stained_brown", MaterialType.CRAFTED),
	HARDENED_CLAY_CYAN("hardened_clay_stained_cyan", MaterialType.CRAFTED),
	HARDENED_CLAY_GRAY("hardened_clay_stained_gray", MaterialType.CRAFTED),
	HARDENED_CLAY_GREEN("hardened_clay_stained_green", MaterialType.CRAFTED),
	HARDENED_CLAY_LIGHT_BLUE("hardened_clay_stained_light_blue", MaterialType.CRAFTED),
	HARDENED_CLAY_LIME("hardened_clay_stained_lime", MaterialType.CRAFTED),
	HARDENED_CLAY_MAGENTA("hardened_clay_stained_magenta", MaterialType.CRAFTED),
	HARDENED_CLAY_ORANGE("hardened_clay_stained_orange", MaterialType.CRAFTED),
	HARDENED_CLAY_PINK("hardened_clay_stained_pink", MaterialType.CRAFTED),
	HARDENED_CLAY_PURPLE("hardened_clay_stained_purple", MaterialType.CRAFTED),
	HARDENED_CLAY_RED("hardened_clay_stained_red", MaterialType.CRAFTED),
	HARDENED_CLAY_SILVER("hardened_clay_stained_silver", MaterialType.CRAFTED),
	HARDENED_CLAY_WHITE("hardened_clay_stained_white", MaterialType.CRAFTED),
	HARDENED_CLAY_YELLOW("hardened_clay_stained_yellow", MaterialType.CRAFTED),
	HARDENED_CLAY("hardened_clay", MaterialType.CRAFTED, MaterialType.NATURAL),
	HAY_BLOCK("hay_block_side", MaterialType.CRAFTED),
	ICE_PACKED("ice_packed", MaterialType.NATURAL), // TODO verify?
	ICE("ice", MaterialType.NATURAL),
	IRON_BARS("iron_bars", MaterialType.CRAFTED),
	IRON_BLOCK("iron_block", MaterialType.ORE),
	IRON_ORE("iron_ore", MaterialType.ORE),
	JUKEBOX("jukebox_top", MaterialType.APPARATUS),
	LAPIS_BLOCK("lapis_block", MaterialType.ORE),
	LAPIS_ORE("lapis_ore", MaterialType.ORE),
	
	OAK_PLANK("planks_oak", MaterialType.CRAFTED),
	OBSIDIAN("obsidian", MaterialType.NATURAL),
	;
	
	public final String textureName;
	private final Set<MaterialType> types;
	
	private Material(String textureName, MaterialType... materialTypes) {
		this.textureName = textureName;
		
		this.types = new LinkedHashSet<MaterialType>(Arrays.asList(materialTypes));
	}
	public Set<MaterialType> getTypes() {
		return Collections.unmodifiableSet(types);
	}
}
