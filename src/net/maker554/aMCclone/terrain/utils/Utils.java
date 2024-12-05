package net.maker554.aMCclone.terrain.utils;

import java.util.Random;
public class Utils {

    public static int randint(int min, int max) {
        Random random = new Random();
         return Math.abs((random.nextInt() % (max + 1 - min))) + min;
    }
}
