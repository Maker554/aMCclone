package net.maker554.aMCclone.terrain.features;

import org.joml.Vector3i;

import java.util.ArrayList;

public class Tree extends Feature {
    public Tree(Vector3i position) {
        super(position);
        blocks = new ArrayList<>();

        // data of tree
        setBlock(new Vector3i(0, 0, 0), 10);
        setBlock(new Vector3i(0, 1, 0), 10);
        setBlock(new Vector3i(0, 2, 0), 10);
        setBlock(new Vector3i(0, 3, 0), 10);
        setBlock(new Vector3i(0, 4, 0), 10);

        setBlock(new Vector3i(-2, 2, -1), 11);
        setBlock(new Vector3i(-2, 2, 0), 11);
        setBlock(new Vector3i(-2, 2, 1), 11);
        setBlock(new Vector3i(-1, 2, -2), 11);
        setBlock(new Vector3i(-1, 2, -1), 11);
        setBlock(new Vector3i(-1, 2, 0), 11);
        setBlock(new Vector3i(-1, 2, 1), 11);
        setBlock(new Vector3i(-1, 2, 2), 11);
        setBlock(new Vector3i(0, 2, -2), 11);
        setBlock(new Vector3i(0, 2, -1), 11);
        setBlock(new Vector3i(0, 2, 1), 11);
        setBlock(new Vector3i(0, 2, 2), 11);
        setBlock(new Vector3i(1, 2, -2), 11);
        setBlock(new Vector3i(1, 2, -1), 11);
        setBlock(new Vector3i(1, 2, 0), 11);
        setBlock(new Vector3i(1, 2, 1), 11);
        setBlock(new Vector3i(1, 2, 2), 11);
        setBlock(new Vector3i(2, 2, -1), 11);
        setBlock(new Vector3i(2, 2, 0), 11);
        setBlock(new Vector3i(2, 2, 1), 11);

        setBlock(new Vector3i(-2, 3, -1), 11);
        setBlock(new Vector3i(-2, 3, 0), 11);
        setBlock(new Vector3i(-2, 3, 1), 11);
        setBlock(new Vector3i(-1, 3, -2), 11);
        setBlock(new Vector3i(-1, 3, -1), 11);
        setBlock(new Vector3i(-1, 3, 0), 11);
        setBlock(new Vector3i(-1, 3, 1), 11);
        setBlock(new Vector3i(-1, 3, 2), 11);
        setBlock(new Vector3i(0, 3, -2), 11);
        setBlock(new Vector3i(0, 3, -1), 11);
        setBlock(new Vector3i(0, 3, 1), 11);
        setBlock(new Vector3i(0, 3, 2), 11);
        setBlock(new Vector3i(1, 3, -2), 11);
        setBlock(new Vector3i(1, 3, -1), 11);
        setBlock(new Vector3i(1, 3, 0), 11);
        setBlock(new Vector3i(1, 3, 1), 11);
        setBlock(new Vector3i(1, 3, 2), 11);
        setBlock(new Vector3i(2, 3, -1), 11);
        setBlock(new Vector3i(2, 3, 0), 11);
        setBlock(new Vector3i(2, 3, 1), 11);

        setBlock(new Vector3i(-1, 4, 0), 11);
        setBlock(new Vector3i(1, 4, 0), 11);
        setBlock(new Vector3i(0, 4, -1), 11);
        setBlock(new Vector3i(0, 4, 1), 11);

        setBlock(new Vector3i(-1, 5, 0), 11);
        setBlock(new Vector3i(1, 5, 0), 11);
        setBlock(new Vector3i(0, 5, 0), 11);
        setBlock(new Vector3i(0, 5, -1), 11);
        setBlock(new Vector3i(0, 5, 1), 11);
    }
}
