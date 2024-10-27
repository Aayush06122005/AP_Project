package io.github.game.MaterialsPackage;

import com.badlogic.gdx.graphics.Texture;
import io.github.game.Mygame;

public abstract class Block {
    private Texture imgToShow;
    private float length_of_x;
    private float length_of_y;
    private float positionX;
    private float positionY;
    private Mygame gameInstance;

    public Block(float a, float b, float c, float d, Mygame e){
        positionX = a;
        positionY = b;
        length_of_x = c;
        length_of_y = d;
        gameInstance = e;
        imgToShow = new Texture("space.jpg");
    }

    public void setImg(Texture aa){
        this.imgToShow = aa;
    }

    public void addToScreen(){
        gameInstance.ourSpriteBatch.draw(imgToShow,positionX ,positionY ,length_of_x,length_of_y);
    }
    public void disposeFromScreen(){
        if(imgToShow!=null){
            imgToShow.dispose();
        }
    }
}
