package ganymedes01.etfuturum.compat.cthandlers;

import ganymedes01.etfuturum.compat.CompatCraftTweaker;
import ganymedes01.etfuturum.core.utils.BrewingFuelRegistry;
import ganymedes01.etfuturum.core.utils.ItemStackMap;
import ganymedes01.etfuturum.recipes.BlastFurnaceRecipes;
import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.mc1710.item.MCItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.*;

import static ganymedes01.etfuturum.compat.CompatCraftTweaker.getInternal;

@ZenClass("mods.etfuturum.brewingFuel")
public class CTBrewingFuels {

	@ZenMethod
	public static void remove(IIngredient fuel) {

		Object internal = getInternal(fuel);

		if(((internal instanceof String && BrewingFuelRegistry.isFuel((String) internal)) || (internal instanceof ItemStack && BrewingFuelRegistry.isFuel((ItemStack) internal)))) {
			final ItemStackMap<Integer> toRemove = new ItemStackMap<>();
			for(Map.Entry<ItemStack, Integer> fuelEntry : BrewingFuelRegistry.getFuels().entrySet()) {
				if(fuel.matches(new MCItemStack(fuelEntry.getKey()))) {
					toRemove.put(fuelEntry.getKey(), fuelEntry.getValue());
				}
			}
			MineTweakerAPI.apply(new RemoveAction(toRemove));
		} else {
			MineTweakerAPI.logWarning("No brewing fuels for " + fuel.toString());
		}
	}

	@ZenMethod
	public static void addFuel(IIngredient fuel, int count) {
		List<IItemStack> items = fuel.getItems();
		if (items == null) {
			MineTweakerAPI.logError("Cannot turn " + fuel.toString() + " into a brewing fuel");
			return;
		}

		Object internal = getInternal(fuel);

		final ItemStack[] toAdd = CompatCraftTweaker.getItemStacks(items);
		MineTweakerAPI.apply(new AddAction(fuel, toAdd, count));
	}

	// ######################
	// ### Action classes ###
	// ######################

	private static class RemoveAction implements IUndoableAction {

		private final ItemStackMap<Integer> items;

		public RemoveAction(ItemStackMap<Integer> items) {
			this.items = items;
		}

		@Override
		public void apply() {
			for (ItemStack item : items.keySet()) {
				BrewingFuelRegistry.remove(item);
			}
		}

		@Override
		public boolean canUndo() {
			return true;
		}

		@Override
		public void undo() {
			for(Map.Entry<ItemStack, Integer> entry : items.entrySet()) {
				BrewingFuelRegistry.registerFuel(entry.getKey(), entry.getValue());
			}
		}

		@Override
		public String describe() {
			return "Removing " + items.size() + " brewing fuels";
		}

		@Override
		public String describeUndo() {
			return "Restoring " + items.size() + " brewing fuels";
		}

		@Override
		public Object getOverrideKey() {
			return null;
		}
	}

	private static class AddAction implements IUndoableAction {

		private final IIngredient ingredient;
		private final ItemStack[] fuels;
		private final int count;

		public AddAction(IIngredient ingredient, ItemStack[] fuels, int count) {
			this.ingredient = ingredient;
			this.fuels = fuels;
			this.count = count;
		}

		@Override
		public void apply() {
			for (ItemStack inputStack : fuels) {
				BrewingFuelRegistry.registerFuel(inputStack, count);
			}
		}

		@Override
		public boolean canUndo() {
			return true;
		}

		@Override
		public void undo() {
			for (ItemStack inputStack : fuels) {
				BrewingFuelRegistry.remove(inputStack);
			}
		}

		@Override
		public String describe() {
			return "Adding brewing fuel for " + ingredient;
		}

		@Override
		public String describeUndo() {
			return "Removing brewing fuel for " + ingredient;
		}

		@Override
		public Object getOverrideKey() {
			return null;
		}
	}
}
