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
import dattran.game.superkid.character.Physik;
import dattran.game.superkid.character.PhysikImpl;
import dattran.game.superkid.config.Flag;
import dattran.game.superkid.config.GameConfig;
import dattran.game.superkid.config.UserData;
import dattran.game.superkid.loader.graphic.kid.KidAnimationLoader;

import java.util.HashSet;
import java.util.Set;

public class KidCharacterImpl implements KidCharacter {
    private final Physik physik;

    private KidState currentState;
    private Fixture kickHitbox;
    private final Set<Enemy> enemiesKickHit = new HashSet<>();
    private final KidInput playerInput;

    private int hp = 100;

    public KidCharacterImpl(World world, Vector2 startPosition, KidState startState) {
        this.physik = PhysikImpl.PhysikImplBuilder.aPhysikImpl()
            .setCharacter(this)
            .setWorld(world).setStartPosition(startPosition)
            .setCategoryBits(Flag.KID)
            .setMaskBits(Flag.GROUND)
            .setUserData(UserData.KID)
            .build();
        this.changeState(startState);
        playerInput = new KidInputKeyboard();
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
    public void update(float delta) {
        currentState.update(this, delta);
        currentState.handleInput(this, playerInput);
    }


    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public boolean isDead() {
        return getHp() <= 0;
    }

    @Override
    public Physik getPhysik() {
        return physik;
    }

    @Override
    public void render(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        getPhysik().render(batch);
    }


    @Override
    public boolean shouldKickHitEnemy(Enemy enemy) {
        Vector2 hitBoxOffset = new Vector2(physik.isFacingRight() ? GameConfig.KID_KICK_OFFSET_RIGHT : GameConfig.KID_KICK_OFFSET_LEFT);
        Vector2 hitBoxCenter = physik.getBody().getPosition().cpy().add(hitBoxOffset);
        Vector2 enemyPos = enemy.getPhysik().getBody().getPosition();

        float hitboxDistance = hitBoxCenter.dst(enemyPos);
        float bodyDistance = physik.getBody().getPosition().dst(enemyPos);

        return bodyDistance < GameConfig.KID_KICK_BODY_MAX_ALLOW_DISTANCE || hitboxDistance < GameConfig.KID_KICK_HIT_BOX_MAX_ALLOW_DISTANCE;
    }


    @Override
    public void addKickHitBox() {
        if  (kickHitbox == null) {
            PolygonShape shape = new PolygonShape();
            float halfWidth = GameConfig.KID_KICK_WIDTH / 2;
            float halfHeight = GameConfig.KID_KICK_HEIGHT / 2;
            Vector2 kickHitboxOffset = new Vector2(physik.isFacingRight() ? GameConfig.KID_KICK_OFFSET_RIGHT : GameConfig.KID_KICK_OFFSET_LEFT);
            shape.setAsBox(halfWidth, halfHeight, kickHitboxOffset, 0);

            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            fixtureDef.isSensor = true;
            fixtureDef.filter.categoryBits = Flag.combine(Flag.KID_ATTACK);
            fixtureDef.filter.maskBits = Flag.combine(Flag.ENEMY);

            kickHitbox = physik.getBody().createFixture(fixtureDef);
            kickHitbox.setUserData("kid_kick");

            shape.dispose();
        }
    }

    @Override
    public void removeKickHitBox() {
        if (kickHitbox != null) {
            physik.getBody().destroyFixture(kickHitbox);
            kickHitbox = null;
        }
    }

    @Override
    public void onKickHit() {
        Vector2 kickHitboxOffset = new Vector2(physik.isFacingRight() ? GameConfig.KID_KICK_OFFSET_RIGHT : GameConfig.KID_KICK_OFFSET_LEFT);
        Vector2 kickHitBoxCenter = physik.getBody().getPosition().cpy().add(kickHitboxOffset);
        float halfWidth = GameConfig.KID_KICK_WIDTH / 2;
        float halfHeight = GameConfig.KID_KICK_HEIGHT / 2;
        physik.getBody().getWorld().QueryAABB(fixture -> {
            if (fixture != kickHitbox && fixture.getUserData().toString().startsWith("enemy_")) {

                Enemy enemy = (Enemy) ((Physik) fixture.getBody().getUserData()).getCharacter();
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
        enemy.getPhysik().setFacingRight(!physik.isFacingRight());
        enemy.gettingHurtByKid(this);
    }

    @Override
    public Set<Enemy> getEnemiesKickHit() {
        return enemiesKickHit;
    }

    @Override
    public int getKickPower() {
        return 10;
    }
}
