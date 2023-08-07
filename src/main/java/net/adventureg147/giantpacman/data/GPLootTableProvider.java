/*
 * Copyright (c) AdventureG147 and contributors
 * All Rights Reserved
 */

package net.adventureg147.giantpacman.data;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.adventureg147.giantpacman.GiantPacman;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.entity.monster.GiantEntity;
import net.minecraft.loot.LootParameterSet;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.ValidationTracker;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GPLootTableProvider extends LootTableProvider {
	public GPLootTableProvider(DataGenerator dataGeneratorIn) {
		super(dataGeneratorIn);
	}

	@Override
	public String getName() {
		return GiantPacman.MODNAME + ": Loot Tables";
	}

	@Override
	protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
		return ImmutableList.of(Pair.of(GPBlockLootTables::new, LootParameterSets.BLOCK), Pair.of(GPEntityLootTables::new, LootParameterSets.ENTITY));
	}

	@Override
	protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
	}
}
