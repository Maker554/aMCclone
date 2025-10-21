package net.maker554.aMCclone.input;

import net.maker554.aMCclone.Client;
import org.joml.Vector2d;
import org.lwjgl.glfw.GLFW;
import renderEngine.WindowManager;

import java.util.HashMap;

public class InputHandler {

    private static final WindowManager windowManager = Client.getWindow();
    private static final HashMap<Integer, Boolean> keyDataDown = new HashMap<>();
    private static final HashMap<Integer, Boolean> keyDataUp = new HashMap<>();

    private static boolean mouse1Down = false;
    private static boolean mouse2Down = false;

    private static boolean isMouseFree;
    private static Vector2d positionHolder;
    private static boolean alting = false;

    public static boolean isKeyPressedDown(int keycode) {
        if (!keyDataDown.containsKey(keycode))
            keyDataDown.put(keycode, false);
        if (windowManager.isKeyPressed(keycode) && !keyDataDown.get(keycode)) {
            keyDataDown.put(keycode, true);
            return true;
        }
        if (!windowManager.isKeyPressed(keycode) && keyDataDown.get(keycode))
            keyDataDown.put(keycode, false);
        return false;
    }

    public static boolean isKeyReleased(int keycode) {
        if (!keyDataUp.containsKey(keycode))
            keyDataUp.put(keycode, false);
        if (windowManager.isKeyPressed(keycode) && !keyDataUp.get(keycode))
            keyDataUp.put(keycode, true);
        if (!windowManager.isKeyPressed(keycode) && keyDataUp.get(keycode)) {
            keyDataUp.put(keycode, false);
            return true;
        }
        return false;
    }

    public static boolean isLeftMouseButtonPressedDown() {
        // left button
        if (Mouse.isLeftButtonPress() && !mouse1Down) {
            mouse1Down = true;
            return true;
        }
        if (!Mouse.isLeftButtonPress() && mouse1Down)
            mouse1Down = false;
        return false;
    }

    public static boolean isRightMouseButtonPressedDown() {
        //right button
        if (Mouse.isRightButtonPress() && !mouse2Down) {
            mouse2Down = true;
            return true;
        }
        if (!Mouse.isRightButtonPress() && mouse2Down)
            mouse2Down = false;
        return false;
    }

    public static void disableMouseView() {
        GLFW.glfwSetInputMode(windowManager.getWindow(), GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);
        if (GLFW.glfwRawMouseMotionSupported()) {
            GLFW.glfwSetInputMode(windowManager.getWindow(), GLFW.GLFW_RAW_MOUSE_MOTION, GLFW.GLFW_TRUE);
        }
    }

    public static void enableMouseView() {
        GLFW.glfwSetInputMode(windowManager.getWindow(), GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
    }

    public static void centerMouse() {
        GLFW.glfwSetCursorPos(windowManager.getWindow(), (double) windowManager.getWidth() / 2, (double) windowManager.getHeight() / 2);
    }

    public static void freeMouse() {
        isMouseFree = true;
        positionHolder = Mouse.getPreviousPos();
        InputHandler.enableMouseView();
        centerMouse();
    }

    public static void freeMouseAlt() {
        freeMouse();
        alting = true;
    }

    public static void lockMouse() {
        isMouseFree = false;
        InputHandler.disableMouseView();
        GLFW.glfwSetCursorPos(windowManager.getWindow(), positionHolder.x, positionHolder.y);
        Mouse.resetDelta();
    }

    public static void lockMouseAlt() {
        if (alting)
            lockMouse();
        alting = false;
    }

    public static boolean getIsMouseFree() {
        return isMouseFree;
    }

    public static void stopAlting() {
        alting = false;
    }

    public static void setIsMouseFree(boolean choice) {
        isMouseFree = choice;
    }

    public static boolean isMouseAtCenter(Vector2d pos) {
        double centerX = windowManager.getWidth() / 2.0;
        double centerY = windowManager.getHeight() / 2.0;
        return Math.abs(pos.x - centerX) < 1.0 && Math.abs(pos.y - centerY) < 1.0;
    }
}
