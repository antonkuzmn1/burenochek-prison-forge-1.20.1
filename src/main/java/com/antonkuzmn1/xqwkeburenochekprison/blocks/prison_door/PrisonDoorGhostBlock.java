package com.antonkuzmn1.xqwkeburenochekprison.blocks.prison_door;

import com.antonkuzmn1.xqwkeburenochekprison.registry.ModBlocks;
import com.antonkuzmn1.xqwkeburenochekprison.utils.VoxelShapeUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
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

import java.util.EnumMap;
import java.util.Map;

@SuppressWarnings("deprecation")
public class PrisonDoorGhostBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public static final BooleanProperty OPENED = BooleanProperty.create("opened");
    public static final BooleanProperty SHUTTER = BooleanProperty.create("shutter");

    public static final VoxelShape SHAPE_NORTH = Shapes.or(
            Shapes.box(
                    0 / 16f, 0 / 16f, 0 / 16f,
                    16 / 16f, 16 / 16f, 6 / 16f
            )
    );

    private static final VoxelShape SHAPE_NORTH_OPENED = Shapes.or(
            Shapes.box(
                    10 / 16f, 0 / 16f, 0 / 16f,
                    16 / 16f, 16 / 16f, 16 / 16f
            )
    );

    protected final Map<Direction, VoxelShape> SHAPES = new EnumMap<>(Direction.class);
    public static final Map<Direction, VoxelShape> SHAPES_OPENED = new EnumMap<>(Direction.class);

    public PrisonDoorGhostBlock() {
        super(Properties.of()
                .strength(2.0f, 6.0f)
                .noOcclusion()
        );

        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(OPENED, false)
                .setValue(SHUTTER, false)
        );

        SHAPES.put(Direction.NORTH, SHAPE_NORTH);
        SHAPES.put(Direction.EAST, VoxelShapeUtils.rotate(Direction.NORTH, Direction.EAST, SHAPE_NORTH));
        SHAPES.put(Direction.SOUTH, VoxelShapeUtils.rotate(Direction.NORTH, Direction.SOUTH, SHAPE_NORTH));
        SHAPES.put(Direction.WEST, VoxelShapeUtils.rotate(Direction.NORTH, Direction.WEST, SHAPE_NORTH));

        SHAPES_OPENED.put(Direction.NORTH, SHAPE_NORTH_OPENED);
        SHAPES_OPENED.put(
                Direction.EAST,
                VoxelShapeUtils.rotate(Direction.NORTH, Direction.EAST, SHAPE_NORTH_OPENED)
        );
        SHAPES_OPENED.put(
                Direction.SOUTH,
                VoxelShapeUtils.rotate(Direction.NORTH, Direction.SOUTH, SHAPE_NORTH_OPENED)
        );
        SHAPES_OPENED.put(
                Direction.WEST,
                VoxelShapeUtils.rotate(Direction.NORTH, Direction.WEST, SHAPE_NORTH_OPENED)
        );

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

        boolean opened = state.getValue(OPENED);

        Map<Direction, VoxelShape> baseShapes = opened ? PrisonDoorBlock.SHAPES_OPENED : PrisonDoorBlock.SHAPES;
        VoxelShape baseShape = baseShapes.get(facing).move(0, -1, 0);
        VoxelShape ghostShape = ModBlocks.PRISON_DOOR_GHOST.get().getShapeForFacing(facing, opened);


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

        BlockPos basePos =  pos.below();

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

    public VoxelShape getShapeForFacing(Direction facing, boolean opened) {
        return (opened ? SHAPES_OPENED : SHAPES).get(facing);
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
        if (!level.isClientSide) {
            BlockPos basePos = pos.below();
            BlockState mainState = level.getBlockState(basePos);
            if (mainState.getBlock() instanceof PrisonDoorBlock tableBlock) {
                return tableBlock.use(mainState, level, basePos, player, hand, hit);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, OPENED, SHUTTER);
    }

    private Map<Direction, VoxelShape> getShapes(@NotNull BlockState state) {
        return state.getValue(OPENED) ? SHAPES_OPENED : SHAPES;
    }
}
