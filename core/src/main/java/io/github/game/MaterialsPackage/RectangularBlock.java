package io.github.game.MaterialsPackage;

import com.badlogic.gdx.graphics.Texture;
import io.github.game.Mygame;

public class RectangularBlock extends Block {
    public RectangularBlock(String material, float a, float b, float c, float d, Mygame e) {
        super(a, b, c, d, e);
        if (material.equals("wooden")) {
            this.setImg(new Texture("rectangleWooden.png"));
        } else if (material.equals("steel")) {
            this.setImg(new Texture("rectangleSteel.png"));
        } else {
            this.setImg(new Texture("rectangleGlass.png"));
        }
    }

}

