package net.maker554.aMCclone.assets;

import net.maker554.aMCclone.utils.Resources;
import net.maker554.aMCclone.utils.TextureCoords;

public class SquareFromAtlas extends Shape {

    private final float height;
    private final float width;

    public SquareFromAtlas(float height, float width, int blockId) {
        this.height = height;
        this.width = width;

        construct(blockId);

        model = loader.loadModel(vertices, textureCoords, indices);
        model.setTexture(Resources.terrainTexture);
    }

    private void construct(int blockId) {

        float y = height / 2;
        float z = width / 2;
        float x = 0;

        vertices = new float[] {

                // Left face
                -x,  y,  z,   // 8
                -x,  y, -z,   // 9
                -x, -y, -z,   // 10
                -x, -y,  z,   // 11

                // Right face
                x,  y,  z,   // 12
                x,  y, -z,   // 13
                x, -y, -z,   // 14
                x, -y,  z,   // 15
        };

        textureCoords = TextureCoords.buildTextureCords(blockId);

        indices = new int[] {
                0, 1, 2, 0, 2, 3,   // left
                4, 6, 5, 4, 7, 6,   // right
        };
    }
}
