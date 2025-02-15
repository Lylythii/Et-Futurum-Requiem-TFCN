package ganymedes01.etfuturum.items.block;

import ganymedes01.etfuturum.configuration.configs.ConfigSounds;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemWoodDoor extends ItemBlock {

	public ItemWoodDoor(Block block) {
		super(block);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (side != 1)
			return false;
		y++;
		if (player.canPlayerEdit(x, y, z, side, stack) && player.canPlayerEdit(x, y + 1, z, side, stack)) {
			if (!field_150939_a.canPlaceBlockAt(world, x, y, z))
				return false;
			ItemDoor.placeDoorBlock(world, x, y, z, MathHelper.floor_double((player.rotationYaw + 180.0F) * 4.0F / 360.0F - 0.5D) & 3, field_150939_a);
			//Disable the sound for continuity, so it doesn't play when the event-based player would not
			if(ConfigSounds.fixSilentPlacing)world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), this.field_150939_a.stepSound.func_150496_b(), (this.field_150939_a.stepSound.getVolume() + 1.0F) / 2.0F, this.field_150939_a.stepSound.getPitch() * 0.8F);
			stack.stackSize--;
			return true;
		}
		return false;
	}
}