/*
 * Copyright (c) AdventureG147 and contributors
 * All Rights Reserved
 */

package net.adventureg147.giantpacman.client;

import net.adventureg147.giantpacman.GiantPacman;
import net.minecraft.client.resources.ClientLanguageMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import java.util.Objects;

public class TooltipEventSubscriber {
	@SuppressWarnings("deprecation")
	public static void onToolTipEvent(ItemTooltipEvent event) {
		if (event.getPlayer() == null) return;
		final ItemStack stack = event.getItemStack();
		final Item item = stack.getItem();
		ResourceLocation registryName = Objects.requireNonNull(item.getRegistryName());

		if (registryName.getNamespace().equals(GiantPacman.MODID)) {
			if (ClientLanguageMap.getInstance().has("tooltip." + GiantPacman.MODID + "." + registryName.getPath())) {
				event.getToolTip().add(new TranslationTextComponent("tooltip." + GiantPacman.MODID + "." + registryName.getPath()).withStyle(TextFormatting.GRAY));
			}
			for (int n = 2; n <= 15; n++) {
				if (ClientLanguageMap.getInstance().has("tooltip." + GiantPacman.MODID + "." + registryName.getPath() + "." + n)) {
					event.getToolTip().add(new TranslationTextComponent("tooltip." + GiantPacman.MODID + "." + registryName.getPath() + "." + n).withStyle(TextFormatting.GRAY));
				}
			}
		}
	}
}
