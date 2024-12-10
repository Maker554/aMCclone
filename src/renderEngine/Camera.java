package renderEngine;

import org.joml.Vector3f;

public class Camera {

    private Vector3f position, rotation;

    public Camera() {
        position = new Vector3f(0, 0, 0);
        rotation = new Vector3f(0, 0,0);
    }

    public Camera(Vector3f position, Vector3f rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public void movePosition(float x, float y, float z) {
       if(z != 0) {
           position.x += (float) Math.sin(Math.toRadians(rotation.y)) * -1.0f * z;
           position.z += (float) Math.cos(Math.toRadians(rotation.y)) * z;
       }
       if(x != 0) {
           position.x += (float) Math.sin(Math.toRadians(rotation.y - 90)) * -1.0f * x;
           position.z += (float) Math.cos(Math.toRadians(rotation.y - 90)) * x;
       }
       position.y += y;
    }

    public void setPosition(Vector3f position_in) {
        this.position = position_in;
    }

    public void setRotation(Vector3f rotation_in) {
        this.rotation = rotation_in;
    }

    public void moveRotation(float x, float y, float z) {
        this.rotation.x += x;
        this.rotation.y += y;
        this.rotation.z += z;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getRotation() {
        return rotation;
    }
}
