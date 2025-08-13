package dattran.game.superkid.character.kid.type;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import dattran.game.superkid.character.Physic;
import dattran.game.superkid.character.PhysicImpl;
import dattran.game.superkid.character.base.type.Player;
import dattran.game.superkid.character.kid.hitbox.KidManager;
import dattran.game.superkid.character.kid.input.KidInput;
import dattran.game.superkid.character.kid.input.KidInputKeyboard;
import dattran.game.superkid.character.kid.state.*;
import dattran.game.superkid.config.Flag;
import dattran.game.superkid.config.UserData;
import dattran.game.superkid.loader.graphic.kid.KidAnimationLoader;
import dattran.game.superkid.screen.GameScreen;

public class Kid implements KidCharacter, Player {
    private final GameScreen gameScreen;

    private final Physic physic;

    private KidState currentState;
    private final KidInput playerInput;

    private int hp = 100;

    private final KidManager kickHitBoxManager = KidManager.createKick(this);
    private final KidManager punchHitBoxManager = KidManager.createPunch(this);
    private final KidManager thumbHitBoxManager = KidManager.createThump(this);

    public Kid(GameScreen gameScreen, Vector2 startPosition, KidState startState) {
        this.physic = PhysicImpl.PhysikImplBuilder.aPhysikImpl()
            .setCharacter(this)
            .setWorld(gameScreen.getWorld()).setStartPosition(startPosition)
            .setCategoryFlags(Flag.KID)
            .setMaskFlags(Flag.ENEMY_ATTACK, Flag.GROUND, Flag.WALL)
            .setUserData(UserData.KID)
            .build();
        this.changeState(startState);
        playerInput = new KidInputKeyboard();
        this.gameScreen = gameScreen;
        gameScreen.getScreenManager().addKid(this);
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
        if (state instanceof KidStateHurt) {
            return KidAnimationLoader.instance.loadedResource().getHurt();
        }
        if (state instanceof KidStateDead) {
            return KidAnimationLoader.instance.loadedResource().getDead();
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
    public void gettingHurt(int damage) {
        setHp(getHp() - damage);
        if (getHp() <= 0) {
            changeState(new KidStateDead());
            return;
        }
        changeState(new KidStateHurt());
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
    public GameScreen getGameScreen() {
        return gameScreen;
    }

    @Override
    public KidManager getKickHitBoxManager() {
        return kickHitBoxManager;
    }

    @Override
    public KidManager getPunchHitBoxManager() {
        return punchHitBoxManager;
    }

    @Override
    public KidManager getThumpHitBoxManager() {
        return thumbHitBoxManager;
    }
}
