package io.github.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
public class WinningScreen implements Screen {
    private Texture Settingbgimg;
    OrthographicCamera myCamera;
    final Mygame gameInstance;
    private Music backgroundMusic;
    FitViewport ourViewPort;
    private Texture crossbtn;
    private Texture winningimg;
    public WinningScreen(final Mygame gameI){
        this.gameInstance = gameI;
        myCamera = new OrthographicCamera();
        myCamera.setToOrtho(false,800,400);
        Settingbgimg = new Texture("Settingbg.jpg");
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.mp3"));
        backgroundMusic.setLooping(true);
        ourViewPort = new FitViewport(800, 400, myCamera);
        crossbtn=new Texture("cross.png");
        // Staricon=new Texture("starIcon.png");
        winningimg=new Texture("youWin.png");
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
        gameInstance.ourSpriteBatch.draw(winningimg,240,70 ,3*(ourViewPort.getWorldWidth())/6,4*(ourViewPort.getWorldHeight())/6);
        gameInstance.ourSpriteBatch.end();
        if (Gdx.input.isTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            Vector3 coordinatesMouseInp = new Vector3(x, y, 0);
            myCamera.unproject(coordinatesMouseInp);
            if (coordinatesMouseInp.x > 700 && coordinatesMouseInp.x < 700 +(ourViewPort.getWorldWidth())/10 && coordinatesMouseInp.y > 320 && coordinatesMouseInp.y < 320 + (ourViewPort.getWorldHeight())/6) {
                gameInstance.setScreen(new MainMenu(gameInstance));
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
