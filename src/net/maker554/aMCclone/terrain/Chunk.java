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

public class Chunk {

    private byte[] data;

    private static ObjectLoader loader = new ObjectLoader();

    private Entity entity;
    private Entity glassEntity;

    private final int x, z;

    public Chunk(int x, int z) { // for generating new terrain
        this.x = x;
        this.z = z;

        generateChunk();
    }

    // creates an empty Chunk object
    public Chunk() {
        x = 0;
        z = 0;
    }

    public Chunk(int x, int z, byte[] data) { // for loading terrain
        loader = new ObjectLoader();
        this.x = x;
        this.z = z;

        this.data = data;

        updateModel(buildMesh());
    }

    private void generateChunk() {
        data = TerrainGeneration.generateTerrain(x, z);
    }

    public void updateModel(MeshData mesh) {
        Model model = loader.loadModel(mesh.vertices, mesh.textureCoords, mesh.indices);
        Model glassModel = loader.loadModel(mesh.verticesT, mesh.textureCoordsT, mesh.indicesT);
        model.setTexture(Resources.terrainTexture);
        glassModel.setTexture(Resources.terrainTexture);

        entity = new Entity(model, new Vector3f(x*Settings.CHUNK_SIZE,0,z*Settings.CHUNK_SIZE), new Vector3f(0, 0, 0), 1);
        glassEntity = new Entity(glassModel, new Vector3f(x*Settings.CHUNK_SIZE,0,z*Settings.CHUNK_SIZE), new Vector3f(0, 0, 0), 1);
    }

    // only generate the mesh without creating the model for rendering
    public MeshData buildMesh() {
        return new MeshData(
                ArrayManager.generateChunkVertices(data),
                ArrayManager.generateChunkTextureCords(data),
                ArrayManager.generateChunkIndices(data),
                ArrayManager.generateChunkVerticesTransparent(data),
                ArrayManager.generateChunkTextureCordsTransparent(data),
                ArrayManager.generateChunkIndicesTransparent(data)
        );
    }

    public void render(RenderManager renderManager, Camera camera) {
        if(entity != null)
            renderManager.render(entity, camera);
    }

    public void renderGlass(RenderManager renderManager, Camera camera) {
        if(glassEntity != null)
            renderManager.render(glassEntity, camera);
    }

    private Vector3f getGlobalCordsFromChunkCords(int inx, int iny, int inz) {
        return new Vector3f(
                inx + this.x * Settings.CHUNK_SIZE,
                iny,
                inz + this.z * Settings.CHUNK_SIZE
        );
    }
    public Vector2i getGlobalCordsFromChunkCords(int inx, int inz) {
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
        updateModel(buildMesh());
    }

    public void setBlockUnloaded(int cordX, int cordY, int cordZ, byte index) {

        if(checkIfCordsAreOutOfChunkSize(cordX, cordY, cordZ))
            return;

        data[ArrayManager.transformDataIndex(cordX, cordY, cordZ)] = index;
    }

    public void setBlockUnloadedNoOverride(int cordX, int cordY, int cordZ, byte index) {

        if(checkIfCordsAreOutOfChunkSize(cordX, cordY, cordZ))
            return;

        if (getBlock(new Vector3i(cordX, cordY, cordZ)) != 0) return; // only replace blocks that are air

        data[ArrayManager.transformDataIndex(cordX, cordY, cordZ)] = index;
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
