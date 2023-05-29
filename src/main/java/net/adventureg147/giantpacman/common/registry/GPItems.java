package net.adventureg147.giantpacman.common.registry;

import net.adventureg147.giantpacman.GiantPacman;
import net.adventureg147.giantpacman.common.item.GPSpawnEggItem;
import net.adventureg147.giantpacman.common.item.PacmanGhostItem;
import net.adventureg147.giantpacman.common.item.ThankYouBlackoutItem;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GPItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, GiantPacman.MODID);

	// Pacman Spawn Egg
	public static final RegistryObject<GPSpawnEggItem> PACMAN_SPAWN_EGG = ITEMS.register("pacman_spawn_egg", () -> new GPSpawnEggItem(GPEntityTypes.GIANT_PACMAN, new Item.Properties().tab(GPItemGroup.PACMAN_GROUP)));

	// Main Ghosts
	public static final RegistryObject<PacmanGhostItem> BLINKY = ITEMS.register("blinky", () -> new PacmanGhostItem(new Item.Properties().rarity(Rarity.RARE).tab(GPItemGroup.PACMAN_GROUP), new EffectInstance(Effects.DAMAGE_BOOST, 600, 2)));
	public static final RegistryObject<PacmanGhostItem> PINKY = ITEMS.register("pinky", () -> new PacmanGhostItem(new Item.Properties().rarity(Rarity.RARE).tab(GPItemGroup.PACMAN_GROUP), new EffectInstance(Effects.MOVEMENT_SPEED, 600, 2)));
	public static final RegistryObject<PacmanGhostItem> INKY = ITEMS.register("inky", () -> new PacmanGhostItem(new Item.Properties().rarity(Rarity.RARE).tab(GPItemGroup.PACMAN_GROUP), new EffectInstance(Effects.DAMAGE_RESISTANCE, 600, 2)));
	public static final RegistryObject<PacmanGhostItem> CLYDE = ITEMS.register("clyde", () -> new PacmanGhostItem(new Item.Properties().rarity(Rarity.RARE).tab(GPItemGroup.PACMAN_GROUP), new EffectInstance(Effects.REGENERATION, 600, 2)));

	// Side Ghosts
	public static final RegistryObject<PacmanGhostItem> SUE = ITEMS.register("sue", () -> new PacmanGhostItem(new Item.Properties().rarity(Rarity.UNCOMMON).tab(GPItemGroup.PACMAN_GROUP), new EffectInstance(Effects.FIRE_RESISTANCE, 2400)));
	public static final RegistryObject<PacmanGhostItem> TIM = ITEMS.register("tim", () -> new PacmanGhostItem(new Item.Properties().rarity(Rarity.UNCOMMON).tab(GPItemGroup.PACMAN_GROUP), new EffectInstance(Effects.DIG_SPEED, 2400)));
	public static final RegistryObject<PacmanGhostItem> FUNKY = ITEMS.register("funky", () -> new PacmanGhostItem(new Item.Properties().rarity(Rarity.UNCOMMON).tab(GPItemGroup.PACMAN_GROUP), new EffectInstance(Effects.JUMP, 2400)));
	public static final RegistryObject<PacmanGhostItem> SPUNKY = ITEMS.register("spunky", () -> new PacmanGhostItem(new Item.Properties().rarity(Rarity.UNCOMMON).tab(GPItemGroup.PACMAN_GROUP), new EffectInstance(Effects.NIGHT_VISION, 2400)));
	public static final RegistryObject<PacmanGhostItem> ORSON = ITEMS.register("orson", () -> new PacmanGhostItem(new Item.Properties().rarity(Rarity.UNCOMMON).tab(GPItemGroup.PACMAN_GROUP), new EffectInstance(Effects.WATER_BREATHING, 2400)));

	// Thank You Blackout!
	public static final RegistryObject<ThankYouBlackoutItem> THANK_YOU_BLACKOUT = ITEMS.register("thank_you_blackout", () -> new ThankYouBlackoutItem(new Item.Properties().rarity(Rarity.EPIC).tab(GPItemGroup.PACMAN_GROUP)));

	// Development Items
	public static final RegistryObject<Item> RANDOM_ITEMS_DROP_TOOLTIP = ITEMS.register("random_items_drop_tooltip", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> PACMAN_ICON = ITEMS.register("pacman_icon", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> VULNERABLE_GHOST_ICON = ITEMS.register("vulnerable_ghost_icon", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> FLASHING_VULNERABLE_GHOST_ICON = ITEMS.register("flashing_vulnerable_ghost_icon", () -> new Item(new Item.Properties()));
}
