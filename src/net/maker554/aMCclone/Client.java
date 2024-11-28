package net.maker554.aMCclone;

import renderEngine.EngineManager;
import renderEngine.WindowManager;
import renderEngine.utils.Consts;

public class Client {

    private static WindowManager window;
    private static TestGame game;

    public static void init() {
        window = new WindowManager(Consts.TITLE, 1280, 720, true);
        game = new TestGame(); // game declaration
        EngineManager engine = new EngineManager(); // start of the program
        try {
            engine.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static WindowManager getWindow() {
        return window;
    }

    public static TestGame getGame() {
        return game;
    }
}
