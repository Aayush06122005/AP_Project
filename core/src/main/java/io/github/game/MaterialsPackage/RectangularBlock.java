package io.github.game.MaterialsPackage;
import com.badlogic.gdx.physics.box2d.World;

import com.badlogic.gdx.graphics.Texture;
import io.github.game.Mygame;

public class RectangularBlock extends Block {
    public RectangularBlock(String material, float a, float b, float c, float d, Mygame e,World world) {
        super(a, b, c, d, e,world);
        if (material.equals("wooden")) {
            this.setImg(new Texture("rectangleWooden.png"));
            this.setHealth(2);
        } else if (material.equals("steel")) {
            this.setImg(new Texture("rectangleSteel.png"));
            this.setHealth(3);
        } else {
            this.setImg(new Texture("rectangleGlass.png"));
            this.setHealth(1);
        }
    }

}

