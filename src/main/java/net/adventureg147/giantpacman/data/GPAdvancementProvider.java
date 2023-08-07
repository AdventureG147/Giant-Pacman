/*
 * Copyright (c) AdventureG147 and contributors
 * All Rights Reserved
 */

package net.adventureg147.giantpacman.data;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.adventureg147.giantpacman.GiantPacman;
import net.adventureg147.giantpacman.common.registry.GPBlocks;
import net.adventureg147.giantpacman.common.registry.GPItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.*;
import net.minecraft.data.AdvancementProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import org.apache.commons.lang3.Validate;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Consumer;

public class GPAdvancementProvider extends AdvancementProvider {
	private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(GiantPacman.MODID, "textures/gui/advancements/backgrounds/giant_pacman.png");
	private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
	private final DataGenerator generator;

	@SuppressWarnings("deprecation")
	public GPAdvancementProvider(DataGenerator generatorIn) {
		super(generatorIn);
		this.generator = generatorIn;
	}

	@Override
	public String getName() {
		return GiantPacman.MODNAME + ": Advancements";
	}

	private static String advancementId(String s) {
		return GiantPacman.MODID + ":root/" + s;
	}

	private static Path getPath(Path pathIn, Advancement advancementIn) {
		return pathIn.resolve("data/" + advancementIn.getId().getNamespace() + "/advancements/" + advancementIn.getId().getPath() + ".json");
	}

	@Override
	public void run(DirectoryCache cache) {
		Path path = this.generator.getOutputFolder();
		Set<ResourceLocation> set = Sets.newHashSet();
		Consumer<Advancement> consumer = (advancement) -> {
			assert advancement.getDisplay() != null;
			GiantPacman.LOGGER.info("=--+--+--+--+--+--+--+--+--+--+--+--=");
			GiantPacman.LOGGER.debug("Advancement Id: " + advancement.getId());
			GiantPacman.LOGGER.debug("Advancement Item Icon: " + advancement.getDisplay().getIcon().getItem());
			GiantPacman.LOGGER.debug("Advancement Frame: " + advancement.getDisplay().getFrame());
			GiantPacman.LOGGER.debug("Advancement Criteria: " + advancement.getCriteria().keySet());
			GiantPacman.LOGGER.debug("Advancement Requirement Name: " + Arrays.deepToString(advancement.getRequirements()));
			GiantPacman.LOGGER.debug("Advancement JSON Id: " + advancement.getDisplay().serializeToJson());
			GiantPacman.LOGGER.info("=--+--+--+--+--+--+--+--+--+--+--+--=");

			if (!set.add(advancement.getId())) throw new IllegalStateException("Duplicate advancement " + advancement.getId());
			else {
				Path path1 = getPath(path, advancement);
				try {
					IDataProvider.save(GSON, cache, advancement.deconstruct().serializeToJson(), path1);
				} catch (IOException e) {
					GiantPacman.LOGGER.error("Couldn't save advancement {}", path1, e);
				}
			}
		};
		this.register(consumer);
	}

	public void register(Consumer<Advancement> t) {
		Advancement root = registerAdvancement("root", FrameType.TASK, GPItems.PACMAN_ICON.get())
				.addCriterion("root", PositionTrigger.Instance.located(LocationPredicate.ANY))
				.save(t, advancementId("root"));

		Advancement pacmanTrophy = registerAdvancement("pacman_trophy", FrameType.TASK, GPBlocks.PACMAN_TROPHY.get()).parent(root)
				.addCriterion("pacman_trophy", InventoryChangeTrigger.Instance.hasItems(GPBlocks.PACMAN_TROPHY.get()))
				.save(t, advancementId("pacman_trophy"));
		Advancement goldenPacmanTrophy = registerAdvancement("golden_pacman_trophy", FrameType.GOAL, GPBlocks.GOLDEN_PACMAN_TROPHY.get()).parent(pacmanTrophy)
				.addCriterion("golden_pacman_trophy", InventoryChangeTrigger.Instance.hasItems(GPBlocks.GOLDEN_PACMAN_TROPHY.get()))
				.save(t, advancementId("golden_pacman_trophy"));
		Advancement diamondPacmanTrophy = registerAdvancement("diamond_pacman_trophy", FrameType.CHALLENGE, GPBlocks.DIAMOND_PACMAN_TROPHY.get()).parent(goldenPacmanTrophy)
				.addCriterion("diamond_pacman_trophy", InventoryChangeTrigger.Instance.hasItems(GPBlocks.DIAMOND_PACMAN_TROPHY.get()))
				.save(t, advancementId("diamond_pacman_trophy"));

		Advancement levelCompleted = registerAdvancement("level_completed", FrameType.GOAL, GPItems.VULNERABLE_GHOST_ICON.get()).parent(root)
				.addCriterion("impossible", new ImpossibleTrigger.Instance())
				.save(t, advancementId("level_completed"));
		Advancement gameOver = registerAdvancement("game_over", FrameType.CHALLENGE, GPItems.FLASHING_VULNERABLE_GHOST_ICON.get()).parent(levelCompleted)
				.addCriterion("impossible", new ImpossibleTrigger.Instance())
				.save(t, advancementId("game_over"));

		Advancement phasmophobia = registerAdvancement("phasmophobia", FrameType.GOAL, GPItems.INKY.get()).parent(root)
				.addCriterion("phasmophobia_blinky", ConsumeItemTrigger.Instance.usedItem(GPItems.BLINKY.get()))
				.addCriterion("phasmophobia_pinky", ConsumeItemTrigger.Instance.usedItem(GPItems.PINKY.get()))
				.addCriterion("phasmophobia_inky", ConsumeItemTrigger.Instance.usedItem(GPItems.INKY.get()))
				.addCriterion("phasmophobia_clyde", ConsumeItemTrigger.Instance.usedItem(GPItems.CLYDE.get()))
				.addCriterion("phasmophobia_sue", ConsumeItemTrigger.Instance.usedItem(GPItems.SUE.get()))
				.addCriterion("phasmophobia_tim", ConsumeItemTrigger.Instance.usedItem(GPItems.TIM.get()))
				.addCriterion("phasmophobia_funky", ConsumeItemTrigger.Instance.usedItem(GPItems.FUNKY.get()))
				.addCriterion("phasmophobia_spunky", ConsumeItemTrigger.Instance.usedItem(GPItems.SPUNKY.get()))
				.addCriterion("phasmophobia_orson", ConsumeItemTrigger.Instance.usedItem(GPItems.ORSON.get()))
				.requirements(IRequirementsStrategy.OR)
				.save(t, advancementId("phasmophobia"));
		Advancement phasmophobiaCured = registerAdvancement("phasmophobia_cured", FrameType.CHALLENGE, GPItems.CLYDE.get()).parent(phasmophobia)
				.addCriterion("phasmophobia_blinky", ConsumeItemTrigger.Instance.usedItem(GPItems.BLINKY.get()))
				.addCriterion("phasmophobia_pinky", ConsumeItemTrigger.Instance.usedItem(GPItems.PINKY.get()))
				.addCriterion("phasmophobia_inky", ConsumeItemTrigger.Instance.usedItem(GPItems.INKY.get()))
				.addCriterion("phasmophobia_clyde", ConsumeItemTrigger.Instance.usedItem(GPItems.CLYDE.get()))
				.addCriterion("phasmophobia_sue", ConsumeItemTrigger.Instance.usedItem(GPItems.SUE.get()))
				.addCriterion("phasmophobia_tim", ConsumeItemTrigger.Instance.usedItem(GPItems.TIM.get()))
				.addCriterion("phasmophobia_funky", ConsumeItemTrigger.Instance.usedItem(GPItems.FUNKY.get()))
				.addCriterion("phasmophobia_spunky", ConsumeItemTrigger.Instance.usedItem(GPItems.SPUNKY.get()))
				.addCriterion("phasmophobia_orson", ConsumeItemTrigger.Instance.usedItem(GPItems.ORSON.get()))
				.requirements(IRequirementsStrategy.AND)
				.save(t, advancementId("phasmophobia_cured"));
	}

	private Advancement.Builder registerAdvancement(String name, FrameType type, IItemProvider... items) {
		Validate.isTrue(items.length > 0);
		return Advancement.Builder.advancement().display(items[0],
				new TranslationTextComponent("advancements.giantpacman." + name + ".title"),
				new TranslationTextComponent("advancements.giantpacman." + name + ".description"),
				BACKGROUND_TEXTURE, type, true, true, false);
	}
}
