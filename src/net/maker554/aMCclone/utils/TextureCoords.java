package net.maker554.aMCclone.utils;

import java.util.Dictionary;
import java.util.Hashtable;

public class TextureCoords {

    private static final Dictionary<Integer, int[]> textureMappings  = new Hashtable<>();

    public static void init() {

        textureMappings.put(1, new int[] {4, 1, 1, 1, 3, 1});  // grass block
        textureMappings.put(2, new int[] {3, 4});              // diamond
        textureMappings.put(3, new int[] {1, 2});              // cobble
        textureMappings.put(4, new int[] {4, 5, 19, 3, 7, 8});
        textureMappings.put(5, new int[] {14, 4, 5, 4, 1, 4});
        textureMappings.put(6, new int[] {8, 2, 9, 1, 16, 1});
        textureMappings.put(7, new int[] {2, 4}); // glass
    }

    public static float[] getTerrainTextureCords(int x, int y) {
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

    public static float[] getTerrainTextureCords(int sideX, int sideY, int topX, int topY) {
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

    public static float[] getTerrainTextureCords(int sideX, int sideY, int topX, int topY, int bottomX, int bottomY) {
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
}

