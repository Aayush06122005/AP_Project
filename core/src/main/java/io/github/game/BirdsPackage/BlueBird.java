package io.github.game.BirdsPackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import io.github.game.Mygame;

public class BlueBird extends Birds {
    public BlueBird(float a, float b, float c, float d, Mygame e, World ourWorld){
        super(new Texture("angryBird3.png"),a,b,c,d,e,ourWorld,8);
    }

}
