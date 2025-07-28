package net.maker554.aMCclone.utils.shapes;

import net.maker554.aMCclone.utils.DirectionEnum;
import net.maker554.aMCclone.utils.VectorMath;
import org.joml.Vector3f;
import org.joml.Vector3i;

public class Plane {

    private Vector3f posVec;
    private Vector3i normal;

    public Plane(Vector3f posVec,Vector3i normal) {
        this.posVec = posVec;
        this.normal = normal;
    }

    public Plane(Vector3f posVec, DirectionEnum direction) {
        this.posVec = new Vector3f().set(posVec);
        this.normal = VectorMath.getNormal(direction);
    }

    public Vector3f getPosVec() {
        return posVec;
    }

    public Vector3i getNormal() {
        return normal;
    }
}
