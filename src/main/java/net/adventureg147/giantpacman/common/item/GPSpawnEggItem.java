package net.adventureg147.giantpacman.common.item;

import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class GPSpawnEggItem extends SpawnEggItem {
	protected final Supplier<? extends EntityType<?>> typeGetter;

	@SuppressWarnings({"deprecation", "DataFlowIssue"})
	public GPSpawnEggItem(Supplier<? extends EntityType<?>> typeIn, Properties properties) {
		super(null, 0xFFFFFFF, 0xFFFFFFF, properties);
		this.typeGetter = typeIn;
	}

	@Override
	public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
		SpawnEggItem.BY_ID.put(this.getType(null), this);
		super.fillItemCategory(group, items);
	}

	@Override
	public EntityType<?> getType(@Nullable CompoundNBT p_208076_1_) {
		return typeGetter.get();
	}
}
