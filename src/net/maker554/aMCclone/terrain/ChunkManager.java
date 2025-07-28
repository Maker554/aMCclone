package net.maker554.aMCclone.terrain;

import org.joml.Vector2i;
import renderEngine.Camera;
import renderEngine.RenderManager;

import java.util.ArrayList;
import java.util.List;

public class ChunkManager {

    private static final int RENDER_DISTANCE =8;

    private static final List<Chunk> chunkList = new ArrayList<>();

    public static void generate(Vector2i playerPos) {
        for (int i=0; i<RENDER_DISTANCE*2; i++) {
            for (int j=0; j<RENDER_DISTANCE*2; j++) {
                chunkList.add(new Chunk(playerPos.x + i - RENDER_DISTANCE, playerPos.y + j - RENDER_DISTANCE));
            }
        }
    }

    public static void render(RenderManager renderManager, Camera camera) {
        for (Chunk i : chunkList) {
            i.render(renderManager, camera);
        }
    }

    public static Chunk getChunk(int x, int z) {
        for (Chunk chunk : chunkList) {
            if (chunk.getX() == x && chunk.getZ() == z) {
                return chunk;
            }
        }
        Chunk newChunk = new Chunk(x, z);
        chunkList.add(newChunk);
        return newChunk;
    }
}
