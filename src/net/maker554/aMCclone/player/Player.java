package net.maker554.aMCclone.player;

import net.maker554.aMCclone.Settings;
import net.maker554.aMCclone.assets.Cube;
import net.maker554.aMCclone.collision.CollisionResult;
import net.maker554.aMCclone.collision.EntityCollision;
import net.maker554.aMCclone.collision.TerrainCollisionMap;
import net.maker554.aMCclone.player.gui.*;
import net.maker554.aMCclone.terrain.ChunkManager;
import net.maker554.aMCclone.utils.RayCast;
import net.maker554.aMCclone.utils.shapes.CollisionBox;
import org.joml.Vector2i;
import org.joml.Vector3f;
import org.joml.Vector3i;
import renderEngine.Camera;
import renderEngine.models.Entity;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final Camera camera;
    private  Vector3f position;
    private FacingEnum facingX;
    private FacingEnum facingZ;
    private int blockBreakingCountDown;

    //GUI ELEMENTS
    public Hand hand;
    public ToolBarGui toolBarGui;
    public CrossHair crossHair;
    public InventoryGui inventory;
    public List<InventoryBlock> inventoryBlocks;
    public HandBlock handBlock;

    private final RayCast rayCast = new RayCast(5);
    private final Entity rayCastDebugEntity;
    private final Vector3f lookingPoint;
    private final EntityCollision  entityCollision;

    private final ToolBar toolBar;

    public Player() {
        camera = new Camera();
        position = new Vector3f();
        blockBreakingCountDown = 0;

        hand = new Hand();
        toolBarGui = new ToolBarGui();
        crossHair = new CrossHair();
        inventory = new InventoryGui();
        inventoryBlocks = new ArrayList<>();

        toolBar = new ToolBar();
        Inventory.init();

        for (int i=0; i < 9; i++) {
            inventoryBlocks.add(new InventoryBlock(toolBar.getId(i)));
            inventoryBlocks.get(i).setPosition(-3.66f +(1.114f*i), -4.83f);
        }
        handBlock = new HandBlock(toolBar.getId());

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

        // generate terrain
        ChunkManager.generateTerrain(getChunkPos());
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

            Vector3i candidatePos = new Vector3i().set(collisionBox.blockPos).add(collisionBox.direction);
            CollisionResult isIntoPlayer = entityCollision.isColliding(position, new Vector3f(), new CollisionBox(candidatePos)); // check the collision with player

            // place block in the candidate pos if it is air and does not collide with the player)
            if (ChunkManager.getBlock(candidatePos ) == 0 && !isIntoPlayer.overlapping) ChunkManager.setBlock(candidatePos, toolBar.getId());

        } else blockBreakingCountDown--;
    }

    public void setCurrentItem(int slot) {
        toolBar.changeSlot(slot);
        handBlock = new HandBlock(toolBar.getId());
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

    public ToolBar getToolBar() {
        return toolBar;
    }

    public Vector2i getChunkPos() {
        return new Vector2i((int) Math.floor(position.x / Settings.CHUNK_SIZE), (int) Math.floor(position.z / Settings.CHUNK_SIZE));
    }

    public void resetBBcountDown() {
        blockBreakingCountDown = 0;
    }

    public void modifyToolBar() {
        Inventory.selectBlock(toolBar);

        inventoryBlocks = new ArrayList<>();
        for (int i=0; i < 9; i++) {
            inventoryBlocks.add(new InventoryBlock(toolBar.getId(i)));
            inventoryBlocks.get(i).setPosition(-3.66f +(1.114f*i), -4.83f);
        }
        handBlock = new HandBlock(toolBar.getId());
    }
}
