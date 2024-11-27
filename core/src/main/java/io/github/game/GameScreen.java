package io.github.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import io.github.game.BirdsPackage.Birds;
import io.github.game.BirdsPackage.BlueBird;
import io.github.game.BirdsPackage.RedBird;
import io.github.game.BirdsPackage.YellowBird;
import io.github.game.MaterialsPackage.RectangularBlock;
import io.github.game.MaterialsPackage.SquareBlock;
import io.github.game.MaterialsPackage.TriangularBlock;
import io.github.game.PigsPackage.Pig1;
import io.github.game.PigsPackage.Pig2;
import io.github.game.PigsPackage.Pig3;

import java.util.ArrayList;

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
    private Body ground;
    private ArrayList<Birds> allBirds;
    private Birds animatingBird = null; // Bird being animated
    private Vector2 animationTarget;   // Target position (catapult center)
    private float animationTime = 0;   // Time elapsed during animation
    private final float totalAnimationDuration = 0.5f; // Animation duration in seconds
    private boolean isBirdLaunched = false;
    public GameScreen(final Mygame gameI,World oW, Box2DDebugRenderer dR) {
        allBirds = new ArrayList<>();
        myCamera = new OrthographicCamera();
        myCamera.setToOrtho(false, 800, 400);
        ourViewPort = new FitViewport(800, 400, myCamera);
        this.gameInstance = gameI;
        shapeMaker = new ShapeRenderer();
        bgimgGround = new Texture("ground.png");
        bgimgSpace = new Texture("space.jpg");
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.mp3"));
        backgroundMusic.setLooping(true);
        ourWorld = new World(new Vector2(0, -9.8f), true);
        debuggerRenderer = new Box2DDebugRenderer();
        catapultInst = new Catapult(new Texture("catapault.png"), 2 * (ourViewPort.getWorldWidth() / 10), ourViewPort.getWorldHeight() / 4, 50, 100, gameInstance);

        bird1 = new BlueBird(25, ourViewPort.getWorldHeight() / 4 + 20, 40, 40, gameInstance,ourWorld);
        bird2 = new YellowBird(70, ourViewPort.getWorldHeight() / 4 + 20, 40, 40, gameInstance,ourWorld);
        bird3 = new RedBird(115, ourViewPort.getWorldHeight() / 4 + 20, 40, 40, gameInstance,ourWorld);
        bird4 = new YellowBird(160, ourViewPort.getWorldHeight() / 4 + 20, 40, 40, gameInstance,ourWorld);
//       bird5 = new BlueBird(185,175, 40, 40, gameInstance,ourWorld);
        allBirds.add(bird1);
        allBirds.add(bird2);
        allBirds.add(bird3);
        allBirds.add(bird4);
//       allBirds.add(bird5);
        BaseBlock1 = new TriangularBlock("wooden", 675 - 178, ourViewPort.getWorldHeight() / 4, 70, 70, gameInstance,ourWorld);
        BaseBlock2 = new TriangularBlock("wooden", 728 + 20 + 8 - 170, ourViewPort.getWorldHeight() / 4, 70, 70, gameInstance,ourWorld);
        BaseBlock3 = new TriangularBlock("wooden", 781 + 40 + 16 - 170, ourViewPort.getWorldHeight() / 4, 70, 70, gameInstance,ourWorld);
        Pausebtn = new Texture("pause.png");
        WinBtn = new Texture("winBtn.png");
        LoseBtn = new Texture("loseBtn.png");
        block1 = new RectangularBlock("steel",470,ourViewPort.getWorldHeight() / 4 + 70,280,20,gameInstance,ourWorld);
        block2 = new SquareBlock("glass",530,ourViewPort.getWorldHeight() / 4 + 90,50,50,gameInstance,ourWorld);
        block3 = new SquareBlock("wood",600,ourViewPort.getWorldHeight() / 4 + 90,50,50,gameInstance,ourWorld);
        pig1 = new Pig1(545,ourViewPort.getWorldHeight() / 4 + 101,20,20,gameInstance,100,ourWorld);
        pig2 = new Pig2(670,ourViewPort.getWorldHeight() / 4 + 90 ,30,30,gameInstance,100,ourWorld);
        pig3 = new Pig3(781 + 40 + 16 - 170 + 80,ourViewPort.getWorldHeight() / 4,30,30,gameInstance,100,ourWorld);


        BodyDef groundDef = new BodyDef();
        groundDef.type = BodyDef.BodyType.StaticBody;
        groundDef.position.set(new Vector2(0,0));
        ground = ourWorld.createBody(groundDef);

        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(ourViewPort.getWorldWidth(), ourViewPort.getWorldHeight() / 4);

        FixtureDef groundFixture = new FixtureDef();
        groundFixture.shape = groundShape;
        groundFixture.density = 1.0f;
        groundFixture.friction = 0.8f;
        ground.createFixture(groundShape, 0.5f);
        groundShape.dispose();
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
//       bird5.addBirdOnScreen();
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

        Vector2 range = new Vector2(catapultInst.launchBound().x/10, catapultInst.launchBound().y/10);
        Vector2 maxRange = new Vector2(range.x + 60f, range.y + 50f);
        Boolean atCatapult = false;
        for (Birds b : allBirds) {
            if (birdToched(b, myCamera)) {
                if (animatingBird == null) { // Start animation if no bird is currently animating
                    if(b.birdState == "ground"){
                        animatingBird = b;
                    }
                    animationTarget = catapultInst.launchBound().getCenter(new Vector2());
                    animationTime = 0;
                    animatingBird.birdState = "animating";
                }
            }
        }
        if (animatingBird != null && animatingBird.birdState == "animating"){
            birdAnimation();
        }

        Birds loadedBird = null;

        for(Birds b : allBirds){

            if(b.birdState == "loaded"){
                loadedBird = b;
            }

        }


        if(loadedBird != null){
            if((Gdx.input.isTouched())) {

                dragging(maxRange, loadedBird);
                System.out.println("Touching");

            }else{
                Vector2 catapultCenter = new Vector2((catapultInst.launchBound().x + catapultInst.launchBound().width/2),(catapultInst.launchBound().y + catapultInst.launchBound().height/2));
                Vector2 birdPos = new Vector2(loadedBird.getBirdBody().getPosition().x - catapultCenter.x,loadedBird.getBirdBody().getPosition().y- catapultCenter.y);
                double birdDis = Math.sqrt((birdPos.x * birdPos.x) + (birdPos.y * birdPos.y));
                System.out.println("Bird: " + birdDis);

                if(birdDis > 35 && !Gdx.input.isTouched() ){
                    loadedBird.birdState = "launchable";
                }

            }
            System.out.println(loadedBird.birdState);

            if(loadedBird.birdState == "launchable"){
                launching(loadedBird);
            }

        }

        //if(loadedBird != null){
        //   launching(loadedBird);
        // }






        //System.out.println(loadedBird.birdState);

        // launching(loadedBird);




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
        ourWorld.step(1 / 60f, 6, 2);
        debuggerRenderer.render(ourWorld, myCamera.combined);
    }


    private void launching(Birds b) {
        if(b.birdState == "launchable"){
            Vector2 catapultPos = catapultInst.launchBound().getCenter(new Vector2());
            Vector2 birdPos = animatingBird.getBirdBody().getPosition();
            Vector2 direction = new Vector2(birdPos.x - catapultPos.x, birdPos.y - catapultPos.y);
            direction.nor();
            b.getBirdBody().setType(BodyDef.BodyType.DynamicBody);;

            // Apply force in the opposite direction to launch the bird
            float birdLaunchPower = 1000000000f;

            animatingBird.getBirdBody().applyLinearImpulse(direction.scl(-birdLaunchPower), birdPos, true);
//                        animatingBird = null;
            b.birdState = "launched";
        }
    }

    private void dragging(Vector2 maxRange, Birds b) {
        if(b != null){
            Vector2 catapultCenter = catapultInst.launchBound().getCenter(new Vector2());
            Vector3 touchPos = Player.getInput(myCamera);
            if(touchPos == null){return;}
            float clampedX = MathUtils.clamp(touchPos.x, catapultCenter.x - maxRange.x, catapultCenter.x + maxRange.x);
            float clampedY = MathUtils.clamp(touchPos.y, catapultCenter.y - maxRange.y, catapultCenter.y + maxRange.y);
            // Set bird's position to clamped values
            b.getBirdBody().setTransform(clampedX, clampedY, 0);
        }
    }

    private void birdAnimation() {

        // Current bird position
        Vector2 currentPos = animatingBird.getBirdBody().getPosition();
        Vector2 interpolatedPosition = currentPos.lerp(animationTarget, animationTime / totalAnimationDuration);

        // Update bird's position
        animatingBird.getBirdBody().setTransform(interpolatedPosition, 0);

        // Increment animation time
        animationTime += Gdx.graphics.getDeltaTime();

        // End animation when complete
        if (animationTime >= totalAnimationDuration) {
            animatingBird.getBirdBody().setTransform(animationTarget, 0); // Snap to final position
            animatingBird.getBirdBody().setType(BodyDef.BodyType.StaticBody);
            animatingBird.birdState = "loaded";// Make bird static on catapult
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
    public void update(float deltaTime) {
        ourWorld.step(1/60f, 6, 2);
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
    public World getWorld() {
        return ourWorld;
    }
    @Override
    public void dispose() {

    }

    public static Boolean birdToched(Birds b,OrthographicCamera myCamera){
        Vector2 pos = b.getBirdBody().getPosition();
        Circle boundsToCheck = b.birdBound();
//        System.out.println("circle : " + boundsToCheck.x + " " + boundsToCheck.y );
        Vector3 worldClick = Player.getInput(myCamera);
        if(worldClick == null){
            return false;
        }

//        System.out.println("worldClick : " + worldClick.x + " " + worldClick.y );
        if (boundsToCheck.contains(worldClick.x, worldClick.y)) {

            return true;
        }

        return false;

    }

    public void launchingBird(){
        Vector2 catapultPos = catapultInst.launchBound().getCenter(new Vector2());
        float launchPowFactor = 0.1f;


    }
}

