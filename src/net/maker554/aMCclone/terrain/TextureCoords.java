package net.maker554.aMCclone.terrain;

public class TextureCoords {

    public static float[] getTerrainTextureCoords(int x, int y) {
        float y0 = 0.0625f * (y - 1); // 0
        float y1 = 0.0625f * y;       // 1
        float x0 = 0.0625f * (x - 1); // 0
        float x1 = 0.0625f * x;       // 1

        float[] textureCords = new float[] {
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

        return textureCords;
    }

    public static float[] getTerrainTextureCoords(int sideX, int sideY, int topX, int topY) {
        float sY0 = 0.0625f * (sideY - 1); // 0
        float sY1 = 0.0625f * sideY;       // 1
        float sX0 = 0.0625f * (sideX - 1); // 0
        float sX1 = 0.0625f * sideX;       // 1

        float tY0 = 0.0625f * (topY - 1); // 0
        float tY1 = 0.0625f * topY;       // 1
        float tX0 = 0.0625f * (topX - 1); // 0
        float tX1 = 0.0625f * topX;       // 1

        float[] textureCords = new float[] {
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

        return textureCords;
    }

    public static float[] getTerrainTextureCoords(int sideX, int sideY, int topX, int topY, int bottomX, int bottomY) {
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

        float[] textureCords = new float[] {
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

        return textureCords;
    }

}
