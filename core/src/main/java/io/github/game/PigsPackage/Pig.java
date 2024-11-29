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
    private int hitPoint;

    public Pig(Texture aa, float a, float b, float c, float d, Mygame e, int h, World ourWorld) {
        imageOfPig = aa;
        positionX = a;
        positionY = b;
        length_of_x = c;
        length_of_y = d;
        gameInstance = e;
        hitPoint = h;


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

        body.createFixture(fixtureDef);

        shapePig.dispose();
        body.setAngularDamping(0.3f);



    }

    public void addPigOnScreen() {
        float pigBodyRotation = body.getAngle() * MathUtils.radiansToDegrees;
        positionX = body.getPosition().x * gameInstance.pixelPerMeter - length_of_x / 2;
        positionY = body.getPosition().y * gameInstance.pixelPerMeter - length_of_y / 2;
        TextureRegion pigRegion = new TextureRegion(imageOfPig);
        gameInstance.ourSpriteBatch.draw(pigRegion, positionX, positionY, length_of_x / 2, length_of_y / 2, length_of_x, length_of_y, 1, 1, pigBodyRotation);
    }

    public void disposePig() {
        if (imageOfPig != null) {
            imageOfPig.dispose();
        }
    }

    public void handleDamage(int damage) {
        hitPoint -= damage;
        if (hitPoint <= 0) {
            disposePig();
        }
    }
}

//    public ContactListener collison(Pig p){
//        return new ContactListener() {
//            @Override
//            public void beginContact(Contact contact) {
//
//                Body bodyA = contact.getFixtureA().getBody();
//                Body bodyB = contact.getFixtureB().getBody();
//
//                if(( b.birdBody == bodyA || birdBody == bodyB)){
//                    b.birdState = "dying";
////                    System.out.println("Bird Touched");
//                    b.birdHealth -= 1;
//                    System.out.println("Bird Health : " + b.birdHealth);
//                }
//            }
//
//            @Override
//            public void endContact(Contact contact) {
//
//            }
//
//            @Override
//            public void preSolve(Contact contact, Manifold oldManifold) {
//
//            }
//
//            @Override
//            public void postSolve(Contact contact, ContactImpulse impulse) {
//
//            }
//        };
//    }
//
//}



