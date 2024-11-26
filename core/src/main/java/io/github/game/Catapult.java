package io.github.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import io.github.game.BirdsPackage.Birds;
import io.github.game.MaterialsPackage.SquareBlock;
import org.w3c.dom.Text;

public class Catapult {
    private Texture catapultImg;
    private float length_of_x;
    private float length_of_y;
    private float positionX;
    private float positionY;
    private Mygame gameInstance;
    private Birds birdInstance;
    private boolean isBirdLoaded;
    public Catapult(Texture aa,float a, float b, float c, float d, Mygame e){
        catapultImg = aa;
        positionX = a;
        positionY = b;
        length_of_x = c;
        length_of_y = d;
        gameInstance = e;
        isBirdLoaded = false;
        birdInstance = null;
    }
    public void loadBird(Birds bird){
        birdInstance = bird;
        isBirdLoaded = true;
    }
    public void addCatapultOnScreen(){
        gameInstance.ourSpriteBatch.draw(catapultImg,positionX ,positionY ,length_of_x,length_of_y);
    }
    public Vector2 getPos(){
        return new Vector2(positionX,positionY);
    }
    public void disposeCatapult(){
        if(catapultImg!=null){
            catapultImg.dispose();
        }
    }
    public Rectangle launchBound(){
        Rectangle launchBounds = new Rectangle(
            positionX,
            positionY + length_of_y/2,
            length_of_x,
            length_of_y/2
        );
        return launchBounds;
    }
}
