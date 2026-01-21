package net.maker554.aMCclone.terrain;

import net.maker554.aMCclone.Settings;
import net.maker554.aMCclone.save.SaveManager;
import net.maker554.aMCclone.terrain.features.Tree;
import net.maker554.aMCclone.terrain.features.UnaddedFeature;
import org.joml.Vector2i;
import org.joml.Vector3i;
import renderEngine.Camera;
import renderEngine.RenderManager;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

import static java.lang.Math.round;
import static net.maker554.aMCclone.terrain.TerrainGeneration.fractalNoise;

public class ChunkManager {

    private static final int RENDER_DISTANCE = 3;
    private static final int TREE_DENSITY = 8;

    private static ConcurrentHashMap<Vector2i, Chunk> chunkMap;
    private static ConcurrentLinkedDeque<UnaddedFeature> unaddedFeaturesList = new ConcurrentLinkedDeque<>();
    private static List<Vector2i> queuedChunks = new ArrayList<>();

    public static void loadTerrain(Vector2i playerPos) {

        chunkMap = SaveManager.loadWorldMap();

        if (chunkMap == null) {
            chunkMap = new ConcurrentHashMap<>();

            System.out.println("generating terrain...");
            // generate terrain for the first time
            for (int i=0; i<RENDER_DISTANCE*2; i++) {
                for (int j=0; j<RENDER_DISTANCE*2; j++) {
                    Vector2i pos = new Vector2i(playerPos.x + i - RENDER_DISTANCE, playerPos.y + j - RENDER_DISTANCE);
                    chunkMap.put(pos, new Chunk(pos.x, pos.y));
                }
            }

            // add features
            System.out.println("placing down trees...");
            for (Chunk chunk : chunkMap.values()) {
                generateTrees(chunk);
            }
            placeUnnamedFeatures();// generate the trees and update the model

            System.out.println("updating models...");
            for (Chunk chunk : chunkMap.values()) {
                chunk.updateModel(chunk.buildMesh()); // finally, generate the mesh and model for all chunks
            }
            System.out.println("done!");
        } else {

            System.out.println("loaded terrain...");
            placeUnnamedFeatures(); // place trees and update the model
        }
    }

    public static void generateTerrain(Vector2i center) {
        for (int i=-RENDER_DISTANCE; i < RENDER_DISTANCE; i++)
            for (int j=-RENDER_DISTANCE; j < RENDER_DISTANCE; j++) {

                Vector2i pos = new Vector2i(center).add(i, j); // get position of the chunk to check

                if (!chunkMap.containsKey(pos) && !queuedChunks.contains(pos)) {
                    System.out.println(pos);
                    // empty chunk object to fill the key so the generation for the chunks does not start again while in progress;
                    queuedChunks.add(pos);
                    generateChunk(pos.x, pos.y);
                }
            }
    }

    private static void placeUnnamedFeatures() {

        // we use an iterator to be able to remove items from the list while looping it
            Iterator<UnaddedFeature> iterator = unaddedFeaturesList.iterator();
            while(iterator.hasNext()) {
                UnaddedFeature unaddedFeature = iterator.next();
                if (chunkMap.containsKey(unaddedFeature.chunkPos)) {
                    Chunk chunk = chunkMap.get(unaddedFeature.chunkPos);
                    TerrainGeneration.placeFeature(unaddedFeature, chunk);
                    iterator.remove();
                }
            }
    }

    public static void render(RenderManager renderManager, Camera camera) {

        if(chunkMap == null) return;

        for (Chunk i : chunkMap.values()) {
            i.render(renderManager, camera);
        }
        for (Chunk i : chunkMap.values()) {
            i.renderGlass(renderManager, camera);
        }
    }

    public static Chunk getChunk(int x, int z) {

        Vector2i pos = new Vector2i(x, z);
        if (chunkMap.containsKey(pos))
            return chunkMap.get(pos);

        generateChunk(x, z);
        return null;
    }

    public static void generateChunk(int x, int z) {
        GenerateChunk generateChunk = new GenerateChunk(x, z);
        new Thread(generateChunk).start();
    }

    public static List<Chunk> getChunkList() {
        return chunkMap.values().stream().toList();
    }

    public static List<UnaddedFeature> getUnaddedFeatures() {
        return unaddedFeaturesList.stream().toList();
    }

    public static void setUnaddedFeatures(ConcurrentLinkedDeque<UnaddedFeature> unaddedFeatures) {
        unaddedFeaturesList = unaddedFeatures;
    }

    public static void setBlock(Vector3i pos, int index) {
        Chunk chunk = getChunk((int) Math.floor((double) pos.x / Settings.CHUNK_SIZE), (int) Math.floor((double) pos.z / Settings.CHUNK_SIZE));
        if (chunk == null) return;
        Vector2i chunkCords = chunk.getChunkCordsFromGlobalCords(pos.x, pos.z);
        chunk.setBlock(chunkCords.x, pos.y, chunkCords.y, (byte) index);
    }

    public static void setBlockUnloaded(Vector3i pos, int index) {
        Chunk chunk = getChunk((int) Math.floor((double) pos.x / Settings.CHUNK_SIZE), (int) Math.floor((double) pos.z / Settings.CHUNK_SIZE));
        if (chunk == null) return;
        Vector2i chunkCords = chunk.getChunkCordsFromGlobalCords(pos.x, pos.z);
        chunk.setBlockUnloaded(chunkCords.x, pos.y, chunkCords.y, (byte) index);
    }

    public static int getBlock(Vector3i pos) {
        Chunk chunk = getChunk((int) Math.floor((double) pos.x / Settings.CHUNK_SIZE), (int) Math.floor((double) pos.z / Settings.CHUNK_SIZE));
        if (chunk == null) return 0;
        Vector2i chunkCords = chunk.getChunkCordsFromGlobalCords(pos.x, pos.z);
        return chunk.getBlock(chunkCords.x, pos.y, chunkCords.y);
    }

    public static void generateTrees(Chunk chunk) {
        // set random for tree generation
        Random random = new Random(TerrainGeneration.getSeed() + (chunk.getX() * 341873128712L) + (chunk.getZ() * 132897987541L));

        for (int j=0; j < TREE_DENSITY; j++) {

            int inx = random.nextInt(0, Settings.CHUNK_SIZE);
            int inz = random.nextInt(0, Settings.CHUNK_SIZE);
            float columnHeight = fractalNoise(chunk.getGlobalCordsFromChunkCords(inx, inz));

            Tree tree = new Tree(new Vector3i(inx, (int) Math.floor(columnHeight) + 1, inz));
            TerrainGeneration.placeFeature(tree, chunk);

            List<Vector2i> nearPos = Arrays.asList( // all positions of adjacent chunks where the features could overflow into
                    new Vector2i(-1, -1),
                    new Vector2i(-1, 0),
                    new Vector2i(-1, 1),
                    new Vector2i(0, 1),
                    new Vector2i(0, -1),
                    new Vector2i(1, -1),
                    new Vector2i(1, 0),
                    new Vector2i(1, 1)
            );

            for (Vector2i pos : nearPos) {
                unaddedFeaturesList.add(new UnaddedFeature(tree, chunk, pos));
            }
        }
    }

    public static ConcurrentHashMap<Vector2i, Chunk> getChunkMap() {
        return chunkMap;
    }

    public static ConcurrentLinkedDeque<UnaddedFeature> getUnaddedFeaturesList() {
        return unaddedFeaturesList;
    }

    public static List<Vector2i> getQueuedChunks() {
        return queuedChunks;
    }
}
