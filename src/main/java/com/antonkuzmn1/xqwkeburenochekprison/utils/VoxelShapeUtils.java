package com.antonkuzmn1.xqwkeburenochekprison.utils;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public final class VoxelShapeUtils {
    private VoxelShapeUtils() {
    }

    public static VoxelShape rotate(
            Direction from,
            Direction to,
            VoxelShape shape
    ) {
        VoxelShape[] buffer = new VoxelShape[]{shape, Shapes.empty()};

        int times = (to.get2DDataValue() - from.get2DDataValue() + 4) % 4;
        for (int i = 0; i < times; i++) {
            buffer[1] = Shapes.empty();
            buffer[0].forAllBoxes((
                    minX, minY, minZ,
                    maxX, maxY, maxZ
            ) -> buffer[1] = Shapes.or(
                    buffer[1],
                    Shapes.box(
                            1 - maxZ, minY, minX,
                            1 - minZ, maxY, maxX
                    )
            ));
            buffer[0] = buffer[1];
        }
        return buffer[0];
    }

    public static Map<Direction, VoxelShape> putAllDirections(
            Map<Direction, VoxelShape> shapes,
            VoxelShape shapeNorth
    ) {
        shapes.put(Direction.NORTH, shapeNorth);
        shapes.put(Direction.EAST, VoxelShapeUtils.rotate(Direction.NORTH, Direction.EAST, shapeNorth));
        shapes.put(Direction.SOUTH, VoxelShapeUtils.rotate(Direction.NORTH, Direction.SOUTH, shapeNorth));
        shapes.put(Direction.WEST, VoxelShapeUtils.rotate(Direction.NORTH, Direction.WEST, shapeNorth));
        return shapes;
    }
}
