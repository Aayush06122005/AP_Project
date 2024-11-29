package io.github.game.MaterialsPackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import io.github.game.BirdsPackage.Birds;
import io.github.game.Mygame;

public abstract class Block {
    private Texture imgToShow;
    private float length_of_x;
    private float length_of_y;
    private float positionX;
    private float positionY;
    private Mygame gameInstance;
    private Body body;
    public String state;
    private int hitPoint;
    private int damage;


    public Block(float a, float b, float c, float d, Mygame e,World world){
        positionX = a;
        positionY = b;
        length_of_x = c;
        length_of_y = d;
        gameInstance = e;
        imgToShow = new Texture("space.jpg");
        state = "exist";

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(a + (c/2), b + (d/2));
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(c/2 , d/2 );

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 10f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.3f;
        fixtureDef.filter.categoryBits = (short)0x0002;
        body.createFixture(fixtureDef);
        this.body.setUserData(this);
        shape.dispose();
    }

    public void setImg(Texture aa){
        this.imgToShow = aa;
    }

 public void addToScreen() {
        if(imgToShow != null){
            Vector2 position = body.getPosition();
            positionX = position.x * gameInstance.pixelPerMeter - length_of_x / 2;
            positionY = position.y * gameInstance.pixelPerMeter - length_of_y / 2;
            float angle = (float) Math.toDegrees(body.getAngle());
            gameInstance.ourSpriteBatch.draw(imgToShow, positionX, positionY, length_of_x / 2, length_of_y / 2, length_of_x, length_of_y, 1, 1, angle, 0, 0, imgToShow.getWidth(), imgToShow.getHeight(), false, false);

        }

}
    public void disposeFromScreen(){

        if(imgToShow!=null){
            imgToShow.dispose();
            imgToShow = null;
        }

    }
    public Body getBody(){
        return body;
    }
    public int getHealth(){
        return hitPoint;
    }
    public void setHealth(int val){
        hitPoint = val;
    }

    public void deathHandler(int damage){
        hitPoint -= damage;
        if(hitPoint <= 0){
            state = "destroy";
        }
    }

    public int getDamage(){
        return damage;
    }
    public void setDamage(int hit){
        damage = hit;
    }


}
