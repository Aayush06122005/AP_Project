package io.github.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import io.github.game.BirdsPackage.RedBird;

public class MainMenu implements Screen {
//    private Texture bgimg;
    private Texture bgimgSpace;
    private Texture bgimgGround;
    OrthographicCamera myCamera;
    final Mygame gameInstance;
    private Music backgroundMusic;
    FitViewport ourViewPort;
    private Texture Playbtn;
    private Texture Settingbtn;
    private Texture Exitbtn;
//    private Texture Profilebtn;
    private RedBird birdForProfileBtn;
    private Texture PlayerName;
    ShapeRenderer shapeMaker;
    private World ourWorld;
    private Box2DDebugRenderer debuggerRenderer;
    public MainMenu(final Mygame gameI,World oW, Box2DDebugRenderer dR){
        myCamera = new OrthographicCamera();
        myCamera.setToOrtho(false,800,400);
        ourViewPort = new FitViewport(800, 400, myCamera);
        this.gameInstance = gameI;
        shapeMaker = new ShapeRenderer();
        bgimgGround = new Texture("ground.png");
        bgimgSpace = new Texture("space.jpg");
        ourWorld = oW;
        debuggerRenderer = dR;
//        bgimg = new Texture("background.jpg");
        Playbtn = new Texture("PlayBtn.png");
//        Profilebtn = new Texture("angryBird1.png");
        birdForProfileBtn = new RedBird(80,360,(ourViewPort.getWorldWidth())/12,(ourViewPort.getWorldHeight())/7,gameInstance,ourWorld);
        PlayerName = new Texture("PlayerName.png");
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.mp3"));
        backgroundMusic.setLooping(true);
        Settingbtn=new Texture("SettingBtn.png");
        Exitbtn = new Texture("ExitBtn.png");
        gameInstance.textWriter.setColor(Color.BLACK);

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
//        gameInstance.ourSpriteBatch.draw(bgimg, 0, 0,ourViewPort.getWorldWidth(), ourViewPort.getWorldHeight());
//        gameInstance.ourSpriteBatch.draw(birdForProfileBtn,80 ,360 ,(ourViewPort.getWorldWidth())/12,(ourViewPort.getWorldHeight())/7);
        birdForProfileBtn.addBirdOnScreen();
        gameInstance.ourSpriteBatch.draw(PlayerName,(ourViewPort.getWorldWidth())/12 + 5,350 ,(ourViewPort.getWorldWidth())/8,(ourViewPort.getWorldHeight())/15);
        gameInstance.ourSpriteBatch.draw(Exitbtn,320 ,100 ,(ourViewPort.getWorldWidth())/5,(ourViewPort.getWorldHeight())/8);
        gameInstance.ourSpriteBatch.draw(Settingbtn,320,160 ,(ourViewPort.getWorldWidth())/5,(ourViewPort.getWorldHeight())/8);
        gameInstance.ourSpriteBatch.draw(Playbtn,320 , 220,(ourViewPort.getWorldWidth())/5,(ourViewPort.getWorldHeight())/8);
        gameInstance.ourSpriteBatch.end();

        if (Gdx.input.isTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            Vector3 coordinatesMouseInp = new Vector3(x, y, 0);
            myCamera.unproject(coordinatesMouseInp);
            if (coordinatesMouseInp.x > 320 && coordinatesMouseInp.x < 320 +(ourViewPort.getWorldWidth())/5 && coordinatesMouseInp.y > 100 && coordinatesMouseInp.y < 100 + (ourViewPort.getWorldHeight()) / 8) {
                Gdx.app.exit();
            }
            if (coordinatesMouseInp.x > 320 && coordinatesMouseInp.x < 320 +(ourViewPort.getWorldWidth())/5 && coordinatesMouseInp.y > 160 && coordinatesMouseInp.y < 160 + (ourViewPort.getWorldHeight()) / 8) {
                gameInstance.setScreen(new Settings(gameInstance,ourWorld,debuggerRenderer)); // replace with your actual settings screen
                dispose();
            }
            if (coordinatesMouseInp.x > 320 && coordinatesMouseInp.x < 320 +(ourViewPort.getWorldWidth())/5 && coordinatesMouseInp.y > 220 && coordinatesMouseInp.y < 220 + (ourViewPort.getWorldHeight()) / 8) {
                gameInstance.setScreen(new AllLevels(gameInstance,ourWorld,debuggerRenderer)); // replace with your actual settings screen
                dispose();
            }
            if (coordinatesMouseInp.x > 0 && coordinatesMouseInp.x < (ourViewPort.getWorldWidth())/12 + 5 && coordinatesMouseInp.y > 340 && coordinatesMouseInp.y < 340 + (ourViewPort.getWorldHeight())/7) {
                gameInstance.setScreen(new Profile(gameInstance,ourWorld,debuggerRenderer)); // replace with your actual settings screen
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
        birdForProfileBtn.disposeBird();
    }
}
