package net.adventureg147.giantpacman.data;

import net.adventureg147.giantpacman.GiantPacman;
import net.adventureg147.giantpacman.common.registry.GPItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nonnull;
import java.util.Collection;
/*
 * Copyright (c) AdventureG147 and contributors
 * All Rights Reserved
 */
public class GPItemModelProvider extends ItemModelProvider {
	public GPItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, GiantPacman.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		generate(GPItems.ITEMS.getEntries());
	}

	@Nonnull
	@Override
	public String getName() {
		return GiantPacman.MODNAME + " Item models";
	}

	private void generate(final Collection<RegistryObject<Item>> items) {
		final ModelFile parentGenerated = getExistingFile(mcLoc("item/generated"));
		final ModelFile.ExistingModelFile parentHandheld = getExistingFile(mcLoc("item/handheld"));

		for (RegistryObject<Item> item : items) {
			String name = item.getId().getPath();

			GiantPacman.LOGGER.debug(item.getId());

			if (!existingFileHelper.exists(getItemResourceLocation(name), TEXTURE) || existingFileHelper.exists(getItemResourceLocation(name), MODEL)) continue;
			getBuilder(item.getId().getPath()).parent(item.get().getMaxDamage(ItemStack.EMPTY) > 0 && !(item.get() instanceof ArmorItem) ? parentHandheld : parentGenerated).texture("layer0", ItemModelProvider.ITEM_FOLDER + "/" + name);
		}
	}

	private static ResourceLocation getResourceLocation(String path) {
		return new ResourceLocation(GiantPacman.MODID, path);
	}

	private static ResourceLocation getItemResourceLocation(String name) {
		return getResourceLocation("item/" + name);
	}
}
