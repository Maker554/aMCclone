package net.maker554.aMCclone.terrain.features;

import org.joml.Vector2i;
import org.joml.Vector3i;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tree extends Feature {

    private static final List<Vector3i> shapePos = Arrays.asList(
            new Vector3i(0, 0, 0),
            new Vector3i(0, 1, 0),
            new Vector3i(0, 2, 0),
            new Vector3i(0, 3, 0),
            new Vector3i(0, 4, 0),

            new Vector3i(-2, 2, -1),
            new Vector3i(-2, 2, 0),
            new Vector3i(-2, 2, 1),
            new Vector3i(-1, 2, -2),
            new Vector3i(-1, 2, -1),
            new Vector3i(-1, 2, 0),
            new Vector3i(-1, 2, 1),
            new Vector3i(-1, 2, 2),
            new Vector3i(0, 2, -2),
            new Vector3i(0, 2, -1),
            new Vector3i(0, 2, 1),
            new Vector3i(0, 2, 2),
            new Vector3i(1, 2, -2),
            new Vector3i(1, 2, -1),
            new Vector3i(1, 2, 0),
            new Vector3i(1, 2, 1),
            new Vector3i(1, 2, 2),
            new Vector3i(2, 2, -1),
            new Vector3i(2, 2, 0),
            new Vector3i(2, 2, 1),

            new Vector3i(-2, 3, -1),
            new Vector3i(-2, 3, 0),
            new Vector3i(-2, 3, 1),
            new Vector3i(-1, 3, -2),
            new Vector3i(-1, 3, -1),
            new Vector3i(-1, 3, 0),
            new Vector3i(-1, 3, 1),
            new Vector3i(-1, 3, 2),
            new Vector3i(0, 3, -2),
            new Vector3i(0, 3, -1),
            new Vector3i(0, 3, 1),
            new Vector3i(0, 3, 2),
            new Vector3i(1, 3, -2),
            new Vector3i(1, 3, -1),
            new Vector3i(1, 3, 0),
            new Vector3i(1, 3, 1),
            new Vector3i(1, 3, 2),
            new Vector3i(2, 3, -1),
            new Vector3i(2, 3, 0),
            new Vector3i(2, 3, 1),

            new Vector3i(-1, 4, 0),
            new Vector3i(1, 4, 0),
            new Vector3i(0, 4, -1),
            new Vector3i(0, 4, 1),

            new Vector3i(-1, 5, 0),
            new Vector3i(1, 5, 0),
            new Vector3i(0, 5, 0),
            new Vector3i(0, 5, -1),
            new Vector3i(0, 5, 1)
    );
    private static final int[] shapeData = new int[]{
            10,
            10,
            10,
            10,
            10,

            11,
            11,
            11,
            11,
            11,
            11,
            11,
            11,
            11,
            11,
            11,
            11,
            11,
            11,
            11,
            11,
            11,
            11,
            11,
            11,

            11,
            11,
            11,
            11,
            11,
            11,
            11,
            11,
            11,
            11,
            11,
            11,
            11,
            11,
            11,
            11,
            11,
            11,
            11,
            11,

            11,
            11,
            11,
            11,

            11,
            11,
            11,
            11,
            11
    };

    public Tree(Vector3i position) {
        super(position);
        blocks = new ArrayList<>();

        for (Vector3i pos : shapePos) {
            setBlock(pos, shapeData[shapePos.indexOf(pos)]);
        }
    }
}
