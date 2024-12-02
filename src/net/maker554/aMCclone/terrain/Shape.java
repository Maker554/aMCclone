package net.maker554.aMCclone.terrain;

import renderEngine.models.ObjectLoader;
import renderEngine.models.Texture;


public class Shape {

    public static float[] vertices = new float[] {
            // Front face
            -0.5f, -0.5f,  0.5f, // Bottom-left
            0.5f, -0.5f,  0.5f, // Bottom-right
            0.5f,  0.5f,  0.5f, // Top-right
            -0.5f,  0.5f,  0.5f, // Top-left

            // Back face
            -0.5f, -0.5f, -0.5f, // Bottom-left
            0.5f, -0.5f, -0.5f, // Bottom-right
            0.5f,  0.5f, -0.5f, // Top-right
            -0.5f,  0.5f, -0.5f, // Top-left

            // Left face
            -0.5f, -0.5f, -0.5f,
            -0.5f, -0.5f,  0.5f,
            -0.5f,  0.5f,  0.5f,
            -0.5f,  0.5f, -0.5f,

            // Right face
            0.5f, -0.5f, -0.5f,
            0.5f, -0.5f,  0.5f,
            0.5f,  0.5f,  0.5f,
            0.5f,  0.5f, -0.5f,

            // Top face
            -0.5f,  0.5f, -0.5f,
            0.5f,  0.5f, -0.5f,
            0.5f,  0.5f,  0.5f,
            -0.5f,  0.5f,  0.5f,

            // Bottom face
            -0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, -0.5f,
            0.5f, -0.5f,  0.5f,
            -0.5f, -0.5f,  0.5f,
    };

    public static int[] indices = new int[]{
            // Front face
            0, 1, 2,
            0, 2, 3,
            // Back face
            4, 6, 5,
            4, 7, 6,
            // Left face
            8, 9, 10,
            8, 10, 11,
            // Right face
            12, 14, 13,
            12, 15, 14,
            // Top face
            16, 18, 17,
            16, 19, 18,
            // Bottom face
            20, 21, 22,
            20, 22, 23
    };

    public static Texture terrainTexture;

    public static void init() throws Exception {

        ObjectLoader loader = new ObjectLoader();

        terrainTexture = new Texture(loader.loadTexture("terrain.png"));
    };
}
