package net.maker554.aMCclone.player;

import net.maker554.aMCclone.Settings;
import net.maker554.aMCclone.assets.Cube;
import net.maker554.aMCclone.collision.TerrainCollisionMap;
import net.maker554.aMCclone.player.gui.CrossHair;
import net.maker554.aMCclone.player.gui.Hand;
import net.maker554.aMCclone.player.gui.Inventory;
import net.maker554.aMCclone.player.gui.ToolBar;
import net.maker554.aMCclone.utils.RayCast;
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

    //GUI ELEMENTS
    public Hand hand;
    public ToolBar taskBar;
    public CrossHair crossHair;
    public Inventory inventory;

    private final RayCast rayCast = new RayCast(5);
    private final Entity rayCastDebugEntity;
    private Vector3f lookingPoint;

    public Player() {
        camera = new Camera();
        position = new Vector3f();

        hand = new Hand();
        taskBar = new ToolBar();
        crossHair = new CrossHair();
        inventory = new Inventory();

        Cube cube = new Cube(0.3f, 0.3f, 0.3f);
        rayCastDebugEntity = new Entity(cube.getModel(), new Vector3f(), new Vector3f(), 1);
        lookingPoint = new Vector3f();
    }

    public void movePosition(float x, float y, float z) {
        if(z != 0) {
            position.x += (float) Math.sin(Math.toRadians(camera.getRotation().y)) * -1.0f * z;
            position.z += (float) Math.cos(Math.toRadians(camera.getRotation().y)) * z;
        }
        if(x != 0) {
            position.x += (float) Math.sin(Math.toRadians(camera.getRotation().y - 90)) * -1.0f * x;
            position.z += (float) Math.cos(Math.toRadians(camera.getRotation().y - 90)) * x;
        }
        position.y += y;
        camera.movePosition(x, y, z);
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

    public void PlaceBlock() {

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
}
