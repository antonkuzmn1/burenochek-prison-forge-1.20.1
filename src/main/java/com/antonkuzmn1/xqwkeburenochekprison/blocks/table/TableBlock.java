package com.antonkuzmn1.xqwkeburenochekprison.blocks.table;

import com.antonkuzmn1.xqwkeburenochekprison.blockentities.TableBlockEntity;
import com.antonkuzmn1.xqwkeburenochekprison.registry.ModBlocks;
import com.antonkuzmn1.xqwkeburenochekprison.utils.VoxelShapeUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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
import net.minecraft.world.phys.BlockHitResult;
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

    public static final BooleanProperty CHAIR = BooleanProperty.create("chair");

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

    private static final VoxelShape SHAPE_NORTH_WITH_CHAIR = Shapes.or(
            SHAPE_NORTH,
            Shapes.box(
                    1 / 16f, 0 / 16f, 0 / 16f,
                    5 / 16f, 1 / 16f, 4 / 16f
            ),
            Shapes.box(
                    2 / 16f, 1 / 16f, 1 / 16f,
                    4 / 16f, 8 / 16f, 3 / 16f
            ),

            Shapes.box(
                    1 / 16f, 0 / 16f, 7 / 16f,
                    5 / 16f, 1 / 16f, 11 / 16f
            ),
            Shapes.box(
                    2 / 16f, 1 / 16f, 8 / 16f,
                    4 / 16f, 8 / 16f, 10 / 16f
            ),

            Shapes.box(
                    3 / 16f, 5 / 16f, 3 / 16f,
                    4 / 16f, 6 / 16f, 8 / 16f
            ),
            Shapes.box(
                    0 / 16f, 5 / 16f, 9 / 16f,
                    2 / 16f, 6 / 16f, 10 / 16f
            ),
            Shapes.box(
                    0 / 16f, 5 / 16f, 1 / 16f,
                    2 / 16f, 6 / 16f, 2 / 16f
            ),

            Shapes.box(
                    0 / 16f, 8 / 16f, 1 / 16f,
                    4 / 16f, 9 / 16f, 10 / 16f
            ),
            Shapes.box(
                    0 / 16f, 9 / 16f, 0 / 16f,
                    5 / 16f, 10 / 16f, 11 / 16f
            )
    );

    public static final Map<Direction, VoxelShape> SHAPES = new EnumMap<>(Direction.class);
    public static final Map<Direction, VoxelShape> SHAPES_WITH_CHAIR = new EnumMap<>(Direction.class);

    static {
        SHAPES.put(Direction.NORTH, SHAPE_NORTH);
        SHAPES.put(Direction.EAST, VoxelShapeUtils.rotate(Direction.NORTH, Direction.EAST, SHAPE_NORTH));
        SHAPES.put(Direction.SOUTH, VoxelShapeUtils.rotate(Direction.NORTH, Direction.SOUTH, SHAPE_NORTH));
        SHAPES.put(Direction.WEST, VoxelShapeUtils.rotate(Direction.NORTH, Direction.WEST, SHAPE_NORTH));

        SHAPES_WITH_CHAIR.put(Direction.NORTH, SHAPE_NORTH_WITH_CHAIR);
        SHAPES_WITH_CHAIR.put(
                Direction.EAST,
                VoxelShapeUtils.rotate(Direction.NORTH, Direction.EAST, SHAPE_NORTH_WITH_CHAIR)
        );
        SHAPES_WITH_CHAIR.put(
                Direction.SOUTH,
                VoxelShapeUtils.rotate(Direction.NORTH, Direction.SOUTH, SHAPE_NORTH_WITH_CHAIR)
        );
        SHAPES_WITH_CHAIR.put(
                Direction.WEST,
                VoxelShapeUtils.rotate(Direction.NORTH, Direction.WEST, SHAPE_NORTH_WITH_CHAIR)
        );
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
                        .setValue(CHAIR, false)
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
        boolean chair = state.getValue(CHAIR);

        VoxelShape baseShape = getShapes(state).get(facing);
        VoxelShape bShape = ModBlocks.TABLE_BEHIND_GHOST.get().getShapeForFacing(facing, chair).move(
                -facing.getStepX(),
                0,
                -facing.getStepZ()
        );
        VoxelShape rShape = ModBlocks.TABLE_RIGHT_GHOST.get().getShapeForFacing(facing, chair).move(
                -facing.getClockWise().getStepX(),
                0,
                -facing.getClockWise().getStepZ()
        );
        VoxelShape brShape = ModBlocks.TABLE_BEHIND_RIGHT_GHOST.get().getShapeForFacing(facing, chair).move(
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
        return getShapes(state).get(state.getValue(FACING));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED, CHAIR);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        Level level = ctx.getLevel();
        BlockPos pos = ctx.getClickedPos();
        Direction facing = ctx.getHorizontalDirection().getOpposite();

        BlockPos bPos = pos.relative(facing.getOpposite());
        BlockPos rPos = pos.relative(facing.getCounterClockWise());
        BlockPos brPos = bPos.relative(facing.getCounterClockWise());

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
                .setValue(TableBehindGhostBlock.CHAIR, false)
                .setValue(TableBehindGhostBlock.PART, TableBlockPart.BEHIND);
        BlockState rightGhost = ModBlocks.TABLE_RIGHT_GHOST.get()
                .defaultBlockState()
                .setValue(TableRightGhostBlock.FACING, facing)
                .setValue(TableBehindGhostBlock.CHAIR, false)
                .setValue(TableRightGhostBlock.PART, TableBlockPart.RIGHT);
        BlockState behindRightGhost = ModBlocks.TABLE_BEHIND_RIGHT_GHOST.get()
                .defaultBlockState()
                .setValue(TableBehindRightGhostBlock.FACING, facing)
                .setValue(TableBehindGhostBlock.CHAIR, false)
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
    public @NotNull InteractionResult use(
            @NotNull BlockState state,
            @NotNull Level level,
            @NotNull BlockPos pos,
            @NotNull Player player,
            @NotNull InteractionHand hand,
            @NotNull BlockHitResult hit
    ) {
        ItemStack stack = player.getItemInHand(hand);

        if (stack.is(ModBlocks.CHAIR.get().asItem()) && !state.getValue(CHAIR)) {
            if (!level.isClientSide) {
                setChair(level, pos, !state.getValue(CHAIR));
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
            }
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @Override
    public @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED)
                ? Fluids.WATER.getSource(false)
                : super.getFluidState(state);
    }

    public void setChair(
            @NotNull Level level,
            @NotNull BlockPos pos,
            boolean value
    ) {
        if (level.isClientSide) return;

        BlockState state = level.getBlockState(pos);

        if (state.getBlock() instanceof TableBlock) {
            level.setBlock(pos, state.setValue(CHAIR, value), Block.UPDATE_ALL);
        }

        Direction facing = state.getValue(FACING);
        BlockPos bPos = pos.relative(facing.getOpposite());
        BlockPos rPos = pos.relative(facing.getCounterClockWise());
        BlockPos brPos = bPos.relative(facing.getCounterClockWise());

        for (BlockPos ghostPos : new BlockPos[]{bPos, rPos, brPos}) {
            BlockState ghostState = level.getBlockState(ghostPos);
            if (ghostState.getBlock() instanceof TableGhostBlock) {
                level.setBlock(
                        ghostPos,
                        ghostState.setValue(TableGhostBlock.CHAIR, value),
                        Block.UPDATE_ALL
                );
            }
        }
    }

    private Map<Direction, VoxelShape> getShapes(@NotNull BlockState state) {
        return state.getValue(CHAIR) ? SHAPES_WITH_CHAIR : SHAPES;
    }
}
