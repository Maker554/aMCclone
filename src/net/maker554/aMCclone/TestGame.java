package net.maker554.aMCclone;

import net.maker554.aMCclone.terrain.Block;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import renderEngine.Camera;
import renderEngine.models.Entity;
import renderEngine.models.Texture;
import renderEngine.utils.ILogic;
import renderEngine.RenderManager;
import renderEngine.WindowManager;
import renderEngine.models.ObjectLoader;

public class TestGame implements ILogic {

    private final float CAMERA_MOVE_SPEED = 0.08f;

    private final RenderManager renderManager;
    private final ObjectLoader loader;
    private final WindowManager windowManager;

    private Block block1;
    private Block block2;

    private Camera camera;
    private Texture terrainTexture;
    Vector3f cameraInc;

    public TestGame() {
        renderManager = new RenderManager();
        loader = new ObjectLoader();
        windowManager = Client.getWindow();
        camera = new Camera();
        cameraInc = new Vector3f(0,0,0);
    }

    @Override
    public void init() throws Exception {
        renderManager.init();
        Settings.init();

        block1 = new Block(new Vector3f(1, 0, -4));
        block2 = new Block(new Vector3f(-1, 0, -4));
    }

    @Override
    public void input() {
        cameraInc.set(0, 0, 0);

        if(windowManager.isKeyPressed(GLFW.GLFW_KEY_W))
            cameraInc.z += -1;
        if(windowManager.isKeyPressed(GLFW.GLFW_KEY_S))
            cameraInc.z += 1;
        if(windowManager.isKeyPressed(GLFW.GLFW_KEY_A))
            cameraInc.x += -1;
        if(windowManager.isKeyPressed(GLFW.GLFW_KEY_D))
            cameraInc.x += 1;
        if(windowManager.isKeyPressed(GLFW.GLFW_KEY_SPACE))
            cameraInc.y += 1;
        if(windowManager.isKeyPressed(GLFW.GLFW_KEY_LEFT_SHIFT))
            cameraInc.y += -1;
        if(windowManager.isKeyPressed(GLFW.GLFW_KEY_R))
            camera.moveRotation(0,2,0);

    }

    @Override
    public void update() {
        camera.movePosition(
                cameraInc.x * CAMERA_MOVE_SPEED,
                cameraInc.y * CAMERA_MOVE_SPEED,
                cameraInc.z * CAMERA_MOVE_SPEED
        );


    }

    @Override
    public void render() {
        if(windowManager.isResize()) {
            GL11.glViewport(0, 0, windowManager.getWidth(), windowManager.getHeight());
            windowManager.setResize(true);
        }
        windowManager.setClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        renderManager.clear();
        renderManager.render(block1.getEntity(), camera);
        renderManager.render(block2.getEntity(), camera);
    }

    @Override
    public void cleanUp() {
        renderManager.cleanUp();
        loader.cleanUp();
    }
}
