package com.antonkuzmn1.xqwkeburenochekprison.blocks;

import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ParashaRimGhostBlock extends ParashaGhostBlock {
    public static final VoxelShape SHAPE_NORTH = Shapes.join(
            Shapes.box(
                    0 / 16f, 0 / 16f, 8 / 16f,
                    16 / 16f, 3 / 16f, 16 / 16f
            ),
            Shapes.or(
                    Shapes.box(
                            5 / 16f, 1 / 16f, 12 / 16f,
                            11 / 16f, 3 / 16f, 16 / 16f
                    ),
                    Shapes.box(
                            6 / 16f, 1 / 16f, 11 / 16f,
                            10 / 16f, 2 / 16f, 12 / 16f
                    ),
                    Shapes.box(
                            5 / 16f, 2 / 16f, 11 / 16f,
                            11 / 16f, 3 / 16f, 12 / 16f
                    ),
                    Shapes.box(
                            4 / 16f, 2 / 16f, 8 / 16f,
                            12 / 16f, 3 / 16f, 11 / 16f
                    )
            ),
            BooleanOp.ONLY_FIRST
    );

    public ParashaRimGhostBlock() {
        super(ParashaBlockPart.FRONT, SHAPE_NORTH);
    }
}
