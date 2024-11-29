package io.github.game.PigsPackage;

import com.badlogic.gdx.graphics.Texture;

import io.github.game.BirdsPackage.Birds;
import io.github.game.Mygame;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;
public abstract class Pig {
    private Texture imageOfPig;
    private float length_of_x;
    private float length_of_y;
    private float positionX;
    private float positionY;
    private Mygame gameInstance;
    private Body body;
    public String state;
    private int hitPoint;
    private boolean effected;
    private World ourWorld;

    public Pig(Texture aa, float a, float b, float c, float d, Mygame e, int h, World world) {
        imageOfPig = aa;
        positionX = a;
        positionY = b;
        length_of_x = c;
        length_of_y = d;
        gameInstance = e;
        ourWorld = world;
        hitPoint = h;
        effected = false;
        state = "alive";


        CircleShape shapePig = new CircleShape();
        shapePig.setRadius(length_of_x / 2 / gameInstance.pixelPerMeter);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(a + (c / 2), b + (d / 2));

        body = ourWorld.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shapePig;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.5f;
        fixtureDef.filter.categoryBits = (short)0x0003;
        this.body.setUserData(this);

        body.createFixture(fixtureDef);

        shapePig.dispose();
        body.setAngularDamping(0.3f);



    }

    public void addPigOnScreen() {
        if(imageOfPig != null){
            float pigBodyRotation = body.getAngle() * MathUtils.radiansToDegrees;
            positionX = body.getPosition().x * gameInstance.pixelPerMeter - length_of_x / 2;
            positionY = body.getPosition().y * gameInstance.pixelPerMeter - length_of_y / 2;
            TextureRegion pigRegion = new TextureRegion(imageOfPig);
            gameInstance.ourSpriteBatch.draw(pigRegion, positionX, positionY, length_of_x / 2, length_of_y / 2, length_of_x, length_of_y, 1, 1, pigBodyRotation);
        }

    }

    public Body getBody(){
        return body;
    }

    public void disposePig() {
        //ourWorld.destroyBody(body);
        if (imageOfPig != null) {
            imageOfPig.dispose();
            imageOfPig = null;
        }
    }


    public boolean isEffected(){
        return effected;
    }
    public void setEffected(boolean cond){
        effected = cond;
    }
    public int getHealth(){
        return  hitPoint;
    }
    public void deathHandler(int damage){
        hitPoint -= damage;
        if(hitPoint <= 0){
            state = "destroy";
        }
    }
}




