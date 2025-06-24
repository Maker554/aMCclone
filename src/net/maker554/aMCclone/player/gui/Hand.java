package net.maker554.aMCclone.player.gui;

import net.maker554.aMCclone.assets.Cube;
import net.maker554.aMCclone.utils.Resources;
import org.joml.Vector3f;
import renderEngine.models.Model;

public class Hand extends GuiElement {

    public Hand() {

        setPosition(7.6f,-3.6f);
        rotation = new Vector3f(20, 100, 0);

        Cube cube = new Cube(2.6f, 6.0f, 2.6f);
        cube.setTexture(Resources.emptyTexture);
        Model model = cube.getModel();

        setEntity(model);
    }
}
