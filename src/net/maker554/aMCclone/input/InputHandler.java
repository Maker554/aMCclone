package net.maker554.aMCclone.input;

import net.maker554.aMCclone.Client;
import renderEngine.WindowManager;

import java.util.HashMap;

public class InputHandler {

    private static final WindowManager windowManager = Client.getWindow();

    private static final HashMap<Integer, Boolean> keyData = new HashMap<>();

    public static boolean isKeyPressedDown(int keycode) {
        if (!keyData.containsKey(keycode))
            keyData.put(keycode, false);
        if (windowManager.isKeyPressed(keycode) && keyData.get(keycode) == false) {
            keyData.put(keycode, true);
            return true;
        }
        if (!windowManager.isKeyPressed(keycode))
            keyData.put(keycode, false);
        return false;
    }
}
