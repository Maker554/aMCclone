package net.maker554.aMCclone.utils.shapes;

import org.joml.Vector3f;
import org.joml.Vector3i;

public class CollisionBox {
    public Vector3i blockPos;
    public Vector3i direction;
    public Vector3f min ;
    public Vector3f max;
    public float distance;
    public Vector3f size;

    public CollisionBox(Vector3i blockPos) {
        this.distance = 0;
        this.direction = new Vector3i(0, 0, 0);
        this.blockPos = blockPos;
        this.max = new Vector3f(blockPos.x, blockPos.y, blockPos.z).add(new Vector3f(0.5f, 0.5f, 0.5f));
        this.min = new Vector3f(blockPos.x, blockPos.y, blockPos.z).add(new Vector3f(-0.5f, -0.5f, -0.5f));
    }

    public CollisionBox(Vector3f pos, Vector3f size) {
        this.distance = 0;
        this.direction = new Vector3i(0, 0, 0);
        this.size = size;
        this.max = new Vector3f();
        this.min = new Vector3f();
        updatePos(pos);
    }

    public void updatePos(Vector3f pos){
        this.min.set(pos.x-0.4f, pos.y -1.6f, pos.z -0.4f);
        this.max.set(pos.x-0.4f, pos.y -1.6f, pos.z -0.4f).add(size);
    }
}
