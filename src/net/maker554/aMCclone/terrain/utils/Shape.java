package net.maker554.aMCclone.terrain.utils;

import renderEngine.models.ObjectLoader;
import renderEngine.models.Texture;


public class Shape {

    public static float[] vertices = new float[] {
            // Front face SUD
            -0.5f, -0.5f,  0.5f, // Bottom-left
            0.5f, -0.5f,  0.5f, // Bottom-right
            0.5f,  0.5f,  0.5f, // Top-right
            -0.5f,  0.5f,  0.5f, // Top-left

            // Back face NORD
            -0.5f, -0.5f, -0.5f, // Bottom-left
            0.5f, -0.5f, -0.5f, // Bottom-right
            0.5f,  0.5f, -0.5f, // Top-right
            -0.5f,  0.5f, -0.5f, // Top-left

            // Left face WEST
            -0.5f, -0.5f, -0.5f,
            -0.5f, -0.5f,  0.5f,
            -0.5f,  0.5f,  0.5f,
            -0.5f,  0.5f, -0.5f,

            // Right face EST
            0.5f, -0.5f, -0.5f,
            0.5f, -0.5f,  0.5f,
            0.5f,  0.5f,  0.5f,
            0.5f,  0.5f, -0.5f,

            // Top face UP
            -0.5f,  0.5f, -0.5f,
            0.5f,  0.5f, -0.5f,
            0.5f,  0.5f,  0.5f,
            -0.5f,  0.5f,  0.5f,

            // Bottom face BOTTOM
            -0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, -0.5f,
            0.5f, -0.5f,  0.5f,
            -0.5f, -0.5f,  0.5f,
    };

    public static int[] indices = new int[]{
            // Front face SUD
            0, 1, 2,
            0, 2, 3,
            // Back face NORD
            4, 6, 5,
            4, 7, 6,
            // Left face WEST
            8, 9, 10,
            8, 10, 11,
            // Right face EST
            12, 14, 13,
            12, 15, 14,
            // Top face UP
            16, 18, 17,
            16, 19, 18,
            // Bottom face DOWN
            20, 21, 22,
            20, 22, 23
    };

    public static Texture terrainTexture;

    public static void init() throws Exception {

        ObjectLoader loader = new ObjectLoader();

        terrainTexture = new Texture(loader.loadTexture("terrain.png"));
    };
}
