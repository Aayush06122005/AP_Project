package io.github.game.PigsPackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import io.github.game.Mygame;

public class Pig1 extends Pig {
    public Pig1(float a, float b, float c, float d, Mygame e, World ourWorld){
        super(new Texture("pig1.png"),a,b,c,d,e,2,ourWorld,100);
    }

}
