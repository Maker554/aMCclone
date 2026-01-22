package net.maker554.aMCclone.terrain;

import net.maker554.aMCclone.Client;
import net.maker554.aMCclone.terrain.features.UnaddedFeature;
import org.joml.Vector2i;
import renderEngine.WindowManager;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class GenerateChunk implements Runnable {

    int x, z;
    WindowManager windowManager;

    public GenerateChunk(int x, int z) {
        this.x = x;
        this.z = z;
        windowManager = Client.getWindow();
    }

    @Override
    public void run() {
        Chunk newChunk = new Chunk(x, z);
        ChunkManager.generateTrees(newChunk); // generate all trees
        // save the chunk in the chunkMap
        ChunkManager.getChunkMap().put(new Vector2i(x, z), newChunk);

        // generate unadded features linked to their chunk
        HashMap<Chunk, MeshData> editedChunks = generateUnaddedFeatures();

        // done after generating unadded features to include them
        MeshData mesh = newChunk.buildMesh(); // at first, we only build the mesh, then we create the model in the main thread

        // update the model on the main thread by sending a new task there
        WindowManager.runOnMainThread(() -> {
            // update model of the other edited chunks
            for (Chunk chunk : editedChunks.keySet()) {
                chunk.updateModel(editedChunks.get(chunk));
            }
            // update model of the new Chunk
            newChunk.updateModel(mesh);

            // chunk is freed from queue for future updates
            ChunkManager.getQueuedChunks().remove(new Vector2i(x, z));
        });
    }

    // same off placeUnaddedFeatures in ChunkManager, but we save chunks in a map with their mesh to return,
    // this is so that we can then pass the map into the main thread to update only the model while building the chunks in this thread
    public HashMap<Chunk, MeshData> generateUnaddedFeatures() {

        HashMap<Chunk, MeshData> editedChunks = new HashMap<>(); // list to return at the end

        // get chunkMap and UnaddedFeatures
        ConcurrentLinkedDeque<UnaddedFeature> unaddedFeaturesList = ChunkManager.getUnaddedFeaturesList();
        ConcurrentHashMap<Vector2i, Chunk> chunkMap = ChunkManager.getChunkMap();

        // we use an iterator to be able to remove items from the list while looping it
        for (UnaddedFeature unaddedFeature : unaddedFeaturesList) {
            if (chunkMap.containsKey(unaddedFeature.chunkPos)) {
                Chunk chunk = chunkMap.get(unaddedFeature.chunkPos);
                TerrainGeneration.placeFeature(unaddedFeature, chunk);
                unaddedFeaturesList.remove(unaddedFeature);

                // put the chunks in the map with empty mesh
                editedChunks.put(chunk, null);
            }
        }
        // finally, we build the mesh for each edited chunk
        editedChunks.replaceAll((chunk, mesh) -> chunk.buildMesh());

        return editedChunks;
    }
}
