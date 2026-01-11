package com.antonkuzmn1.xqwkeburenochekprison.blocks.table;

import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TableRightGhostBlock extends TableGhostBlock {
    public static final VoxelShape SHAPE_NORTH = Shapes.or(
            Shapes.box(
                    5 / 16f, 0 / 16f, 12 / 16f,
                    10 / 16f, 1 / 16f, 16 / 16f
            ),
            Shapes.box(
                    6 / 16f, 1 / 16f, 13 / 16f,
                    9 / 16f, 9 / 16f, 16 / 16f
            ),
            Shapes.box(
                    5 / 16f, 9 / 16f, 12 / 16f,
                    10 / 16f, 14 / 16f, 16 / 16f
            ),

            Shapes.box(
                    10 / 16f, 11 / 16f, 13 / 16f,
                    16 / 16f, 14 / 16f, 14 / 16f
            ),

            Shapes.box(
                    4 / 16f, 14 / 16f, 11 / 16f,
                    16 / 16f, 16 / 16f, 16 / 16f
            )
    );

    private static final VoxelShape SHAPE_NORTH_WITH_CHAIR = Shapes.or(
            SHAPE_NORTH,
            Shapes.box(
                    10 / 16f, 0 / 16f, 0 / 16f,
                    14 / 16f, 1 / 16f, 4 / 16f
            ),
            Shapes.box(
                    11 / 16f, 1 / 16f, 1 / 16f,
                    13 / 16f, 8 / 16f, 3 / 16f
            ),

            Shapes.box(
                    10 / 16f, 0 / 16f, 7 / 16f,
                    14 / 16f, 1 / 16f, 11 / 16f
            ),
            Shapes.box(
                    11 / 16f, 1 / 16f, 8 / 16f,
                    13 / 16f, 8 / 16f, 10 / 16f
            ),

            Shapes.box(
                    11 / 16f, 5 / 16f, 3 / 16f,
                    12 / 16f, 6 / 16f, 8 / 16f
            ),
            Shapes.box(
                    13 / 16f, 5 / 16f, 9 / 16f,
                    16 / 16f, 6 / 16f, 10 / 16f
            ),
            Shapes.box(
                    13 / 16f, 5 / 16f, 1 / 16f,
                    16 / 16f, 6 / 16f, 2 / 16f
            ),

            Shapes.box(
                    11 / 16f, 8 / 16f, 1 / 16f,
                    16 / 16f, 9 / 16f, 10 / 16f
            ),
            Shapes.box(
                    10 / 16f, 9 / 16f, 0 / 16f,
                    16 / 16f, 10 / 16f, 11 / 16f
            )
    );

    public TableRightGhostBlock() {
        super(TableBlockPart.RIGHT, SHAPE_NORTH, SHAPE_NORTH_WITH_CHAIR);
    }
}
