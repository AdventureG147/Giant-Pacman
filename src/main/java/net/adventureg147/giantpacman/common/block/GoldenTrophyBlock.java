package net.adventureg147.giantpacman.common.block;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GoldenTrophyBlock extends TrophyBlock {
	public GoldenTrophyBlock(Properties builder) {
		super(builder);
	}

	@OnlyIn(Dist.CLIENT)
	public float getShadeBrightness(BlockState pState, IBlockReader pLevel, BlockPos pPos) {
		return 1.0F;
	}
}
