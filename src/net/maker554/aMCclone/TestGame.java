package net.maker554.aMCclone;

import net.maker554.aMCclone.collision.TerrainCollisionMap;
import net.maker554.aMCclone.input.Mouse;
import net.maker554.aMCclone.player.FacingEnum;
import net.maker554.aMCclone.player.Inventory;
import net.maker554.aMCclone.player.Player;
import net.maker554.aMCclone.player.gui.InventoryBlock;
import net.maker554.aMCclone.player.gui.debug.DebugManager;
import net.maker554.aMCclone.save.SaveManager;
import net.maker554.aMCclone.terrain.Chunk;
import net.maker554.aMCclone.terrain.ChunkManager;
import net.maker554.aMCclone.terrain.TerrainGeneration;
import net.maker554.aMCclone.input.InputHandler;
import net.maker554.aMCclone.utils.Resources;
import net.maker554.aMCclone.utils.TextureCoords;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;
import org.joml.Vector3i;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import renderEngine.models.Entity;
import renderEngine.text.TextManager;
import renderEngine.utils.ILogic;
import renderEngine.RenderManager;
import renderEngine.WindowManager;
import renderEngine.models.ObjectLoader;

import static java.lang.Math.round;
import static org.lwjgl.glfw.GLFW.*;

public class TestGame implements ILogic {


    private final RenderManager renderManager;
    private final ObjectLoader loader;
    private final WindowManager windowManager;

    private Player player;
    private final Vector3f cameraInc;

    private boolean inInventory = false;
    private boolean inDebug = false;

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

        // load data
        player = SaveManager.loadPlayer();
        ChunkManager.loadTerrain(player.getChunkPos());

        //fix player pos
        if (player.getPosition().y == -10000) {
            Vector3f pos = player.getPosition();
            for (int i=0; i<Settings.CHUNK_HEIGHT; i++) {
                // continue untill the block is not air
                if (ChunkManager.getBlock(new Vector3i((int) Math.floor(pos.x), Settings.CHUNK_HEIGHT - i, (int) Math.floor(pos.z))) != 0) {
                    player.setPosition(new Vector3f(0, Settings.CHUNK_HEIGHT - i + 2, 0));
                    break;
                }
            }
        }

        // initialize debug lines
        for (int i = 0; i <= 10; i++)
            DebugManager.setDebugLine("", i);
    }

    @Override
    public void input() {
        cameraInc.set(0, 0, 0);

        // movement
        if(windowManager.isKeyPressed(GLFW.GLFW_KEY_W))
            cameraInc.z -= 1;
        if(windowManager.isKeyPressed(GLFW.GLFW_KEY_S))
            cameraInc.z += 1;
        if(windowManager.isKeyPressed(GLFW.GLFW_KEY_A))
            cameraInc.x -= 1;
        if(windowManager.isKeyPressed(GLFW.GLFW_KEY_D))
            cameraInc.x += 1;
        if(windowManager.isKeyPressed(GLFW.GLFW_KEY_SPACE))
            cameraInc.y += 1;
        if(windowManager.isKeyPressed(GLFW.GLFW_KEY_LEFT_SHIFT))
            cameraInc.y -= 1;

        // inventory
        if(InputHandler.isKeyPressedDown(GLFW.GLFW_KEY_LEFT_ALT) && !InputHandler.getIsMouseFree()) {
            InputHandler.freeMouseAlt();
        }
        if(InputHandler.isKeyReleased(GLFW.GLFW_KEY_LEFT_ALT) && InputHandler.getIsMouseFree()) {
            InputHandler.lockMouseAlt();
        }

        // toolbar
        if(windowManager.isKeyPressed(GLFW_KEY_1)) player.setCurrentItem(0);
        if(windowManager.isKeyPressed(GLFW_KEY_2)) player.setCurrentItem(1);
        if(windowManager.isKeyPressed(GLFW_KEY_3)) player.setCurrentItem(2);
        if(windowManager.isKeyPressed(GLFW_KEY_4)) player.setCurrentItem(3);
        if(windowManager.isKeyPressed(GLFW_KEY_5)) player.setCurrentItem(4);
        if(windowManager.isKeyPressed(GLFW_KEY_6)) player.setCurrentItem(5);
        if(windowManager.isKeyPressed(GLFW_KEY_7)) player.setCurrentItem(6);
        if(windowManager.isKeyPressed(GLFW_KEY_8)) player.setCurrentItem(7);
        if(windowManager.isKeyPressed(GLFW_KEY_9)) player.setCurrentItem(8);

        if(InputHandler.isKeyPressedDown(GLFW.GLFW_KEY_E)) {
            inInventory = !inInventory;
            if (inInventory) {
                InputHandler.stopAlting();
                InputHandler.freeMouse();
            } else {
                InputHandler.lockMouse();
            }
        }
        if(InputHandler.isKeyPressedDown(GLFW.GLFW_KEY_F3)) {
            inDebug = !inDebug;
        }

        if((InputHandler.isLeftMouseButtonPressedDown() || InputHandler.isRightMouseButtonPressedDown()) && !inInventory) player.resetBBcountDown();

        if (Mouse.isLeftButtonPress())
            if (inInventory) {
                player.modifyToolBar();
            } else {
                player.breakBlock();
            }

        if (Mouse.isRightButtonPress() && !inInventory) {
            player.placeBlock();
        }
    }

    @Override
    public void update() {
        SaveManager.autoSave(player, ChunkManager.getChunkList());

        player.movePosition(
                cameraInc.x * Settings.PLAYER_MOVE_SPEED,
                cameraInc.y * Settings.PLAYER_MOVE_SPEED,
                cameraInc.z * Settings.PLAYER_MOVE_SPEED
        );
        if (!InputHandler.getIsMouseFree()) {
            Vector2f rotVec = Mouse.getDisplayVec();
            player.getCamera().moveRotation(rotVec.x * Settings.CAMERA_SPEED, rotVec.y * Settings.CAMERA_SPEED, 0);
            Mouse.resetDelta();
        }
        player.sync();

        // block placing test

        // debug updates
        Vector3f pos = player.getPosition();
        Vector3f rot = player.getCamera().getRotation();
        String facingX = player.getFacingX() == FacingEnum.POSITIVE ? "positive" : "negative";
        String facingZ = player.getFacingZ() == FacingEnum.POSITIVE ? "positive" : "negative";
        Chunk currentChunk = ChunkManager.getChunk(player.getChunkPos().x, player.getChunkPos().y);

        Vector2i chunkCords = new Vector2i(0, 0);
        if (currentChunk != null) chunkCords = currentChunk.getChunkCordsFromGlobalCords(round(pos.x), round(pos.z));
        if (currentChunk == null) currentChunk = new Chunk(0, 0);

        if (inDebug) {
            DebugManager.updateDebugLine("player position X: " + round(pos.x) + " Y: " + round(pos.y) + " Z: " + round(pos.z), 1);
            DebugManager.updateDebugLine("player position X: " + pos.x + " Y: " + pos.y + " Z: " + pos.z, 2);
            DebugManager.updateDebugLine("player rotation X: " + round(rot.x) + " Y: " + round(rot.y), 3);
            DebugManager.updateDebugLine("facing X:" + facingX + " Z: " + facingZ, 4);
            DebugManager.updateDebugLine("player chunk position X: " + player.getChunkPos().x + " Z: " + player.getChunkPos().y, 6);
            DebugManager.updateDebugLine("chunk cords X: " + chunkCords.x + " Y: " + chunkCords.y, 8);
            DebugManager.updateDebugLine("current block: " + currentChunk.getBlock(chunkCords.x, round(pos.y), chunkCords.y), 9);
            DebugManager.updateDebugLine("seed: " + TerrainGeneration.getSeed(), 10);
        }
    }

    @Override
    public void render() {
        if (player == null) {
            try {
                dummyInit();
            } catch (Exception _) {}
        }
        // execute queued tasks in the window manager
        windowManager.processTasks();

        if(windowManager.isResize()) {
            GL11.glViewport(0, 0, windowManager.getWidth(), windowManager.getHeight());
            windowManager.setResize(true);
        }
        windowManager.setClearColor(0.79f, 1.0f, 1.0f, 1.0f);
        renderManager.clear();

        // world
        ChunkManager.render(renderManager, player.getCamera());
        if (inDebug) {
            renderManager.render(player.getRayCastDebugEntity(), player.getCamera());
            for (Entity plane : TerrainCollisionMap.getDebugPlanesList()) {
                renderManager.render(plane, player.getCamera());
            }
        }

        // GUI
        player.handBlock.render(renderManager);

        if (inInventory) {
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            player.inventory.render(renderManager);
            Inventory.render(renderManager);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
        }

        if(!inDebug)
            player.crossHair.render(renderManager);
        player.hand.render(renderManager);

        for (InventoryBlock inventoryBlock : player.inventoryBlocks) {
            inventoryBlock.render(renderManager);
        }
        player.toolBarGui.render(renderManager);

        TextManager.beginFrame();

        //debug

        if (inDebug) {
            DebugManager.renderDebug(renderManager);
        }
        // text

        TextManager.endFrame();
    }

    public void dummyInit() throws Exception{
        renderManager.init();
        Resources.init();
        TextureCoords.init();
        TerrainGeneration.init();

        // load data
        player = new Player();
    }

    @Override
    public void cleanUp() {
        SaveManager.saveWorld(ChunkManager.getChunkList());
        SaveManager.savePlayer(player);
        renderManager.cleanUp();
        loader.cleanUp();
    }
}
