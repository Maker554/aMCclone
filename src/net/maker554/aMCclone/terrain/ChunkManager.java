package net.maker554.aMCclone.terrain;

import net.maker554.aMCclone.Settings;
import net.maker554.aMCclone.save.SaveManager;
import org.joml.Vector2i;
import org.joml.Vector3i;
import renderEngine.Camera;
import renderEngine.RenderManager;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;

public class ChunkManager {

    private static final int RENDER_DISTANCE =8;

    private static List<Chunk> chunkList;

    public static void loadTerrain(Vector2i playerPos) {

        chunkList = SaveManager.loadWorld();

        if (chunkList == null) {
            chunkList = new ArrayList<>();

            // generate terrain for first time
            for (int i=0; i<RENDER_DISTANCE*2; i++) {
                for (int j=0; j<RENDER_DISTANCE*2; j++) {
                    chunkList.add(new Chunk(playerPos.x + i - RENDER_DISTANCE, playerPos.y + j - RENDER_DISTANCE));
                }
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

    public static List<Chunk> getChunkList() {
        return chunkList;
    }

    public static void setBlock(Vector3i pos, int index) {
        Chunk chunk = getChunk((int) Math.floor((double) pos.x / Settings.CHUNK_SIZE), (int) Math.floor((double) pos.z / Settings.CHUNK_SIZE));
        Vector2i chunkCords = chunk.getChunkCordsFromGlobalCords(pos.x, pos.z);
        chunk.setBlock(chunkCords.x, pos.y, chunkCords.y, (byte) index);
    }

    public static int getBlock(Vector3i pos) {
        Chunk chunk = getChunk((int) Math.floor((double) pos.x / Settings.CHUNK_SIZE), (int) Math.floor((double) pos.z / Settings.CHUNK_SIZE));
        Vector2i chunkCords = chunk.getChunkCordsFromGlobalCords(pos.x, pos.z);
        return chunk.getBlock(chunkCords.x, pos.y, chunkCords.y);
    }
}
