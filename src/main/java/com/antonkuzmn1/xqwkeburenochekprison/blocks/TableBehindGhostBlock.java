package com.antonkuzmn1.xqwkeburenochekprison.blocks;

import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TableBehindGhostBlock extends TableGhostBlock {
    public static final VoxelShape SHAPE_NORTH = Shapes.or(
            Shapes.box(
                    6 / 16f, 0 / 16f, 0 / 16f,
                    11 / 16f, 1 / 16f, 1 / 16f
            ),
            Shapes.box(
                    6 / 16f, 10 / 16f, 0 / 16f,
                    11 / 16f, 14 / 16f, 1 / 16f
            ),

            Shapes.box(
                    6 / 16f, 0 / 16f, 10 / 16f,
                    11 / 16f, 1 / 16f, 15 / 16f
            ),
            Shapes.box(
                    7 / 16f, 1 / 16f, 11 / 16f,
                    10 / 16f, 10 / 16f, 14 / 16f
            ),
            Shapes.box(
                    6 / 16f, 10 / 16f, 10 / 16f,
                    11 / 16f, 14 / 16f, 15 / 16f
            ),

            Shapes.box(
                    0 / 16f, 11 / 16f, 13 / 16f,
                    6 / 16f, 14 / 16f, 14 / 16f
            ),
            Shapes.box(
                    9 / 16f, 11 / 16f, 1 / 16f,
                    10 / 16f, 14 / 16f, 10 / 16f
            ),

            Shapes.box(
                    0 / 16f, 14 / 16f, 0 / 16f,
                    12 / 16f, 16 / 16f, 16 / 16f
            )
    );

    public TableBehindGhostBlock() {
        super(TableBlockPart.BEHIND, SHAPE_NORTH);
    }
}
