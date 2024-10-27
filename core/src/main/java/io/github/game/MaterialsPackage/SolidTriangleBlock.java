package io.github.game.MaterialsPackage;

import com.badlogic.gdx.graphics.Texture;
import io.github.game.Mygame;

public class SolidTriangleBlock extends Block {
    public SolidTriangleBlock(String material, float a, float b, float c, float d, Mygame e) {
        super(a, b, c, d, e);
        if (material.equals("wooden")) {
            this.setImg(new Texture("solidTriangleWood.png"));
        } else if (material.equals("Steel")) {
            this.setImg(new Texture("solidTriangleSteel.png"));
        } else {
            this.setImg(new Texture("solidTriangleGlass.png"));
        }
    }

}
