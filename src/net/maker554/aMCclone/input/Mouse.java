package net.maker554.aMCclone.input;

import net.maker554.aMCclone.Client;
import org.joml.Vector2d;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import java.util.ArrayList;
import java.util.List;

public class Mouse {

    private static Vector2f movement = new Vector2f(0 ,0);
    private static boolean[] buttons = new boolean[5];

    public static boolean movingCamera = true;

    private static final float SENSIBILITY = 0.3f;

    public static void init(long window) {

        GLFW.glfwSetMouseButtonCallback(window, new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                if (action == GLFW.GLFW_PRESS) {
                    System.out.println("Mouse button " + button + " pressed.");
                    buttons[button] = true;
                } else if (action == GLFW.GLFW_RELEASE) {
                    System.out.println("Mouse button " + button + " released.");
                    buttons[button] = false;
                }
            }
        });

        GLFW.glfwSetCursorPosCallback(window, new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                if (movingCamera) {
                    // basic camera movement
                    Vector2i center = new Vector2i(Client.getWindow().getHeight() / 4, Client.getWindow().getWidth() / 4);
                    movement.x = ((float) xpos - center.x) * SENSIBILITY;
                    movement.y = ((float) ypos - center.y) * SENSIBILITY;
                    GLFW.glfwSetCursorPos(window, center.x, center.y);
                } else {
                    movement.x = 0;
                    movement.y = 0;
                }
            }
        });
    }

    public static Vector2f getMovement() {
        return movement;
    }
}
