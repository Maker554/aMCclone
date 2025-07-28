package renderEngine.text;

import net.maker554.aMCclone.Client;
import org.joml.Vector4i;
import org.lwjgl.BufferUtils;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NanoVG;
import org.lwjgl.nanovg.NanoVGGL3;
import renderEngine.WindowManager;

import java.nio.FloatBuffer;

public class TextManager {

    private static final long vg = NanoVGGL3.nvgCreate(NanoVGGL3.NVG_ANTIALIAS | NanoVGGL3.NVG_STENCIL_STROKES);
    private static final WindowManager windowManager = Client.getWindow();

    private static final String DEFAULT_FONT = "default";
    private static final Vector4i DEFAULT_COLOR = new Vector4i(0, 0, 0, 255);

    public static void init() {
        int font = NanoVG.nvgCreateFont(vg, "default", "resources/fonts/BrownieStencil.ttf");
        if (font == -1) {
            throw new RuntimeException("Failed to load font.");
        }
    }

    public static void render(String text, int posX, int posY, float size, String font, Vector4i color) {
        NanoVG.nvgBeginFrame(vg, windowManager.getWidth(), windowManager.getHeight(), 1);
        NanoVG.nvgFontSize(vg, size);
        NanoVG.nvgFontFace(vg, font);

        NVGColor colorAlloc = NVGColor.calloc();
        NanoVG.nvgRGBA((byte)color.x, (byte)color.y, (byte) color.z, (byte)color.w, colorAlloc);
        NanoVG.nvgFillColor(vg, colorAlloc);

        NanoVG.nvgText(vg, posX, posY, text);

        NanoVG.nvgEndFrame(vg);
    }

    public static void render(String text, int posX, int posY, float size) {
        render(text, posX, posY, size, DEFAULT_FONT, DEFAULT_COLOR);
    }

    public static void render(String text, int posX, int posY, float size, String font) {
        render(text, posX, posY, size, font, DEFAULT_COLOR);
    }

    public static void render(String text, int posX, int posY, float size, Vector4i color) {
        render(text, posX, posY, size, DEFAULT_FONT, color);
    }

    public static void renderWithBackground(String text, int posX, float posY, float size, String font, Vector4i color, Vector4i backgroundColor) {
        FloatBuffer bounds = BufferUtils.createFloatBuffer(4);
        NanoVG.nvgFontSize(vg, size);
        NanoVG.nvgFontFace(vg, font);
        NanoVG.nvgTextBounds(vg, posX, posY, text, bounds);

        float bgX = bounds.get(0);
        float bgY = bounds.get(1);
        float bgWidth = bounds.get(2) - bounds.get(0);
        float bgHeight = bounds.get(3) - bounds.get(1);

        NVGColor bgColor = NVGColor.calloc();
        NanoVG.nvgRGBA((byte)backgroundColor.x, (byte)backgroundColor.y, (byte)backgroundColor.z, (byte)backgroundColor.w, bgColor);
        NanoVG.nvgBeginPath(vg);
        NanoVG.nvgRect(vg, bgX - 8, bgY - 8, bgWidth + 24, bgHeight + 8);  // Add padding
        NanoVG.nvgFillColor(vg, bgColor);
        NanoVG.nvgFill(vg);

        NVGColor textColor = NVGColor.calloc();
        NanoVG.nvgRGBA((byte)color.x, (byte)color.y, (byte) color.z, (byte)color.w, textColor);
        NanoVG.nvgFillColor(vg, textColor);
        NanoVG.nvgText(vg, posX, posY, text);

        bgColor.free();
        textColor.free();
    }

    public static void beginFrame() {
        NanoVG.nvgBeginFrame(vg, windowManager.getWidth(), windowManager.getHeight(), 1);
    }

    public static void endFrame() {
        NanoVG.nvgEndFrame(vg);
    }
}
