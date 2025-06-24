package net.maker554.aMCclone.assets;

import net.maker554.aMCclone.utils.Resources;
import renderEngine.models.ObjectLoader;

public class Cube extends Shape {

    private static final ObjectLoader loader = new ObjectLoader();

    private final float height;
    private final float length;
    private final float width;

    public Cube(float height, float length, float width) {
        this.height = height;
        this.length = length;
        this.width = width;

        construct();

        model = loader.loadModel(vertices, textureCoords, indices);
        model.setTexture(Resources.emptyTexture);
    }

    private void construct() {

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

        textureCoords = new float[] {
                // Back face (corrected)
                1.0f, 0.0f  ,
                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f,

                // Front face
                0.0f, 0.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,
                0.0f, 1.0f,

                // Left face (corrected)
                1.0f, 0.0f,
                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f,

                // Right face
                0.0f, 0.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,
                0.0f, 1.0f,

                // Bottom face (corrected)
                0.0f, 1.0f,
                1.0f, 1.0f,
                1.0f, 0.0f,
                0.0f, 0.0f,

                // Top face
                0.0f, 1.0f,
                1.0f, 1.0f,
                1.0f, 0.0f,
                0.0f, 0.0f
        };


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
