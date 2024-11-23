package io.github.game.MaterialsPackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import io.github.game.Mygame;

public abstract class Block {
    private Texture imgToShow;
    private float length_of_x;
    private float length_of_y;
    private float positionX;
    private float positionY;
    private Mygame gameInstance;
    private Body body;


    public Block(float a, float b, float c, float d, Mygame e,World world){
        positionX = a;
        positionY = b;
        length_of_x = c;
        length_of_y = d;
        gameInstance = e;
        imgToShow = new Texture("space.jpg");

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(a, b);
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(c / 2, d / 2);
        body.createFixture(shape, 0.0f);
        shape.dispose();
    }

    public void setImg(Texture aa){
        this.imgToShow = aa;
    }

    public void addToScreen(){
        Vector2 position = body.getPosition();
        gameInstance.ourSpriteBatch.draw(imgToShow,positionX ,positionY ,length_of_x,length_of_y);
    }
    public void disposeFromScreen(){
        if(imgToShow!=null){
            imgToShow.dispose();
        }
    }
}
