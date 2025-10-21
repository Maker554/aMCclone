package net.maker554.aMCclone.utils.shapes;

import net.maker554.aMCclone.collision.TerrainCollisionMap;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector3i;

import java.util.ArrayList;
import java.util.List;

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

    public boolean checkCollision(CollisionBox box) {
        // get the t min and max, factor to multiply in the line equation for each axis to get the point where it enter each slab
        Vector2f slabX = new Vector2f((box.min.x - posVec.x) / direction.x, (box.max.x - posVec.x) / direction.x);
        Vector2f slabY = new Vector2f((box.min.y - posVec.y) / direction.y, (box.max.y - posVec.y) / direction.y);
        Vector2f slabZ = new Vector2f((box.min.z - posVec.z) / direction.z, (box.max.z - posVec.z) / direction.z);

        // swap t if direction is negative
        if (direction.x < 0) slabX = new Vector2f(slabX.y, slabX.x);
        if (direction.y < 0) slabY = new Vector2f(slabY.y, slabY.x);
        if (direction.z < 0) slabZ = new Vector2f(slabZ.y, slabZ.x);

        // exceptions result in 0 for the max comparison
        if(slabX.x == Float.POSITIVE_INFINITY) slabX.x = 0;

        // find the t where it has entered all axis
        float t_enter = Math.max(Math.max(slabX.x, slabY.x), slabZ.x);
        // find the t where it has exited all axis
        float t_exit = Math.min(Math.min(slabX.y, slabY.y), slabZ.y);

        // determine the face
        if (t_enter == slabX.x) {
            if (direction.x < 0) box.direction.x = 1;
            else box.direction.x = -1;
        }
        if (t_enter == slabY.x) {
            if (direction.y < 0) box.direction.y = 1;
            else box.direction.y = -1;
        }
        if (t_enter == slabZ.x) {
            if (direction.z < 0) box.direction.z = 1;
            else box.direction.z = -1;
        }

        // the distance is saved into the collision box
        box.distance = t_enter;

        // if the line exit all axis before it has entered all of them there is no collision
        return t_exit > t_enter;
    }

    // return collision box from the list if it is colliding with the line
    public CollisionBox getPointedBlock(Vector3f playerPos) {

        final List<CollisionBox> collidingList = new ArrayList<>();

        for (CollisionBox box : TerrainCollisionMap.getCollisionBoxList()) {
            if (checkCollision(box)) {
                collidingList.add(box);
            }
        }
        // get the closest block to the player that is not behind
        float closest_t = 9999;
        CollisionBox collisionBox = null;
        for (CollisionBox box : collidingList) {
            if(0 <= box.distance && box.distance < closest_t)  {
                closest_t = box.distance;
                collisionBox = box;
            }
        }

        return collisionBox;
    }
}
