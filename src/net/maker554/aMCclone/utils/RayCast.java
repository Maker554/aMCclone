package net.maker554.aMCclone.utils;

import net.maker554.aMCclone.utils.shapes.Line;
import org.joml.Vector3f;

public class RayCast {

    private Vector3f vector;
    private float length;
    private Line line;

    public RayCast(float length) {
        this.length = length;
        vector = new Vector3f(length, length, length);
        line = new Line();
    }

    public void setVector(Vector3f rotation) {
        vector.x = (float) Math.sin(Math.toRadians(rotation.y)) * (float) Math.cos(Math.toRadians(rotation.x));
        vector.y = (float) Math.sin(Math.toRadians(rotation.x)) * -1.0f;
        vector.z = (float) Math.cos(Math.toRadians(rotation.y)) * (float) Math.cos(Math.toRadians(rotation.x)) * -1.0f;
        vector.mul(length);
    }

    public void setLine(Vector3f posVec) {
        line.set(posVec, vector);
    }

    public void setVectorDir(Vector3f vector) {
        this.vector = vector.mul(length);
    }

    public Vector3f getVector() {
        return vector;
    }
}
