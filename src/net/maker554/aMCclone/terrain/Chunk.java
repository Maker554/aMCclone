package net.maker554.aMCclone.terrain;

import net.maker554.aMCclone.Settings;
import net.maker554.aMCclone.utils.ArrayManager;
import net.maker554.aMCclone.utils.Resources;
import org.joml.Vector2i;
import org.joml.Vector3f;
import org.joml.Vector3i;
import renderEngine.Camera;
import renderEngine.RenderManager;
import renderEngine.models.Entity;
import renderEngine.models.Model;
import renderEngine.models.ObjectLoader;

import java.util.Random;

public class Chunk {

    private byte[] data;

    private static ObjectLoader loader;

    private Entity entity;

    private final int x, z;

    public Chunk(int x, int z) {
        loader = new ObjectLoader();
        this.x = x;
        this.z = z;

        generateChunk();

        updateChunkModel();
    }

    private void generateChunk() {

        data = TerrainGeneration.generateTerrain(x, z);
    }

    private void updateChunkModel() {
        Model model = loader.loadModel(ArrayManager.generateChunkVertices(data), ArrayManager.generateChunkTextureCords(data), ArrayManager.generateChunkIndices(data));
        model.setTexture(Resources.terrainTexture);

        entity = new Entity(model, new Vector3f(x*Settings.CHUNK_SIZE,0,z*Settings.CHUNK_SIZE), new Vector3f(0, 0, 0), 1);
    }

    public void render(RenderManager renderManager, Camera camera) {

        if(entity != null)
            renderManager.render(entity, camera);
        else
            System.out.println("entity is null");
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

    public Vector2i getChunkCordsFromGlobalCords(int inx, int inz) {
        Vector2i cords = new Vector2i(inx % Settings.CHUNK_SIZE, inz % Settings.CHUNK_SIZE);
        if (cords.x < 0)
            cords.x += Settings.CHUNK_SIZE;
        if (cords.y < 0)
            cords.y += Settings.CHUNK_SIZE;
        return cords;
    }

    public void setBlock(int cordX, int cordY, int cordZ, byte index) {

        if(checkIfCordsAreOutOfChunkSize(cordX, cordY, cordZ))
            return;

        data[ArrayManager.transformDataIndex(cordX, cordY, cordZ)] = index;
        updateChunkModel();
    }

    public byte getBlock(int cordX, int cordY, int cordZ) {

        if(checkIfCordsAreOutOfChunkSize(cordX, cordY, cordZ))
            return 0;

        return data[ArrayManager.transformDataIndex(cordX, cordY, cordZ)];
    }

    public byte getBlock(Vector3i pos) {

        if(checkIfCordsAreOutOfChunkSize(pos.x, pos.y, pos.z))
            return 0;

        return data[ArrayManager.transformDataIndex(pos.x, pos.y, pos.z)];
    }

    private boolean checkIfCordsAreOutOfChunkSize(int cordX, int cordY, int cordZ) {
        boolean outOfChunkSizeX = cordX < 0 || cordX >= Settings.CHUNK_SIZE;
        boolean outOfChunkSizeY = cordY < 0 || cordY >= Settings.CHUNK_HEIGHT;
        boolean outOfChunkSizeZ = cordZ < 0 || cordZ >= Settings.CHUNK_SIZE;

        return outOfChunkSizeX || outOfChunkSizeY || outOfChunkSizeZ;
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    public byte[] getData() {
        return data.clone();
    }
}
