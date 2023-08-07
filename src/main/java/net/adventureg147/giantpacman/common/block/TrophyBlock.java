/*
 * Copyright (c) AdventureG147 and contributors
 * All Rights Reserved
 */

package net.adventureg147.giantpacman.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class TrophyBlock extends HorizontalBlock {
	protected static final VoxelShape BASE_1 = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
	protected static final VoxelShape BASE_2 = Block.box(1.0D, 2.0D, 1.0D, 15.0D, 3.0D, 15.0D);
	protected static final VoxelShape BASE = VoxelShapes.or(BASE_1, BASE_2);
	protected static final VoxelShape STAND = Block.box(6.0D, 3.0D, 6.0D, 10.0D, 5.0D, 10.0D);
	protected static final VoxelShape PACMAN = Block.box(1.0D, 5.0D, 1.0D, 15.0D, 20.0D, 15.0D);
	protected static final VoxelShape SHAPE = VoxelShapes.or(BASE, STAND, PACMAN);

	public TrophyBlock(Properties builder) {
		super(builder);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(HORIZONTAL_FACING);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.defaultBlockState().setValue(HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
	}

	@Override
	public VoxelShape getShape(BlockState pState, IBlockReader pLevel, BlockPos pPos, ISelectionContext pContext) {
		return SHAPE;
	}

	@Override
	public VoxelShape getCollisionShape(BlockState pState, IBlockReader pLevel, BlockPos pPos, ISelectionContext pContext) {
		return SHAPE;
	}

	@Override
	public VoxelShape getOcclusionShape(BlockState pState, IBlockReader pLevel, BlockPos pPos) {
		return SHAPE;
	}

	public boolean useShapeForLightOcclusion(BlockState pState) {
		return true;
	}

	public boolean propagatesSkylightDown(BlockState pState, IBlockReader pReader, BlockPos pPos) {
		return true;
	}
}
