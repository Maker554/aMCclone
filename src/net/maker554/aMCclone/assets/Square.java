package net.maker554.aMCclone.assets;

import net.maker554.aMCclone.utils.Resources;

public class Square extends Shape{

    private final float height;
    private final float width;

    public Square(float height, float width) {
        this.height = height;
        this.width = width;

        construct();

        model = loader.loadModel(vertices, textureCoords, indices);
        model.setTexture(Resources.emptyTexture);
    }

    private void construct() {

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

        textureCoords = new float[] {

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
        };

        indices = new int[] {
                0, 1, 2, 0, 2, 3,   // left
                4, 6, 5, 4, 7, 6,   // right
        };
    }
}
