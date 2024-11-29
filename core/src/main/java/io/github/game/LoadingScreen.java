package io.github.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.sun.tools.javac.Main;

public class LoadingScreen implements Screen {
    private Texture backgroundLoadingImage;
    OrthographicCamera myCamera;
    final Mygame gameInstance;
    private Music backgroundMusic;
    FitViewport ourViewPort;
    private World ourWorld;
    private Box2DDebugRenderer debuggerRenderer;
    public LoadingScreen(final Mygame gameI){
        this.gameInstance = gameI;
        myCamera = new OrthographicCamera();
        myCamera.setToOrtho(false,800,400);
        backgroundLoadingImage = new Texture("backgroundImageLoadingPage.jpg");
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.mp3"));
        backgroundMusic.setLooping(true);
        ourViewPort = new FitViewport(800, 400, myCamera);
        gameInstance.textWriter.setColor(Color.BLACK);
        ourWorld = new World(new Vector2(0, -9.8f), true);
        debuggerRenderer = new Box2DDebugRenderer();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        myCamera.update();
        ourViewPort.apply();
        gameInstance.ourSpriteBatch.setProjectionMatrix(myCamera.combined);
        gameInstance.ourSpriteBatch.begin();
        gameInstance.ourSpriteBatch.draw(backgroundLoadingImage, 0, 0,ourViewPort.getWorldWidth(), ourViewPort.getWorldHeight());
//        gameInstance.textWriter.draw(gameInstance.ourSpriteBatch, "Tap anywhere to begin!", 325, 70);
        gameInstance.ourSpriteBatch.end();
        ourWorld.step(1 / 60f, 6, 2);
        debuggerRenderer.render(ourWorld, myCamera.combined);
        if (Gdx.input.justTouched()) {
            gameInstance.setScreen(new MainMenu(gameInstance,ourWorld,debuggerRenderer));
            dispose();
        }
    }

    @Override
    public void resize(int w, int h) {
        ourViewPort.update(w, h, true);
    }

    @Override
    public void show() {

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
        ourWorld.dispose();
        debuggerRenderer.dispose();
    }
}
