package net.adventureg147.giantpacman.common.registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class GPItemGroup {
	public static final ItemGroup PACMAN_GROUP = new ItemGroup("giantpacman.item_group") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(GPItems.PACMAN_SPAWN_EGG.get());
		}
	};
}
