package net.adventureg147.giantpacman.data;

import net.adventureg147.giantpacman.GiantPacman;
import net.adventureg147.giantpacman.common.registry.GPEntityTypes;
import net.adventureg147.giantpacman.common.registry.GPItems;
import net.minecraft.data.loot.EntityLootTables;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.conditions.KilledByPlayer;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashSet;
import java.util.Set;

public class GPEntityLootTables extends EntityLootTables {
	private final Set<EntityType<?>> knownEntities = new HashSet<>();

	@Override
	@ParametersAreNonnullByDefault
	public void add(EntityType<?> entity, LootTable.Builder builder) {
		super.add(entity, builder);
		knownEntities.add(entity);
	}

	public String getName() {
		return GiantPacman.MODNAME + ": Entity Loot Tables";
	}

	@Override
	protected void addTables() {
		add(GPEntityTypes.GIANT_PACMAN.get(),
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(GPItems.BLINKY.get())
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(GPItems.PINKY.get())
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(GPItems.INKY.get())
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(GPItems.CLYDE.get())
										.when(KilledByPlayer.killedByPlayer())))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantRange.exactly(1))
								.add(ItemLootEntry.lootTableItem(GPItems.SUE.get())
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(GPItems.TIM.get())
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(GPItems.FUNKY.get())
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(GPItems.SPUNKY.get())
										.when(KilledByPlayer.killedByPlayer()))
								.add(ItemLootEntry.lootTableItem(GPItems.ORSON.get())
										.when(KilledByPlayer.killedByPlayer()))));
	}

	@Override
	public Set<EntityType<?>> getKnownEntities() {
		return knownEntities;
	}
}
