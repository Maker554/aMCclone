package net.maker554.aMCclone.terrain;

import net.maker554.aMCclone.terrain.utils.Shape;
import net.maker554.aMCclone.terrain.utils.TextureCoords;
import org.joml.Vector3f;
import renderEngine.models.Entity;
import renderEngine.models.ObjectLoader;

public class Block {

    private Entity entity;
    private Vector3f position;

    public Block(Vector3f position, int type) {
        this.position = position;

        ObjectLoader loader = new ObjectLoader();

        entity = new Entity(
                loader.loadModel(Shape.vertices, TextureCoords.getTerrainTextureCords(4, 1, 1, 1, 3, 1), Shape.indices),
                position,
                new Vector3f(0,0,0),
                1.0f);

        entity.getModel().setTexture(Shape.terrainTexture);
    }

    public Entity getEntity() {
        return entity;
    }
}
