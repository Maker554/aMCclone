package net.maker554.aMCclone.terrain;

import net.maker554.aMCclone.Settings;
import net.maker554.aMCclone.utils.ArrayManager;
import net.maker554.aMCclone.utils.Resources;
import org.joml.Vector2i;
import org.joml.Vector3f;
import renderEngine.Camera;
import renderEngine.RenderManager;
import renderEngine.models.Entity;
import renderEngine.models.Model;
import renderEngine.models.ObjectLoader;

import java.util.Random;

public class Chunk {

    private byte[] data = new byte[Settings.CHUNK_SIZE *Settings.CHUNK_SIZE*Settings.CHUNK_SIZE];

    private static ObjectLoader loader;

    private int x, z;

    public Chunk(int x, int z) {
        loader = new ObjectLoader();
        this.x = x;
        this.z = z;
        System.out.println(this.x);
        System.out.println(this.z);
        generateChunk();
    }

    private void generateChunk() {

        int terrainHeight = 2;

        data = TerrainGeneration.generateTerrain(x, z);
    }

    public void render(RenderManager renderManager, Camera camera) {
        Model model = loader.loadModel(ArrayManager.generateChunkVertices(data), ArrayManager.generateChunkTextureCords(data), ArrayManager.generateChunkIndices(data));
        model.setTexture(Resources.terrainTexture);

        Entity entity = new Entity(model, new Vector3f(x*Settings.CHUNK_SIZE,0,z*Settings.CHUNK_SIZE), new Vector3f(0, 0, 0), 1);

        renderManager.render(entity, camera);
    }

    private Vector3f getGlobalCordsFromChunkCords(int inx, int iny, int inz) {
        return new Vector3f(
                inx + this.x * Settings.CHUNK_SIZE,
                iny,
                inz + this.z * Settings.CHUNK_SIZE
        );
    }
    private Vector2i getGlobalCordsFromChunkCords(int inx, int inz) {
        return new Vector2i(
                (inx + this.x * Settings.CHUNK_SIZE),
                (inz + this.z * Settings.CHUNK_SIZE)
        );
    }
}
