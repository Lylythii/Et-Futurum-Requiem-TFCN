package ganymedes01.etfuturum.inventory;

import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.ModItems;
import ganymedes01.etfuturum.inventory.slot.SlotLimited;
import ganymedes01.etfuturum.inventory.slot.SlotSmithingResult;
import ganymedes01.etfuturum.recipes.ModRecipes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerSmithingTable extends Container {
    public final World world;
    public final InventoryBasic inputSlots = new InventoryBasic("Smithing", true, 2) {
        @Override
        public void markDirty() {
            super.markDirty();
            onCraftMatrixChanged(this);
        }
    };
    public final InventoryCraftResult resultSlot = new InventoryCraftResult();
    public final Slot applicant = new Slot(inputSlots, 0, 27, 47);
    public final SlotLimited ingot = new SlotLimited(inputSlots, 1, 76, 47, ContainerSmithingTable::isNetheriteIngot);
    public final SlotSmithingResult result = new SlotSmithingResult(this, resultSlot, 2, 134, 47);

    public ContainerSmithingTable(InventoryPlayer inv, World world) {
        this.world = world;
        addSlotToContainer(applicant);
        addSlotToContainer(ingot);
        addSlotToContainer(result);
        for (int y = 0; y < 3; y++) for (int x = 0; x < 9; x++) addSlotToContainer(new Slot(inv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
        for (int y = 0; y < 9; y++) addSlotToContainer(new Slot(inv, y, 8 + y * 18, 142));
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);
        if (!world.isRemote) {
            for (int i = 0; i < inputSlots.getSizeInventory(); i++) {
                final ItemStack stack = inputSlots.getStackInSlotOnClosing(i);
                if (stack != null) player.dropPlayerItemWithRandomChoice(stack, false);
            }
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        final Object s = inventorySlots.get(index);
        if (!(s instanceof Slot)) return null;
        final Slot slot = (Slot) s;
        if (!slot.getHasStack()) return null;
        final ItemStack newStack = slot.getStack();
        final ItemStack oldStack = newStack.copy();
        boolean merged = false;
        if (index <= 2) {
            merged = mergeItemStack(newStack, 3, 39, true);
        } else if (index < 30) {
            merged = tryUpper(newStack) || mergeItemStack(newStack, 30, 39, false);
        } else {
            merged = tryUpper(newStack) || mergeItemStack(newStack, 3, 30, false);
        }
        if (!merged) return null;
        if (newStack.stackSize == 0){
            slot.putStack(null);
        } else slot.onSlotChanged();
        slot.onPickupFromSlot(player, newStack);
        return oldStack;
    }

    private boolean tryUpper(ItemStack newStack) {
        return !ingot.getHasStack() && isNetheriteIngot(newStack) && mergeItemStack(newStack, 1, 2, false) || !applicant.getHasStack() && mergeItemStack(newStack, 0, 1, false);
    }

    @Override
    public void onCraftMatrixChanged(IInventory inv) {
        super.onCraftMatrixChanged(inv);
        if (inv == this.inputSlots) {
            updateSmithingOutput();
            detectAndSendChanges();
        }
    }

    public void updateSmithingOutput() {
        final ItemStack a = inputSlots.getStackInSlot(0);
        final ItemStack b = inputSlots.getStackInSlot(1);
        if (a == null) {
            resultSlot.setInventorySlotContents(0, null);
            return;
        }
        
        Pair<Object, Item> pair = getResult(a);
        
        if (pair == null || b == null || !secondItemMatches(b, pair.getLeft())) {
            resultSlot.setInventorySlotContents(0, null);
            return;
        }
        
        final ItemStack result = new ItemStack(pair.getRight());
        final Map<?, ?> enchantmentMap = EnchantmentHelper.getEnchantments(a);
        EnchantmentHelper.setEnchantments(enchantmentMap, result);
        result.setItemDamage(a.getItemDamage());
        if(a.hasDisplayName()) {
            result.setStackDisplayName(a.getDisplayName());
        }
        resultSlot.setInventorySlotContents(0, result);
    }

    private static boolean isNetheriteIngot(ItemStack stack) {
        return stack.getItem().equals(ModItems.netherite_ingot);
    }
    
    private Pair<Object, Item> getResult(ItemStack stack) {
    	if(ModRecipes.smithingRecipeMap.containsKey(stack.getItem())) {
    		return ModRecipes.smithingRecipeMap.get(stack.getItem());
    	}
    	for(String string : EtFuturum.getOreStrings(stack)) {
    		if(ModRecipes.smithingRecipeMap.containsKey(string)) {
    			return ModRecipes.smithingRecipeMap.get(string);
    		}
    	}
    	return null;
    }
    
    private boolean secondItemMatches(ItemStack input2, Object expectedInput) {
    	if(input2.getItem() == expectedInput) {
    		return true;
    	} else
    	if(expectedInput instanceof String) {
    		return EtFuturum.hasDictTag(input2, (String)expectedInput);
    	}
    	return false;
    }

    public boolean unable(){
        return (applicant.getHasStack() || ingot.getHasStack()) && !result.getHasStack();
    }
}
