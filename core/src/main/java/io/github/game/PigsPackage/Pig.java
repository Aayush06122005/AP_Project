package io.github.game.PigsPackage;

import com.badlogic.gdx.graphics.Texture;
import io.github.game.Mygame;
import com.badlogic.gdx.physics.box2d.*;
public abstract class Pig {
    private Texture imageOfPig;
    private float length_of_x;
    private float length_of_y;
    private float positionX;
    private float positionY;
    private Mygame gameInstance;
    private Body body;
    private int health;
    public Pig(Texture aa,float a, float b, float c, float d, Mygame e,int h,World ourWorld){
        imageOfPig = aa;
        positionX = a;
        positionY = b;
        length_of_x = c;
        length_of_y = d;
        gameInstance = e;
        health = h;


        CircleShape shapePig = new CircleShape();
        shapePig.setRadius(length_of_x / 2 / gameInstance.pixelPerMeter);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(a + (c/2), b + (d/2));

        body = ourWorld.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shapePig;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.5f;
        body.createFixture(fixtureDef);

        shapePig.dispose();

    }

    public void addPigOnScreen(){
        gameInstance.ourSpriteBatch.draw(imageOfPig,positionX ,positionY ,length_of_x,length_of_y);
    }
    public void disposePig(){
        if(imageOfPig!=null){
            imageOfPig.dispose();
        }
    }

    public void handleDamage(int damage){
        health -= damage;
        if(health<=0){
            disposePig();
        }
    }
}
