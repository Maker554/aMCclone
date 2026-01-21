package net.maker554.aMCclone.save;

import net.maker554.aMCclone.player.Player;
import net.maker554.aMCclone.terrain.Chunk;
import net.maker554.aMCclone.terrain.ChunkManager;
import net.maker554.aMCclone.terrain.features.FeatureBlock;
import net.maker554.aMCclone.terrain.features.UnaddedFeature;
import org.joml.Vector2i;
import org.joml.Vector3f;
import org.joml.Vector3i;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class SaveManager {

    private static int counter = 0;

    public static void saveWorld(List<Chunk> chunkList) {

        List<byte[]> chunkDataList = new ArrayList<>();
        List<int[]> chunkIndexList = new ArrayList<>();
        List<List<Vector3i>> unaddedFeatureDataPosList = new ArrayList<>();
        List<List<Byte>> unaddedFeatureDataList = new ArrayList<>();
        List<Vector2i> unaddedFeaureChunkPosList = new ArrayList<>();
        List<Vector3i> unaddedFeatureLocalPosList = new ArrayList<>();

        for (Chunk chunk : chunkList) {
            chunkDataList.add(chunk.getData());
            chunkIndexList.add(new int[]{chunk.getX(), chunk.getZ()});
        }

        // packing data of all features
        for (UnaddedFeature unaddedFeature : ChunkManager.getUnaddedFeatures()) {
            List<Vector3i> dataPosList = new ArrayList<>();
            List<Byte> dataList = new ArrayList<>();
            // packing data for a feature
            for (FeatureBlock block : unaddedFeature.blocks) {
                dataPosList.add(block.location);
                dataList.add(block.id);
            }

            // adding packed data off the feature to the list for all features
            unaddedFeatureDataPosList.add(dataPosList);
            unaddedFeatureDataList.add(dataList);
            // adding rest of data for the specific feature
            unaddedFeaureChunkPosList.add(unaddedFeature.chunkPos);
            unaddedFeatureLocalPosList.add(unaddedFeature.getPosition());
        }

        List<Object> level = Arrays.asList(
                chunkDataList,
                chunkIndexList,
                unaddedFeatureDataPosList,
                unaddedFeatureDataList,
                unaddedFeaureChunkPosList,
                unaddedFeatureLocalPosList
        );

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("level.dat"))) {
            out.writeObject(level);
        } catch (IOException _) {}
    }

    public static ConcurrentHashMap<Vector2i, Chunk> loadWorldMap() {
        ConcurrentHashMap<Vector2i, Chunk> chunkMap = new ConcurrentHashMap<>();
        List<Object> level = new ArrayList<>();

        // load chunks and cordinates
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("level.dat"))) {
            level = (List<Object>) in.readObject();
        } catch (IOException | ClassNotFoundException _) {}

        if (level == null || level.size() < 6) return null;

        List<byte[]> chunkDataList = (List<byte[]>) level.getFirst();
        List<int[]> chunkIndexList = (List<int[]>) level.get(1);
        List<List<Vector3i>> unaddedFeatureDataPosList = (List<List<Vector3i>>) level.get(2);
        List<List<Byte>> unaddedFeatureDataList = (List<List<Byte>>) level.get(3);
        List<Vector2i> unaddedFeaureChunkPosList = (List<Vector2i>) level.get(4);
        List<Vector3i> unaddedFeatureLocalPosList = (List<Vector3i>) level.get(5);

        // generate unaddedFeatures
        ConcurrentLinkedDeque<UnaddedFeature> unaddedFeatures = new ConcurrentLinkedDeque<>();

        for (int i = 0; i < unaddedFeaureChunkPosList.size(); i++) {

            // create the blocklist with position and data
            List<FeatureBlock> blocks = new ArrayList<>();
            Iterator<Byte> iterator = unaddedFeatureDataList.get(i).iterator(); // iterator to cycle both dataPos and data in the same loop

            for (Vector3i dataPos : unaddedFeatureDataPosList.get(i)) {
                // add the block to the list
                 blocks.add(new FeatureBlock(iterator.next(), dataPos));
            }

            unaddedFeatures.add(new UnaddedFeature(blocks, unaddedFeatureLocalPosList.get(i), unaddedFeaureChunkPosList.get(i)));
        }

        // set the loaded unadded features in the chunk manager
        ChunkManager.setUnaddedFeatures(unaddedFeatures);

        // crate chunkMap form loaded data
        if (!chunkDataList.isEmpty() && chunkIndexList.size() == chunkDataList.size()) {
            for (int i = 0; i < chunkDataList.size(); i++) {
                int[] position = chunkIndexList.get(i);
                chunkMap.put(new Vector2i(position[0], position[1]), new Chunk(position[0], position[1], chunkDataList.get(i)));
            }
            return chunkMap;
        }
        return null;
    }

    public static void savePlayer(Player player) {

        List<Vector3f> pack = new ArrayList<>();
        pack.add(player.getPosition());
        pack.add(player.getCamera().getRotation());

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("player.dat"))) {
            out.writeObject(pack);
        } catch (IOException _) {}
    }

    public static Player loadPlayer() {

        Player player = new Player();
        List<Vector3f> pack = new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("player.dat"))) {
            pack = (List<Vector3f>) in.readObject();
        } catch (IOException | ClassNotFoundException _) {}

        if (!pack.isEmpty()) {
            player.setPosition(pack.getFirst());
            player.getCamera().setRotation(pack.get(1));
        } else {
            player.movePosition(0, -10000, 0);
        }
        return player;
    }

    public static void saveData(long seed) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.dat"))) {
            out.writeObject(seed);
        } catch (IOException _) {}
    }

    public static long loadData() {
        long seed = 0;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.dat"))) {
             seed = (long) in.readObject();
        } catch (IOException | ClassNotFoundException _) {}

        return seed;
    }

    public static void autoSave(Player player, List<Chunk> chunkList) {
        counter++;
        if (counter > 2000) {
            counter = 0;
            saveWorld(chunkList);
            savePlayer(player);
        }
    }
}
