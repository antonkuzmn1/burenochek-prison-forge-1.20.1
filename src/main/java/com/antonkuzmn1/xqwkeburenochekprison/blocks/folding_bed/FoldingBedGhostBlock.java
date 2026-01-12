package com.antonkuzmn1.xqwkeburenochekprison.blocks.folding_bed;

import com.antonkuzmn1.xqwkeburenochekprison.registry.ModBlocks;
import com.antonkuzmn1.xqwkeburenochekprison.utils.VoxelShapeUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.EnumMap;
import java.util.Map;

@SuppressWarnings("deprecation")
public class FoldingBedGhostBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty OCCUPIED = BlockStateProperties.OCCUPIED;

    public static final BooleanProperty FOLDED = BooleanProperty.create("folded");

    private static final VoxelShape SHAPE_NORTH = Shapes.or(
            Shapes.box(
                    0 / 16f, 0 / 16f, 0 / 16f,
                    16 / 16f, 9 / 16f, 16 / 16f
            ) // TODO HITBOX REQUIRED
    );

    private static final VoxelShape SHAPE_NORTH_FOLDED = Shapes.or(
            SHAPE_NORTH,
            Shapes.box(
                    0 / 16f, 0 / 16f, 0 / 16f,
                    16 / 16f, 9 / 16f, 16 / 16f
            ) // TODO HITBOX REQUIRED
    );

    private final Map<Direction, VoxelShape> SHAPES = new EnumMap<>(Direction.class);
    private final Map<Direction, VoxelShape> SHAPES_FOLDED = new EnumMap<>(Direction.class);

    public FoldingBedGhostBlock() {
        super(Properties.of()
                .strength(2.0f, 6.0f)
                .noOcclusion()
        );

        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(FOLDED, false)
                .setValue(OCCUPIED, false)
        );

        VoxelShapeUtils.putAllDirections(SHAPES, SHAPE_NORTH);
        VoxelShapeUtils.putAllDirections(SHAPES_FOLDED, SHAPE_NORTH_FOLDED);
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

        boolean folded = state.getValue(FOLDED);

        Map<Direction, VoxelShape> baseShapes = folded ? FoldingBedBlock.SHAPES_FOLDED : FoldingBedBlock.SHAPES;

        VoxelShape baseShape = baseShapes.get(facing).move(facingX, 0, facingZ);
        VoxelShape ghostShape = ModBlocks.FOLDING_BED_GHOST.get().getShapeForFacing(facing, folded);

        return Shapes.or(baseShape, ghostShape);
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

        BlockPos basePos = pos.relative(facing);

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
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.startSleepInBed(pos);
            return InteractionResult.PASS;
        }

        return InteractionResult.PASS;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        boolean shouldBeFolded = !level.isNight();

        if (state.getValue(FOLDED) != shouldBeFolded) {
            level.setBlock(pos, state.setValue(FOLDED, shouldBeFolded), Block.UPDATE_ALL);
        }
    }

    public VoxelShape getShapeForFacing(Direction facing, boolean folded) {
        return (folded ? SHAPES_FOLDED : SHAPES).get(facing);
    }

    @Override
    public boolean isBed(
            BlockState state,
            BlockGetter level,
            BlockPos pos,
            @Nullable Entity sleeper
    ) {
        return true;
    }

    @Override
    public Direction getBedDirection(
            BlockState state,
            LevelReader level,
            BlockPos pos
    ) {
        return state.getValue(FACING).getOpposite();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, FOLDED, OCCUPIED);
    }

    private Map<Direction, VoxelShape> getShapes(@NotNull BlockState state) {
        return state.getValue(FOLDED) ? SHAPES_FOLDED : SHAPES;
    }
}
