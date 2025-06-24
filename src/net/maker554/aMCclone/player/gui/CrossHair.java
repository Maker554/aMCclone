package net.maker554.aMCclone.player.gui;

import net.maker554.aMCclone.assets.Square;
import net.maker554.aMCclone.utils.Resources;
import renderEngine.models.Model;

public class CrossHair extends GuiElement {

    public CrossHair() {

        Square square = new Square(0.5f, 0.5f);
        square.setTexture(Resources.croosHairTexture);
        setEntity(square.getModel());
    }
}
