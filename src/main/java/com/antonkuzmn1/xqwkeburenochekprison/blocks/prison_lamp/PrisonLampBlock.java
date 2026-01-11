package com.antonkuzmn1.xqwkeburenochekprison.blocks.prison_lamp;

import com.antonkuzmn1.xqwkeburenochekprison.blockentities.PrisonLampBlockEntity;
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
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.EnumMap;
import java.util.Map;

@SuppressWarnings("deprecation")
public class PrisonLampBlock extends Block implements EntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public static final IntegerProperty LIGHT = IntegerProperty.create("light", 0, 2);

    private static final VoxelShape SHAPE_NORTH = Shapes.or(
            Shapes.box(
                    6 / 16f, 1 / 16f, 5 / 16f,
                    11 / 16f, 6 / 16f, 10 / 16f
            ),

            Shapes.box(
                    7 / 16f, 6 / 16f, 6 / 16f,
                    10 / 16f, 11 / 16f, 9 / 16f
            ),

            Shapes.box(
                    6 / 16f, 7 / 16f, 5 / 16f,
                    11 / 16f, 8 / 16f, 10 / 16f
            ),
            Shapes.box(
                    6 / 16f, 9 / 16f, 5 / 16f,
                    11 / 16f, 10 / 16f, 10 / 16f
            ),

            Shapes.box(
                    8 / 16f, 11 / 16f, 7 / 16f,
                    9 / 16f, 16 / 16f, 8 / 16f
            )
    );

    public static final Map<Direction, VoxelShape> SHAPES = new EnumMap<>(Direction.class);

    static {
        VoxelShapeUtils.putAllDirections(SHAPES, SHAPE_NORTH);
    }

    public PrisonLampBlock() {
        super(Properties.of()
                .strength(2.0f, 6.0f)
                .noOcclusion()
        );
        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(FACING, Direction.NORTH)
                        .setValue(LIGHT, 0)
        );
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new PrisonLampBlockEntity(pos, state);
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
        builder.add(FACING, LIGHT);
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
            @NotNull BlockState state,
            @NotNull Level level,
            @NotNull BlockPos pos,
            @NotNull BlockState newState,
            boolean isMoving
    ) {
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
        if (player.isShiftKeyDown()) {
            if (level.isClientSide) return InteractionResult.SUCCESS;

            int next = (state.getValue(LIGHT) + 1) % 3;
            level.setBlock(pos, state.setValue(LIGHT, next), Block.UPDATE_ALL);

            return InteractionResult.CONSUME;
        }

        return InteractionResult.PASS;
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return switch (state.getValue(LIGHT)) {
            case 1 -> 7;
            case 2 -> 12;
            default -> 0;
        };
    }
}
