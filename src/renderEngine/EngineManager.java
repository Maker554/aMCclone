package renderEngine;

import net.maker554.aMCclone.Client;
import net.maker554.aMCclone.input.Mouse;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import renderEngine.text.TextManager;
import renderEngine.utils.Consts;
import renderEngine.utils.ILogic;

public class EngineManager {

    public static final long NANOSECOND = 1000000000L;
    public static final float FRAMERATE = 1000;

    public static int fps;
    private static float frametime = 1.0f / FRAMERATE;

    private boolean isRunning;

    private WindowManager window; // gotten from the Client class
    private ILogic gameLogic; // gotten from the Client class
    private GLFWErrorCallback errorCallback;

    private void init() throws Exception {
        GLFW.glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
        window = Client.getWindow();
        gameLogic = Client.getGame();
        window.init();
        gameLogic.init();
        Mouse.init(window.getWindow());
        TextManager.init();
    }

    public void start() throws Exception {
        init();
        if (isRunning)
            return;
        run();
    }

    public void run() {
        isRunning = true;
        int frames = 0;
        long frameCounter = 0;
        long lastTime = System.nanoTime();
        double unprocessedTime = 0;

        while (isRunning) {
            boolean render = false;
            long startTime = System.nanoTime();
            long passedTime = startTime - lastTime;
            lastTime = startTime;

            unprocessedTime += passedTime / (double) NANOSECOND;
            frameCounter += passedTime;

            input();

            while(unprocessedTime > frametime) {
                render = true;
                unprocessedTime -= frametime;

                if(window.windowShouldClose())
                    stop();

                if(frameCounter >= NANOSECOND) {
                    setFps(frames);
                    window.setTitle(Consts.TITLE + "  FPS: " + getFps());
                    frames = 0;
                    frameCounter = 0;
                }
            }

            if(render) {
                update();
                render();
                frames++;
            }
        }
        cleanUp();
    }

    private void stop() {
        if(!isRunning)
            return;
        isRunning = false;
    }

    private void input() {
        gameLogic.input();
    }

    private void render() {
        gameLogic.render();
        window.resetGLState();
        window.update();
    }

    private void update() {
        gameLogic.update();
    }

    private void cleanUp() {
        gameLogic.cleanUp();
        window.cleanUp();
        errorCallback.free();
        GLFW.glfwTerminate();
    }

    public static int getFps() {
        return fps;
    }

    public static void setFps(int fps) {
        EngineManager.fps = fps;
    }
}
