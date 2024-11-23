package io.github.game.MaterialsPackage;
import com.badlogic.gdx.physics.box2d.World;

import com.badlogic.gdx.graphics.Texture;
import io.github.game.Mygame;

public class SquareBlock extends Block {
    public SquareBlock(String material, float a, float b, float c, float d, Mygame e,World world) {
        super(a, b, c, d, e,world);
        if (material.equals("wooden")) {
            this.setImg(new Texture("squareWood.png"));
        } else if (material.equals("Steel")) {
            this.setImg(new Texture("squareSteel.png"));
        } else {
            this.setImg(new Texture("squareGlass.png"));
        }
    }

}

