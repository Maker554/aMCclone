package net.maker554.aMCclone.collision;

import org.joml.Vector3f;

public class CollisionResult {

    public boolean x = true;
    public boolean y = true;
    public boolean z = true;

    public Vector3f collidingPos = new Vector3f();

    CollisionResult(Vector3f collidingPos){
        this.collidingPos = collidingPos;
    }
}
