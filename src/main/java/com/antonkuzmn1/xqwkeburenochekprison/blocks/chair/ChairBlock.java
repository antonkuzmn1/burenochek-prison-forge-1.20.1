package com.antonkuzmn1.xqwkeburenochekprison.blocks.chair;

import com.antonkuzmn1.xqwkeburenochekprison.blockentities.ChairBlockEntity;
import com.antonkuzmn1.xqwkeburenochekprison.utils.VoxelShapeUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.EnumMap;
import java.util.Map;

@SuppressWarnings("deprecation")
public class ChairBlock extends Block implements EntityBlock, SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_NORTH = Shapes.or(
            Shapes.box(
                    3 / 16f, 0 / 16f, 2 / 16f,
                    7 / 16f, 1 / 16f, 6 / 16f
            ),
            Shapes.box(
                    4 / 16f, 1 / 16f, 3 / 16f,
                    6 / 16f, 8 / 16f, 5 / 16f
            ),

            Shapes.box(
                    10 / 16f, 0 / 16f, 2 / 16f,
                    14 / 16f, 1 / 16f, 6 / 16f
            ),
            Shapes.box(
                    11 / 16f, 1 / 16f, 3 / 16f,
                    13 / 16f, 8 / 16f, 5 / 16f
            ),

            Shapes.box(
                    3 / 16f, 0 / 16f, 9 / 16f,
                    7 / 16f, 1 / 16f, 13 / 16f
            ),
            Shapes.box(
                    4 / 16f, 1 / 16f, 10 / 16f,
                    6 / 16f, 8 / 16f, 12 / 16f
            ),

            Shapes.box(
                    10 / 16f, 0 / 16f, 9 / 16f,
                    14 / 16f, 1 / 16f, 13 / 16f
            ),
            Shapes.box(
                    11 / 16f, 1 / 16f, 10 / 16f,
                    13 / 16f, 8 / 16f, 12 / 16f
            ),

            Shapes.box(
                    6 / 16f, 5 / 16f, 3 / 16f,
                    11 / 16f, 6 / 16f, 4 / 16f
            ),
            Shapes.box(
                    6 / 16f, 5 / 16f, 11 / 16f,
                    11 / 16f, 6 / 16f, 12 / 16f
            ),
            Shapes.box(
                    4 / 16f, 5 / 16f, 5 / 16f,
                    5 / 16f, 6 / 16f, 10 / 16f
            ),
            Shapes.box(
                    12 / 16f, 5 / 16f, 5 / 16f,
                    13 / 16f, 6 / 16f, 10 / 16f
            ),

            Shapes.box(
                    4 / 16f, 8 / 16f, 3 / 16f,
                    13 / 16f, 9 / 16f, 12 / 16f
            ),
            Shapes.box(
                    3 / 16f, 9 / 16f, 2 / 16f,
                    14 / 16f, 10 / 16f, 13 / 16f
            )
    );

    public static final Map<Direction, VoxelShape> SHAPES = new EnumMap<>(Direction.class);

    static {
        VoxelShapeUtils.putAllDirections(SHAPES, SHAPE_NORTH);
    }

    public ChairBlock() {
        super(Properties.of()
                        .strength(2.0f, 6.0f)
                        .noOcclusion()
        );
        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(FACING, Direction.NORTH)
                        .setValue(WATERLOGGED, false)
        );
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new ChairBlockEntity(pos, state);
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public @NotNull VoxelShape getShape(
            @NotNull BlockState state,
            @NotNull BlockGetter world,
            @NotNull BlockPos pos,
            @NotNull CollisionContext context
    ) {
        return SHAPES.get(state.getValue(FACING));
    }

    @Override
    public @NotNull VoxelShape getCollisionShape(
            @NotNull BlockState state,
            @NotNull BlockGetter world,
            @NotNull BlockPos pos,
            @NotNull CollisionContext context
    ) {
        return SHAPES.get(state.getValue(FACING));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        Direction facing = ctx.getHorizontalDirection().getOpposite();
        return this.defaultBlockState().setValue(FACING, facing);
    }

    @Override
    public @NotNull BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public @NotNull BlockState mirror(@NotNull BlockState state, Mirror mirror) {
        return rotate(state, mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public void setPlacedBy(
            @NotNull Level level,
            @NotNull BlockPos pos,
            @NotNull BlockState state,
            LivingEntity placer,
            @NotNull ItemStack stack
    ) {
        // pass
    }

    @Override
    public void onRemove(
            BlockState state,
            @NotNull Level level,
            @NotNull BlockPos pos,
            BlockState newState,
            boolean isMoving
    ) {
        if (state.getBlock() == newState.getBlock()) return;
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED)
                ? Fluids.WATER.getSource(false)
                : super.getFluidState(state);
    }
}
