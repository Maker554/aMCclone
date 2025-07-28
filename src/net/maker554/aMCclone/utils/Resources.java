package net.maker554.aMCclone.utils;

import renderEngine.models.ObjectLoader;
import renderEngine.models.Texture;

public class Resources {

    static ObjectLoader loader = new ObjectLoader();
    public static Texture terrainTexture;
    public static Texture emptyTexture;
    public static Texture toolBarTexture;
    public static Texture croosHairTexture;
    public static Texture debugBackgroundTexture;

    public static void init() throws Exception {

        terrainTexture = new Texture(loader.loadTexture("terrain.png"));
        emptyTexture = new Texture(loader.loadTexture("empty.png"));
        toolBarTexture = new Texture(loader.loadTexture("toolbar.png"));
        croosHairTexture = new Texture(loader.loadTexture("crosshair.png"));
        debugBackgroundTexture = new Texture(loader.loadTexture("debugBackground.png"));
    };
}
