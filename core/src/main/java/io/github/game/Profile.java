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
public class Profile implements Screen {
    private Texture Profilebgimg;
    OrthographicCamera myCamera;
    final Mygame gameInstance;
    private Music backgroundMusic;
    FitViewport ourViewPort;
    private Texture crossbtn;
    private Texture editbtn;
    private World ourWorld;
    private Box2DDebugRenderer debuggerRenderer;

    public Profile(final Mygame gameI,World oW, Box2DDebugRenderer dR){
        this.gameInstance = gameI;
        myCamera = new OrthographicCamera();
        myCamera.setToOrtho(false,800,400);
        Profilebgimg = new Texture("Profile.jpg");
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.mp3"));
        backgroundMusic.setLooping(true);
        ourViewPort = new FitViewport(800, 400, myCamera);
        crossbtn=new Texture("cross.png");
        editbtn=new Texture("editIcon.png");
        ourWorld = oW;
        debuggerRenderer = dR;
//        gameInstance.textWriter.getData().setScale(2.0f);
//        gameInstance.textWriter.setColor(Color.BLACK);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        myCamera.update();
        ourViewPort.apply();
        gameInstance.ourSpriteBatch.setProjectionMatrix(myCamera.combined);
        gameInstance.ourSpriteBatch.begin();
        gameInstance.ourSpriteBatch.draw(Profilebgimg, 0, 0,ourViewPort.getWorldWidth(), ourViewPort.getWorldHeight());
        gameInstance.ourSpriteBatch.draw(crossbtn,700 ,320 ,(ourViewPort.getWorldWidth())/10,(ourViewPort.getWorldHeight())/6);
//        gameInstance.ourSpriteBatch.draw(Settingbtn,280 ,200 ,(ourViewPort.getWorldWidth())/10,(ourViewPort.getWorldHeight())/6);
//        gameInstance.ourSpriteBatch.draw(Settingbtn,360,200 ,(ourViewPort.getWorldWidth())/10,(ourViewPort.getWorldHeight())/6);
        gameInstance.textWriter.draw(gameInstance.ourSpriteBatch, "Name - Player", 300, 250);
        gameInstance.textWriter.getData().setScale(1.5f);
        gameInstance.textWriter.setColor(Color.WHITE);
        gameInstance.ourSpriteBatch.draw(editbtn,475,235 ,(ourViewPort.getWorldWidth())/40,(ourViewPort.getWorldHeight())/24);
        gameInstance.textWriter.draw(gameInstance.ourSpriteBatch, "Account ID - 1234", 300, 210);
//        gameInstance.ourSpriteBatch.draw(Settingbtn,475,185 ,(ourViewPort.getWorldWidth())/20,(ourViewPort.getWorldHeight())/12);
        gameInstance.textWriter.draw(gameInstance.ourSpriteBatch, "Player Level - 10", 300, 170);
        gameInstance.ourSpriteBatch.end();

        if (Gdx.input.isTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            Vector3 coordinatesMouseInp = new Vector3(x, y, 0);
            myCamera.unproject(coordinatesMouseInp);
            if (coordinatesMouseInp.x > 700 && coordinatesMouseInp.x < 700 +(ourViewPort.getWorldWidth())/10 && coordinatesMouseInp.y > 320 && coordinatesMouseInp.y < 320 + (ourViewPort.getWorldHeight())/6) {
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
