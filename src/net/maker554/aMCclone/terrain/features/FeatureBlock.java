package net.maker554.aMCclone.terrain.features;

import org.joml.Vector3i;

public class FeatureBlock {
    public byte id;
    public Vector3i location;

    public FeatureBlock(byte id, Vector3i position, Vector3i location) {
        this.id = id;
        this.location = new Vector3i(location).add(position);
    }

    public FeatureBlock(byte id, Vector3i position) {
        this.id = id;
        this.location = position;
    }
}
