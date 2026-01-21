package net.maker554.aMCclone.player;

import net.maker554.aMCclone.Client;
import net.maker554.aMCclone.input.Mouse;
import net.maker554.aMCclone.player.gui.HandBlock;
import net.maker554.aMCclone.player.gui.InventoryBlock;
import net.maker554.aMCclone.utils.TextureCoords;
import org.joml.Vector2d;
import renderEngine.RenderManager;
import renderEngine.WindowManager;

import java.util.*;

public class Inventory {

    private static final float PADDING = 0.1f;

    public static List<Integer> selectableBlocks;
    public static List<InventoryBlock> selectableBlockGui;

    public static int selectedBlockId;

    public static void init() {

        selectedBlockId = 0;

        selectableBlocks =  new ArrayList<>();
        selectableBlockGui = new ArrayList<>();

        // adds all block ids available to inventory
        selectableBlocks.addAll(TextureCoords.textureMappings.keySet());

        for (int i=0;i<selectableBlocks.size();i++) {

            float x = i % 8 - 3.4f;
            float y = (i - x) / 8 - 2;

            InventoryBlock bl = new InventoryBlock(selectableBlocks.get(i));
            bl.setPosition(x, y);
            selectableBlockGui.add(bl);
        }
    }

    public static void render(RenderManager renderManager) {
        for (InventoryBlock inventoryBlock : selectableBlockGui) {
            inventoryBlock.render(renderManager);
        }
    }

    public static void selectBlock(ToolBar toolBar) {

        int width = Client.getWindow().getWidth();
        int height = Client.getWindow().getHeight();

        double box = height / 21.8f;
        double padding = height / 24.83f;
        Vector2d mousePos = Mouse.getCurrentPos().add(new Vector2d(-(width - height / 1.578947368) / 2, -(height - height / 1.578947368) / 2));

        int slotX = -1;
        if (mousePos.x % (box + padding) <= box) {
            slotX = (int) Math.floor(mousePos.x / (box + padding));
        }
        int slotY = -1;
        if (-mousePos.y % (box + padding) <= box) {
            slotY = - (int) Math.floor(mousePos.y / (box + padding)) - 1;
        }

        if (slotX !=-1 && slotY !=-1) {
            int index = slotX + (slotY) * 8;
            if (index >= 0 && index < selectableBlocks.size()) {
                selectedBlockId = selectableBlocks.get(index);
                toolBar.modify(selectedBlockId);
            }
        }
    }
}
