package net.maker554.aMCclone.player;

import net.maker554.aMCclone.player.gui.CrossHair;
import net.maker554.aMCclone.player.gui.Hand;
import net.maker554.aMCclone.player.gui.Inventory;
import net.maker554.aMCclone.player.gui.ToolBar;
import org.joml.Vector3f;
import renderEngine.Camera;

public class Player {

    private  Camera camera;
    private  Vector3f position;

    //GUI ELEMENTS
    public Hand hand;
    public ToolBar taskBar;
    public CrossHair crossHair;
    public Inventory inventory;

    public Player() {
        camera = new Camera();
        position = new Vector3f();

        hand = new Hand();
        taskBar = new ToolBar();
        crossHair = new CrossHair();
        inventory = new Inventory();
    }

    public void movePosition(float x, float y, float z) {
        if(z != 0) {
            position.x += (float) Math.sin(Math.toRadians(camera.getRotation().y)) * -1.0f * z;
            position.z += (float) Math.cos(Math.toRadians(camera.getRotation().y)) * z;
        }
        if(x != 0) {
            position.x += (float) Math.sin(Math.toRadians(camera.getRotation().y - 90)) * -1.0f * x;
            position.z += (float) Math.cos(Math.toRadians(camera.getRotation().y - 90)) * x;
        }
        position.y += y;
        camera.movePosition(x, y, z);
    }

    public void sync() {
    }

    public  Vector3f getPosition() {
        return position;
    }

    public Camera getCamera() {
        return camera;
    }

    public  void setPosition(Vector3f position) {
        this.position = position;
    }
}
