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
public class ParashaGhostBlock extends Block implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<ParashaBlockPart> PART = EnumProperty.create("part", ParashaBlockPart.class);

    protected final Map<Direction, VoxelShape> shapes = new EnumMap<>(Direction.class);

    public ParashaGhostBlock(ParashaBlockPart part, VoxelShape shapeNorth) {
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
        ParashaBlockPart part = state.getValue(PART);

        VoxelShape baseShape = ParashaBlock.SHAPES.get(facing);
        VoxelShape rimShape = ModBlocks.PARASHA_RIM_GHOST.get().getShapeForFacing(facing);
        VoxelShape cisternShape = ModBlocks.PARASHA_CISTERN_GHOST.get().getShapeForFacing(facing);

        if (part == ParashaBlockPart.TOP) {
            baseShape = baseShape.move(0, -1, 0);
            rimShape = rimShape.move(facing.getStepX(), -1, facing.getStepZ());
        } else {
            baseShape = baseShape.move(-facing.getStepX(), 0, -facing.getStepZ());
            cisternShape = cisternShape.move(-facing.getStepX(), 1, -facing.getStepZ());
        }

        return Shapes.or(baseShape, rimShape, cisternShape);
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
        ParashaBlockPart part = state.getValue(PART);

        BlockPos basePos = switch (part) {
            case FRONT -> pos.relative(facing.getOpposite());
            case TOP -> pos.below();
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
