package net.maker554.aMCclone.player.gui;

import org.joml.Vector2f;
import org.joml.Vector3f;
import renderEngine.RenderManager;
import renderEngine.models.Entity;
import renderEngine.models.Model;
import renderEngine.models.ObjectLoader;

public class GuiElement {

    protected Vector3f rotation;
    private Vector3f position;
    private Entity entity;

    private static float DIST = 0.05f;

    public GuiElement() {
        setPosition(0,0);
        rotation = new Vector3f(0, 90, 0);
    }

    public void render(RenderManager renderManager) {
        if(entity != null)
            renderManager.renderGui(entity);
        else
            throw new Error("entity is set to null");
    }

    protected void setPosition(float x, float y) {
        position = new Vector3f(x*DIST/10, y*DIST/10, -DIST);
    }

    protected Vector3f getPosition() {return position;}

    protected void setEntity(Model model) {
        if (model == null)
            throw new Error("model is null");
        entity = new Entity(model, position, rotation, DIST / 10f);
    }
}
