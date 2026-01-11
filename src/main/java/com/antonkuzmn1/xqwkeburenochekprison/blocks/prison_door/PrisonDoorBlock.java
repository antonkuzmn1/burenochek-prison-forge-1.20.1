package com.antonkuzmn1.xqwkeburenochekprison.blocks.prison_door;

import com.antonkuzmn1.xqwkeburenochekprison.blockentities.PrisonDoorBlockEntity;
import com.antonkuzmn1.xqwkeburenochekprison.registry.ModBlocks;
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
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.EnumMap;
import java.util.Map;

@SuppressWarnings("deprecation")
public class PrisonDoorBlock extends Block implements EntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_NORTH = Shapes.or(
            Shapes.box(
                    0 / 16f, 0 / 16f, 0 / 16f,
                    16 / 16f, 16 / 16f, 6 / 16f
            )
    );

    public static final Map<Direction, VoxelShape> SHAPES = new EnumMap<>(Direction.class);

    static {
        SHAPES.put(Direction.NORTH, SHAPE_NORTH);
        SHAPES.put(Direction.EAST, VoxelShapeUtils.rotate(Direction.NORTH, Direction.EAST, SHAPE_NORTH));
        SHAPES.put(Direction.SOUTH, VoxelShapeUtils.rotate(Direction.NORTH, Direction.SOUTH, SHAPE_NORTH));
        SHAPES.put(Direction.WEST, VoxelShapeUtils.rotate(Direction.NORTH, Direction.WEST, SHAPE_NORTH));
    }

    public PrisonDoorBlock() {
        super(Properties.of()
                .strength(2.0f, 6.0f)
//                .requiresCorrectToolForDrops()
                .noOcclusion()
        );
        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(FACING, Direction.NORTH)
        );
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new PrisonDoorBlockEntity(pos, state);
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

        VoxelShape baseShape = PrisonDoorBlock.SHAPES.get(facing);
        VoxelShape ghostShape = ModBlocks.PRISON_DOOR_GHOST.get().getShapeForFacing(facing).move(
                0,
                1,
                0
        );

        return Shapes.or(baseShape, ghostShape);
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
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        Level level = ctx.getLevel();
        BlockPos pos = ctx.getClickedPos();
        Direction facing = ctx.getHorizontalDirection().getOpposite();

        BlockPos topPos = pos.above();

        if (!level.getBlockState(topPos).isAir()) {
            if (level.getBlockState(topPos).canBeReplaced(ctx)) {
                level.destroyBlock(topPos, true);
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
        BlockPos topPos = pos.above();

        BlockState cisternGhost = ModBlocks.PRISON_DOOR_GHOST.get()
                .defaultBlockState()
                .setValue(PrisonDoorGhostBlock.FACING, facing);

        if (level.isEmptyBlock(topPos)) {
            level.setBlock(topPos, cisternGhost, 3);
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
            level.removeBlock(pos.above(), false);
        }

        super.onRemove(state, level, pos, newState, isMoving);
    }
}
