package net.maker554.aMCclone.terrain;

import org.joml.Vector3f;
import org.lwjgl.system.MemoryUtil;
import renderEngine.Camera;
import renderEngine.RenderManager;
import renderEngine.models.Entity;
import renderEngine.models.ObjectLoader;


import java.util.Random;

public class Chunk {

    private static final int CHUNK_SIZE = 8;

    private static int[] data = new int[CHUNK_SIZE*CHUNK_SIZE*CHUNK_SIZE];

    private static ObjectLoader loader;
    private static Camera camera;

    public Chunk(int x, int y) {
        loader = new ObjectLoader();
        camera = new Camera();
        generateChunk();
    }

    private void generateChunk() {

        Random a = new Random();

        for(int i = 0; i < CHUNK_SIZE; i++) {
            for (int j = 0; j < CHUNK_SIZE; j++) {
                for (int k = 0; k < CHUNK_SIZE; k++)
                    data[transformDataIndex(i,j,k)] = (int) (a.nextInt() % 20);
            }
        }
    }

    public static void render(RenderManager renderManager, Camera camera) {
        Entity entity;
        for(int i = 0; i < CHUNK_SIZE; i++)
            for(int j = 0; j < CHUNK_SIZE; j++)
                for(int k = 0; k < CHUNK_SIZE; k++)
                    if(data[transformDataIndex(i,j,k)] == 0) {

                        entity = new Entity(
                                loader.loadModel(Shape.vertices, TextureCoords.getTerrainTextureCoords(4, 1, 1, 1, 3, 1), Shape.indices),
                                new Vector3f(i, j, k),
                                new Vector3f(0, 0, 0),
                                1.0f);
                        entity.getModel().setTexture(Shape.terrainTexture);

                        renderManager.render(entity, camera);

                        entity = null;
                    }
    }

    private static int transformDataIndex(int x, int y, int z) {
        return (x*CHUNK_SIZE + y)*CHUNK_SIZE + z;
    }
}
