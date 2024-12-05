package net.maker554.aMCclone.utils;

import renderEngine.models.ObjectLoader;
import renderEngine.models.Texture;

public class Resources {

    static ObjectLoader loader = new ObjectLoader();
    public static Texture terrainTexture;

    public static void init() throws Exception {

        terrainTexture = new Texture(loader.loadTexture("terrain.png"));
    };
}
