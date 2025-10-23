package net.maker554.aMCclone.player.gui;

import net.maker554.aMCclone.assets.CubeFromAtlas;
import renderEngine.models.Model;

public class HandBlock extends GuiElement {

    public HandBlock(int blockId) {

        setRotation(180, 90, 0);
        setPosition(4, -2);

        CubeFromAtlas cube = new CubeFromAtlas(3f, 3f, 3f, blockId);
        Model model = cube.getModel();

        setEntity(model);
    }
}
