package net.adventureg147.giantpacman.common.registry;

import net.adventureg147.giantpacman.GiantPacman;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

public class GPTags {
	public static class Items {
		public static ITag.INamedTag<Item> tag(String name) {
			return ItemTags.bind(GiantPacman.MODID + ":" + name);
		}
		public static final ITag.INamedTag<Item> UNDROPPABLE = tag("undroppable");
	}
}
