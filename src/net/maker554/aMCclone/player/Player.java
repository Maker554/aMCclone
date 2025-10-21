package net.maker554.aMCclone.player;

import net.maker554.aMCclone.Settings;
import net.maker554.aMCclone.assets.Cube;
import net.maker554.aMCclone.collision.CollisionResult;
import net.maker554.aMCclone.collision.EntityCollision;
import net.maker554.aMCclone.collision.TerrainCollisionMap;
import net.maker554.aMCclone.player.gui.CrossHair;
import net.maker554.aMCclone.player.gui.Hand;
import net.maker554.aMCclone.player.gui.Inventory;
import net.maker554.aMCclone.player.gui.ToolBar;
import net.maker554.aMCclone.terrain.ChunkManager;
import net.maker554.aMCclone.utils.RayCast;
import net.maker554.aMCclone.utils.shapes.CollisionBox;
import org.joml.Vector2i;
import org.joml.Vector3f;
import org.joml.Vector3i;
import renderEngine.Camera;
import renderEngine.models.Entity;

public class Player {

    private final Camera camera;
    private  Vector3f position;
    private FacingEnum facingX;
    private FacingEnum facingZ;
    private int blockBreakingCountDown;

    //GUI ELEMENTS
    public Hand hand;
    public ToolBar taskBar;
    public CrossHair crossHair;
    public Inventory inventory;

    private final RayCast rayCast = new RayCast(5);
    private final Entity rayCastDebugEntity;
    private Vector3f lookingPoint;
    private EntityCollision  entityCollision;

    public Player() {
        camera = new Camera();
        position = new Vector3f();
        blockBreakingCountDown = 0;

        hand = new Hand();
        taskBar = new ToolBar();
        crossHair = new CrossHair();
        inventory = new Inventory();

        Cube cube = new Cube(0.3f, 0.3f, 0.3f);
        rayCastDebugEntity = new Entity(cube.getModel(), new Vector3f(), new Vector3f(), 1);
        lookingPoint = new Vector3f();
        entityCollision = new EntityCollision(new CollisionBox(position, new Vector3f(0.8f, 1.8f, 0.8f)));
    }

    public void movePosition(float x, float y, float z) {

        Vector3f moveVec = new Vector3f();

        if(z != 0) {
            moveVec.x += (float) Math.sin(Math.toRadians(camera.getRotation().y)) * -1.0f * z;
            moveVec.z += (float) Math.cos(Math.toRadians(camera.getRotation().y)) * z;
        }
        if(x != 0) {
            moveVec.x += (float) Math.sin(Math.toRadians(camera.getRotation().y - 90)) * -1.0f * x;
            moveVec.z += (float) Math.cos(Math.toRadians(camera.getRotation().y - 90)) * x;
        }
        moveVec.y += y;

        CollisionResult collisionResult = entityCollision.isColliding(position, moveVec, TerrainCollisionMap.getCollisionBoxList());

        if (collisionResult.x) position.x += moveVec.x;
        if (collisionResult.y) position.y += moveVec.y;
        if (collisionResult.z) position.z += moveVec.z;

        camera.setPosition(position);
    }

    public void sync() {

        // rayCast
        rayCast.setVector(camera.getRotation());
        rayCast.setLine(position);
        lookingPoint.zero();
        lookingPoint.add(position);
        lookingPoint.add(rayCast.getVector());
        rayCastDebugEntity.setPosition(lookingPoint);

        //facing
        if (camera.getRotation().y > 180) {
            facingX = FacingEnum.NEGATIVE;
        } else {
            facingX = FacingEnum.POSITIVE;
        }

        if (camera.getRotation().y > 270 || camera.getRotation().y < 90) {
            facingZ = FacingEnum.NEGATIVE;
        } else {
            facingZ = FacingEnum.POSITIVE;
        }

        // collision planes

        TerrainCollisionMap.calculateMap(new Vector3f().set(position));
    }

    public void breakBlock() {
        if(blockBreakingCountDown <= 0) {
            blockBreakingCountDown = Settings.BLOCK_BREAKING_COUNTDOWN;

            CollisionBox collisionBox = rayCast.getLine().getPointedBlock(position);
            if(collisionBox == null) return; // if no block is colliding with the ray cast then no block is broken

            ChunkManager.setBlock(collisionBox.blockPos, 0);

        } else blockBreakingCountDown--;
    }

    public void placeBlock() {
        if(blockBreakingCountDown <= 0) {
            blockBreakingCountDown = Settings.BLOCK_BREAKING_COUNTDOWN;

            CollisionBox collisionBox = rayCast.getLine().getPointedBlock(position);
            if (collisionBox == null) return;

            // place block in the candidate pos if it is air)
            Vector3i candidatePos = new Vector3i().set(collisionBox.blockPos).add(collisionBox.direction);
            if (ChunkManager.getBlock(candidatePos) == 0) ChunkManager.setBlock(candidatePos, 7);

        } else blockBreakingCountDown--;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Camera getCamera() {
        return camera;
    }

    public  void setPosition(Vector3f position) {
        this.position = position;
        camera.setPosition(position);
    }

    public Entity getRayCastDebugEntity() {
        return rayCastDebugEntity;
    }

    public FacingEnum getFacingX() {
        return facingX;
    }

    public FacingEnum getFacingZ() {
        return facingZ;
    }

    public Vector2i getChunkPos() {
        return new Vector2i((int) Math.floor(position.x / Settings.CHUNK_SIZE), (int) Math.floor(position.z / Settings.CHUNK_SIZE));
    }

    public void resetBBcountDown() {
        blockBreakingCountDown = 0;
    }
}
