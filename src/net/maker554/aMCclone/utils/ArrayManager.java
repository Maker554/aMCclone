package net.maker554.aMCclone.utils;

import net.maker554.aMCclone.Settings;

public class ArrayManager {

    public static float[] generateChunkVertices(byte[] chunkData) {

        float[] vertices = new float[calculateFacesNumber(chunkData) * 12];  // vertices array created, the size is calculated with a function.

        int arrayIndex = 0;

        for(byte i = 0; i < Settings.CHUNK_SIZE; i++)
            for (byte j = 0; j < Settings.CHUNK_SIZE; j++)
                for (byte k = 0; k < Settings.CHUNK_SIZE; k++) {

                    // first every block not transparent
                    // then every transparent block
                    if (chunkData[transformDataIndex(i, j, k)] != 0 && chunkData[transformDataIndex(i, j, k)] != 7) {
                        if (checkNearFace(chunkData, i, j, k, Directions.SUD)) {  // check if the near face is not air
                            vertices[arrayIndex++] = -0.5f + i;
                            vertices[arrayIndex++] = -0.5f + j;
                            vertices[arrayIndex++] = -0.5f + k;
                            vertices[arrayIndex++] = 0.5f + i;
                            vertices[arrayIndex++] = -0.5f + j;
                            vertices[arrayIndex++] = -0.5f + k;
                            vertices[arrayIndex++] = 0.5f + i;
                            vertices[arrayIndex++] = 0.5f + j;
                            vertices[arrayIndex++] = -0.5f + k;
                            vertices[arrayIndex++] = -0.5f + i;
                            vertices[arrayIndex++] = 0.5f + j;
                            vertices[arrayIndex++] = -0.5f + k;
                        }

                        if (checkNearFace(chunkData, i, j, k, Directions.NORD)) {
                            vertices[arrayIndex++] = -0.5f + i;
                            vertices[arrayIndex++] = -0.5f + j;
                            vertices[arrayIndex++] = 0.5f + k;
                            vertices[arrayIndex++] = 0.5f + i;
                            vertices[arrayIndex++] = -0.5f + j;
                            vertices[arrayIndex++] = 0.5f + k;
                            vertices[arrayIndex++] = 0.5f + i;
                            vertices[arrayIndex++] = 0.5f + j;
                            vertices[arrayIndex++] = 0.5f + k;
                            vertices[arrayIndex++] = -0.5f + i;
                            vertices[arrayIndex++] = 0.5f + j;
                            vertices[arrayIndex++] = 0.5f + k;
                        }

                        if (checkNearFace(chunkData, i, j, k, Directions.EST)) {
                            vertices[arrayIndex++] = 0.5f + i;
                            vertices[arrayIndex++] = -0.5f + j;
                            vertices[arrayIndex++] = -0.5f + k;
                            vertices[arrayIndex++] = 0.5f + i;
                            vertices[arrayIndex++] = -0.5f + j;
                            vertices[arrayIndex++] = 0.5f + k;
                            vertices[arrayIndex++] = 0.5f + i;
                            vertices[arrayIndex++] = 0.5f + j;
                            vertices[arrayIndex++] = 0.5f + k;
                            vertices[arrayIndex++] = 0.5f + i;
                            vertices[arrayIndex++] = 0.5f + j;
                            vertices[arrayIndex++] = -0.5f + k;
                        }

                        if (checkNearFace(chunkData, i, j, k, Directions.WEST)) {
                            vertices[arrayIndex++] = -0.5f + i;
                            vertices[arrayIndex++] = -0.5f + j;
                            vertices[arrayIndex++] = -0.5f + k;
                            vertices[arrayIndex++] = -0.5f + i;
                            vertices[arrayIndex++] = -0.5f + j;
                            vertices[arrayIndex++] = 0.5f + k;
                            vertices[arrayIndex++] = -0.5f + i;
                            vertices[arrayIndex++] = 0.5f + j;
                            vertices[arrayIndex++] = 0.5f + k;
                            vertices[arrayIndex++] = -0.5f + i;
                            vertices[arrayIndex++] = 0.5f + j;
                            vertices[arrayIndex++] = -0.5f + k;
                        }

                        if (checkNearFace(chunkData, i, j, k, Directions.UP)) {
                            vertices[arrayIndex++] = -0.5f + i;
                            vertices[arrayIndex++] = 0.5f + j;
                            vertices[arrayIndex++] = -0.5f + k;
                            vertices[arrayIndex++] = 0.5f + i;
                            vertices[arrayIndex++] = 0.5f + j;
                            vertices[arrayIndex++] = -0.5f + k;
                            vertices[arrayIndex++] = 0.5f + i;
                            vertices[arrayIndex++] = 0.5f + j;
                            vertices[arrayIndex++] = 0.5f + k;
                            vertices[arrayIndex++] = -0.5f + i;
                            vertices[arrayIndex++] = 0.5f + j;
                            vertices[arrayIndex++] = 0.5f + k;
                        }

                        if (checkNearFace(chunkData, i, j, k, Directions.DOWN)) {
                            vertices[arrayIndex++] = -0.5f + i;
                            vertices[arrayIndex++] = -0.5f + j;
                            vertices[arrayIndex++] = -0.5f + k;
                            vertices[arrayIndex++] = 0.5f + i;
                            vertices[arrayIndex++] = -0.5f + j;
                            vertices[arrayIndex++] = -0.5f + k;
                            vertices[arrayIndex++] = 0.5f + i;
                            vertices[arrayIndex++] = -0.5f + j;
                            vertices[arrayIndex++] = 0.5f + k;
                            vertices[arrayIndex++] = -0.5f + i;
                            vertices[arrayIndex++] = -0.5f + j;
                            vertices[arrayIndex++] = 0.5f + k;
                        }
                    }
                }
        for(byte i = 0; i < Settings.CHUNK_SIZE; i++)
            for (byte j = 0; j < Settings.CHUNK_SIZE; j++)
                for (byte k = 0; k < Settings.CHUNK_SIZE; k++) {

                    if (chunkData[transformDataIndex(i,j,k)] == 7) {

                        if (checkNearFace(chunkData, i, j, k, Directions.SUD)) {  // check if the near face is not air
                            vertices[arrayIndex++] = -0.5f + i;
                            vertices[arrayIndex++] = -0.5f + j;
                            vertices[arrayIndex++] = -0.5f + k;
                            vertices[arrayIndex++] = 0.5f + i;
                            vertices[arrayIndex++] = -0.5f + j;
                            vertices[arrayIndex++] = -0.5f + k;
                            vertices[arrayIndex++] = 0.5f + i;
                            vertices[arrayIndex++] = 0.5f + j;
                            vertices[arrayIndex++] = -0.5f + k;
                            vertices[arrayIndex++] = -0.5f + i;
                            vertices[arrayIndex++] = 0.5f + j;
                            vertices[arrayIndex++] = -0.5f + k;
                        }

                        if (checkNearFace(chunkData, i, j, k, Directions.NORD)) {
                            vertices[arrayIndex++] = -0.5f + i;
                            vertices[arrayIndex++] = -0.5f + j;
                            vertices[arrayIndex++] = 0.5f + k;
                            vertices[arrayIndex++] = 0.5f + i;
                            vertices[arrayIndex++] = -0.5f + j;
                            vertices[arrayIndex++] = 0.5f + k;
                            vertices[arrayIndex++] = 0.5f + i;
                            vertices[arrayIndex++] = 0.5f + j;
                            vertices[arrayIndex++] = 0.5f + k;
                            vertices[arrayIndex++] = -0.5f + i;
                            vertices[arrayIndex++] = 0.5f + j;
                            vertices[arrayIndex++] = 0.5f + k;
                        }

                        if (checkNearFace(chunkData, i, j, k, Directions.EST)) {
                            vertices[arrayIndex++] = 0.5f + i;
                            vertices[arrayIndex++] = -0.5f + j;
                            vertices[arrayIndex++] = -0.5f + k;
                            vertices[arrayIndex++] = 0.5f + i;
                            vertices[arrayIndex++] = -0.5f + j;
                            vertices[arrayIndex++] = 0.5f + k;
                            vertices[arrayIndex++] = 0.5f + i;
                            vertices[arrayIndex++] = 0.5f + j;
                            vertices[arrayIndex++] = 0.5f + k;
                            vertices[arrayIndex++] = 0.5f + i;
                            vertices[arrayIndex++] = 0.5f + j;
                            vertices[arrayIndex++] = -0.5f + k;
                        }

                        if (checkNearFace(chunkData, i, j, k, Directions.WEST)) {
                            vertices[arrayIndex++] = -0.5f + i;
                            vertices[arrayIndex++] = -0.5f + j;
                            vertices[arrayIndex++] = -0.5f + k;
                            vertices[arrayIndex++] = -0.5f + i;
                            vertices[arrayIndex++] = -0.5f + j;
                            vertices[arrayIndex++] = 0.5f + k;
                            vertices[arrayIndex++] = -0.5f + i;
                            vertices[arrayIndex++] = 0.5f + j;
                            vertices[arrayIndex++] = 0.5f + k;
                            vertices[arrayIndex++] = -0.5f + i;
                            vertices[arrayIndex++] = 0.5f + j;
                            vertices[arrayIndex++] = -0.5f + k;
                        }

                        if (checkNearFace(chunkData, i, j, k, Directions.UP)) {
                            vertices[arrayIndex++] = -0.5f + i;
                            vertices[arrayIndex++] = 0.5f + j;
                            vertices[arrayIndex++] = -0.5f + k;
                            vertices[arrayIndex++] = 0.5f + i;
                            vertices[arrayIndex++] = 0.5f + j;
                            vertices[arrayIndex++] = -0.5f + k;
                            vertices[arrayIndex++] = 0.5f + i;
                            vertices[arrayIndex++] = 0.5f + j;
                            vertices[arrayIndex++] = 0.5f + k;
                            vertices[arrayIndex++] = -0.5f + i;
                            vertices[arrayIndex++] = 0.5f + j;
                            vertices[arrayIndex++] = 0.5f + k;
                        }

                        if (checkNearFace(chunkData, i, j, k, Directions.DOWN)) {
                            vertices[arrayIndex++] = -0.5f + i;
                            vertices[arrayIndex++] = -0.5f + j;
                            vertices[arrayIndex++] = -0.5f + k;
                            vertices[arrayIndex++] = 0.5f + i;
                            vertices[arrayIndex++] = -0.5f + j;
                            vertices[arrayIndex++] = -0.5f + k;
                            vertices[arrayIndex++] = 0.5f + i;
                            vertices[arrayIndex++] = -0.5f + j;
                            vertices[arrayIndex++] = 0.5f + k;
                            vertices[arrayIndex++] = -0.5f + i;
                            vertices[arrayIndex++] = -0.5f + j;
                            vertices[arrayIndex++] = 0.5f + k;
                        }
                    }
                }
        return vertices;
    }

    public static int[] generateChunkIndices(byte[] chunkData) {
        int[] indices = new int[calculateFacesNumber(chunkData) * 6];

        int arrayIndex = 0;
        int offset = 0;

        for(byte i = 0; i < Settings.CHUNK_SIZE; i++)
            for (byte j = 0; j < Settings.CHUNK_SIZE; j++)
                for (byte k = 0; k < Settings.CHUNK_SIZE; k++) {

                    if (chunkData[transformDataIndex(i, j, k)] != 0 && chunkData[transformDataIndex(i, j, k)] != 7) {

                        if (checkNearFace(chunkData, i, j, k, Directions.SUD)) {
                            indices[arrayIndex++] = offset;
                            indices[arrayIndex++] = offset + 2;
                            indices[arrayIndex++] = offset + 1;
                            indices[arrayIndex++] = offset;
                            indices[arrayIndex++] = offset + 3;
                            indices[arrayIndex++] = offset + 2;
                            offset += 4;
                        }

                        if (checkNearFace(chunkData, i, j, k, Directions.NORD)) {
                            indices[arrayIndex++] = offset;
                            indices[arrayIndex++] = offset + 1;
                            indices[arrayIndex++] = offset + 2;
                            indices[arrayIndex++] = offset;
                            indices[arrayIndex++] = offset + 2;
                            indices[arrayIndex++] = offset + 3;
                            offset += 4;
                        }

                        if (checkNearFace(chunkData, i, j, k, Directions.EST)) {
                            indices[arrayIndex++] = offset;
                            indices[arrayIndex++] = offset + 2;
                            indices[arrayIndex++] = offset + 1;
                            indices[arrayIndex++] = offset;
                            indices[arrayIndex++] = offset + 3;
                            indices[arrayIndex++] = offset + 2;
                            offset += 4;
                        }

                        if (checkNearFace(chunkData, i, j, k, Directions.WEST)) {
                            indices[arrayIndex++] = offset;
                            indices[arrayIndex++] = offset + 1;
                            indices[arrayIndex++] = offset + 2;
                            indices[arrayIndex++] = offset;
                            indices[arrayIndex++] = offset + 2;
                            indices[arrayIndex++] = offset + 3;
                            offset += 4;
                        }

                        if (checkNearFace(chunkData, i, j, k, Directions.UP)) {
                            indices[arrayIndex++] = offset;
                            indices[arrayIndex++] = offset + 2;
                            indices[arrayIndex++] = offset + 1;
                            indices[arrayIndex++] = offset;
                            indices[arrayIndex++] = offset + 3;
                            indices[arrayIndex++] = offset + 2;
                            offset += 4;
                        }

                        if (checkNearFace(chunkData, i, j, k, Directions.DOWN)) {
                            indices[arrayIndex++] = offset;
                            indices[arrayIndex++] = offset + 1;
                            indices[arrayIndex++] = offset + 2;
                            indices[arrayIndex++] = offset;
                            indices[arrayIndex++] = offset + 2;
                            indices[arrayIndex++] = offset + 3;
                            offset += 4;

                        }
                    }
                }

        for(byte i = 0; i < Settings.CHUNK_SIZE; i++)
            for (byte j = 0; j < Settings.CHUNK_SIZE; j++)
                for (byte k = 0; k < Settings.CHUNK_SIZE; k++) {

                    if (chunkData[transformDataIndex(i, j, k)] == 7) {

                        if (checkNearFace(chunkData, i, j, k, Directions.SUD)) {
                            indices[arrayIndex++] = offset;
                            indices[arrayIndex++] = offset + 2;
                            indices[arrayIndex++] = offset + 1;
                            indices[arrayIndex++] = offset;
                            indices[arrayIndex++] = offset + 3;
                            indices[arrayIndex++] = offset + 2;
                            offset += 4;
                        }

                        if (checkNearFace(chunkData, i, j, k, Directions.NORD)) {
                            indices[arrayIndex++] = offset;
                            indices[arrayIndex++] = offset + 1;
                            indices[arrayIndex++] = offset + 2;
                            indices[arrayIndex++] = offset;
                            indices[arrayIndex++] = offset + 2;
                            indices[arrayIndex++] = offset + 3;
                            offset += 4;
                        }

                        if (checkNearFace(chunkData, i, j, k, Directions.EST)) {
                            indices[arrayIndex++] = offset;
                            indices[arrayIndex++] = offset + 2;
                            indices[arrayIndex++] = offset + 1;
                            indices[arrayIndex++] = offset;
                            indices[arrayIndex++] = offset + 3;
                            indices[arrayIndex++] = offset + 2;
                            offset += 4;
                        }

                        if (checkNearFace(chunkData, i, j, k, Directions.WEST)) {
                            indices[arrayIndex++] = offset;
                            indices[arrayIndex++] = offset + 1;
                            indices[arrayIndex++] = offset + 2;
                            indices[arrayIndex++] = offset;
                            indices[arrayIndex++] = offset + 2;
                            indices[arrayIndex++] = offset + 3;
                            offset += 4;
                        }

                        if (checkNearFace(chunkData, i, j, k, Directions.UP)) {
                            indices[arrayIndex++] = offset;
                            indices[arrayIndex++] = offset + 2;
                            indices[arrayIndex++] = offset + 1;
                            indices[arrayIndex++] = offset;
                            indices[arrayIndex++] = offset + 3;
                            indices[arrayIndex++] = offset + 2;
                            offset += 4;
                        }

                        if (checkNearFace(chunkData, i, j, k, Directions.DOWN)) {
                            indices[arrayIndex++] = offset;
                            indices[arrayIndex++] = offset + 1;
                            indices[arrayIndex++] = offset + 2;
                            indices[arrayIndex++] = offset;
                            indices[arrayIndex++] = offset + 2;
                            indices[arrayIndex++] = offset + 3;
                            offset += 4;

                        }
                    }
                }
        return indices;
    }

    public static float[] generateChunkTextureCords(byte[] chunkData) {
        float[] textureCords = new float[calculateFacesNumber(chunkData) * 8];

        int arrayIndex = 0;
        int offset;
        float[] sample;

        for(byte i = 0; i < Settings.CHUNK_SIZE; i++)
            for (byte j = 0; j < Settings.CHUNK_SIZE; j++)
                for (byte k = 0; k < Settings.CHUNK_SIZE; k++) {

                    if (chunkData[transformDataIndex(i, j, k)] != 0 && chunkData[transformDataIndex(i, j, k)] != 7) {
                        offset = 0;
                        sample = TextureCoords.buildTextureCords(chunkData[transformDataIndex(i,j,k)]);

                        if (checkNearFace(chunkData, i, j, k, Directions.NORD))
                            for (int count = 0; count < 8; count++)
                                textureCords[arrayIndex++] = sample[offset + count]; // put first 8 elements from the sample into the textureCords array
                        offset += 8; // offset on the sample array to put the others faces

                        if (checkNearFace(chunkData, i, j, k, Directions.SUD))
                            for (int count = 0; count < 8; count++)
                                textureCords[arrayIndex++] = sample[offset + count];
                        offset += 8;

                        if (checkNearFace(chunkData, i, j, k, Directions.EST))
                            for (int count = 0; count < 8; count++)
                                textureCords[arrayIndex++] = sample[offset + count];
                        offset += 8;

                        if (checkNearFace(chunkData, i, j, k, Directions.WEST))
                            for (int count = 0; count < 8; count++)
                                textureCords[arrayIndex++] = sample[offset + count];
                        offset += 8;

                        if (checkNearFace(chunkData, i, j, k, Directions.UP))
                            for (int count = 0; count < 8; count++)
                                textureCords[arrayIndex++] = sample[offset + count];
                        offset += 8;

                        if (checkNearFace(chunkData, i, j, k, Directions.DOWN))
                            for (int count = 0; count < 8; count++)
                                textureCords[arrayIndex++] = sample[offset + count];
                        offset += 8;
                    }
                }

        for(byte i = 0; i < Settings.CHUNK_SIZE; i++)
            for (byte j = 0; j < Settings.CHUNK_SIZE; j++)
                for (byte k = 0; k < Settings.CHUNK_SIZE; k++) {

                    if (chunkData[transformDataIndex(i, j, k)] == 7) {
                        offset = 0;
                        sample = TextureCoords.buildTextureCords(chunkData[transformDataIndex(i,j,k)]);

                        if (checkNearFace(chunkData, i, j, k, Directions.NORD))
                            for (int count = 0; count < 8; count++)
                                textureCords[arrayIndex++] = sample[offset + count]; // put first 8 elements from the sample into the textureCords array
                        offset += 8; // offset on the sample array to put the others faces

                        if (checkNearFace(chunkData, i, j, k, Directions.SUD))
                            for (int count = 0; count < 8; count++)
                                textureCords[arrayIndex++] = sample[offset + count];
                        offset += 8;

                        if (checkNearFace(chunkData, i, j, k, Directions.EST))
                            for (int count = 0; count < 8; count++)
                                textureCords[arrayIndex++] = sample[offset + count];
                        offset += 8;

                        if (checkNearFace(chunkData, i, j, k, Directions.WEST))
                            for (int count = 0; count < 8; count++)
                                textureCords[arrayIndex++] = sample[offset + count];
                        offset += 8;

                        if (checkNearFace(chunkData, i, j, k, Directions.UP))
                            for (int count = 0; count < 8; count++)
                                textureCords[arrayIndex++] = sample[offset + count];
                        offset += 8;

                        if (checkNearFace(chunkData, i, j, k, Directions.DOWN))
                            for (int count = 0; count < 8; count++)
                                textureCords[arrayIndex++] = sample[offset + count];
                        offset += 8;
                    }
                }

        return textureCords;
    }

    public static int calculateFacesNumber(byte[] chunkData) {
        int count = 0;

        for(byte i = 0; i < Settings.CHUNK_SIZE; i++) {
            for (byte j = 0; j < Settings.CHUNK_SIZE; j++) {
                for (byte k = 0; k < Settings.CHUNK_SIZE; k++) {
                    count += checkNearFace(chunkData, i, j, k, Directions.NORD) ? 1 : 0;  // ? 1 : 0 is used to convert boolean to int
                    count += checkNearFace(chunkData, i, j, k, Directions.SUD) ? 1 : 0;
                    count += checkNearFace(chunkData, i, j, k, Directions.EST) ? 1 : 0;
                    count += checkNearFace(chunkData, i, j, k, Directions.WEST) ? 1 : 0;
                    count += checkNearFace(chunkData, i, j, k, Directions.UP) ? 1 : 0;
                    count += checkNearFace(chunkData, i, j, k, Directions.DOWN) ? 1 : 0;
                }
            }
        }

        return count;
    }

    public static boolean checkNearFace(byte[] chunkData, byte x, byte y, byte z, Directions direction) {

        if (chunkData[transformDataIndex(x, y, z)] == 0)
            return false;

        byte checkX = x;
        byte checkY = y;
        byte checkZ = z;

        switch (direction) {

            case NORD -> {
                checkZ++;
                break;
            }
            case SUD -> {
                checkZ--;
                break;
            }
            case EST -> {
                checkX++;
                break;
            }
            case WEST -> {
                checkX--;
                break;
            }
            case UP -> {
                checkY++;
                break;
            }
            case DOWN -> {
                checkY--;
            }
        }

        if (checkX >= Settings.CHUNK_SIZE || checkX < 0 || checkY >= Settings.CHUNK_SIZE || checkY < 0 || checkZ >= Settings.CHUNK_SIZE || checkZ < 0 )
            return true;

        return chunkData[transformDataIndex(checkX, checkY, checkZ)] == 0 || chunkData[transformDataIndex(checkX, checkY, checkZ)] == 7;
    }

    public static int transformDataIndex(int x, int y, int z) {
        return (x* Settings.CHUNK_SIZE + y)*Settings.CHUNK_SIZE + z;
    }

    public static int transformDataIndex(byte x, byte y, byte z) {
        return (x* Settings.CHUNK_SIZE + y)*Settings.CHUNK_SIZE + z;
    }
}
