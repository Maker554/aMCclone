package net.maker554.aMCclone;

import net.maker554.aMCclone.input.Mouse;
import net.maker554.aMCclone.player.Player;
import net.maker554.aMCclone.terrain.ChunkManager;
import net.maker554.aMCclone.terrain.TerrainGeneration;
import net.maker554.aMCclone.input.InputHandler;
import net.maker554.aMCclone.utils.Resources;
import net.maker554.aMCclone.utils.TextureCoords;
import org.joml.Vector2i;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import renderEngine.utils.ILogic;
import renderEngine.RenderManager;
import renderEngine.WindowManager;
import renderEngine.models.ObjectLoader;

import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;

public class TestGame implements ILogic {

    private final float PLAYER_MOVE_SPEED = 0.08f;

    private final RenderManager renderManager;
    private final ObjectLoader loader;
    private final WindowManager windowManager;

    private Player player;
    Vector3f cameraInc;

    private boolean inInventory = false;

    public TestGame() {
        renderManager = new RenderManager();
        loader = new ObjectLoader();
        windowManager = Client.getWindow();
        cameraInc = new Vector3f(0,0,0);
    }

    @Override
    public void init() throws Exception {
        renderManager.init();
        Resources.init();
        TextureCoords.init();
        TerrainGeneration.init();
        Mouse.init(windowManager.getWindow());

        ChunkManager.generate(new Vector2i(0, 0));

        player = new Player();
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

        if(InputHandler.isKeyPressedDown(GLFW.GLFW_KEY_E))
            inInventory = !inInventory;
        if(InputHandler.isKeyPressedDown(GLFW.GLFW_KEY_C))
            Mouse.movingCamera = !Mouse.movingCamera;

        if(Mouse.getMovement().x != 0 || Mouse.getMovement().y != 0)
            player.getCamera().moveRotation(Mouse.getMovement().y, Mouse.getMovement().x, 0);
    }

    @Override
    public void update() {
        player.movePosition(
                cameraInc.x * PLAYER_MOVE_SPEED,
                cameraInc.y * PLAYER_MOVE_SPEED,
                cameraInc.z * PLAYER_MOVE_SPEED
        );

        player.sync();
    }

    @Override
    public void render() {
        if(windowManager.isResize()) {
            GL11.glViewport(0, 0, windowManager.getWidth(), windowManager.getHeight());
            windowManager.setResize(true);
        }
        windowManager.setClearColor(0.79f, 1.0f, 1.0f, 1.0f);
        renderManager.clear();

        ChunkManager.render(renderManager, player.getCamera());

        if (inInventory)
            player.inventory.render(renderManager);
        player.crossHair.render(renderManager);
        player.hand.render(renderManager);
        player.taskBar.render(renderManager);
    }

    @Override
    public void cleanUp() {
        renderManager.cleanUp();
        loader.cleanUp();
    }
}
