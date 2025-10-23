package net.maker554.aMCclone.assets;

import net.maker554.aMCclone.utils.Resources;
import net.maker554.aMCclone.utils.TextureCoords;
import renderEngine.models.ObjectLoader;

public class CubeFromAtlas extends Shape {

    private static final ObjectLoader loader = new ObjectLoader();

    private final float height;
    private final float length;
    private final float width;

    public CubeFromAtlas(float height, float length, float width, int blockId) {
        this.height = height;
        this.length = length;
        this.width = width;

        construct(blockId);

        model = loader.loadModel(vertices, textureCoords, indices);
        model.setTexture(Resources.terrainTexture);
    }

    private void construct(int blockId) {

        float x = length / 2;
        float y = height / 2;
        float z = width / 2;

        vertices = new float[] {

                -x, -y, -z,  // 0
                x, -y, -z,   // 1
                x,  y, -z,   // 2
                -x,  y, -z,   // 3

                // Front face
                -x, -y,  z,   // 4
                x, -y,  z,    // 5
                x,  y,  z,    // 6
                -x,  y,  z,    // 7

                // Left face (corrected winding)
                -x,  y,  z,   // 8
                -x,  y, -z,   // 9
                -x, -y, -z,   // 10
                -x, -y,  z,   // 11

                // Right face
                x,  y,  z,   // 12
                x,  y, -z,   // 13
                x, -y, -z,   // 14
                x, -y,  z,   // 15

                // Bottom face (corrected winding)
                -x, -y, -z,   // 16
                x, -y, -z,    // 17
                x, -y,  z,    // 18
                -x, -y,  z,    // 19

                // Top face
                -x,  y, -z,   // 20
                x,  y, -z,    // 21
                x,  y,  z,    // 22
                -x,  y,  z     // 23
        };

        textureCoords = TextureCoords.buildTextureCords(blockId);

        indices = new int[] {
                0, 2, 1,  0, 3, 2,    // back
                4, 5, 6,  4, 6, 7,    // front
                8, 9, 10, 8, 10,11,   // left
                12,14,13, 12,15,14,   // right
                16,17,18, 16,18,19,   // bottom
                20,22,21, 20,23,22    // top
        };
    }
}
