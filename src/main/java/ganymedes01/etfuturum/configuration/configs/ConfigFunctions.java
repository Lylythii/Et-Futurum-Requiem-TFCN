package ganymedes01.etfuturum.configuration.configs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ganymedes01.etfuturum.configuration.ConfigBase;
import net.minecraft.item.Item;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;

public class ConfigFunctions extends ConfigBase {

	public static boolean enableBowRendering;
	public static boolean enableSkullDrop;
	public static boolean enableRecipeForPrismarine;
	public static boolean enableFancySkulls;
	public static boolean enableTransparentAmour;
	public static boolean enableUpdatedFoodValues;
	public static boolean enablePlayerSkinOverlay;
	public static boolean enableAutoAddSmoker;
	public static boolean enableAutoAddBlastFurnace;
	public static boolean enableMeltGear = false;
	public static boolean enableRecipeForTotem;
	public static List<Item> shulkerBans;
	public static String[] shulkerBansString;
	public static int shulkerBoxTooltipLines;
	public static boolean enableExtraF3HTooltips;
	public static final String[] shulkerDefaultBans = new String[] {
			"etfuturum:shulker_box",
			"minecraft:writable_book",
			"minecraft:written_book",
			"BiblioCraft:item.BigBook",
			"antiqueatlas:antiqueAtlas",
			"DQMIIINext:ItemMahounoTutu11",
			"DQMIIINext:ItemOokinaFukuro",
			"DQMIIINext:ItemOokinaFukuroR",
			"DQMIIINext:ItemOokinaFukuroG",
			"DQMIIINext:ItemOokinaFukuroB",
			"DQMIIINext:ItemOokinaFukuroY",
			"JABBA:moverDiamond",
			"JABBA:mover",
			"ExtraUtilities:bedrockiumIngot",
			"ExtraUtilities:golden_lasso",
			"ExtraUtilities:block_bedrockium",
			"ExtraUtilities:golden_bag",
			"ExtraSimple:lasso",
			"ExtraSimple:goldenBag",
			"ExtraSimple:bedrockium",
			"ExtraSimple:bedrockiumBlock",
			"antiqueatlas:antiqueAtlas",
			"OpenBlocks:devnull",
			"warpbook:warpbook",
			"StorageDrawers:fullDrawers1",
			"StorageDrawers:fullDrawers2",
			"StorageDrawers:fullDrawers4",
			"StorageDrawers:halfDrawers2",
			"StorageDrawers:halfDrawers4",
			"StorageDrawers:compDrawers",
			"StorageDrawers:controller",
			"StorageDrawers:controllerSlave",
			"StorageDrawersBop:fullDrawers1",
			"StorageDrawersBop:fullDrawers2",
			"StorageDrawersBop:fullDrawers4",
			"StorageDrawersBop:halfDrawers2",
			"StorageDrawersBop:halfDrawers4",
			"dendrology:fullDrawers1",
			"dendrology:fullDrawers2",
			"dendrology:fullDrawers4",
			"dendrology:halfDrawers2",
			"dendrology:halfDrawers4",
			"StorageDrawers:fullCustom1",
			"StorageDrawers:fullCustom2",
			"StorageDrawers:fullCustom4",
			"StorageDrawers:halfCustom2",
			"StorageDrawers:halfCustom4",
			"StorageDrawers:trimCustom",
			"StorageDrawersBop:fullDrawers1",
			"StorageDrawersBop:fullDrawers2",
			"StorageDrawersBop:fullDrawers4",
			"StorageDrawersBop:halfDrawers2",
			"StorageDrawersBop:halfDrawers4",
			"StorageDrawersBop:trim",
			"StorageDrawersForestry:fullDrawers1A",
			"StorageDrawersForestry:fullDrawers2A",
			"StorageDrawersForestry:fullDrawers4A",
			"StorageDrawersForestry:halfDrawers2A",
			"StorageDrawersForestry:halfDrawers4A",
			"StorageDrawersForestry:trimA",
			"StorageDrawersNatura:fullDrawers1",
			"StorageDrawersNatura:fullDrawers2",
			"StorageDrawersNatura:fullDrawers4",
			"StorageDrawersNatura:halfDrawers2",
			"StorageDrawersNatura:halfDrawers4",
			"StorageDrawersNatura:trim",
			"ironbackpacks:basicBackpack",
			"ironbackpacks:goldBackpack",
			"ironbackpacks:diamondBackpack",
			"ironbackpacks:ironBackpack",
			"WitchingGadgets:item.WG_Bag",
			"betterstorage:cardboardBox",
			"betterstorage:backpack",
			"betterstorage:thaumcraftBackpack",
			"adventurebackpack:adventureBackpack"
	};
	public static boolean enableSilkTouchingMushrooms;
	public static boolean enableSticksFromDeadBushes;
	public static boolean enableStoneBrickRecipes;
	public static boolean enableShearableCobwebs;
	public static boolean registerRawItemAsOre;
	public static boolean enableFloatingTrapDoors;
	public static boolean enableHoeMining;
	public static boolean enableHayBaleFalls;
	public static int hayBaleReducePercent;
	public static int totemHealPercent;
	public static boolean enableNetheriteFlammable;
	public static boolean enableExtraBurnableBlocks;
	public static boolean enableUpdatedHarvestLevels;
	public static String[] extraDropRawOres = new String[] {"oreCopper", "oreTin"};

	static final String catChanges = "changes";
	static final String catSettings = "settings";
	static final String catClient = "client";

	public static final String PATH = configDir + File.separator + "functions.cfg";
	public static final ConfigFunctions configInstance = new ConfigFunctions(new File(Launch.minecraftHome, PATH));

	public ConfigFunctions(File file) {
		super(file);
		setCategoryComment(catChanges, "Changes to existing content.");
		setCategoryComment(catSettings, "Settings for Et Futurum content.");
		setCategoryComment(catClient, "Client-side settings or changes.");
		
		configCats.add(getCategory(catChanges));
		configCats.add(getCategory(catSettings));
		configCats.add(getCategory(catClient));
	}

	protected void syncConfigOptions() {
		Configuration cfg = configInstance;
		
		//changes
		enableExtraBurnableBlocks = cfg.getBoolean("enableExtraBurnableBlocks", catChanges, true, "Fences, gates and dead bushes burn");
		enableUpdatedHarvestLevels = cfg.getBoolean("enableUpdatedHarvestLevels", catChanges, true, "Packed Ice, ladders and melons have preferred tools");
		enableSilkTouchingMushrooms = cfg.getBoolean("enableSilkMushroom", catChanges, true, "Mushroom blocks can be silk-touched");
		enableSticksFromDeadBushes = cfg.getBoolean("enableBushSticks", catChanges, true, "Dead Bushes drop sticks");
		enableSkullDrop = cfg.getBoolean("enableSkullDrop", catChanges, true, "Skulls drop from charged creeper kills");
		enableUpdatedFoodValues = cfg.getBoolean("enableUpdatedFood", catChanges, true, "Use updated food values");
		enableShearableCobwebs = cfg.getBoolean("enableShearableCobwebs", catChanges, true, "");
		enableStoneBrickRecipes = cfg.getBoolean("enableStoneBrickRecipes", catChanges, true, "Makes mossy, cracked and chiseled stone brick craftable");
		enableFloatingTrapDoors = cfg.getBoolean("enableFloatingTrapDoors", catChanges, true, "");
		enableHayBaleFalls = cfg.getBoolean("enableHayBaleFalls", catChanges, true, "If true, fall damage on a hay bale will be reduced");
		enableHoeMining = cfg.getBoolean("enableHoeMining", catChanges, true, "Allows blocks like hay bales, leaves etc to mine faster with hoes");
		hayBaleReducePercent = cfg.getInt("hayBaleReducePercent", catChanges, 20, 0, 99, "If enableHayBaleFalls is true, what percent should we keep for the fall damage?");
		
		//settings
		enableNetheriteFlammable = cfg.getBoolean("enableNetheriteFlammable", catSettings, false, "Set to true to disable the fireproof item entity Netherite/ancient debris etc uses");
		enableRecipeForPrismarine = cfg.getBoolean("enablePrismarineRecipes", catSettings, true, "Recipe for prismarine if you want it without the temples, or want it craftable alongside temples.");
		enableAutoAddSmoker = cfg.getBoolean("enableAutoAddSmoker", catSettings, true, "Auto-adds smeltable foods to the smoker, turn off for only vanilla food");
		enableAutoAddBlastFurnace = cfg.getBoolean("enableAutoAddBlastFurnace", catSettings, true, "Auto-adds ores to the blast furnace, detected if the input has the \"ore\" oreDictionary prefix and is smeltable. Turn off for only vanilla Et Futurum ores");
		enableRecipeForTotem = cfg.getBoolean("enableRecipeForTotem", catSettings, false, "Recipe for totems since there's no other way to get them currently.");
		shulkerBansString = cfg.getStringList("shulkerBans", catSettings, shulkerDefaultBans, "Things (namespaced:id) that should not go inside a Shulker Box. Used to ensure recursive storage, book banning and data overloads with certain items can be stopped. A default list is provided, but it might not cover everything so be sure to check with the mods you have. Be sure to check the default list for this frequently, it will be updated frequently.");
		totemHealPercent = cfg.getInt("totemHealPercent", catSettings, 5, 5, 100, "Percentage of max health for totem to set you at if you die with it. (5% is 0.05, 20 * 0.05 = 1, 1 health is one half-heart)");
		
		//client
		enableTransparentAmour = cfg.getBoolean("enableTransparentAmour", catClient, true, "Allow non-opaque armour");
		enableBowRendering = cfg.getBoolean("enableBowRendering", catClient, true, "Bows render pulling animation on inventory");
		enableFancySkulls = cfg.getBoolean("enableFancySkulls", catClient, true, "Skulls render 3D in inventory");
		enablePlayerSkinOverlay = cfg.getBoolean("enablePlayerSkinOverlay", catClient, true, "Allows use of 1.8 skin format, and Alex skins. Also includes some fixes from SkinPort. (Per SkinPort author's permission) Disable if skin is displaying oddly. Not compatible with OptiFine, use FastCraft instead.");
		enableExtraF3HTooltips = cfg.getBoolean("enableExtraF3HTooltips", catClient, true, "Enables NBT tag count and item namespace label on F3 + H debug item labels");
		shulkerBoxTooltipLines = cfg.getInt("shulkerBoxTooltipLines", catClient, 5, 0, Byte.MAX_VALUE, "The maximum amount of items a Shulker box can display on its tooltip. When the box has more stacks inside than this number, the rest of the stacks are displayed as \"And x more...\". Set to 0 to disable Shulker Box tooltips.");
	}
}