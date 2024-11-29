package io.github.game.MaterialsPackage;
import com.badlogic.gdx.physics.box2d.World;

import com.badlogic.gdx.graphics.Texture;
import io.github.game.Mygame;

public class SolidTriangleBlock extends Block {
    public SolidTriangleBlock(String material, float a, float b, float c, float d, Mygame e,World world) {
        super(a, b, c, d, e,world);
        if (material.equals("wooden")) {
            this.setImg(new Texture("solidTriangleWood.png"));
            this.setHealth(2);
        } else if (material.equals("Steel")) {
            this.setImg(new Texture("solidTriangleSteel.png"));
            this.setHealth(3);
        } else {
            this.setImg(new Texture("solidTriangleGlass.png"));
            this.setHealth(1);
        }
    }

}
