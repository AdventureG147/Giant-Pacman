package net.adventureg147.giantpacman.common.worldgen;

import net.adventureg147.giantpacman.common.registry.GPEntityTypes;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class BiomeLoadEventSubscriber {
	public static void onBiomeLoadingEvent(final BiomeLoadingEvent event) {
		MobSpawnHandler.addMobSpawns(event);
	}

	private static class MobSpawnHandler {
		private static final Consumer<MobSpawnInfoBuilder> PACMAN_SPAWNS = (builder) -> {
			builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(GPEntityTypes.GIANT_PACMAN.get(), 40, 1, 2));
		};

		public static void addMobSpawns(BiomeLoadingEvent event) {
			MobSpawnInfoBuilder spawnInfoBuilder = event.getSpawns();
			RegistryKey<Biome> biome = RegistryKey.create(ForgeRegistries.Keys.BIOMES, Objects.requireNonNull(event.getName(), "Who registered null name biome, naming criticism!"));

			if (biome == Biomes.PLAINS) {
				PACMAN_SPAWNS.accept(spawnInfoBuilder);
			} else if (biome == Biomes.DARK_FOREST) {
				PACMAN_SPAWNS.accept(spawnInfoBuilder);
			} else if (biome == Biomes.TAIGA) {
				PACMAN_SPAWNS.accept(spawnInfoBuilder);
			} else if (biome == Biomes.BEACH) {
				PACMAN_SPAWNS.accept(spawnInfoBuilder);
			} else if (biome == Biomes.BIRCH_FOREST) {
				PACMAN_SPAWNS.accept(spawnInfoBuilder);
			} else if (biome == Biomes.DESERT) {
				PACMAN_SPAWNS.accept(spawnInfoBuilder);
			}
		}
	}
}
