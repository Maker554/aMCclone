package net.maker554.aMCclone.utils;

import java.util.*;

public class TextureCoords {

    public static final Map<Integer, int[]> textureMappings  = new HashMap<>();
    public static final List<Integer> transparentBlockIds = new ArrayList<>();

    public static void init() {

        textureMappings.put(1, new int[] {4, 1, 1, 1, 3, 1});                        // grass block
        textureMappings.put(2, new int[] {3, 4});                                    // diamond block
        textureMappings.put(3, new int[] {1, 2});                                    // cobblestone
        textureMappings.put(4, textureMap(2, 1));                             // stone block
        textureMappings.put(5, textureMap(3, 1));                             // dirt
        textureMappings.put(6, textureMap(9, 2));                             // diamond
        textureMappings.put(7, new int[] {2, 4});                                    // glass
        textureMappings.put(8, textureMap(13, 4, 12, 3, 5, 1)); // crafting table
        textureMappings.put(9, textureMap(5, 1));                             // planks
        textureMappings.put(10, textureMap(5,2, 6, 2));                // log
        textureMappings.put(11, textureMap(5, 4));                            // leaves
        textureMappings.put(12, textureMap(8, 1));                            // bricks
        textureMappings.put(13, textureMap(9, 1, 10, 1, 11, 1));// tnt
        textureMappings.put(14, textureMap(3, 2));                            // sand
        textureMappings.put(15, textureMap(4, 2));                            // gravel
        textureMappings.put(16, textureMap(4, 3, 5, 1));               // bookshelf
        textureMappings.put(17, textureMap(6, 3));                            // obsidian
        textureMappings.put(18, textureMap(1, 4));                            // sponge

        for (int i=1; i<=16; i++) {
            textureMappings.put(18+ i, textureMap(i, 5));                        // all wool colors
        }

        transparentBlockIds.add(7);
        transparentBlockIds.add(11);
    }

    private static float[] getTerrainTextureCords(int x, int y) {
        float y0 = 0.0625f * (y - 1); // 0
        float y1 = 0.0625f * y;       // 1
        float x0 = 0.0625f * (x - 1); // 0
        float x1 = 0.0625f * x;       // 1

        return new float[] {
                x0, y1,
                x1, y1,
                x1, y0,
                x0, y0,

                x0, y1,
                x1, y1,
                x1, y0,
                x0, y0,

                x0, y1,
                x1, y1,
                x1, y0,
                x0, y0,

                x0, y1,
                x1, y1,
                x1, y0,
                x0, y0,

                x0, y1,
                x1, y1,
                x1, y0,
                x0, y0,

                x0, y1,
                x1, y1,
                x1, y0,
                x0, y0,
        };
    }

    private static float[] getTerrainTextureCords(int sideX, int sideY, int topX, int topY) {
        float sY0 = 0.0625f * (sideY - 1); // 0
        float sY1 = 0.0625f * sideY;       // 1
        float sX0 = 0.0625f * (sideX - 1); // 0
        float sX1 = 0.0625f * sideX;       // 1

        float tY0 = 0.0625f * (topY - 1); // 0
        float tY1 = 0.0625f * topY;       // 1
        float tX0 = 0.0625f * (topX - 1); // 0
        float tX1 = 0.0625f * topX;       // 1

        return new float[] {
                sX0, sY1,
                sX1, sY1,
                sX1, sY0,
                sX0, sY0,

                sX0, sY1,
                sX1, sY1,
                sX1, sY0,
                sX0, sY0,

                sX0, sY1,
                sX1, sY1,
                sX1, sY0,
                sX0, sY0,

                sX0, sY1,
                sX1, sY1,
                sX1, sY0,
                sX0, sY0,

                tX0, tY1,
                tX1, tY1,
                tX1, tY0,
                tX0, tY0,

                tX0, tY1,
                tX1, tY1,
                tX1, tY0,
                tX0, tY0,
        };
    }

    private static float[] getTerrainTextureCords(int sideX, int sideY, int topX, int topY, int bottomX, int bottomY) {
        float sY0 = 0.0625f * (sideY - 1); // 0
        float sY1 = 0.0625f * sideY;       // 1
        float sX0 = 0.0625f * (sideX - 1); // 0
        float sX1 = 0.0625f * sideX;       // 1

        float tY0 = 0.0625f * (topY - 1); // 0
        float tY1 = 0.0625f * topY;       // 1
        float tX0 = 0.0625f * (topX - 1); // 0
        float tX1 = 0.0625f * topX;       // 1

        float bY0 = 0.0625f * (bottomY - 1); // 0
        float bY1 = 0.0625f * bottomY;       // 1
        float bX0 = 0.0625f * (bottomX - 1); // 0
        float bX1 = 0.0625f * bottomX;       // 1

        return new float[] {
                sX0, sY1,
                sX1, sY1,
                sX1, sY0,
                sX0, sY0,

                sX0, sY1,
                sX1, sY1,
                sX1, sY0,
                sX0, sY0,

                sX0, sY1,
                sX1, sY1,
                sX1, sY0,
                sX0, sY0,

                sX0, sY1,
                sX1, sY1,
                sX1, sY0,
                sX0, sY0,

                tX0, tY1,
                tX1, tY1,
                tX1, tY0,
                tX0, tY0,

                bX0, bY1,
                bX1, bY1,
                bX1, bY0,
                bX0, bY0,
        };
    }

    public static float[] buildTextureCords(int blockType) {

        int[] atlasCords = textureMappings.get(blockType);
        float[] textureCords;

        try {
            if (atlasCords.length == 2) {
                textureCords = getTerrainTextureCords(atlasCords[0], atlasCords[1]);
            } else if (atlasCords.length == 4) {
                textureCords = getTerrainTextureCords(atlasCords[0], atlasCords[1], atlasCords[2], atlasCords[3]);
            } else if (atlasCords.length == 6) {
                textureCords = getTerrainTextureCords(atlasCords[0], atlasCords[1], atlasCords[2], atlasCords[3], atlasCords[4], atlasCords[5]);
            } else throw new RuntimeException("Invalid texture mappings");
        } catch (Exception e) {textureCords = getTerrainTextureCords(1, 9);}

        return  textureCords;
    }

    private static int[] textureMap(int sX, int sY) {
        return new int[]{sX, sY};
    }

    private static int[] textureMap(int sX, int sY, int tX, int tY) {
        return new int[]{sX, sY, tX, tY};
    }

    private static int[] textureMap(int sX, int sY, int tX, int tY, int bX, int bY) {
        return new int[]{sX, sY, tX, tY, bX, bY};
    }
}

