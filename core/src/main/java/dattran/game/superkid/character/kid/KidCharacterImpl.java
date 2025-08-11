package dattran.game.superkid.character.kid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import dattran.game.superkid.character.Enemy;
import dattran.game.superkid.character.kid.input.KidInput;
import dattran.game.superkid.character.kid.input.KidInputKeyboard;
import dattran.game.superkid.character.kid.state.*;
import dattran.game.superkid.config.GameConfig;
import dattran.game.superkid.loader.graphic.kid.KidAnimationLoader;

import java.util.HashSet;
import java.util.Set;

public class KidCharacterImpl implements KidCharacter {

    private KidState currentState;
    private TextureRegion frame;
    private boolean facingRight = true;
    private int groundContacts = 0;

    private Fixture kickHitbox;
    private Set<Enemy> enemiesKickHit = new HashSet<>();


    private World world;
    private Body body;

    private final KidInput playerInput;

    public KidCharacterImpl(World world, Vector2 startPosition, KidState startState) {
        this.world = world;
        this.body = defineBody(startPosition);

        this.changeState(startState);
        playerInput = new KidInputKeyboard();
    }



    @Override
    public boolean isFacingRight() {
        return facingRight;
    }

    @Override
    public Animation<TextureRegion> getAnimation(KidState state) {
        if (state instanceof KidStateIdle) {
            return KidAnimationLoader.instance.loadedResource().getIdle();
        }
        if (state instanceof KidStateWalk) {
            return KidAnimationLoader.instance.loadedResource().getWalk();
        }
        if (state instanceof KidStateRun) {
            return KidAnimationLoader.instance.loadedResource().getRun();
        }
        if (state instanceof KidStateKick) {
            return KidAnimationLoader.instance.loadedResource().getKick();
        }
        if (state instanceof KidStatePunch) {
            return KidAnimationLoader.instance.loadedResource().getPunch();
        }
        if (state instanceof KidStateThump) {
            return KidAnimationLoader.instance.loadedResource().getThump();
        }
        if (state instanceof KidStateShield) {
            return KidAnimationLoader.instance.loadedResource().getShield();
        }
        if (state instanceof KidStateJump) {
            return KidAnimationLoader.instance.loadedResource().getJump();
        }
        if (state instanceof KidStateFall) {
            return KidAnimationLoader.instance.loadedResource().getFall();
        }
        return KidAnimationLoader.instance.loadedResource().getIdle();
    }

    @Override
    public void changeState(KidState state) {
        if (currentState != null) {
            currentState.exit(this);
        }
        if (currentState != state) {
            currentState = state;
            currentState.enter(this);
        }
    }

    @Override
    public void setCurrentFrame(TextureRegion frame) {
        this.frame = frame;
    }

    @Override
    public void update(float delta) {
        currentState.update(this, delta);
        currentState.handleInput(this, playerInput);
    }

    @Override
    public void render(Batch batch) {
        update(Gdx.graphics.getDeltaTime());

        float width = frame.getRegionWidth() / GameConfig.PPM;
        float height = frame.getRegionHeight() / GameConfig.PPM;

        float spriteX = body.getPosition().x - width / 2;
        float spriteY = body.getPosition().y - height / 2;

        batch.draw(frame, spriteX, spriteY, width, height);
    }

    @Override
    public Body defineBody(Vector2 position) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position.x, position.y);
        bodyDef.bullet = true;
        body = world.createBody(bodyDef);
        body.setUserData(this);

        PolygonShape bodyShape = new PolygonShape();
        float bodyWidth = GameConfig.KID_WIDTH/2;
        float bodyHeight = GameConfig.KID_HEIGHT /2;
        bodyShape.setAsBox(bodyWidth, bodyHeight);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = bodyShape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.2f;
        fixtureDef.filter.categoryBits = GameConfig.KID_BIT;
        fixtureDef.filter.maskBits = GameConfig.GROUND_BIT;

        Fixture mainFixture = body.createFixture(fixtureDef);
        mainFixture.setUserData("kid");
        bodyShape.dispose();
        return body;
    }

    @Override
    public Body getBody() {
        return body;
    }

    @Override
    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
    }

    @Override
    public boolean isOnGround() {
        return groundContacts > 0;
    }

    @Override
    public void incrementGroundContacts() {
        groundContacts++;
    }

    @Override
    public void decrementGroundContacts() {
        if (groundContacts > 0) {
            groundContacts--;
        }
    }

    @Override
    public KidState getCurrentState() {
        return currentState;
    }

    @Override
    public boolean shouldKickHitEnemy(Enemy enemy) {
        Vector2 hitBoxOffset = new Vector2(isFacingRight() ? GameConfig.KID_KICK_OFFSET_RIGHT : GameConfig.KID_KICK_OFFSET_LEFT);
        Vector2 hitBoxCenter = getBody().getPosition().cpy().add(hitBoxOffset);
        Vector2 enemyPos = enemy.getBody().getPosition();

        float hitboxDistance = hitBoxCenter.dst(enemyPos);
        float bodyDistance = getBody().getPosition().dst(enemyPos);

        Gdx.app.log("BodyDistance:" + bodyDistance, "Hitboxdistance:" + hitboxDistance);

        return bodyDistance < GameConfig.KID_KICK_BODY_MAX_ALLOW_DISTANCE || hitboxDistance < GameConfig.KID_KICK_HIT_BOX_MAX_ALLOW_DISTANCE;
    }


    @Override
    public void addKickHitBox() {
        if  (kickHitbox == null) {
            PolygonShape shape = new PolygonShape();
            float halfWidth = GameConfig.KID_KICK_WIDTH / 2;
            float halfHeight = GameConfig.KID_KICK_HEIGHT / 2;
            Vector2 kickHitboxOffset = new Vector2(isFacingRight() ? GameConfig.KID_KICK_OFFSET_RIGHT : GameConfig.KID_KICK_OFFSET_LEFT);
            shape.setAsBox(halfWidth, halfHeight, kickHitboxOffset, 0);

            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            fixtureDef.isSensor = true;
            fixtureDef.filter.categoryBits = GameConfig.KID_ATTACK_BIT;
            fixtureDef.filter.maskBits = GameConfig.ENEMY_BIT;

            kickHitbox = body.createFixture(fixtureDef);
            kickHitbox.setUserData("kid_kick");

            shape.dispose();
        }
    }

    @Override
    public void removeKickHitBox() {
        if (kickHitbox != null) {
            body.destroyFixture(kickHitbox);
            kickHitbox = null;
        }
    }

    @Override
    public void onKickHit() {
        Vector2 kickHitboxOffset = new Vector2(isFacingRight() ? GameConfig.KID_KICK_OFFSET_RIGHT : GameConfig.KID_KICK_OFFSET_LEFT);
        Vector2 kickHitBoxCenter = body.getPosition().cpy().add(kickHitboxOffset);
        float halfWidth = GameConfig.KID_KICK_WIDTH / 2;
        float halfHeight = GameConfig.KID_KICK_HEIGHT / 2;
        body.getWorld().QueryAABB(fixture -> {
            if (fixture != kickHitbox && fixture.getUserData().toString().startsWith("enemy_")) {
                Enemy enemy = (Enemy) fixture.getBody().getUserData();
                if (shouldKickHitEnemy(enemy)) {
                    onKickHitEnemy(enemy);
                }
            }
            return true;
        },kickHitBoxCenter.x - halfWidth, kickHitBoxCenter.y - halfHeight,kickHitBoxCenter.x + halfWidth, kickHitBoxCenter.y + halfHeight );
    }

    @Override
    public void onKickHitEnemy(Enemy enemy) {
        if (enemiesKickHit.contains(enemy)) {
            return;
        }
        enemiesKickHit.add(enemy);
        Gdx.app.log("Kick Enemy:", enemy.toString());;
    }

    @Override
    public Set<Enemy> getEnemiesKickHit() {
        return enemiesKickHit;
    }
}
