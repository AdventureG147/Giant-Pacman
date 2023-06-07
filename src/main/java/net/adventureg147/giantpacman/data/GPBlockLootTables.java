package net.adventureg147.giantpacman.data;

import net.adventureg147.giantpacman.GiantPacman;
import net.adventureg147.giantpacman.common.registry.GPBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.stream.Collectors;

public class GPBlockLootTables extends BlockLootTables {
	@Override
	protected void addTables() {
		// Trophies
		dropSelf(GPBlocks.PACMAN_TROPHY.get());
		dropSelf(GPBlocks.STONE_PACMAN_STATUE.get());
		dropSelf(GPBlocks.GOLDEN_PACMAN_TROPHY.get());
		dropSelf(GPBlocks.DIAMOND_PACMAN_TROPHY.get());
	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return ForgeRegistries.BLOCKS.getValues().stream()
				.filter(block -> Objects.requireNonNull(block.getRegistryName()).getNamespace().equals(GiantPacman.MODID))
				.collect(Collectors.toList());
	}
}
