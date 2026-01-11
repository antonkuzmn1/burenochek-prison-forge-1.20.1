package com.antonkuzmn1.xqwkeburenochekprison.blocks.table;

import com.antonkuzmn1.xqwkeburenochekprison.registry.ModBlocks;
import com.antonkuzmn1.xqwkeburenochekprison.utils.VoxelShapeUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;

@SuppressWarnings("deprecation")
public class TableGhostBlock extends Block implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<TableBlockPart> PART = EnumProperty.create("part", TableBlockPart.class);

    public static final BooleanProperty CHAIR = BooleanProperty.create("chair");

    protected final Map<Direction, VoxelShape> shapes = new EnumMap<>(Direction.class);
    protected final Map<Direction, VoxelShape> shapesWithChair = new EnumMap<>(Direction.class);

    public TableGhostBlock(TableBlockPart part, VoxelShape shapeNorth, VoxelShape shapesNorthWithChair) {
        super(Properties.of()
                .strength(2.0f, 6.0f)
                .noOcclusion()
        );

        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(PART, part)
                .setValue(WATERLOGGED, false)
                .setValue(CHAIR, false)
        );

        VoxelShapeUtils.putAllDirections(shapes, shapeNorth);
        VoxelShapeUtils.putAllDirections(shapesWithChair, shapesNorthWithChair);
    }

    public TableGhostBlock(TableBlockPart part, VoxelShape shapeNorth) {
        this(part, shapeNorth, shapeNorth);
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
    public @NotNull VoxelShape getShape(
            @NotNull BlockState state,
            @NotNull BlockGetter world,
            @NotNull BlockPos pos,
            @NotNull CollisionContext context
    ) {
        Direction facing = state.getValue(FACING);
        int facingX = facing.getStepX();
        int facingZ = facing.getStepZ();
        Direction facingCW = facing.getClockWise();
        int facingCWX = facingCW.getStepX();
        int facingCWZ = facingCW.getStepZ();
        TableBlockPart part = state.getValue(PART);

        boolean chair = state.getValue(CHAIR);

        Map<Direction, VoxelShape> baseShapes = chair ? TableBlock.SHAPES_WITH_CHAIR : TableBlock.SHAPES;

        VoxelShape baseShape = baseShapes.get(facing);
        VoxelShape behindShape = ModBlocks.TABLE_BEHIND_GHOST.get().getShapeForFacing(facing, chair);
        VoxelShape rightShape = ModBlocks.TABLE_RIGHT_GHOST.get().getShapeForFacing(facing, chair);
        VoxelShape behindRightShape = ModBlocks.TABLE_BEHIND_RIGHT_GHOST.get().getShapeForFacing(facing, chair);

        if (part == TableBlockPart.BEHIND) {
            baseShape = baseShape.move(facingX, 0, facingZ);
            rightShape = rightShape.move(facingX - facingCWX, 0, facingZ - facingCWZ);
            behindRightShape = behindRightShape.move(-facingCWX, 0, -facingCWZ);
        } else if (part == TableBlockPart.BEHIND_RIGHT) {
            baseShape = baseShape.move(facingX + facingCWX, 0, facingZ + facingCWZ);
            behindShape = behindShape.move(facingCWX, 0, facingCWZ);
            rightShape = rightShape.move(facingX, 0, facingZ);
        } else {
            baseShape = baseShape.move(facingCWX, 0, facingCWZ);
            behindShape = behindShape.move(-facingX + facingCWX, 0, -facingZ + facingCWZ);
            behindRightShape = behindRightShape.move(-facingX, 0, -facingZ);
        }

        return Shapes.or(baseShape, behindShape, rightShape, behindRightShape);
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public boolean skipRendering(
            @NotNull BlockState state,
            @NotNull BlockState adjacent,
            @NotNull Direction dir
    ) {
        return true;
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
        if (level.isClientSide) return;

        Direction facing = state.getValue(FACING);
        Direction left = facing.getClockWise();

        TableBlockPart part = state.getValue(PART);
        BlockPos basePos = switch (part) {
            case BEHIND -> pos.relative(facing);
            case RIGHT -> pos.relative(left);
            case BEHIND_RIGHT -> pos.relative(facing).relative(left);
        };

        level.destroyBlock(basePos, true);
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public void spawnDestroyParticles(
            @NotNull Level level,
            @NotNull Player player,
            @NotNull BlockPos pos,
            @NotNull BlockState state
    ) {
        // do nothing
    }

    @Override
    public void stepOn(
            @NotNull Level level,
            @NotNull BlockPos pos,
            @NotNull BlockState state,
            @NotNull Entity entity
    ) {
        // do nothing
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
                Direction facing = state.getValue(FACING);
                Direction left = facing.getClockWise();

                TableBlockPart part = state.getValue(PART);
                BlockPos basePos = switch (part) {
                    case BEHIND -> pos.relative(facing);
                    case RIGHT -> pos.relative(left);
                    case BEHIND_RIGHT -> pos.relative(facing).relative(left);
                };

                BlockState mainState = level.getBlockState(basePos);
                if (mainState.getBlock() instanceof TableBlock tableBlock) {
                    return tableBlock.use(mainState, level, basePos, player, hand, hit);
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

    public VoxelShape getShapeForFacing(Direction facing, boolean chair) {
        return (chair ? shapesWithChair : shapes).get(facing);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, PART, WATERLOGGED, CHAIR);
    }

    private Map<Direction, VoxelShape> getShapes(@NotNull BlockState state) {
        return state.getValue(CHAIR) ? shapesWithChair : shapes;
    }
}
