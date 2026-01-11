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
import net.minecraft.world.phys.shapes.BooleanOp;
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
            Shapes.join(
                    Shapes.or(
                            Shapes.box(
                                    0 / 16f, 0 / 16f, 0 / 16f,
                                    4 / 16f, 2 / 16f, 2 / 16f
                            ),

                            Shapes.box(
                                    14 / 16f, 0 / 16f, 1 / 16f,
                                    16 / 16f, 2 / 16f, 2 / 16f
                            ),
                            Shapes.box(
                                    14 / 16f, 0 / 16f, 0 / 16f,
                                    15 / 16f, 2 / 16f, 1 / 16f
                            ),

                            Shapes.box(
                                    14 / 16f, 8 / 16f, 1 / 16f,
                                    16 / 16f, 11 / 16f, 2 / 16f
                            ),
                            Shapes.box(
                                    14 / 16f, 8 / 16f, 0 / 16f,
                                    15 / 16f, 11 / 16f, 1 / 16f
                            ),

                            Shapes.box(
                                    4 / 16f, 7 / 16f, 0 / 16f,
                                    12 / 16f, 11 / 16f, 1 / 16f
                            ),

                            Shapes.box(
                                    2 / 16f, 0 / 16f, 1 / 16f,
                                    14 / 16f, 14 / 16f, 5 / 16f
                            ),
                            Shapes.box(
                                    0 / 16f, 0 / 16f, 2 / 16f,
                                    2 / 16f, 13 / 16f, 4 / 16f
                            ),
                            Shapes.box(
                                    14 / 16f, 0 / 16f, 2 / 16f,
                                    16 / 16f, 13 / 16f, 4 / 16f
                            ),

                            Shapes.box(
                                    0 / 16f, 13 / 16f, 0 / 16f,
                                    3 / 16f, 14 / 16f, 6 / 16f
                            ),
                            Shapes.box(
                                    13 / 16f, 13 / 16f, 0 / 16f,
                                    16 / 16f, 14 / 16f, 6 / 16f
                            ),

                            Shapes.box(
                                    0 / 16f, 14 / 16f, 0 / 16f,
                                    16 / 16f, 16 / 16f, 6 / 16f
                            )
                    ),
                    Shapes.box(
                            5 / 16f, 8 / 16f, 0 / 16f,
                            11 / 16f, 10 / 16f, 5 / 16f
                    ),
                    BooleanOp.ONLY_FIRST
            ),
            Shapes.box(
                    6 / 16f, 8 / 16f, 1 / 16f,
                    7 / 16f, 10 / 16f, 4 / 16f
            ),
            Shapes.box(
                    9 / 16f, 8 / 16f, 1 / 16f,
                    10 / 16f, 10 / 16f, 4 / 16f
            )
    );

    public static final VoxelShape SHAPE_NORTH_WITH_SHUTTER = Shapes.or(
            SHAPE_NORTH,
            Shapes.box(
                    5 / 16f, 8 / 16f, 0 / 16f,
                    11 / 16f, 10 / 16f, 1 / 16f
            )
    );

    private static final VoxelShape SHAPE_NORTH_OPENED = Shapes.or(
            Shapes.join(
                    Shapes.or(
                            Shapes.box(
                                    10 / 16f, 0 / 16f, 12 / 16f,
                                    12 / 16f, 2 / 16f, 16 / 16f
                            ),

                            Shapes.box(
                                    11 / 16f, 0 / 16f, 0 / 16f,
                                    12 / 16f, 2 / 16f, 2 / 16f
                            ),
                            Shapes.box(
                                    10 / 16f, 0 / 16f, 1 / 16f,
                                    11 / 16f, 2 / 16f, 2 / 16f
                            ),

                            Shapes.box(
                                    11 / 16f, 8 / 16f, 0 / 16f,
                                    12 / 16f, 11 / 16f, 2 / 16f
                            ),
                            Shapes.box(
                                    10 / 16f, 8 / 16f, 1 / 16f,
                                    11 / 16f, 11 / 16f, 2 / 16f
                            ),

                            Shapes.box(
                                    10 / 16f, 7 / 16f, 4 / 16f,
                                    11 / 16f, 11 / 16f, 12 / 16f
                            ),

                            Shapes.box(
                                    11 / 16f, 0 / 16f, 2 / 16f,
                                    15 / 16f, 14 / 16f, 14 / 16f
                            ),
                            Shapes.box(
                                    12 / 16f, 0 / 16f, 14 / 16f,
                                    14 / 16f, 13 / 16f, 16 / 16f
                            ),
                            Shapes.box(
                                    12 / 16f, 0 / 16f, 0 / 16f,
                                    14 / 16f, 13 / 16f, 2 / 16f
                            ),

                            Shapes.box(
                                    10 / 16f, 13 / 16f, 13 / 16f,
                                    16 / 16f, 14 / 16f, 16 / 16f
                            ),
                            Shapes.box(
                                    10 / 16f, 13 / 16f, 0 / 16f,
                                    16 / 16f, 14 / 16f, 3 / 16f
                            ),

                            Shapes.box(
                                    10 / 16f, 14 / 16f, 0 / 16f,
                                    16 / 16f, 16 / 16f, 16 / 16f
                            )
                    ),
                    Shapes.box(
                            10 / 16f, 8 / 16f, 5 / 16f,
                            15 / 16f, 10 / 16f, 11 / 16f
                    ),
                    BooleanOp.ONLY_FIRST
            ),
            Shapes.box(
                    11 / 16f, 8 / 16f, 9 / 16f,
                    14 / 16f, 10 / 16f, 10 / 16f
            ),
            Shapes.box(
                    11 / 16f, 8 / 16f, 6 / 16f,
                    14 / 16f, 10 / 16f, 7 / 16f
            )
    );

    private static final VoxelShape SHAPE_NORTH_OPENED_WITH_SHUTTER = Shapes.or(
            SHAPE_NORTH_OPENED,
            Shapes.box(
                    10 / 16f, 8 / 16f, 5 / 16f,
                    11 / 16f, 10 / 16f, 11 / 16f
            )
    );

    protected final Map<Direction, VoxelShape> SHAPES = new EnumMap<>(Direction.class);
    protected final Map<Direction, VoxelShape> SHAPES_WITH_SHUTTER = new EnumMap<>(Direction.class);
    public static final Map<Direction, VoxelShape> SHAPES_OPENED = new EnumMap<>(Direction.class);
    public static final Map<Direction, VoxelShape> SHAPES_OPENED_WITH_SHUTTER = new EnumMap<>(Direction.class);

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

        VoxelShapeUtils.putAllDirections(SHAPES, SHAPE_NORTH);
        VoxelShapeUtils.putAllDirections(SHAPES_OPENED, SHAPE_NORTH_OPENED);
        VoxelShapeUtils.putAllDirections(SHAPES_WITH_SHUTTER, SHAPE_NORTH_WITH_SHUTTER);
        VoxelShapeUtils.putAllDirections(SHAPES_OPENED_WITH_SHUTTER, SHAPE_NORTH_OPENED_WITH_SHUTTER);
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
        boolean shutter = state.getValue(SHUTTER);

        Map<Direction, VoxelShape> baseShapes = opened ? PrisonDoorBlock.SHAPES_OPENED : PrisonDoorBlock.SHAPES;
        VoxelShape baseShape = baseShapes.get(facing).move(0, -1, 0);
        VoxelShape ghostShape = ModBlocks.PRISON_DOOR_GHOST.get().getShapeForFacing(facing, opened, shutter);


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

        BlockPos basePos = pos.below();

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

    public VoxelShape getShapeForFacing(Direction facing, boolean opened, boolean shutter) {
        Map<Direction, VoxelShape> shapes = SHAPES;
        if (opened && shutter) {
            shapes = SHAPES_OPENED_WITH_SHUTTER;
        } else if (opened) {
            shapes = SHAPES_OPENED;
        } else if (shutter) {
            shapes = SHAPES_WITH_SHUTTER;
        }
        return shapes.get(facing);
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
        boolean opened = state.getValue(OPENED);
        boolean shutter = state.getValue(SHUTTER);
        if (opened && shutter) {
            return SHAPES_OPENED_WITH_SHUTTER;
        } else if (opened) {
            return SHAPES_OPENED;
        } else if (shutter) {
            return SHAPES_WITH_SHUTTER;
        }
        return SHAPES;
    }
}
