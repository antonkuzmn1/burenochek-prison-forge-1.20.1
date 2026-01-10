package com.antonkuzmn1.xqwkeburenochekprison.blocks;

import com.antonkuzmn1.xqwkeburenochekprison.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
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

    protected final Map<Direction, VoxelShape> shapes = new EnumMap<>(Direction.class);

    public TableGhostBlock(TableBlockPart part, VoxelShape shapeNorth) {
        super(Properties.of()
                        .strength(2.0f, 6.0f)
//                .requiresCorrectToolForDrops()
                        .noOcclusion()
        );

        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(PART, part)
                .setValue(WATERLOGGED, false)
        );

        shapes.put(Direction.NORTH, shapeNorth);
        shapes.put(Direction.EAST, ParashaBlock.rotateShape(Direction.NORTH, Direction.EAST, shapeNorth));
        shapes.put(Direction.SOUTH, ParashaBlock.rotateShape(Direction.NORTH, Direction.SOUTH, shapeNorth));
        shapes.put(Direction.WEST, ParashaBlock.rotateShape(Direction.NORTH, Direction.WEST, shapeNorth));
    }

    @Override
    public @NotNull VoxelShape getCollisionShape(
            @NotNull BlockState state,
            @NotNull BlockGetter world,
            @NotNull BlockPos pos,
            @NotNull CollisionContext context
    ) {
        return this.shapes.get(state.getValue(FACING));
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

        VoxelShape baseShape = TableBlock.SHAPES.get(facing);
        VoxelShape behindShape = ModBlocks.TABLE_BEHIND_GHOST.get().getShapeForFacing(facing);
        VoxelShape rightShape = ModBlocks.TABLE_RIGHT_GHOST.get().getShapeForFacing(facing);
        VoxelShape behindRightShape = ModBlocks.TABLE_BEHIND_RIGHT_GHOST.get().getShapeForFacing(facing);

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
        Direction forward = facing.getOpposite();
        Direction left = facing.getCounterClockWise();

        TableBlockPart part = state.getValue(PART);
        BlockPos basePos = switch (part) {
            case BEHIND -> pos.relative(forward);
            case RIGHT -> pos.relative(left);
            case BEHIND_RIGHT -> pos.relative(forward).relative(left);
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
    public @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED)
                ? Fluids.WATER.getSource(false)
                : super.getFluidState(state);
    }

    public VoxelShape getShapeForFacing(Direction facing) {
        return shapes.get(facing);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, PART, WATERLOGGED);
    }
}
