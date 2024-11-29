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
import io.github.game.MaterialsPackage.Block;
import io.github.game.MaterialsPackage.RectangularBlock;
import io.github.game.MaterialsPackage.SquareBlock;
import io.github.game.MaterialsPackage.TriangularBlock;
import io.github.game.PigsPackage.Pig;
import io.github.game.PigsPackage.Pig1;
import io.github.game.PigsPackage.Pig2;
import io.github.game.PigsPackage.Pig3;

import java.util.ArrayList;

public class GameScreen implements Screen {
    private static final float FIXED_TIMESTEP = 1 / 60f;  // Fixed timestep (60 updates per second)
    private float accumulator = 0f;
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
    private Block block;
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
        ourWorld = new World(new Vector2(0, -25f), true);
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
        groundShape.setAsBox(ourViewPort.getWorldWidth() + 100, ourViewPort.getWorldHeight() / 4);

        FixtureDef groundFixture = new FixtureDef();
        groundFixture.shape = groundShape;
        groundFixture.density = 2f;
        groundFixture.friction = 0.8f;
        groundFixture.restitution = 1f;
        ground.createFixture(groundShape, 0.5f);
        groundShape.dispose();



        float worldWidth = ourViewPort.getWorldWidth();
        float worldHeight = ourViewPort.getWorldHeight();
        float boundaryThickness = 1f;

        BodyDef ceilingDef = new BodyDef();
        ceilingDef.type = BodyDef.BodyType.StaticBody;
        ceilingDef.position.set(worldWidth / 2, worldHeight - boundaryThickness / 2);
        Body ceiling = ourWorld.createBody(ceilingDef);
        PolygonShape ceilingShape = new PolygonShape();
        ceilingShape.setAsBox(worldWidth / 2, boundaryThickness / 2);
        ceiling.createFixture(ceilingShape, 0.0f);
        ceilingShape.dispose();

        BodyDef leftWallDef = new BodyDef();
        leftWallDef.type = BodyDef.BodyType.StaticBody;
        leftWallDef.position.set(boundaryThickness / 2, worldHeight / 2);
        Body leftWall = ourWorld.createBody(leftWallDef);
        PolygonShape leftWallShape = new PolygonShape();
        leftWallShape.setAsBox(boundaryThickness / 2, worldHeight / 2);
        leftWall.createFixture(leftWallShape, 0.0f);
        leftWallShape.dispose();

        BodyDef rightWallDef = new BodyDef();
        rightWallDef.type = BodyDef.BodyType.StaticBody;
        rightWallDef.position.set(worldWidth - boundaryThickness / 2 + 100, worldHeight / 2);
        Body rightWall = ourWorld.createBody(rightWallDef);
        PolygonShape rightWallShape = new PolygonShape();
        rightWallShape.setAsBox(boundaryThickness / 2, worldHeight / 2);
        rightWall.createFixture(rightWallShape, 0.0f);
        rightWallShape.dispose();
//
//        for(Birds b : allBirds ){
//            b.getBirdBody().setType(BodyDef.BodyType.StaticBody);
//        }

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
//        Boolean atCatapult = false;
//        Birds DebugBird = null;
        for (Birds b : allBirds) {
            if (birdToched(b, myCamera)) {
                if (animatingBird == null && b.birdState != "launched") { // Start animation if no bird is currently animating
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
        Birds launchedBird = null;

        for(Birds b : allBirds){

            if(b.birdState == "loaded"){
                loadedBird = b;
            }

            else if(b.birdState == "launched" || b.birdState == "dying" || b.birdState == "dead"){
                launchedBird= b;
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
                if (loadedBird.birdState == "launchable") {
                    launching(loadedBird);
                }

            }


        }
        if(launchedBird != null) {


            if (launchedBird.birdState == "dead") {
                animatingBird = null;
                deathhandler(launchedBird);
            }

            handleCollison(launchedBird);

            if ((launchedBird.birdHealth <= 0 || (launchedBird.getBirdBody().getLinearVelocity().x == 0 && launchedBird.getBirdBody().getLinearVelocity().y == 0))) {
                launchedBird.birdState = "dead";
            }
        }
//        MassData data = null;
//        allBirds.get(0).getBirdBody().setMassData(data);
      //System.out.println(allBirds.get(3).getBirdBody().getMass());
        if(block != null && block.getHealth() <= 0){
            deathhandler1(block);
        }






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
        accumulator += delta;
        while (accumulator >= FIXED_TIMESTEP) {
            ourWorld.step(FIXED_TIMESTEP, 6, 2);  // Update physics at fixed timestep
            accumulator -= FIXED_TIMESTEP;  // Reduce the accumulator by fixed timestep
        }

        debuggerRenderer.render(ourWorld, myCamera.combined);
    }

    private void deathhandler(Birds loadedBird) {
        ourWorld.destroyBody(loadedBird.getBirdBody());
        loadedBird.disposeBird();
        allBirds.remove(loadedBird);
    }
    private void deathhandler1(Block bl) {
        ourWorld.destroyBody(bl.getBody());
        bl.disposeFromScreen();
        block = null;
        System.out.println("Total bodies in the world: " + ourWorld.getBodyCount());

    }


    private void launching(Birds b) {
        if (b.birdState.equals("launchable")) {
            // Get positions of the catapult and bird
            Vector2 catapultPos = catapultInst.launchBound().getCenter(new Vector2());
            Vector2 birdPos = animatingBird.getBirdBody().getPosition();

            // Calculate direction from the catapult to the bird
            Vector2 direction = new Vector2(birdPos.x - catapultPos.x, birdPos.y - catapultPos.y);
            direction.nor();  // Normalize the vector for consistent direction

            // Make the bird's body dynamic
            b.getBirdBody().setType(BodyDef.BodyType.DynamicBody);

            float forceY = animatingBird.getBirdBody().getMass() * ourWorld.getGravity().y;

            // Apply a fixed launch power, independent of bird's mass
            float forcX = 1500000000000000000f;
            float birdLaunchPower = (float)Math.sqrt((forcX * forcX) + (forceY *forceY));

            // Apply impulse in the opposite direction of the catapult-to-bird vector
            animatingBird.getBirdBody().applyLinearImpulse(direction.scl(-birdLaunchPower), birdPos, true);
            Vector2 currentVelocity = animatingBird.getBirdBody().getLinearVelocity();
//            animatingBird.getBirdBody().setGravityScale(01f);
//
//            float launchSpeed =  100000000;
//            Vector2 launchVelocity = currentVelocity.nor().scl(launchSpeed);
//            animatingBird.getBirdBody().setLinearVelocity(launchVelocity);

            // Set the bird's state to "launched"
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



    public void handleCollison(Birds b){

        ourWorld.setContactListener(new GameContactListener(b));
    }

    class GameContactListener implements ContactListener{
        private Birds b;
        public GameContactListener(Birds bird){
            b = bird;
        }

        @Override
        public void beginContact(Contact contact) {

            Body bodyA = contact.getFixtureA().getBody();
            Body bodyB = contact.getFixtureB().getBody();
            Filter filterB = contact.getFixtureB().getFilterData();
            Filter filterA = contact.getFixtureA().getFilterData();
            short blockC = (short)0x0002;
            Block bl;


            if(( b.getBirdBody() == bodyA || b.getBirdBody() == bodyB)){
                b.birdState = "dying";
//                    System.out.println("Bird Touched");
                b.birdHealth -= 1;
                System.out.println("Bird Health : " + b.birdHealth);
            }

            if(( b.getBirdBody() == bodyA && filterB.categoryBits == blockC) || (filterA.categoryBits == blockC && b.getBirdBody() == bodyB)){
                if(bodyA.getUserData().equals(b)){
                    block = (Block)bodyB.getUserData();
                }else{
                    block = (Block)bodyA.getUserData();
                }
                block.deathHandler(b.getDamage());


            }
        }


        @Override
        public void endContact(Contact contact) {

        }

        @Override
        public void preSolve(Contact contact, Manifold oldManifold) {

        }

        @Override
        public void postSolve(Contact contact, ContactImpulse impulse) {

        }
    }

}

