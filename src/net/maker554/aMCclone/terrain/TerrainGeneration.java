package net.maker554.aMCclone.terrain;

import net.maker554.aMCclone.Settings;
import net.maker554.aMCclone.save.SaveManager;
import net.maker554.aMCclone.utils.ArrayManager;
import org.joml.Vector2i;

public class TerrainGeneration {

    private static final int OCTAVES = 9;
    private static final float LACUNARITY = 1.55f;
    private static final float GAIN = 0.80f;

    private static final float I_AMPLITUDE = 25;

    private static PerlinNoise noise;
    private static float offset;
    private static long seed;

    public static void init() {
        // random seed loaded, if it is 0 (not created) it is set as the current time
        seed = SaveManager.loadData();
        if (seed == 0) {
            seed = System.currentTimeMillis();
            SaveManager.saveData(seed);
        }
        noise = new PerlinNoise(seed);
    }

    public static float fractalNoise(Vector2i cords) {
        float value = 0;
        float amplitude = I_AMPLITUDE;
        float frequency = 0.005f;

        offset = 30;

        // loop of octaves
        for(int i = 0; i < OCTAVES; i++) {
            value += amplitude * (float) noise.noise( cords.x * frequency, cords.y * frequency);
            amplitude *= GAIN;
            frequency *= LACUNARITY;

            //offset += amplitude / 3;
        }

        return value;
    }

    public static byte[] generateTerrain(int chunk_x, int chunk_z) {
        // 3D array representing the terrain
        byte[] data = new byte[Settings.CHUNK_SIZE *Settings.CHUNK_HEIGHT*Settings.CHUNK_SIZE];
        
        // Loop through each column of the 3D array
        for (int x = 0; x < Settings.CHUNK_SIZE; x++) {
            for (int y = 0; y < Settings.CHUNK_HEIGHT; y++) {
                for (int z = 0; z < Settings.CHUNK_SIZE; z++) {
                    float columnHeight = fractalNoise(new Vector2i(x + (chunk_x * Settings.CHUNK_SIZE), z + (chunk_z * Settings.CHUNK_SIZE))) - offset;

                    if(y < columnHeight - 3) {
                        data[ArrayManager.transformDataIndex(x, y, z)] = (byte) 4; // stone
                    } else if (y < columnHeight - 1) {
                        data[ArrayManager.transformDataIndex(x, y, z)] = (byte) 5; // dirt
                    } else if (y < columnHeight) {
                        data[ArrayManager.transformDataIndex(x, y, z)] = (byte) 1; // grass
                    } else {
                        data[ArrayManager.transformDataIndex(x, y, z)] = (byte) 0; // air
                    }
                }
            }
        }

        //data[ArrayManager.transformDataIndex(8, 8, 8)] = 2;

        return data;
    }

    public static long getSeed() {
        return seed;
    }
}
