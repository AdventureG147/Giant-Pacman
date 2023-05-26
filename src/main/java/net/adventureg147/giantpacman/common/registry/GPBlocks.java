package net.adventureg147.giantpacman.common.registry;

import net.adventureg147.giantpacman.GiantPacman;
import net.adventureg147.giantpacman.common.block.GoldenTrophyBlock;
import net.adventureg147.giantpacman.common.block.TrophyBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import java.util.function.Supplier;

public class GPBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, GiantPacman.MODID);
	public static final DeferredRegister<Item> ITEM_BLOCKS = DeferredRegister.create(ForgeRegistries.ITEMS, GiantPacman.MODID);

	public static final RegistryObject<Block> PACMAN_TROPHY = registerBlock("pacman_trophy", () -> new TrophyBlock(AbstractBlock.Properties.of(Material.DECORATION).noOcclusion()), GPItemGroup.PACMAN_GROUP);
	public static final RegistryObject<Block> GOLDEN_TROPHY = registerBlock("golden_pacman_trophy", () -> new GoldenTrophyBlock(AbstractBlock.Properties.of(Material.DECORATION).noOcclusion()), GPItemGroup.PACMAN_GROUP);

	public static <B extends Block> RegistryObject<B> registerBlock(String name, Supplier<? extends B> supplier, ItemGroup itemGroup) {
		RegistryObject<B> block = BLOCKS.register(name, supplier);
		ITEM_BLOCKS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(itemGroup)));
		return block;
	}
}
