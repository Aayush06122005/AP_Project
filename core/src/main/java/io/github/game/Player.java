package io.github.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Player {

    public static Vector3 getInput(OrthographicCamera myCamera) {
        if(Gdx.input.isTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.input.getY();
//            Vector2 WorldPos = new Vector2(touchX, touchY);
            Vector3 worldPos = myCamera.unproject(new Vector3(touchX, touchY, 0));
//            System.out.println("X: " + worldPos.x + " Y: " + worldPos.y);
            return worldPos;
        }
        return null;
    }
}
