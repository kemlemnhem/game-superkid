package dattran.game.superkid.character.kid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import dattran.game.superkid.character.Physic;
import dattran.game.superkid.character.PhysicImpl;
import dattran.game.superkid.character.kid.hitbox.KidHitBoxManager;
import dattran.game.superkid.character.kid.input.KidInput;
import dattran.game.superkid.character.kid.input.KidInputKeyboard;
import dattran.game.superkid.character.kid.state.*;
import dattran.game.superkid.config.Flag;
import dattran.game.superkid.config.UserData;
import dattran.game.superkid.loader.graphic.kid.KidAnimationLoader;

public class KidCharacterImpl implements KidCharacter {
    private final Physic physic;

    private KidState currentState;
    private final KidInput playerInput;

    private int hp = 100;

    private final KidHitBoxManager kickHitBoxManager = KidHitBoxManager.createKick(this);
    private final KidHitBoxManager punchHitBoxManager = KidHitBoxManager.createPunch(this);
    private final KidHitBoxManager thumbHitBoxManager = KidHitBoxManager.createThump(this);

    public KidCharacterImpl(World world, Vector2 startPosition, KidState startState) {
        this.physic = PhysicImpl.PhysikImplBuilder.aPhysikImpl()
            .setCharacter(this)
            .setWorld(world).setStartPosition(startPosition)
            .setCategoryFlags(Flag.KID)
            .setMaskFlags(Flag.GROUND)
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
    public Physic getPhysic() {
        return physic;
    }

    @Override
    public void render(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        getPhysic().render(batch);
    }

    @Override
    public KidHitBoxManager getKickHitBoxManager() {
        return kickHitBoxManager;
    }

    @Override
    public KidHitBoxManager getPunchHitBoxManager() {
        return punchHitBoxManager;
    }

    @Override
    public KidHitBoxManager getThumpHitBoxManager() {
        return thumbHitBoxManager;
    }
}
