package net.maker554.aMCclone.player.gui;

import net.maker554.aMCclone.assets.Square;
import net.maker554.aMCclone.utils.Resources;
import renderEngine.models.Model;

public class ToolBar extends GuiElement{

    public ToolBar() {

        setPosition(0, -4.8f);

        Square square = new Square(1.1f, 11.7f);
        square.setTexture(Resources.toolBarTexture);
        Model model = square.getModel();

        setEntity(model);
    }
}
