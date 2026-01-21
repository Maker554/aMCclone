package net.maker554.aMCclone.input;

import net.maker554.aMCclone.Client;
import net.maker554.aMCclone.TestGame;
import org.joml.Vector2d;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

public class Mouse {

    private static final Vector2d previousPos = new Vector2d(-1,0);
    private static  Vector2d currentPos = new Vector2d(0,0);
    private static final Vector2f displayVec = new Vector2f();

    private static boolean leftButtonPress = false, rightButtonPress = false;

    public static void init(long window) {

        InputHandler.disableMouseView();

        GLFW.glfwSetCursorPosCallback(window, (win, xpos, ypos) -> {

            if (xpos < 0) {
                GLFW.glfwSetCursorPos(win, 500, ypos);
                previousPos.x = -1;
            }
            currentPos.x = xpos;
            currentPos.y = ypos;

            if (previousPos.x < 0) {
                previousPos.x = xpos;
                previousPos.y = ypos;
                return;
            }
            if (!InputHandler.getIsMouseFree()) {
                double deltaX = xpos - previousPos.x;
                double deltaY = ypos - previousPos.y;

                displayVec.y += (float) deltaX;
                displayVec.x += (float) deltaY;

                previousPos.x = xpos;
                previousPos.y = ypos;
            }
        });

        GLFW.glfwSetMouseButtonCallback(window, (window1, button, action, mods) -> {
            leftButtonPress = button == GLFW.GLFW_MOUSE_BUTTON_1 && action == GLFW.GLFW_PRESS;
            rightButtonPress = button == GLFW.GLFW_MOUSE_BUTTON_2 && action == GLFW.GLFW_PRESS;
        });

    }

    public static void resetDelta() {
        displayVec.x = 0;
        displayVec.y = 0;
    }

    public static boolean isLeftButtonPress() {
        return leftButtonPress;
    }

    public static boolean isRightButtonPress() {
        return rightButtonPress;
    }

    public static Vector2f getDisplayVec() {
        return displayVec;
    }

    public static Vector2d getPreviousPos() {
        return previousPos;
    }

    public static Vector2d getCurrentPos() {
        return new Vector2d(currentPos.x ,currentPos.y);
    }

    public static void setPreviousPos(int x, int y) {
        previousPos.x = x;
        previousPos.y = y;
    }
}
