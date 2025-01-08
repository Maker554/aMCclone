package net.maker554.aMCclone.input;

import org.joml.Vector2d;
import org.joml.Vector2f;

public class Mouse {

    private final Vector2d previousPos, currentPos;
    //private final Vector2f displayVec;

    private boolean inWindow = false, leftButtonPress = false, rightButtonPress = false;

    public Mouse() {
        previousPos = new Vector2d(-1, -1);
        currentPos = new Vector2d(0, 0);
    }

}
