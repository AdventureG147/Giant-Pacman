package net.adventureg147.giantpacman.common.item;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectUtils;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.*;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

public class PacmanGhostItem extends Item {
	public final EffectInstance effect;

	public PacmanGhostItem(Properties properties, EffectInstance effect) {
		super(properties);
		this.effect = effect;
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (player.isCrouching()) {
			if (!world.isClientSide) {
				player.addEffect(new EffectInstance(effect.getEffect(), effect.getDuration(), effect.getAmplifier()));
			}
			if (!player.isCreative()) {
				stack.shrink(1);
			}
			return ActionResult.success(stack);
		}
		return super.use(world, player, hand);
	}

	@Override
	public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		super.appendHoverText(stack, world, tooltip, flag);

		IFormattableTextComponent potion = this.effect.getEffect().getDisplayName().copy();
		List<Pair<Attribute, AttributeModifier>> list1 = Lists.newArrayList();
		Effect effect = this.effect.getEffect();
		Map<Attribute, AttributeModifier> map = effect.getAttributeModifiers();
		if (!map.isEmpty()) {
			for(Map.Entry<Attribute, AttributeModifier> entry : map.entrySet()) {
				AttributeModifier attributemodifier = entry.getValue();
				AttributeModifier attributemodifier1 = new AttributeModifier(attributemodifier.getName(), effect.getAttributeModifierValue(this.effect.getAmplifier(), attributemodifier), attributemodifier.getOperation());
				list1.add(new Pair<>(entry.getKey(), attributemodifier1));
			}
		}

		tooltip.add(new TranslationTextComponent("item.giantpacman.ghost.tooltip", new KeybindTextComponent("key.sneak"), new KeybindTextComponent("key.use")).withStyle(TextFormatting.GRAY));

		if (this.effect.getAmplifier() > 0) {
			potion = new TranslationTextComponent("potion.withAmplifier", potion, new TranslationTextComponent("potion.potency." + this.effect.getAmplifier()));
		}

		if (this.effect.getDuration() > 20) {
			potion = new TranslationTextComponent("potion.withDuration", potion, EffectUtils.formatDuration(this.effect, 1.0F));
		}

		tooltip.add(potion.withStyle(effect.getCategory().getTooltipFormatting()));

		if (!list1.isEmpty()) {
			tooltip.add(StringTextComponent.EMPTY);
			tooltip.add((new TranslationTextComponent("potion.whenDrank")).withStyle(TextFormatting.DARK_PURPLE));

			for(Pair<Attribute, AttributeModifier> pair : list1) {
				AttributeModifier attributemodifier2 = pair.getSecond();
				double d0 = attributemodifier2.getAmount();
				double d1;
				if (attributemodifier2.getOperation() != AttributeModifier.Operation.MULTIPLY_BASE && attributemodifier2.getOperation() != AttributeModifier.Operation.MULTIPLY_TOTAL) {
					d1 = attributemodifier2.getAmount();
				} else {
					d1 = attributemodifier2.getAmount() * 100.0D;
				}

				if (d0 > 0.0D) {
					tooltip.add((new TranslationTextComponent("attribute.modifier.plus." + attributemodifier2.getOperation().toValue(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(d1), new TranslationTextComponent(pair.getFirst().getDescriptionId()))).withStyle(TextFormatting.BLUE));
				} else if (d0 < 0.0D) {
					d1 = d1 * -1.0D;
					tooltip.add((new TranslationTextComponent("attribute.modifier.take." + attributemodifier2.getOperation().toValue(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(d1), new TranslationTextComponent(pair.getFirst().getDescriptionId()))).withStyle(TextFormatting.RED));
				}
			}
		}
	}
}
