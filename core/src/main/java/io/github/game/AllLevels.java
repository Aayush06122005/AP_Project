package io.github.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
public class AllLevels implements Screen {
    private Texture bgimgSpace;
    private Texture bgimgGround;
    OrthographicCamera myCamera;
    final Mygame gameInstance;
    private Music backgroundMusic;
    FitViewport ourViewPort;
    private Texture Cross;
    private Texture Level1btn;
    private Texture Level2btn;
    private Texture Level3btn;
    private Texture selectALevel;
    private World ourWorld;
    private Box2DDebugRenderer debuggerRenderer;
    public AllLevels(final Mygame gameI,World oW, Box2DDebugRenderer dR){
        this.gameInstance = gameI;
        myCamera = new OrthographicCamera();
        myCamera.setToOrtho(false,800,400);
        bgimgGround = new Texture("ground.png");
        bgimgSpace = new Texture("space.jpg");
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.mp3"));
        backgroundMusic.setLooping(true);
        ourViewPort = new FitViewport(800, 400, myCamera);
        Cross = new Texture("cross.png");
        Level1btn=new Texture("Level1.png");
        Level2btn=new Texture("Level2.png");
        Level3btn=new Texture("Level3.png");
        selectALevel = new Texture("SelectLevel.png");
        ourWorld = oW;
        debuggerRenderer = dR;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        myCamera.update();
        ourViewPort.apply();
        gameInstance.ourSpriteBatch.setProjectionMatrix(myCamera.combined);
        gameInstance.ourSpriteBatch.begin();
        gameInstance.ourSpriteBatch.draw(bgimgGround, 0, 0,ourViewPort.getWorldWidth(), ourViewPort.getWorldHeight()/4);
        gameInstance.ourSpriteBatch.draw(bgimgSpace, 0, ourViewPort.getWorldHeight()/4 ,ourViewPort.getWorldWidth(), 3 * (ourViewPort.getWorldHeight()/4));
        gameInstance.ourSpriteBatch.draw(selectALevel, 270, 320,ourViewPort.getWorldWidth()/3, ourViewPort.getWorldHeight()/5);
        gameInstance.ourSpriteBatch.draw(Cross,750 ,355 ,(ourViewPort.getWorldWidth())/18,(ourViewPort.getWorldHeight())/10);
        gameInstance.ourSpriteBatch.draw(Level1btn,230 ,200 ,(ourViewPort.getWorldWidth())/10,(ourViewPort.getWorldHeight())/6);
        gameInstance.ourSpriteBatch.draw(Level2btn,360,200 ,(ourViewPort.getWorldWidth())/10,(ourViewPort.getWorldHeight())/6);
        gameInstance.ourSpriteBatch.draw(Level3btn,490,200 ,(ourViewPort.getWorldWidth())/10,(ourViewPort.getWorldHeight())/6);
        gameInstance.ourSpriteBatch.end();

        if (Gdx.input.isTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            Vector3 coordinatesMouseInp = new Vector3(x, y, 0);
            myCamera.unproject(coordinatesMouseInp);
            if (coordinatesMouseInp.x > 230 && coordinatesMouseInp.x < 230 +(ourViewPort.getWorldWidth())/10 && coordinatesMouseInp.y > 200 && coordinatesMouseInp.y < 200 + (ourViewPort.getWorldHeight())/6) {
                gameInstance.setScreen(new GameScreen(gameInstance,ourWorld,debuggerRenderer));
                dispose();
            }
            if (coordinatesMouseInp.x > 750 && coordinatesMouseInp.x < 750 +(ourViewPort.getWorldWidth())/18 && coordinatesMouseInp.y > 355 && coordinatesMouseInp.y < 355 + (ourViewPort.getWorldHeight())/10) {
                gameInstance.setScreen(new MainMenu(gameInstance,ourWorld,debuggerRenderer));
                dispose();
            }
        }
    }

    @Override
    public void resize(int w, int h) {
        ourViewPort.update(w, h, true);
    }

    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown
        backgroundMusic.play();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
