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
	DIRT("dirt", MaterialType.NATURAL),
	DIRT_PODZOL("dirt_podzol_top", MaterialType.NATURAL),
	DIRT_MYCELIUM("mycelium_top", MaterialType.NATURAL),
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
	GLASS_BLACK("glass_black", MaterialType.GLASS),
	GLASS_BLUE("glass_blue", MaterialType.GLASS),
	GLASS_BROWN("glass_brown", MaterialType.GLASS),
	GLASS_CYAN("glass_cyan", MaterialType.GLASS),
	GLASS_GRAY("glass_gray", MaterialType.GLASS),
	GLASS_GREEN("glass_green", MaterialType.GLASS),
	GLASS_LIGHT_BLUE("glass_light_blue", MaterialType.GLASS),
	GLASS_LIME("glass_lime", MaterialType.GLASS),
	GLASS_MAGENTA("glass_magenta", MaterialType.GLASS),
	GLASS_ORANGE("glass_orange", MaterialType.GLASS),
	GLASS_PINK("glass_pink", MaterialType.GLASS),
	GLASS_PURPLE("glass_purple", MaterialType.GLASS),
	GLASS_RED("glass_red", MaterialType.GLASS),
	GLASS_SILVER("glass_silver", MaterialType.GLASS),
	GLASS_WHITE("glass_white", MaterialType.GLASS),
	GLASS_YELLOW("glass_yellow", MaterialType.GLASS),
	GLASS("glass", MaterialType.CRAFTED),
	GLOWSTONE("glowstone", MaterialType.CRAFTED),
	GOLD_BLOCK("gold_block", MaterialType.ORE),
	GOLD_ORE("gold_ore", MaterialType.ORE),
	GRASS("grass_top", MaterialType.NATURAL),
	GRAVEL("gravel", MaterialType.NATURAL),
	// TODO: add 'natural' tag to appropriate clay types
	HARDENED_CLAY_BLACK("hardened_clay_stained_black", MaterialType.HARDENED_CLAY),
	HARDENED_CLAY_BLUE("hardened_clay_stained_blue", MaterialType.HARDENED_CLAY),
	HARDENED_CLAY_BROWN("hardened_clay_stained_brown", MaterialType.HARDENED_CLAY),
	HARDENED_CLAY_CYAN("hardened_clay_stained_cyan", MaterialType.HARDENED_CLAY),
	HARDENED_CLAY_GRAY("hardened_clay_stained_gray", MaterialType.HARDENED_CLAY),
	HARDENED_CLAY_GREEN("hardened_clay_stained_green", MaterialType.HARDENED_CLAY),
	HARDENED_CLAY_LIGHT_BLUE("hardened_clay_stained_light_blue", MaterialType.HARDENED_CLAY),
	HARDENED_CLAY_LIME("hardened_clay_stained_lime", MaterialType.HARDENED_CLAY),
	HARDENED_CLAY_MAGENTA("hardened_clay_stained_magenta", MaterialType.HARDENED_CLAY),
	HARDENED_CLAY_ORANGE("hardened_clay_stained_orange", MaterialType.HARDENED_CLAY),
	HARDENED_CLAY_PINK("hardened_clay_stained_pink", MaterialType.HARDENED_CLAY),
	HARDENED_CLAY_PURPLE("hardened_clay_stained_purple", MaterialType.HARDENED_CLAY),
	HARDENED_CLAY_RED("hardened_clay_stained_red", MaterialType.HARDENED_CLAY),
	HARDENED_CLAY_SILVER("hardened_clay_stained_silver", MaterialType.HARDENED_CLAY),
	HARDENED_CLAY_WHITE("hardened_clay_stained_white", MaterialType.HARDENED_CLAY),
	HARDENED_CLAY_YELLOW("hardened_clay_stained_yellow", MaterialType.HARDENED_CLAY),
	HARDENED_CLAY("hardened_clay", MaterialType.CRAFTED),
	HAY_BLOCK("hay_block_side", MaterialType.CRAFTED),
	ICE_PACKED("ice_packed", MaterialType.NATURAL), // TODO verify?
	ICE("ice", MaterialType.NATURAL),
	IRON_BARS("iron_bars", MaterialType.CRAFTED),
	IRON_BLOCK("iron_block", MaterialType.ORE),
	IRON_ORE("iron_ore", MaterialType.ORE),
	JUKEBOX("jukebox_top", MaterialType.APPARATUS),
	LAPIS_BLOCK("lapis_block", MaterialType.ORE),
	LAPIS_ORE("lapis_ore", MaterialType.ORE),
	LEAVES_ACACIA("leaves_acacia", MaterialType.PLANT),
	LEAVES_BIG_OAK("leaves_big_oak", MaterialType.PLANT),
	LEAVES_BIRCH("leaves_birch", MaterialType.PLANT),
	LEAVES_JUNGLE("leaves_jungle", MaterialType.PLANT),
	LEAVES_OAK("leaves_oak", MaterialType.PLANT),
	LEAVES_SPRUCE("leaves_spruce", MaterialType.PLANT),
	LEVER("lever", MaterialType.REDSTONE),
	LOG_ACACIA("log_acacia_top", MaterialType.PLANT),
	LOG_BIG_OAK("log_big_oak_top", MaterialType.PLANT),
	LOG_BIRCH("log_birch_top", MaterialType.PLANT),
	LOG_JUNGLE("log_jungle_top", MaterialType.PLANT),
	LOG_OAK("log_oak_top", MaterialType.PLANT),
	LOG_SPRUCE("log_spruce_top", MaterialType.PLANT),
	MELON_STEM("melon_stem_disconnected", MaterialType.PLANT),
	MELON("melon_top", MaterialType.PLANT),
	MOB_SPAWNER("mob_spawner"),
	MUSHROOM_BLOCK_BROWN("mushroom_block_skin_brown", MaterialType.PLANT),
	MUSHROOM_BLOCK_RED("mushroom_block_skin_red", MaterialType.PLANT),
	MUSHROOM_BLOCK_STEM("mushroom_block_skin_stem", MaterialType.PLANT),
	MUSHROOM_BROWN("mushroom_brown", MaterialType.PLANT),
	MUSHROOM_RED("mushroom_red", MaterialType.PLANT),
	NETHER_BRICK("nether_brick", MaterialType.CRAFTED),
	NETHER_WART("nether_wart_stage_2", MaterialType.PLANT),
	NETHERRACK("netherrack", MaterialType.NATURAL),
	NOTE_BLOCK("noteblock", MaterialType.APPARATUS),
	OBSIDIAN("obsidian", MaterialType.NATURAL),
	PISTON("piston_side", MaterialType.REDSTONE),
	PLANKS_ACACIA("planks_acacia", MaterialType.CRAFTED),
	PLANKS_BIG_OAK("planks_big_oak", MaterialType.CRAFTED),
	PLANKS_BIRCH("planks_birch", MaterialType.CRAFTED),
	PLANKS_JUNGLE("planks_jungle", MaterialType.CRAFTED),
	PLANKS_OAK("planks_oak", MaterialType.CRAFTED),
	PLANKS_SPRUCE("planks_spruce", MaterialType.CRAFTED),
	POTATOES("potatoes_stage_3", MaterialType.PLANT),
	PUMPKIN("pumpkin_face_off", MaterialType.PLANT),
	PUMPKIN_LAMP("pumpkin_face_on", MaterialType.CRAFTED),
	PUMPKIN_STEM("pumpkin_stem_disconnected", MaterialType.PLANT),
	QUARTZ_BLOCK_CHISELED("quartz_block_chiseled_top", MaterialType.CRAFTED),
	QUARTZ_BLOCK_LINES("quartz_block_lines_top", MaterialType.CRAFTED),
	QUARTZ_BLOCK("quartz_block_top", MaterialType.CRAFTED),
	QUARTZ_ORE("quartz_ore", MaterialType.ORE),
	RAIL_ACTIVATOR("rail_activator_powered", MaterialType.REDSTONE),
	RAIL_DETECTOR("rail_detector_powered", MaterialType.REDSTONE),
	RAIL_BOOSTER("rail_golden_powered", MaterialType.REDSTONE),
	RAIL("rail_normal", MaterialType.CRAFTED),
	REDSTONE_BLOCK("redstone_block", MaterialType.REDSTONE),
	REDSTONE_DUST("redstone_dust_line", MaterialType.REDSTONE),
	REDSTONE_LAMP("redstone_lamp_on", MaterialType.REDSTONE),
	REDSTONE_ORE("redstone_ore", MaterialType.ORE),
	REDSTONE_TORCH("redstone_torch_on", MaterialType.REDSTONE),
	SAND_RED("red_sand", MaterialType.NATURAL),
	SAND("sand", MaterialType.NATURAL),
	SANDSTONE_CHISELED("sandstone_carved", MaterialType.CRAFTED),
	SANDSTONE("sandstone_normal", MaterialType.NATURAL, MaterialType.CRAFTED),
	SANDSTONE_SMOOTH("sandstone_smooth", MaterialType.CRAFTED),
	SAPLING_ACACIA("sapling_acacia", MaterialType.PLANT),
	SAPLING_BIRCH("sapling_birch", MaterialType.PLANT),
	SAPLING_JUNGLE("sapling_jungle", MaterialType.PLANT),
	SAPLING_OAK("sapling_oak", MaterialType.PLANT),
	SAPLING_ROOFED_OAK("sapling_roofed_oak", MaterialType.PLANT),
	SAPLING_SPRUCE("sapling_spruce", MaterialType.PLANT),
	SNOW("snow", MaterialType.NATURAL),
	SOUL_SAND("soul_sand", MaterialType.NATURAL),
	SPONGE("sponge", MaterialType.NATURAL),
	STONE_SLAB("stone_slab", MaterialType.CRAFTED),
	STONE("stone", MaterialType.NATURAL),
	STONE_BRICK_CHISELED("stonebrick_carved", MaterialType.CRAFTED),
	STONE_BRICK_CRACKED("stonebrick_cracked", MaterialType.CRAFTED),
	STONE_BRICK_MOSSY("stonebrick_mossy", MaterialType.CRAFTED),
	STONE_BRICK("stonebrick"),
	SUGARCANE("reeds", MaterialType.PLANT),
	TALL_GRASS("tallgrass", MaterialType.PLANT),
	TNT("tnt_side", MaterialType.CRAFTED),
	TORCH("torch_on", MaterialType.CRAFTED),
	TRAPDOOR("trapdoor", MaterialType.CRAFTED),
	TRIPWIRE_SOURCE("trip_wire_source", MaterialType.CRAFTED),
	TRIPWIRE("trip_wire", MaterialType.NATURAL),
	VINE("vine", MaterialType.PLANT),
	WATER_LILY("waterlily", MaterialType.PLANT),
	WEB("web"),
	WHEAT("wheat_stage_7", MaterialType.PLANT),
	WOOL_BLACK("wool_colored_black", MaterialType.WOOL),
	WOOL_BLUE("wool_colored_blue", MaterialType.WOOL),
	WOOL_BROWN("wool_colored_brown", MaterialType.WOOL),
	WOOL_CYAN("wool_colored_cyan", MaterialType.WOOL),
	WOOL_GRAY("wool_colored_gray", MaterialType.WOOL),
	WOOL_GREEN("wool_colored_green", MaterialType.WOOL),
	WOOL_LIGHT_BLUE("wool_colored_light_blue", MaterialType.WOOL),
	WOOL_LIME("wool_colored_lime", MaterialType.WOOL),
	WOOL_MAGENTA("wool_colored_magenta", MaterialType.WOOL),
	WOOL_ORANGE("wool_colored_orange", MaterialType.WOOL),
	WOOL_PINK("wool_colored_pink", MaterialType.WOOL),
	WOOL_PURPLE("wool_colored_purple", MaterialType.WOOL),
	WOOL_RED("wool_colored_red", MaterialType.WOOL),
	WOOL_SILVER("wool_colored_silver", MaterialType.WOOL),
	WOOL_WHITE("wool_colored_white", MaterialType.WOOL),
	WOOL_YELLOW("wool_colored_yellow", MaterialType.WOOL),
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
