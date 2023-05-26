package net.adventureg147.giantpacman.common.item;


import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class ThankYouBlackout extends Item {
	public ThankYouBlackout(Properties properties) {
		super(properties);
	}

	@SuppressWarnings("NullableProblems")
	@Override
	public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		super.appendHoverText(stack, world, tooltip, flag);

		tooltip.add(new StringTextComponent("This is a gift for Blackout!"));
		tooltip.add(new StringTextComponent("For helping me throughout the making of this mod."));
		tooltip.add(new StringTextComponent(""));
		tooltip.add(new StringTextComponent("Thank you so much Blackout!"));
	}
}
