package ganymedes01.etfuturum.core.proxy;

import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.client.gui.inventory.GuiEnchantment;
import ganymedes01.etfuturum.configuration.ConfigurationHandler;
import ganymedes01.etfuturum.core.handlers.ModEventHandler;
import ganymedes01.etfuturum.core.utils.Utils;
import ganymedes01.etfuturum.entities.EntityArmourStand;
import ganymedes01.etfuturum.entities.EntityEndermite;
import ganymedes01.etfuturum.entities.EntityRabbit;
import ganymedes01.etfuturum.entities.EntityTippedArrow;
import ganymedes01.etfuturum.entities.ModEntityList;
import ganymedes01.etfuturum.inventory.ContainerEnchantment;
import ganymedes01.etfuturum.lib.GUIsID;
import ganymedes01.etfuturum.tileentities.TileEntityBanner;
import ganymedes01.etfuturum.tileentities.TileEntityEndRod;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy implements IGuiHandler {

	public void registerEvents() {
		FMLCommonHandler.instance().bus().register(ConfigurationHandler.INSTANCE);
		MinecraftForge.EVENT_BUS.register(new ModEventHandler());
	}

	public void registerEntities() {
		if (EtFuturum.enableBanners)
			GameRegistry.registerTileEntity(TileEntityBanner.class, Utils.getUnlocalisedName("banner"));
		if (EtFuturum.enableArmourStand)
			ModEntityList.registerEntity(EntityArmourStand.class, "wooden_armorstand", 0, EtFuturum.instance, 64, 1, true);
		if (EtFuturum.enableEndermite)
			ModEntityList.registerEntity(EntityEndermite.class, "endermite", 1, EtFuturum.instance, 64, 1, true, 1447446, 7237230);
		if (EtFuturum.enableChorusFruit)
			GameRegistry.registerTileEntity(TileEntityEndRod.class, Utils.getUnlocalisedName("end_rod"));
		if (EtFuturum.enableTippedArrows)
			ModEntityList.registerEntity(EntityTippedArrow.class, "tipped_arrow", 2, EtFuturum.instance, 64, 20, true);

		if (EtFuturum.enableRabbit) {
			ModEntityList.registerEntity(EntityRabbit.class, "rabbit", 3, EtFuturum.instance, 80, 3, true, 10051392, 7555121);

			List<BiomeGenBase> biomes = new LinkedList<BiomeGenBase>();
			label: for (BiomeGenBase biome : BiomeGenBase.getBiomeGenArray())
				if (biome != null)
					// Check if pigs can spawn on this biome
					for (Object obj : biome.getSpawnableList(EnumCreatureType.creature))
						if (obj instanceof SpawnListEntry) {
							SpawnListEntry entry = (SpawnListEntry) obj;
							if (entry.entityClass == EntityPig.class) {
								biomes.add(biome);
								continue label;
							}
						}

			EntityRegistry.addSpawn(EntityRabbit.class, 10, 3, 3, EnumCreatureType.creature, biomes.toArray(new BiomeGenBase[biomes.size()]));
		}
	}

	public void registerRenderers() {
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
			case GUIsID.ENCHANTING_TABLE:
				return new ContainerEnchantment(player.inventory, world, x, y, z);
			default:
				return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
			case GUIsID.ENCHANTING_TABLE:
				return new GuiEnchantment(player.inventory, world, null);
			default:
				return null;
		}
	}
}