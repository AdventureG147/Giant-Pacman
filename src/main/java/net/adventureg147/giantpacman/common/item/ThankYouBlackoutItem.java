/*
 * Copyright (c) AdventureG147 and contributors
 * All Rights Reserved
 */

package net.adventureg147.giantpacman.common.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ThankYouBlackoutItem extends Item {
	public ThankYouBlackoutItem(Properties properties) {
		super(properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		super.appendHoverText(stack, world, tooltip, flag);

		tooltip.add(new StringTextComponent("This is a gift for Blackout!").withStyle(TextFormatting.GRAY));
		tooltip.add(new StringTextComponent("For helping me throughout the making of this mod.").withStyle(TextFormatting.GRAY));
		tooltip.add(new StringTextComponent(""));
		tooltip.add(new StringTextComponent("Thank you so much Blackout!").withStyle(TextFormatting.GRAY));
	}
}
