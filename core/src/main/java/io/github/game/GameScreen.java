package io.github.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import io.github.game.BirdsPackage.BlueBird;
import io.github.game.BirdsPackage.RedBird;
import io.github.game.BirdsPackage.YellowBird;
import io.github.game.MaterialsPackage.RectangularBlock;
import io.github.game.MaterialsPackage.SquareBlock;
import io.github.game.MaterialsPackage.TriangularBlock;
import io.github.game.PigsPackage.Pig1;
import io.github.game.PigsPackage.Pig2;
import io.github.game.PigsPackage.Pig3;

public class GameScreen implements Screen {
    private Texture bgimgSpace;
    private Texture bgimgGround;
    OrthographicCamera myCamera;
    private Mygame gameInstance;
    private Music backgroundMusic;
    FitViewport ourViewPort;
    ShapeRenderer shapeMaker;
    private Catapult catapultInst;
    private BlueBird bird1;
    private YellowBird bird2;
    private RedBird bird3;
    private YellowBird bird4;
    private BlueBird bird5;
    private TriangularBlock BaseBlock1;
    private TriangularBlock BaseBlock2;
    private TriangularBlock BaseBlock3;
    private RectangularBlock block1;
    private SquareBlock block2;
    private SquareBlock block3;
    private Texture Pausebtn;
    private Texture WinBtn;
    private Texture LoseBtn;
    private Pig1 pig1;
    private Pig2 pig2;
    private Pig3 pig3;
    private Pig1 pig4;
    private World ourWorld;
    private Box2DDebugRenderer debuggerRenderer;


    public GameScreen(final Mygame gameI,World oW, Box2DDebugRenderer dR) {
       myCamera = new OrthographicCamera();
       myCamera.setToOrtho(false, 800, 400);
       ourViewPort = new FitViewport(800, 400, myCamera);
       this.gameInstance = gameI;
       shapeMaker = new ShapeRenderer();
       bgimgGround = new Texture("ground.png");
       bgimgSpace = new Texture("space.jpg");
       backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.mp3"));
       backgroundMusic.setLooping(true);
       catapultInst = new Catapult(new Texture("catapault.png"), 2 * (ourViewPort.getWorldWidth() / 10), ourViewPort.getWorldHeight() / 4, 50, 100, gameInstance);
       bird1 = new BlueBird(0, ourViewPort.getWorldHeight() / 4, 40, 40, gameInstance,ourWorld);
       bird2 = new YellowBird(45, ourViewPort.getWorldHeight() / 4, 40, 40, gameInstance,ourWorld);
       bird3 = new RedBird(90, ourViewPort.getWorldHeight() / 4, 40, 40, gameInstance,ourWorld);
       bird4 = new YellowBird(135, ourViewPort.getWorldHeight() / 4, 40, 40, gameInstance,ourWorld);
       bird5 = new BlueBird(2 * (ourViewPort.getWorldWidth() / 10) + 2, ourViewPort.getWorldHeight() / 4 + 75, 40, 40, gameInstance,ourWorld);
       BaseBlock1 = new TriangularBlock("wooden", 675 - 178, ourViewPort.getWorldHeight() / 4, 70, 70, gameInstance);
       BaseBlock2 = new TriangularBlock("wooden", 728 + 20 + 8 - 170, ourViewPort.getWorldHeight() / 4, 70, 70, gameInstance);
       BaseBlock3 = new TriangularBlock("wooden", 781 + 40 + 16 - 170, ourViewPort.getWorldHeight() / 4, 70, 70, gameInstance);
       Pausebtn = new Texture("pause.png");
       WinBtn = new Texture("winBtn.png");
       LoseBtn = new Texture("loseBtn.png");
       block1 = new RectangularBlock("steel",470,ourViewPort.getWorldHeight() / 4 + 70,280,20,gameInstance);
        block2 = new SquareBlock("glass",530,ourViewPort.getWorldHeight() / 4 + 90,50,50,gameInstance);
        block3 = new SquareBlock("wood",600,ourViewPort.getWorldHeight() / 4 + 90,50,50,gameInstance);
        pig1 = new Pig1(545,ourViewPort.getWorldHeight() / 4 + 101,20,20,gameInstance);
        pig2 = new Pig2(670,ourViewPort.getWorldHeight() / 4 + 90,30,30,gameInstance);
        pig3 = new Pig3(781 + 40 + 16 - 170 + 80,ourViewPort.getWorldHeight() / 4,30,30,gameInstance);
        ourWorld = oW;
        debuggerRenderer = dR;
    }
    @Override
    public void render(float delta) {
       myCamera.update();
       ourViewPort.apply();
       gameInstance.ourSpriteBatch.setProjectionMatrix(myCamera.combined);
       gameInstance.ourSpriteBatch.begin();
       gameInstance.ourSpriteBatch.draw(bgimgGround, 0, 0, ourViewPort.getWorldWidth(), ourViewPort.getWorldHeight() / 4);
       gameInstance.ourSpriteBatch.draw(bgimgSpace, 0, ourViewPort.getWorldHeight() / 4, ourViewPort.getWorldWidth(), 3 * (ourViewPort.getWorldHeight() / 4));
       gameInstance.ourSpriteBatch.draw(Pausebtn,5,ourViewPort.getWorldHeight() - 50 , 40,40);
        gameInstance.ourSpriteBatch.draw(WinBtn,50,ourViewPort.getWorldHeight() - 50 , 50,40);
        gameInstance.ourSpriteBatch.draw(LoseBtn,110,ourViewPort.getWorldHeight() - 50 , 50,40);
       catapultInst.addCatapultOnScreen();
       bird1.addBirdOnScreen();
       bird2.addBirdOnScreen();
       bird3.addBirdOnScreen();
       bird4.addBirdOnScreen();
       bird5.addBirdOnScreen();
       BaseBlock1.addToScreen();
       BaseBlock2.addToScreen();
       BaseBlock3.addToScreen();
        block1.addToScreen();
        block2.addToScreen();
        block3.addToScreen();
        pig1.addPigOnScreen();
        pig2.addPigOnScreen();
        pig3.addPigOnScreen();
       gameInstance.ourSpriteBatch.end();
        if (Gdx.input.isTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            Vector3 coordinatesMouseInp = new Vector3(x, y, 0);
            myCamera.unproject(coordinatesMouseInp);
            if (coordinatesMouseInp.x > 5 && coordinatesMouseInp.x < 45 && coordinatesMouseInp.y > ourViewPort.getWorldHeight() - 50 && coordinatesMouseInp.y < ourViewPort.getWorldHeight() - 10) {
                gameInstance.setScreen(new PauseScreen(gameInstance,ourWorld,debuggerRenderer)); // replace with your actual settings screen
                dispose();
            }
            if (coordinatesMouseInp.x > 50 && coordinatesMouseInp.x < 100 && coordinatesMouseInp.y > ourViewPort.getWorldHeight() - 50 && coordinatesMouseInp.y < ourViewPort.getWorldHeight() - 10) {
                gameInstance.setScreen(new WinningScreen(gameInstance,ourWorld,debuggerRenderer)); // replace with your actual settings screen
                dispose();
            }
            if (coordinatesMouseInp.x > 110 && coordinatesMouseInp.x < 160 && coordinatesMouseInp.y > ourViewPort.getWorldHeight() - 50 && coordinatesMouseInp.y < ourViewPort.getWorldHeight() - 10) {
                gameInstance.setScreen(new LosingScreen(gameInstance,ourWorld,debuggerRenderer)); // replace with your actual settings screen
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
