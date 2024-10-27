package io.github.game.PigsPackage;

import com.badlogic.gdx.graphics.Texture;
import io.github.game.Mygame;

public abstract class Pig {
    private Texture imageOfPig;
    private float length_of_x;
    private float length_of_y;
    private float positionX;
    private float positionY;
    private Mygame gameInstance;

    public Pig(Texture aa,float a, float b, float c, float d, Mygame e){
        imageOfPig = aa;
        positionX = a;
        positionY = b;
        length_of_x = c;
        length_of_y = d;
        gameInstance = e;
    }

    public void addPigOnScreen(){
        gameInstance.ourSpriteBatch.draw(imageOfPig,positionX ,positionY ,length_of_x,length_of_y);
    }
    public void disposePig(){
        if(imageOfPig!=null){
            imageOfPig.dispose();
        }
    }
}
