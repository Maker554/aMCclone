package renderEngine;

import org.joml.Vector3f;

public class Camera {

    private Vector3f position, rotation;

    public Camera() {
        position = new Vector3f(0, 0, 0);
        rotation = new Vector3f(0, 90,0);
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
        if (rotation.x + x <= 90)
            if (rotation.x + x >= -90)
                this.rotation.x += x;
            else
                this.rotation.x = -90;
        else
            this.rotation.x = 90;
        this.rotation.y += y;
        this.rotation.z += z;

        if (rotation.y > 360) rotation.y -= 360;
        if (rotation.y < 0) rotation.y += 360;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getRotation() {
        return rotation;
    }
}
