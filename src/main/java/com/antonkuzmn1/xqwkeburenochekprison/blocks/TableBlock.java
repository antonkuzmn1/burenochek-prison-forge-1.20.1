package com.antonkuzmn1.xqwkeburenochekprison.blocks;

import com.antonkuzmn1.xqwkeburenochekprison.blockentities.TableBlockEntity;
import com.antonkuzmn1.xqwkeburenochekprison.registry.ModBlocks;
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
public class TableBlock extends Block implements EntityBlock, SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_NORTH = Shapes.or(
            Shapes.box(
                    6 / 16f, 0 / 16f, 12 / 16f,
                    11 / 16f, 1 / 16f, 16 / 16f
            ),
            Shapes.box(
                    7 / 16f, 1 / 16f, 13 / 16f,
                    10 / 16f, 10 / 16f, 16 / 16f
            ),
            Shapes.box(
                    6 / 16f, 10 / 16f, 12 / 16f,
                    11 / 16f, 14 / 16f, 16 / 16f
            ),

            Shapes.box(
                    0 / 16f, 11 / 16f, 13 / 16f,
                    6 / 16f, 14 / 16f, 14 / 16f
            ),

            Shapes.box(
                    0 / 16f, 14 / 16f, 11 / 16f,
                    12 / 16f, 16 / 16f, 16 / 16f
            )
    );

    public static final Map<Direction, VoxelShape> SHAPES = new EnumMap<>(Direction.class);

    static {
        SHAPES.put(Direction.NORTH, SHAPE_NORTH);
        SHAPES.put(Direction.EAST, rotateShape(Direction.NORTH, Direction.EAST, SHAPE_NORTH));
        SHAPES.put(Direction.SOUTH, rotateShape(Direction.NORTH, Direction.SOUTH, SHAPE_NORTH));
        SHAPES.put(Direction.WEST, rotateShape(Direction.NORTH, Direction.WEST, SHAPE_NORTH));
    }

    public TableBlock() {
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
        return new TableBlockEntity(pos, state);
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
        Direction facing = state.getValue(FACING);

        VoxelShape baseShape = TableBlock.SHAPES.get(facing);
        VoxelShape bShape = ModBlocks.TABLE_BEHIND_GHOST.get().getShapeForFacing(facing).move(
                -facing.getStepX(),
                0,
                -facing.getStepZ()
        );
        VoxelShape rShape = ModBlocks.TABLE_RIGHT_GHOST.get().getShapeForFacing(facing).move(
                -facing.getClockWise().getStepX(),
                0,
                -facing.getClockWise().getStepZ()
        );
        VoxelShape brShape = ModBlocks.TABLE_BEHIND_RIGHT_GHOST.get().getShapeForFacing(facing).move(
                -facing.getStepX() - facing.getClockWise().getStepX(),
                0,
                -facing.getStepZ() - facing.getClockWise().getStepZ()
        );

        return Shapes.or(baseShape, bShape, rShape, brShape);
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
        Level level = ctx.getLevel();
        BlockPos pos = ctx.getClickedPos();
        Direction facing = ctx.getHorizontalDirection().getOpposite();

        BlockPos bPos = pos.relative(facing.getOpposite());
        BlockPos rPos = pos.relative(facing.getClockWise());
        BlockPos brPos = bPos.relative(facing.getClockWise());

        if (!level.getBlockState(bPos).isAir()) {
            if (level.getBlockState(bPos).canBeReplaced(ctx)) {
                level.destroyBlock(bPos, true);
            } else return null;
        }

        if (!level.getBlockState(rPos).isAir()) {
            if (level.getBlockState(rPos).canBeReplaced(ctx)) {
                level.destroyBlock(rPos, true);
            } else return null;
        }

        if (!level.getBlockState(brPos).isAir()) {
            if (level.getBlockState(brPos).canBeReplaced(ctx)) {
                level.destroyBlock(brPos, true);
            } else return null;
        }

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
            Level level,
            @NotNull BlockPos pos,
            @NotNull BlockState state,
            LivingEntity placer,
            @NotNull ItemStack stack
    ) {
        if (level.isClientSide) return;

        Direction facing = state.getValue(FACING);

        BlockPos bPos = pos.relative(facing.getOpposite());
        BlockPos rPos = pos.relative(facing.getCounterClockWise());
        BlockPos brPos = bPos.relative(facing.getCounterClockWise());

        BlockState behindGhost = ModBlocks.TABLE_BEHIND_GHOST.get()
                .defaultBlockState()
                .setValue(TableBehindGhostBlock.FACING, facing)
                .setValue(TableBehindGhostBlock.PART, TableBlockPart.BEHIND);
        BlockState rightGhost = ModBlocks.TABLE_RIGHT_GHOST.get()
                .defaultBlockState()
                .setValue(TableRightGhostBlock.FACING, facing)
                .setValue(TableRightGhostBlock.PART, TableBlockPart.RIGHT);
        BlockState behindRightGhost = ModBlocks.TABLE_BEHIND_RIGHT_GHOST.get()
                .defaultBlockState()
                .setValue(TableBehindRightGhostBlock.FACING, facing)
                .setValue(TableBehindRightGhostBlock.PART, TableBlockPart.BEHIND_RIGHT);

        if (level.isEmptyBlock(bPos)) {
            level.setBlock(bPos, behindGhost, 3);
        }

        if (level.isEmptyBlock(rPos)) {
            level.setBlock(rPos, rightGhost, 3);
        }

        if (level.isEmptyBlock(brPos)) {
            level.setBlock(brPos, behindRightGhost, 3);
        }
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

        if (!level.isClientSide) {
            Direction facing = state.getValue(FACING);

            BlockPos bPos = pos.relative(facing.getOpposite());
            BlockPos rPos = pos.relative(facing.getCounterClockWise());
            BlockPos brPos = bPos.relative(facing.getCounterClockWise());

            level.removeBlock(bPos, false);
            level.removeBlock(rPos, false);
            level.removeBlock(brPos, false);
        }

        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED)
                ? Fluids.WATER.getSource(false)
                : super.getFluidState(state);
    }

    @SuppressWarnings("SameParameterValue")
    public static VoxelShape rotateShape(Direction from, Direction to, VoxelShape shape) {
        VoxelShape[] buffer = new VoxelShape[]{shape, Shapes.empty()};

        int times = (to.get2DDataValue() - from.get2DDataValue() + 4) % 4;
        for (int i = 0; i < times; i++) {
            buffer[1] = Shapes.empty();
            buffer[0].forAllBoxes((
                    minX, minY, minZ,
                    maxX, maxY, maxZ
            ) -> buffer[1] = Shapes.or(
                    buffer[1],
                    Shapes.box(
                            1 - maxZ, minY, minX,
                            1 - minZ, maxY, maxX
                    )
            ));
            buffer[0] = buffer[1];
        }
        return buffer[0];
    }

}
