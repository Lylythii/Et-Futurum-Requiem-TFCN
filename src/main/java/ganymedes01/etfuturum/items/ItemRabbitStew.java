package ganymedes01.etfuturum.items;

import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.blocks.IConfigurable;
import ganymedes01.etfuturum.configuration.configs.ConfigEntities;
import ganymedes01.etfuturum.core.utils.Utils;
import net.minecraft.item.ItemSoup;

public class ItemRabbitStew extends ItemSoup implements IConfigurable {

	public ItemRabbitStew() {
		super(10);
		setTextureName("rabbit_stew");
		setUnlocalizedName(Utils.getUnlocalisedName("rabbit_stew"));
		setCreativeTab(isEnabled() ? EtFuturum.creativeTabItems : null);
	}

	@Override
	public boolean isEnabled() {
		return ConfigEntities.enableRabbit;
	}
}