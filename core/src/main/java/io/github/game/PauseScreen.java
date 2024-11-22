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
public class PauseScreen implements Screen {
    private Texture Settingbgimg;
    OrthographicCamera myCamera;
    final Mygame gameInstance;
    private Music backgroundMusic;
    FitViewport ourViewPort;
    private Texture crossbtn;
    private Texture saveBtn;
    private Texture restartBtn;
    private Texture exitBtn;
    private Texture resumeBtn;
    private World ourWorld;
    private Box2DDebugRenderer debuggerRenderer;

    public PauseScreen(final Mygame gameI,World oW, Box2DDebugRenderer dR){
        this.gameInstance = gameI;
        myCamera = new OrthographicCamera();
        myCamera.setToOrtho(false,800,400);
        Settingbgimg = new Texture("Settingbg.jpg");
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.mp3"));
        backgroundMusic.setLooping(true);
        ourViewPort = new FitViewport(800, 400, myCamera);
        crossbtn=new Texture("cross.png");
        saveBtn=new Texture("save.png");
        restartBtn=new Texture("restart.png");
        exitBtn=new Texture("exit.png");
        resumeBtn = new Texture("resume.png");
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
        gameInstance.ourSpriteBatch.draw(Settingbgimg, 0, 0,ourViewPort.getWorldWidth(), ourViewPort.getWorldHeight());
        gameInstance.ourSpriteBatch.draw(crossbtn,700 ,320 ,(ourViewPort.getWorldWidth())/10,(ourViewPort.getWorldHeight())/6);
        gameInstance.ourSpriteBatch.draw(resumeBtn,350 ,250 ,100,30);
        gameInstance.ourSpriteBatch.draw(saveBtn,350,210 ,100,30);
        gameInstance.ourSpriteBatch.draw(restartBtn,350,170 ,100,30);
        gameInstance.ourSpriteBatch.draw(exitBtn,350,130 ,100,30);


//
        gameInstance.ourSpriteBatch.end();
        if (Gdx.input.isTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            Vector3 coordinatesMouseInp = new Vector3(x, y, 0);
            myCamera.unproject(coordinatesMouseInp);
            if (coordinatesMouseInp.x > 350 && coordinatesMouseInp.x < 450 && coordinatesMouseInp.y > 250 && coordinatesMouseInp.y < 280){
                gameInstance.setScreen(new GameScreen(gameInstance,ourWorld,debuggerRenderer));
                dispose();
            }
            if (coordinatesMouseInp.x > 350 && coordinatesMouseInp.x < 450 && coordinatesMouseInp.y > 210 && coordinatesMouseInp.y < 240){
                gameInstance.setScreen(new MainMenu(gameInstance,ourWorld,debuggerRenderer));
                dispose();
            }
            if (coordinatesMouseInp.x > 350 && coordinatesMouseInp.x < 450 && coordinatesMouseInp.y > 170 && coordinatesMouseInp.y < 200){
                gameInstance.setScreen(new GameScreen(gameInstance,ourWorld,debuggerRenderer));
                dispose();
            }
            if (coordinatesMouseInp.x > 350 && coordinatesMouseInp.x < 450 && coordinatesMouseInp.y > 130 && coordinatesMouseInp.y < 160){
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
