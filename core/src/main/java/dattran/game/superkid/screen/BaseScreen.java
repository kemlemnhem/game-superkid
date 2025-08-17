package dattran.game.superkid.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import dattran.game.superkid.character.homeless1.Homeless1;
import dattran.game.superkid.character.homeless1.state.Homeless1StateIdle1;
import dattran.game.superkid.character.kid.state.KidStateIdle;
import dattran.game.superkid.character.kid.type.Kid;
import dattran.game.superkid.config.Flag;
import dattran.game.superkid.config.GameConfig;
import dattran.game.superkid.config.UserData;
import dattran.game.superkid.listener.CharacterContactListener;
import dattran.game.superkid.loader.graphic.homeless1.Homeless1AnimationLoader;
import dattran.game.superkid.loader.graphic.kid.KidAnimationLoader;
import dattran.game.superkid.ui.Hud;

public class BaseScreen implements GameScreen {
    private final Batch batch;

    private final OrthographicCamera camera;
    private final Viewport gameport;

    private final World world;
    private final ScreenManager screenManager = new BaseScreenManager();

    private final TiledMap tiledMap;
    private final OrthogonalTiledMapRenderer mapRenderer;

    private Hud hud;

    protected BaseScreen(Batch batch, String tileMap) {
        this.batch = batch;

        this.camera = new OrthographicCamera();
        float viewportWidth = GameConfig.VIEWPORT_WIDTH / GameConfig.PPM;
        float viewportHeight = GameConfig.VIEWPORT_HEIGHT / GameConfig.PPM;
        this.gameport = new FitViewport(viewportWidth, viewportHeight, camera);
        camera.position.set(viewportWidth / 2, viewportHeight / 2, 0);
        camera.update();


        world = new World(new Vector2(0, GameConfig.WORLD_GRAVITY), true);
        world.setContactListener(new CharacterContactListener());

        // load map
        TmxMapLoader loader = new TmxMapLoader();
        tiledMap = loader.load(tileMap);
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1f / GameConfig.PPM);

        float mapStartX = 0f;
        MapProperties prop = tiledMap.getProperties();
        int mapWidth = prop.get("width", Integer.class);
        int tilePixelWidth = prop.get("tilewidth", Integer.class);
        float mapEndX = (mapWidth*tilePixelWidth)/GameConfig.PPM;
        screenManager.setMapWidth(mapEndX);

        int mapHeight = prop.get("height", Integer.class);
        int tilePixelHeight = prop.get("tileheight", Integer.class);
        float mapEndY = (mapHeight*tilePixelHeight)/GameConfig.PPM;
        screenManager.setMapHeight(mapEndY);
        createMapBoundaries(mapStartX, mapEndX, mapEndY);

        /*for (RectangleMapObject object : tiledMap.getLayers().get("groundObject").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = object.getRectangle();
            BodyDef bodyDef = new BodyDef();
            Body body;
            PolygonShape shape = new PolygonShape();
            FixtureDef fixtureDef = new FixtureDef();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.x + rect.width / 2) / GameConfig.PPM,
                (rect.y + rect.height / 2) / GameConfig.PPM);
            body = world.createBody(bodyDef);

            shape.setAsBox(rect.width / 2 / GameConfig.PPM, rect.height / 2 / GameConfig.PPM);

            fixtureDef.shape = shape;
            fixtureDef.filter.categoryBits  = Flag.combine(Flag.GROUND);
            fixtureDef.filter.maskBits = Flag.combine(Flag.KID, Flag.ENEMY);
            body.createFixture(fixtureDef).setUserData("ground");
            shape.dispose();
        }

        //Enemy Layer
        for (RectangleMapObject object : tiledMap.getLayers().get("enemyObject").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = object.getRectangle();
            float x = rect.x / GameConfig.PPM;
            float y = (Homeless1AnimationLoader.instance.loadedResource().getMaxHeight() + 32) / (2*GameConfig.PPM);
            String type = object.getProperties().get("type", String.class);
            if ("Homeless1".equals(type)) {
                new Homeless1(this, new Vector2(x,y), new Homeless1StateIdle1());
            }
        }*/

        float kidX = KidAnimationLoader.instance.loadedResource().getMaxWidth() / (2*GameConfig.PPM);
        float kidY = (KidAnimationLoader.instance.loadedResource().getMaxHeight() + 32) / (2*GameConfig.PPM);
        new Kid(this, new Vector2(kidX, kidY), new KidStateIdle());

        //Hud

        hud = new Hud(100);
    }

    private void createWall(float x, float centerY, float height) {
        BodyDef bdef = new BodyDef();
        bdef.position.set(x, centerY);
        bdef.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bdef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.1f, height / 2f);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.friction = 0f;
        fdef.restitution = 0f;

        fdef.filter.categoryBits = Flag.combine(Flag.WALL);
        fdef.filter.maskBits = Flag.combine(Flag.KID, Flag.ENEMY);

        body.createFixture(fdef).setUserData(UserData.WALL);

        shape.dispose();
    }

    private void createMapBoundaries(float mapStartX, float mapEndX, float mapHeight) {
        createWall(mapStartX - 0.1f, mapHeight / 2f, mapHeight);
        createWall(mapEndX + 0.1f, mapHeight / 2f, mapHeight);
    }



    public void clampCamera() {
        camera.position.x = Math.max(camera.viewportWidth / 2f,
            Math.min(camera.position.x, GameConfig.VIEWPORT_WIDTH - camera.viewportWidth / 2f));

        camera.position.y = Math.max(camera.viewportHeight / 2f,
            Math.min(camera.position.y, GameConfig.VIEWPORT_HEIGHT - camera.viewportHeight / 2f));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(GameConfig.WORLD_DELTA_TIME, GameConfig.WORLD_VELOCITY_ITERATION, GameConfig.WORLD_POSITION_ITERATION);


        Vector2 kidPosition = screenManager.getKidPosition();
        float halfViewportWidth = camera.viewportWidth / 2f;
        float halfViewportHeight = camera.viewportHeight / 2f;

        float cameraX = Math.min(Math.max(kidPosition.x, halfViewportWidth), GameConfig.VIEWPORT_WIDTH - halfViewportWidth);
        float cameraY = Math.min(Math.max(kidPosition.y, halfViewportHeight), GameConfig.VIEWPORT_HEIGHT - halfViewportHeight);
        camera.position.set(cameraX, cameraY, 0);
        clampCamera();
        camera.update();

        // map render
        // mapRenderer.setView(camera);

        // add margin to avoid cutting big tile
        mapRenderer.setView(camera.combined,
            camera.position.x - camera.viewportWidth / 2 - 2,
            camera.position.y - camera.viewportHeight / 2 - 2,
            camera.viewportWidth + 4,
            camera.viewportHeight + 4
        );


        mapRenderer.render();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        screenManager.render(batch);
        batch.end();
        if (screenManager.getKid() != null) {
            hud.updateHealth(screenManager.getKid().getHp());
        }
        else {
            hud.updateHealth(0);
        }
        hud.draw();
    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width, height);
        gameport.apply();
        hud.resize(width, height);
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
        hud.dispose();
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public ScreenManager getScreenManager() {
        return screenManager;
    }
}
