package net.adventureg147.giantpacman.common.registry;

import net.adventureg147.giantpacman.GiantPacman;
import net.adventureg147.giantpacman.common.item.GPSpawnEggItem;
import net.adventureg147.giantpacman.common.item.ThankYouBlackout03;
import net.minecraft.item.Item;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GPItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, GiantPacman.MODID);

	public static final RegistryObject<GPSpawnEggItem> PACMAN_SPAWN_EGG = ITEMS.register("pacman_spawn_egg",
			() -> new GPSpawnEggItem(GPEntityTypes.GIANT_PACMAN, new Item.Properties().tab(GPItemGroup.PACMAN_GROUP)));

	public static final RegistryObject<ThankYouBlackout03> THANK_YOU_BLACKOUT_03 = ITEMS.register("thank_you_blackout03",
			() -> new ThankYouBlackout03(GPItems.ITEMS, new Item.Properties().tab(GPItemGroup.PACMAN_GROUP)));
}
