package net.maker554.aMCclone.terrain;

import java.util.Random;

public class PerlinNoise {

    private int[] permutation; // Permutation table for pseudo-random gradients

    public PerlinNoise(long seed) {
        permutation = new int[512]; // 512 for wrapping
        Random random = new Random(seed);

        int[] p = new int[256];
        for (int i = 0; i < 256; i++) {
            p[i] = i;
        }

        // Shuffle the permutation table
        for (int i = 255; i > 0; i--) {
            int swap = random.nextInt(i + 1);
            int temp = p[i];
            p[i] = p[swap];
            p[swap] = temp;
        }

        // Duplicate the table
        for (int i = 0; i < 256; i++) {
            permutation[i] = p[i];
            permutation[256 + i] = p[i];
        }
    }

    private double fade(double t) {
        // Fade function: 6t^5 - 15t^4 + 10t^3
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    private double lerp(double t, double a, double b) {
        // Linear interpolation
        return a + t * (b - a);
    }

    private double grad(int hash, double x, double y) {
        // Gradient function: chooses a gradient based on hash
        int h = hash & 3; // Only use 2 bits
        double u = h < 2 ? x : y;
        double v = h < 2 ? y : x;
        return ((h & 1) == 0 ? u : -u) + ((h & 2) == 0 ? v : -v);
    }

    public double noise(double x, double y) {
        // Find unit square containing the point
        int X = (int) Math.floor(x) & 255;
        int Y = (int) Math.floor(y) & 255;

        // Compute relative x, y inside the unit square
        x -= Math.floor(x);
        y -= Math.floor(y);

        // Compute fade curves
        double u = fade(x);
        double v = fade(y);

        // Hash coordinates of the square's corners
        int aa = permutation[permutation[X] + Y];
        int ab = permutation[permutation[X] + Y + 1];
        int ba = permutation[permutation[X + 1] + Y];
        int bb = permutation[permutation[X + 1] + Y + 1];

        // Add blended results from corners
        double res = lerp(v,
                lerp(u, grad(aa, x, y), grad(ba, x - 1, y)),
                lerp(u, grad(ab, x, y - 1), grad(bb, x - 1, y - 1))
        );
        return (res + 1.0) / 2.0; // Normalize to 0.0 - 1.0
    }
}

