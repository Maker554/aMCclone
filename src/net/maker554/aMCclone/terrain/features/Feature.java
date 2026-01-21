package net.maker554.aMCclone.terrain.features;

import org.joml.Vector3i;

import java.util.List;

public class Feature {
    public List<FeatureBlock> blocks;
    protected Vector3i position;

    public Feature(Vector3i location) {
        this.position = location;
    }

    protected void setBlock(Vector3i loc, int id) {
        blocks.add(new FeatureBlock((byte) id, position, loc));
    }
}
