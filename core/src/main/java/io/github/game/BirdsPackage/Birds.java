package io.github.game.BirdsPackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import io.github.game.Mygame;

public abstract class Birds {
    private Texture imageOfBird;
    private float length_of_x;
    private float length_of_y;
    private float positionX;
    private float positionY;
    private Mygame gameInstance;
    private Body birdBody;
    public Birds(Texture aa, float a, float b, float c, float d, Mygame e, World ourWorld){
        imageOfBird = aa;
        positionX = a;
        positionY = b;
        length_of_x = c;
        length_of_y = d;
        gameInstance = e;

        BodyDef bodyDefiner = new BodyDef();
        bodyDefiner.type = BodyDef.BodyType.DynamicBody;
        bodyDefiner.position.set(a, b);
        birdBody = ourWorld.createBody(bodyDefiner);

        CircleShape birdShape = new CircleShape();
        birdShape.setRadius(length_of_x / 2 / gameInstance.pixelPerMeter);

        FixtureDef fixtureDefine = new FixtureDef();
        fixtureDefine.shape = birdShape;
        fixtureDefine.density = 1.0f;
        fixtureDefine.friction = 0.3f;
        fixtureDefine.restitution = 0.6f;

        birdBody.createFixture(fixtureDefine);
        birdShape.dispose();
    }

    public void addBirdOnScreen(){
        Vector2 birdPosition = birdBody.getPosition();
        gameInstance.ourSpriteBatch.draw(imageOfBird,birdPosition.x * gameInstance.pixelPerMeter -length_of_x/2,birdPosition.y * gameInstance.pixelPerMeter - length_of_y/2,length_of_x,length_of_y);
    }
    public void disposeBird(){
        if(imageOfBird!=null){
            imageOfBird.dispose();
        }
    }
}
