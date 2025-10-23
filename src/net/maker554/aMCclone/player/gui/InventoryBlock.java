package net.maker554.aMCclone.player.gui;

import net.maker554.aMCclone.assets.SquareFromAtlas;
import renderEngine.models.Model;

public class InventoryBlock extends GuiElement{

    public InventoryBlock(int blockId) {

        setRotation(180f, 90f, 0f);

        SquareFromAtlas square = new SquareFromAtlas(0.55f, 0.55f, blockId);
        Model model = square.getModel();

        setEntity(model);
    }

    public void setPosition(float x, float y) {
        super.setPosition(x, y);
    }
}
