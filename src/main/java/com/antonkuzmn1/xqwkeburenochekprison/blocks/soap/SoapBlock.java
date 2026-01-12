package com.antonkuzmn1.xqwkeburenochekprison.blocks.soap;

import com.antonkuzmn1.xqwkeburenochekprison.blockentities.SoapBlockEntity;
import com.antonkuzmn1.xqwkeburenochekprison.registry.ModItems;
import com.antonkuzmn1.xqwkeburenochekprison.utils.VoxelShapeUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.EnumMap;
import java.util.Map;

@SuppressWarnings("deprecation")
public class SoapBlock extends Block implements EntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_NORTH = Shapes.box(
            7 / 16f, 0 / 16f, 7 / 16f,
            10 / 16f, 2 / 16f, 9 / 16f
    );

    public static final Map<Direction, VoxelShape> SHAPES = new EnumMap<>(Direction.class);

    static {
        VoxelShapeUtils.putAllDirections(SHAPES, SHAPE_NORTH);
    }

    public SoapBlock() {
        super(Properties.of().strength(0.5f, 1.0f).noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new SoapBlockEntity(pos, state);
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
        builder.add(FACING);
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
    public float getDestroyProgress(
            @NotNull BlockState state,
            Player player,
            @NotNull BlockGetter level,
            @NotNull BlockPos pos
    ) {
        if (!player.isCreative()) {
            if (player.level().isClientSide) {
                player.displayClientMessage(
                        Component.translatable("hint.xqwkeburenochekprison.soap.how_to_pick_up"),
                        true
                );
            }
            return 0.0F;
        }

        return super.getDestroyProgress(state, player, level, pos);
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

            ItemStack soap = new ItemStack(ModItems.SOAP.get());

            if (!player.getInventory().add(soap)) {
                player.drop(soap, false);
            }

            level.removeBlock(pos, false);

            return InteractionResult.CONSUME;
        }

        if (level.isClientSide) {
            player.displayClientMessage(
                    Component.translatable("hint.xqwkeburenochekprison.soap.how_to_pick_up"),
                    true
            );
        }

        return InteractionResult.PASS;
    }
}
