package net.maker554.aMCclone.player.gui;

import net.maker554.aMCclone.assets.Square;
import renderEngine.models.Model;

public class InventoryGui extends GuiElement{

    public InventoryGui() {

        setPosition(0, 1);
        Square square = new Square(10f, 10f);
        Model model = square.getModel();

        setEntity(model);
    }
}
