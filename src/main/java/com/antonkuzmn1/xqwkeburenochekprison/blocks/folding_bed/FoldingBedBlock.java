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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.EnumMap;
import java.util.Map;

@SuppressWarnings("deprecation")
public class FoldingBedBlock extends Block implements EntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

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

    public static final Map<Direction, VoxelShape> SHAPES = new EnumMap<>(Direction.class);
    public static final Map<Direction, VoxelShape> SHAPES_FOLDED = new EnumMap<>(Direction.class);

    static {
        VoxelShapeUtils.putAllDirections(SHAPES, SHAPE_NORTH);
        VoxelShapeUtils.putAllDirections(SHAPES_FOLDED, SHAPE_NORTH_FOLDED);
    }

    public FoldingBedBlock() {
        super(Properties.of()
                .strength(1.0f, 2.0f)
                .noOcclusion()
        );
        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(FACING, Direction.NORTH)
                        .setValue(FOLDED, false)
        );
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new FoldingBedBlockEntity(pos, state);
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
        boolean folded = state.getValue(FOLDED);

        VoxelShape baseShape = getShapes(state).get(facing);
        VoxelShape gShape = ModBlocks.FOLDING_BED_GHOST.get().getShapeForFacing(facing, folded).move(
                -facing.getStepX(),
                0,
                -facing.getStepZ()
        );

        return Shapes.or(baseShape, gShape);
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
        builder.add(FACING, FOLDED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        Level level = ctx.getLevel();
        BlockPos pos = ctx.getClickedPos();
        Direction facing = ctx.getHorizontalDirection().getOpposite();

        BlockPos gPos = pos.relative(facing.getOpposite());

        if (!level.getBlockState(gPos).isAir()) {
            if (level.getBlockState(gPos).canBeReplaced(ctx)) {
                level.destroyBlock(gPos, true);
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

        BlockPos gPos = pos.relative(facing.getOpposite());

        BlockState behindGhost = ModBlocks.FOLDING_BED_GHOST.get()
                .defaultBlockState()
                .setValue(FoldingBedGhostBlock.FACING, facing)
                .setValue(FoldingBedGhostBlock.FOLDED, false);

        if (level.isEmptyBlock(gPos)) {
            level.setBlock(gPos, behindGhost, 3);
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
            BlockPos gPos = pos.relative(facing.getOpposite());
            level.removeBlock(gPos, false);
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
        if (level.isClientSide) {
            player.setPose(Pose.SLEEPING);
            player.teleportTo(
                    pos.getX() + 0.5,
                    pos.getY() + 1.8,
                    pos.getZ() + 0.5
            );

            player.setDeltaMovement(0, 0, 0);
            return InteractionResult.SUCCESS;
        }

        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.setPose(Pose.SLEEPING);
            serverPlayer.teleportTo(
                    pos.getX() + 0.5,
                    pos.getY() + 0.2,
                    pos.getZ() + 0.5
            );

            serverPlayer.setDeltaMovement(0, 0, 0);
            serverPlayer.startSleepInBed(pos); // TODO NOT WORKING

            return InteractionResult.PASS;
        }
        // АЛО ПУШИЬЬ
//        var result = serverPlayer.startSleepInBed(pos);
//        if (result.left().isPresent()) {
//            return InteractionResult.FAIL;
//        } else {
//            return InteractionResult.CONSUME;
//        }
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

    private Map<Direction, VoxelShape> getShapes(@NotNull BlockState state) {
        return state.getValue(FOLDED) ? SHAPES_FOLDED : SHAPES;
    }
}
