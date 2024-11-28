package net.maker554.aMCclone;

import org.lwjgl.opengl.GL11;
import renderEngine.models.ObjectLoader;
import renderEngine.models.Texture;

public class Settings {

    public static Texture terrainTexture;

    public static void init() throws Exception{

        ObjectLoader loader = new ObjectLoader();

        terrainTexture = new Texture(loader.loadTexture("terrain.png"));

    }
}
