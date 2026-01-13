package com.antonkuzmn1.xqwkeburenochekprison.blocks.folding_bed;

import com.antonkuzmn1.xqwkeburenochekprison.blockentities.FoldingBedBlockEntity;
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
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BedBlock;
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
import org.spongepowered.asm.mixin.Overwrite;

import javax.annotation.Nullable;
import java.util.EnumMap;
import java.util.Map;

@SuppressWarnings("deprecation")
public class FoldingBedGhostBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty OCCUPIED = BlockStateProperties.OCCUPIED;

    public static final BooleanProperty FOLDED = BooleanProperty.create("folded");

    private static final VoxelShape SHAPE_NORTH = Shapes.or(
            box(3, 4, 0, 15, 5, 15),
            box(2, 2, 0, 3, 4, 14),
            box(15, 2, 0, 16, 4, 14),
            box(4, 2, 15, 14, 4, 16),
            box(14, 2, 14, 16, 6, 16),
            box(3, 1, 0, 15, 2, 15),
            box(2, 2, 14, 4, 6, 16),
            box(4, 5, 7, 14, 6, 14),
            box(0, 3, 12, 2, 16, 15),
            box(15, 1, -1, 16, 2, 0),
            box(15, 1, 0, 16, 2, 2),
            box(3, 0, -1, 15, 1, 0),
            box(3, 0, 0, 15, 1, 2),
            box(2, 1, -1, 8, 2, 0),
            box(2, 1, 0, 8, 2, 2),
            box(3, 1, -1, 8, 2, 2),
            box(2, 1, 12, 3, 2, 15),
            box(1, 2, 12, 2, 3, 15),
            box(3, 0, 12, 15, 1, 15),
            box(15, 1, 12, 16, 2, 15)
    );


    private static final VoxelShape SHAPE_NORTH_FOLDED = Shapes.or(
            box(6, 2, 0, 7, 3, 2),
            box(7, 3, 0, 8, 15, 2),
            box(6, 15, 0, 7, 16, 2),

            box(5, 1, 12, 6, 2, 15),
            box(6, 2, 12, 7, 3, 15),
            box(7, 3, 12, 8, 15, 15),
            box(6, 15, 12, 7, 16, 15),

            box(6, 3, 0, 7, 15, 15),
            box(4, 2, 0, 6, 16, 16),

            box(3, 3, 0, 4, 15, 14),
            box(2, 4, 7, 3, 14, 14),

            box(2, 2, 14, 6, 4, 16),
            box(2, 14, 14, 6, 16, 16),

            box(0, 2, 12, 2, 15, 15)
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
    public void onPlace(
            @NotNull BlockState state,
            Level level,
            @NotNull BlockPos pos,
            @NotNull BlockState oldState,
            boolean isMoving
    ) {
        if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
            serverLevel.scheduleTick(pos, this, 20);
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        boolean shouldBeFolded = !level.isNight();

        if (state.getValue(FOLDED) != shouldBeFolded) {
            level.setBlock(
                    pos,
                    state.setValue(FOLDED, shouldBeFolded),
                    Block.UPDATE_ALL
            );
        }

        level.scheduleTick(pos, this, 20);
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
