package ganymedes01.etfuturum.blocks;

import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.client.sound.ModSounds;
import ganymedes01.etfuturum.configuration.configs.ConfigBlocksItems;
import ganymedes01.etfuturum.configuration.configs.ConfigSounds;
import ganymedes01.etfuturum.core.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockDeepslateCobbled extends Block implements IConfigurable {

	public BlockDeepslateCobbled() {
		super(Material.rock);
		this.setHardness(3.5F);
		this.setResistance(6);
		this.setBlockName(Utils.getUnlocalisedName("cobbled_deepslate"));
		this.setBlockTextureName("cobbled_deepslate");
		this.setCreativeTab(isEnabled() ? EtFuturum.creativeTabBlocks : null);
		this.setStepSound(ConfigSounds.newBlockSounds ? ModSounds.soundDeepslate : soundTypeStone);
	}

	@Override
	public boolean isEnabled() {
		return ConfigBlocksItems.enableDeepslate;
	}
}