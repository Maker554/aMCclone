package net.maker554.aMCclone.utils.shapes;

import net.maker554.aMCclone.collision.TerrainCollisionMap;
import org.joml.Vector3f;
import org.joml.Vector3i;

public class Line {
    private Vector3f posVec;
    private Vector3f direction;

    public Line() {
        this.posVec = new Vector3f();
        this.direction = new Vector3f();
    }

    public Line(Vector3f posVec, Vector3f direction) {
        set(posVec, direction);
    }

    public void set(Vector3f posVec, Vector3f direction) {
        this.posVec = posVec;
        this.direction = new Vector3f().set(direction).normalize();
    }

    public boolean checkCollision(Plane plane) {

        Vector3i normal = plane.getNormal();
        Vector3f planePos = plane.getPosVec();

        // find scalar
        float t = (
                normal.x * (planePos.x - this.posVec.x) +
                normal.y * (planePos.y - this.posVec.y) +
                normal.z * (planePos.z - this.posVec.z)
        ) / (
                normal.x * this.direction.x +
                normal.y * this.direction.y +
                normal.z * this.direction.z
        );

        // use scalar to get the intersection point on the line
        Vector3f intP = new Vector3f().set(posVec).add(new Vector3f().set(direction).mul(t));

        // check distance between plane center and intersection point
        return Math.abs(intP.x - planePos.x) <= 0.5f && Math.abs(intP.y - planePos.y) <= 0.5f && Math.abs(intP.z - planePos.z) <= 0.5f;
    }

    public Plane getCollidingFace() {
        for (Plane plane : TerrainCollisionMap.getPlanesList()) {
            if (checkCollision(plane))
                return plane;
        }
        return new Plane(new Vector3f(0, -1, 0), new Vector3i());
    }
}
