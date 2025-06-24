package net.maker554.aMCclone.assets;

import renderEngine.models.Model;
import renderEngine.models.ObjectLoader;
import renderEngine.models.Texture;

public class Shape {

    static final ObjectLoader loader = new ObjectLoader();

    protected float[] vertices;
    protected int[] indices;
    protected float[] textureCoords;

    protected Model model;

    public Model getModel() {
        return model;
    }

    public void setTexture(Texture texture) {
        if (model != null)
            model.setTexture(texture);
    }
}
