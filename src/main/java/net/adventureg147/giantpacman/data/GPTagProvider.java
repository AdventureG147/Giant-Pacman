package net.adventureg147.giantpacman.data;

import net.adventureg147.giantpacman.GiantPacman;
import net.adventureg147.giantpacman.common.registry.GPBlocks;
import net.adventureg147.giantpacman.common.registry.GPTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.nio.file.Path;

public class GPTagProvider extends BlockTagsProvider {
	public GPTagProvider(DataGenerator gen, ExistingFileHelper existingFileHelper) {
		super(gen, GiantPacman.MODID, existingFileHelper);
	}

	public static class GPBlockTagProvider extends BlockTagsProvider {
		public GPBlockTagProvider(DataGenerator gen, ExistingFileHelper existingFileHelper) {
			super(gen, GiantPacman.MODID, existingFileHelper);
		}

		protected Path getPath(ResourceLocation resourceLocation) {
			return this.generator.getOutputFolder().resolve("data/" + resourceLocation.getNamespace() + "/tags/blocks/" + resourceLocation.getPath() + ".json");
		}

		@Override
		public String getName() {
			return GiantPacman.MODNAME + ": Block Tags";
		}

		@Override
		protected void addTags() {

		}
	}

	public static class GPItemTagProvider extends ItemTagsProvider {
		public GPItemTagProvider(DataGenerator gen, ExistingFileHelper existingFileHelper) {
			super(gen, new GPBlockTagProvider(gen, existingFileHelper), GiantPacman.MODID, existingFileHelper);
		}

		protected Path getPath(ResourceLocation resourceLocation) {
			return this.generator.getOutputFolder().resolve("data/" + resourceLocation.getNamespace() + "/tags/items/" + resourceLocation.getPath() + ".json");
		}

		@Override
		public String getName() {
			return GiantPacman.MODNAME + ": Item Tags";
		}

		@Override
		protected void addTags() {
			this.tag(GPTags.Items.UNDROPPABLE).add(GPBlocks.PACMAN_TROPHY.get().asItem(), GPBlocks.GOLDEN_PACMAN_TROPHY.get().asItem(),
					Blocks.BEDROCK.asItem(), Blocks.END_PORTAL_FRAME.asItem(), Blocks.BARRIER.asItem(), Blocks.COMMAND_BLOCK.asItem(), Blocks.CHAIN_COMMAND_BLOCK.asItem(), Blocks.REPEATING_COMMAND_BLOCK.asItem(), Blocks.STRUCTURE_BLOCK.asItem(), Blocks.STRUCTURE_VOID.asItem(), Blocks.JIGSAW.asItem(), Blocks.SPAWNER.asItem(),
					Items.DEBUG_STICK, Items.COMMAND_BLOCK_MINECART, Items.WRITTEN_BOOK, Items.ENCHANTED_BOOK);
		}
	}
}
