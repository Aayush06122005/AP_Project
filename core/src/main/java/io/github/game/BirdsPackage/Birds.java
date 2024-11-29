package io.github.game.BirdsPackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import io.github.game.Mygame;

public abstract class Birds {
    public Texture imageOfBird;
    private float length_of_x;
    private float length_of_y;
    private float positionX;
    private float positionY;
    private Mygame gameInstance;
    private Body birdBody;
    public String birdState;
    public int birdHealth;
    private int damage;
    public String category;
    public Birds(Texture aa, float a, float b, float c, float d, Mygame e, World ourWorld,int birdhealth,int hit){
        imageOfBird = aa;
        positionX = a;
        positionY = b;
        length_of_x = c;
        length_of_y = d;
        gameInstance = e;
        birdState = "ground";
        birdHealth = birdhealth;
        category = "Bird";
        damage = hit;

        BodyDef bodyDefiner = new BodyDef();
        bodyDefiner.type = BodyDef.BodyType.StaticBody;
        bodyDefiner.position.set(a, b);
        birdBody = ourWorld.createBody(bodyDefiner);
        CircleShape birdShape = new CircleShape();
        birdShape.setRadius(length_of_x / 2 / gameInstance.pixelPerMeter);

        FixtureDef fixtureDefine = new FixtureDef();
        fixtureDefine.shape = birdShape;
        fixtureDefine.density = 20f;
        fixtureDefine.friction = 0.8f;
        fixtureDefine.restitution = 0.7f;
        this.birdBody.setUserData(this);

        birdBody.createFixture(fixtureDefine);
        birdShape.dispose();
        birdBody.setAngularDamping(0.4f);
    }
    public void launch(float ang,float sp){
        birdBody.setLinearVelocity((float)Math.cos(ang) * sp, (float)Math.sin(ang) * sp);
    }
    public Body getBirdBody(){
        return birdBody;
    }
    public void addBirdOnScreen(){
        if (imageOfBird != null) {
            float birdBodyRotation = birdBody.getAngle() * MathUtils.radiansToDegrees;
            Vector2 birdPosition = birdBody.getPosition();
            TextureRegion birdRegion = new TextureRegion(imageOfBird);
            gameInstance.ourSpriteBatch.draw(birdRegion, birdPosition.x * gameInstance.pixelPerMeter - length_of_x / 2, birdPosition.y * gameInstance.pixelPerMeter - length_of_y / 2, length_of_x/2, length_of_y/2, length_of_x,length_of_y,1,1,birdBodyRotation);
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
//    public void ifInsideCatapult(Catapult c, OrthographicCamera myCamera){
//        Rectangle boundsToCheck = c.launchBound();
////        System.out.println("worldClick : " + worldClick.x + " " + worldClick.y );
//        if (boundsToCheck.contains(this.getBirdBody().getPosition().x, this.getBirdBody().getPosition().y)) {
//            System.out.println("Bird inside catapult!");
//            this.getBirdBody().setType(BodyDef.BodyType.StaticBody);
//        }else{
//            if(!GameScreen.birdToched(this,myCamera)){
//                this.getBirdBody().setType(BodyDef.BodyType.DynamicBody);
//            }
//
//        }
//
//    }
    public int getDamage(){
        return damage;
    }



}
