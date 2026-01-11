package com.antonkuzmn1.xqwkeburenochekprison.blocks.parasha;

import com.antonkuzmn1.xqwkeburenochekprison.blockentities.ParashaBlockEntity;
import com.antonkuzmn1.xqwkeburenochekprison.blocks.prison_door.PrisonDoorBlock;
import com.antonkuzmn1.xqwkeburenochekprison.registry.ModBlocks;
import com.antonkuzmn1.xqwkeburenochekprison.utils.VoxelShapeUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3d;

import java.util.EnumMap;
import java.util.List;
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

        VoxelShapeUtils.putAllDirections(shapes, shapeNorth);
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
    public @NotNull VoxelShape getVisualShape(
            @NotNull BlockState state,
            @NotNull BlockGetter level,
            @NotNull BlockPos pos,
            @NotNull CollisionContext context
    ) {
        return shapes.get(state.getValue(FACING));
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
    public @NotNull InteractionResult use(
            @NotNull BlockState state,
            @NotNull Level level,
            @NotNull BlockPos pos,
            @NotNull Player player,
            @NotNull InteractionHand hand,
            @NotNull BlockHitResult hit
    ) {
        if (state.getValue(PART) == ParashaBlockPart.TOP) {
            if (level.isClientSide) return InteractionResult.SUCCESS;

            ServerLevel serverLevel = (ServerLevel) level;
            ParashaStorage storage = ParashaStorage.get(serverLevel);

            Direction facing = state.getValue(FACING);
            BlockPos mainPos = pos.below();
            BlockPos rimPos = pos.below().relative(facing);

            double x1 = mainPos.getX() + 0.5;
            double y1 = mainPos.getY() + 0.5;
            double z1 = mainPos.getZ() + 0.5;

            double x2 = rimPos.getX() + 0.5;
            double y2 = rimPos.getY() + 0.5;
            double z2 = rimPos.getZ() + 0.5;

            double midX = (x1 + x2) / 2.0;
            double midY = (y1 + y2) / 2.0;
            double midZ = (z1 + z2) / 2.0;

            serverLevel.sendParticles(
                    ParticleTypes.SPLASH,
                    midX,
                    midY,
                    midZ,
                    10,
                    0.2, 0.2, 0.2,
                    0.1
            );

            BlockEntity mainBE = level.getBlockEntity(mainPos);
            if (mainBE instanceof ParashaBlockEntity) {
                ParashaBlockEntity.playFlushAnimation(mainPos);
            }

            if (storage.getItems().isEmpty()) {
                List<ItemEntity> items = level.getEntitiesOfClass(
                        ItemEntity.class,
                        new AABB(
                                rimPos.getX() + 0 / 16f,
                                rimPos.getY() + 1 / 16f,
                                rimPos.getZ() + 8 / 16f,
                                mainPos.getX() + 16 / 16f,
                                mainPos.getY() + 16 / 16f,
                                mainPos.getZ() + 8 / 16f
                        )
                );

                if (items.isEmpty()) return InteractionResult.PASS;

                for (ItemEntity item : items) {
                    storage.getItems().add(item.getItem().copy());
                    item.discard();
                }

                storage.setDirty();
            } else {
                for (ItemStack stack : storage.getItems()) {
                    ItemEntity drop = new ItemEntity(
                            level,
                            rimPos.getX() + 0.5,
                            rimPos.getY() + 1,
                            rimPos.getZ() + 0.5,
                            stack
                    );
                    level.addFreshEntity(drop);
                }

                storage.getItems().clear();
                storage.setDirty();
            }
        }

        return InteractionResult.PASS;
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
