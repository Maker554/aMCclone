package net.maker554.aMCclone.collision;

import net.maker554.aMCclone.utils.shapes.CollisionBox;
import org.joml.Vector3f;

import java.util.List;

public class EntityCollision {

    CollisionBox collisionBox;

    public EntityCollision(CollisionBox collisionBox) {
        this.collisionBox = collisionBox;
    }

    public CollisionResult isColliding(Vector3f pos, Vector3f movingVec, List<CollisionBox> collisionBoxes) {
        this.collisionBox.updatePos(pos);
        CollisionResult collisionResult = new CollisionResult();

        if (collisionBoxes == null) return collisionResult;

        for (CollisionBox box : collisionBoxes){

            float EPS = 1e-6f;

            boolean checkX = collisionBox.max.x + movingVec.x + EPS > box.min.x  && collisionBox.min.x + movingVec.x + EPS< box.max.x;
            boolean checkY = collisionBox.max.y + movingVec.y + EPS > box.min.y  && collisionBox.min.y + movingVec.y + EPS < box.max.y;
            boolean checkZ = collisionBox.max.z + movingVec.z + EPS > box.min.z  && collisionBox.min.z + movingVec.z + EPS < box.max.z;

            boolean softCheckX = collisionBox.max.x + EPS > box.min.x  && collisionBox.min.x + EPS < box.max.x;
            boolean softCheckY = collisionBox.max.y + EPS > box.min.y  && collisionBox.min.y + EPS < box.max.y;
            boolean softCheckZ = collisionBox.max.z + EPS > box.min.z  && collisionBox.min.z + EPS < box.max.z;

            // check X axis
            if (movingVec.x != 0 && checkX && softCheckY && softCheckZ) {
                collisionResult.x = false;
            }
            // check Y axis
            if (movingVec.y != 0 && softCheckX && checkY && softCheckZ) {
                collisionResult.y = false;
            }
            // check Z axis
            if (movingVec.z != 0 && softCheckX && softCheckY && checkZ) {
                collisionResult.z = false;
            }

            if (!collisionResult.x && !collisionResult.y && !collisionResult.z) break;
        }
        return collisionResult;
    }

    public CollisionResult isColliding(Vector3f pos, Vector3f movingVec, CollisionBox box) {
        this.collisionBox.updatePos(pos);
        CollisionResult collisionResult = new CollisionResult();
        if (collisionBox == null) return collisionResult;

        float EPS = 1e-6f;

        boolean checkX = collisionBox.max.x + movingVec.x + EPS > box.min.x  && collisionBox.min.x + movingVec.x + EPS< box.max.x;
        boolean checkY = collisionBox.max.y + movingVec.y + EPS > box.min.y  && collisionBox.min.y + movingVec.y + EPS < box.max.y;
        boolean checkZ = collisionBox.max.z + movingVec.z + EPS > box.min.z  && collisionBox.min.z + movingVec.z + EPS < box.max.z;

        boolean softCheckX = collisionBox.max.x + EPS > box.min.x  && collisionBox.min.x + EPS < box.max.x;
        boolean softCheckY = collisionBox.max.y + EPS > box.min.y  && collisionBox.min.y + EPS < box.max.y;
        boolean softCheckZ = collisionBox.max.z + EPS > box.min.z  && collisionBox.min.z + EPS < box.max.z;

        if (softCheckX && softCheckY && softCheckZ) {
            collisionResult.overlapping = true;
            return collisionResult;
        }

        // check X axis
        if (movingVec.x != 0 && checkX && softCheckY && softCheckZ) {
            collisionResult.x = false;
        }
        // check Y axis
        if (movingVec.y != 0 && softCheckX && checkY && softCheckZ) {
            collisionResult.y = false;
        }
        // check Z axis
        if (movingVec.z != 0 && softCheckX && softCheckY && checkZ) {
            collisionResult.z = false;
        }

        return collisionResult;
    }
}
