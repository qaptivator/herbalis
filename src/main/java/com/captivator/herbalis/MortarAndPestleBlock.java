package com.captivator.herbalis;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MortarAndPestleBlock extends Block {
    protected static final VoxelShape MORTAR = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 5.0D, 13.0D);
    protected static final VoxelShape PESTLE = Block.box(4.5D, 4.0D, 9.5D, 6.5D, 8.0D, 11.5D);
    protected static final VoxelShape SHAPE = Shapes.or(MORTAR, PESTLE);

    public MortarAndPestleBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return MORTAR;
    }
}
