package io.github.game.BirdsPackage;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import io.github.game.Catapult;
import io.github.game.GameScreen;
import io.github.game.Mygame;
import io.github.game.Player;

public abstract class Birds {
    private Texture imageOfBird;
    private float length_of_x;
    private float length_of_y;
    private float positionX;
    private float positionY;
    private Mygame gameInstance;
    private Body birdBody;
    public String birdState;
    public int birdHealth;
    public Birds(Texture aa, float a, float b, float c, float d, Mygame e, World ourWorld,int birdhealth){
        imageOfBird = aa;
        positionX = a;
        positionY = b;
        length_of_x = c;
        length_of_y = d;
        gameInstance = e;
        birdState = "ground";
        birdHealth = birdhealth;

        BodyDef bodyDefiner = new BodyDef();
        bodyDefiner.type = BodyDef.BodyType.StaticBody;
        bodyDefiner.position.set(a, b);
        birdBody = ourWorld.createBody(bodyDefiner);
        CircleShape birdShape = new CircleShape();
        birdShape.setRadius(length_of_x / 2 / gameInstance.pixelPerMeter);

        FixtureDef fixtureDefine = new FixtureDef();
        fixtureDefine.shape = birdShape;
        fixtureDefine.density = 1.0f;
        fixtureDefine.friction = 0.7f;
        fixtureDefine.restitution = 0.3f;

        birdBody.createFixture(fixtureDefine);
        birdShape.dispose();
    }
    public void launch(float ang,float sp){
        birdBody.setLinearVelocity((float)Math.cos(ang) * sp, (float)Math.sin(ang) * sp);
    }
    public Body getBirdBody(){
        return birdBody;
    }
    public void addBirdOnScreen(){
        if (imageOfBird != null) {
            Vector2 birdPosition = birdBody.getPosition();
            gameInstance.ourSpriteBatch.draw(imageOfBird, birdPosition.x * gameInstance.pixelPerMeter - length_of_x / 2, birdPosition.y * gameInstance.pixelPerMeter - length_of_y / 2, length_of_x, length_of_y);
        }
    }
    public void disposeBird(){
        if(imageOfBird!=null){
            imageOfBird.dispose();
            imageOfBird = null;
        }
    }
    public Circle birdBound() {
        return new Circle(
            this.getBirdBody().getPosition().x,  // Center X
            this.getBirdBody().getPosition().y,  // Center Y
            (float) Math.sqrt(Math.pow(length_of_x / 2, 2) + Math.pow(length_of_y / 2, 2)) // Radius
        );
    }
    public void ifInsideCatapult(Catapult c, OrthographicCamera myCamera){
        Rectangle boundsToCheck = c.launchBound();
//        System.out.println("worldClick : " + worldClick.x + " " + worldClick.y );
        if (boundsToCheck.contains(this.getBirdBody().getPosition().x, this.getBirdBody().getPosition().y)) {
            System.out.println("Bird inside catapult!");
            this.getBirdBody().setType(BodyDef.BodyType.StaticBody);
        }else{
            if(!GameScreen.birdToched(this,myCamera)){
                this.getBirdBody().setType(BodyDef.BodyType.DynamicBody);
            }

        }

    }

    public ContactListener collison(Birds b){
        return new ContactListener() {
            @Override
            public void beginContact(Contact contact) {

                Body bodyA = contact.getFixtureA().getBody();
                Body bodyB = contact.getFixtureB().getBody();

                if(( b.birdBody == bodyA || birdBody == bodyB)){
                    b.birdState = "dying";
//                    System.out.println("Bird Touched");
                    b.birdHealth -= 1;
                    System.out.println("Bird Health : " + b.birdHealth);
                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        };
    }

}
