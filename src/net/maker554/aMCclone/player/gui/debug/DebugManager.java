package net.maker554.aMCclone.player.gui.debug;

import org.joml.Vector4i;
import renderEngine.RenderManager;
import renderEngine.text.TextManager;

import java.util.ArrayList;
import java.util.List;

public class  DebugManager {

    private static final float TEXT_SIZE = 25f;

    private static final List<DebugText> texts = new ArrayList<>();
    private static int VERTICAL_OFFSET = 20;
    private static int ORIZONTAL_OFFSET = 8;

    public static void setDebugLine(String text, int index) {
        texts.add(index, new DebugText(
                text,
                ORIZONTAL_OFFSET,
                (VERTICAL_OFFSET + (index+1) * (TEXT_SIZE + 7.95f))
        ));
    }

    public static void updateDebugLine(String text, int index) {
        texts.get(index).text = text;
    }

    public static void renderDebug(RenderManager renderManager) {
        for(DebugText text : texts) {
            if(!text.text.isEmpty())
                TextManager.renderWithBackground(
                        text.text,
                        text.posX,
                        text.posY,
                        TEXT_SIZE,
                        "default",
                        new Vector4i(255, 255, 255, 255),
                        new Vector4i(40, 40, 40, 100)
                );
        }
    }
}
