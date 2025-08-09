package dattran.game.superkid.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import dattran.game.superkid.character.kid.KidCharacter;
import dattran.game.superkid.character.kid.KidCharacterImpl;
import dattran.game.superkid.character.kid.KidStateIdle;
import dattran.game.superkid.config.GameConfig;

public class SeaportScreen implements Screen {
    private final Batch batch;

    private final OrthographicCamera camera;
    private final Viewport gameport;

    private World world;

    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer mapRenderer;

    private final KidCharacter kid;

    public SeaportScreen(Batch batch) {
        this.batch = batch;

        this.camera = new OrthographicCamera();
        float viewportWidth = GameConfig.VIEWPORT_WIDTH / GameConfig.PPM;
        float viewportHeight = GameConfig.VIEWPORT_HEIGHT / GameConfig.PPM;
        this.gameport = new FitViewport(viewportWidth, viewportHeight, camera);
        camera.position.set(viewportWidth / 2, viewportHeight / 2, 0);
        camera.update();


        world = new World(new Vector2(0, GameConfig.WORLD_GRAVITY), true);

        // load map
        TmxMapLoader loader = new TmxMapLoader();
        tiledMap = loader.load("graphic/level/seaport/seaport.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1f / GameConfig.PPM);
        createMapBound();

        kid = new KidCharacterImpl(world, new Vector2(64/*halb of kid width*/ / GameConfig.PPM,(32/*ground high*/ + 64 /*halb of kid height*/)/ GameConfig.PPM), new KidStateIdle());
    }

    private void createMapBound() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bodyDef);

        ChainShape chain = new ChainShape();
        chain.createLoop(new Vector2[] {
            new Vector2(0, 0),
            new Vector2(GameConfig.SEAPORT_MAP_WIDTH, 0),
            new Vector2(GameConfig.SEAPORT_MAP_WIDTH, GameConfig.SEAPORT_MAP_HEIGHT),
            new Vector2(0, GameConfig.SEAPORT_MAP_HEIGHT),
        });

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = chain;
        fixtureDef.friction = 0f;
        fixtureDef.isSensor = false;

        body.createFixture(fixtureDef);
        chain.dispose();
    }

    public void clampCamera() {
        camera.position.x = Math.max(camera.viewportWidth / 2f,
            Math.min(camera.position.x, GameConfig.SEAPORT_MAP_WIDTH - camera.viewportWidth / 2f));

        camera.position.y = Math.max(camera.viewportHeight / 2f,
            Math.min(camera.position.y, GameConfig.SEAPORT_MAP_HEIGHT - camera.viewportHeight / 2f));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(GameConfig.WORLD_DELTA_TIME, GameConfig.WORLD_VELOCITY_ITERATION, GameConfig.WORLD_POSITION_ITERATION);


        Vector2 playerPos = kid.getBody().getPosition();
        float halfViewportWidth = camera.viewportWidth / 2f;
        float halfViewportHeight = camera.viewportHeight / 2f;

        float cameraX = Math.min(Math.max(playerPos.x, halfViewportWidth), GameConfig.SEAPORT_MAP_WIDTH - halfViewportWidth);
        float cameraY = Math.min(Math.max(playerPos.y, halfViewportHeight), GameConfig.SEAPORT_MAP_HEIGHT - halfViewportHeight);
        camera.position.set(cameraX, cameraY, 0);
        clampCamera();
        camera.update();

        // map render
        mapRenderer.setView(camera);
        mapRenderer.render();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        kid.render(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width, height);
        gameport.apply();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        world.dispose();
        tiledMap.dispose();
        mapRenderer.dispose();
    }
}
