package io.github.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
public class Mygame extends Game {
    public BitmapFont textWriter;
    public SpriteBatch ourSpriteBatch;
    public static final float  pixelPerMeter= 100;
    @Override
    public void create() {
        ourSpriteBatch = new SpriteBatch();
        textWriter = new BitmapFont();
        this.setScreen(new LoadingScreen(this));
    }
    @Override
    public void dispose(){
        ourSpriteBatch.dispose();
        textWriter.dispose();
    }
}
;
