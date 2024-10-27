package io.github.game;

import com.badlogic.gdx.graphics.Texture;
import org.w3c.dom.Text;

public class Catapult {
    private Texture catapultImg;
    private float length_of_x;
    private float length_of_y;
    private float positionX;
    private float positionY;
    private Mygame gameInstance;

    public Catapult(Texture aa,float a, float b, float c, float d, Mygame e){
        catapultImg = aa;
        positionX = a;
        positionY = b;
        length_of_x = c;
        length_of_y = d;
        gameInstance = e;
    }

    public void addCatapultOnScreen(){
        gameInstance.ourSpriteBatch.draw(catapultImg,positionX ,positionY ,length_of_x,length_of_y);
    }
    public void disposeCatapult(){
        if(catapultImg!=null){
            catapultImg.dispose();
        }
    }
}
