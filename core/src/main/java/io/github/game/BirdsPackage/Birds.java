package io.github.game.BirdsPackage;

import com.badlogic.gdx.graphics.Texture;
import io.github.game.Mygame;

public abstract class Birds {
    private Texture imageOfBird;
    private float length_of_x;
    private float length_of_y;
    private float positionX;
    private float positionY;
    private Mygame gameInstance;

    public Birds(Texture aa,float a, float b, float c, float d, Mygame e){
        imageOfBird = aa;
        positionX = a;
        positionY = b;
        length_of_x = c;
        length_of_y = d;
        gameInstance = e;
    }

    public void addBirdOnScreen(){
        gameInstance.ourSpriteBatch.draw(imageOfBird,positionX ,positionY ,length_of_x,length_of_y);
    }
    public void disposeBird(){
        if(imageOfBird!=null){
            imageOfBird.dispose();
        }
    }
}
