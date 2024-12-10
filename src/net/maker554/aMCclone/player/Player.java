package net.maker554.aMCclone.player;

import org.joml.Vector3f;
import renderEngine.Camera;

public class Player {

    private  Camera camera;
    private  Vector3f position;

    public Player() {
        camera = new Camera();
    }

    public Camera getCamera() {
        return camera;
    }

    public  Vector3f getPosition() {
        return position;
    }

    public  void setPosition(Vector3f position) {
        this.position = position;
    }
}
