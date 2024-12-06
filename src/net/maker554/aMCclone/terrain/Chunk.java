package net.maker554.aMCclone.terrain;

import net.maker554.aMCclone.Settings;
import net.maker554.aMCclone.utils.ArrayManager;
import net.maker554.aMCclone.utils.Resources;
import net.maker554.aMCclone.utils.Utils;
import org.joml.Vector3f;
import renderEngine.Camera;
import renderEngine.RenderManager;
import renderEngine.models.Entity;
import renderEngine.models.Model;
import renderEngine.models.ObjectLoader;

import java.util.Random;

public class Chunk {

    private static final byte[] data = new byte[Settings.CHUNK_SIZE *Settings.CHUNK_SIZE*Settings.CHUNK_SIZE];

    private static ObjectLoader loader;

    private int x, z;

    public Chunk(int x, int z) {
        loader = new ObjectLoader();
        generateChunk();
        this.x = x;
        this.z = z;
    }

    private void generateChunk() {

        Random a = new Random();

        boolean temp = true;

        for(byte i = 0; i < Settings.CHUNK_SIZE; i++) {
            for (byte j = 0; j < Settings.CHUNK_SIZE; j++){
                for (byte k = 0; k < Settings.CHUNK_SIZE; k++) {

                        byte sus = (byte) Utils.randint(1, 7);
                        if (sus > 3) sus = 7;
                        data[ArrayManager.transformDataIndex(i, j, k)] = sus;
                }
                temp = !temp;
            }
            temp = !temp;
        }
    }

    public void render(RenderManager renderManager, Camera camera) {
        Model model = loader.loadModel(ArrayManager.generateChunkVertices(data), ArrayManager.generateChunkTextureCords(data), ArrayManager.generateChunkIndices(data));
        model.setTexture(Resources.terrainTexture);

        Entity entity = new Entity(model, new Vector3f(x*Settings.CHUNK_SIZE,0,z*Settings.CHUNK_SIZE), new Vector3f(0, 0, 0), 1);

        renderManager.render(entity, camera);
    }
}
