package io.github.game.MaterialsPackage;

import com.badlogic.gdx.graphics.Texture;
import io.github.game.Mygame;

public class TriangularBlock extends Block {
    public TriangularBlock(String material, float a, float b, float c, float d, Mygame e) {
        super(a, b, c, d, e);
        if (material.equals("wooden")) {
            this.setImg(new Texture("triangleWooden.png"));
        } else if (material.equals("Steel")) {
            this.setImg(new Texture("triangleSteel.png"));
        } else {
            this.setImg(new Texture("triangleGlass.png"));
        }
    }

}
