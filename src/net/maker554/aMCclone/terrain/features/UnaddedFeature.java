package net.maker554.aMCclone.terrain.features;

import net.maker554.aMCclone.Settings;
import net.maker554.aMCclone.terrain.Chunk;
import org.joml.Vector2i;
import org.joml.Vector3i;

import java.util.List;

public class UnaddedFeature extends Feature {

    public Vector2i chunkPos;

    public UnaddedFeature(Feature feature, Chunk chunk, Vector2i chunkOffset) {

        super(new Vector3i(feature.position.x, feature.position.y, feature.position.z));

        this.chunkPos = new Vector2i(chunk.getX() + chunkOffset.x, chunk.getZ() + chunkOffset.y);

        if (chunkOffset.x == 1) position.x -= Settings.CHUNK_SIZE;
        else if (chunkOffset.x == -1) position.x += Settings.CHUNK_SIZE;

        if (chunkOffset.y == 1) position.z -= Settings.CHUNK_SIZE;
        else if (chunkOffset.y == -1) position.z += Settings.CHUNK_SIZE;

        blocks = new Tree(position).blocks;
    }

    public UnaddedFeature (List<FeatureBlock> blockList, Vector3i localPos, Vector2i chunkPos) {

        super(localPos); // sets position
        this.blocks = blockList;
        this.chunkPos = chunkPos;
    }

    public Vector3i getPosition() {
        return position;
    }
}
