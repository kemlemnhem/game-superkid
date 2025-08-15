package dattran.game.superkid.character.homeless1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import dattran.game.superkid.character.Physic;
import dattran.game.superkid.character.PhysicImpl;
import dattran.game.superkid.character.base.type.Enemy;
import dattran.game.superkid.character.homeless1.hitbox.Homeless1HitBox;
import dattran.game.superkid.character.homeless1.state.*;
import dattran.game.superkid.config.Flag;
import dattran.game.superkid.config.UserData;
import dattran.game.superkid.loader.graphic.homeless1.Homeless1AnimationLoader;
import dattran.game.superkid.screen.GameScreen;

public class Homeless1 implements Homeless1Character, Enemy {
    private final GameScreen gameScreen;
    private final Physic physic;

    private Homeless1State currentState;
    private int hp = 200;
    private float attackCoolDown;

    private final Homeless1HitBox attack1BoxManager = Homeless1HitBox.createAttack1(this);

    private final Homeless1HitBox attack2BoxManager = Homeless1HitBox.createAttack2(this);

    private final Homeless1HitBox specialBoxManager = Homeless1HitBox.createSpecial(this);

    public Homeless1(GameScreen gameScreen, Vector2 startPosition, Homeless1State startState) {
        this.gameScreen = gameScreen;
        this.physic = PhysicImpl.PhysikImplBuilder.aPhysikImpl()
            .setCharacter(this)
            .setWorld(gameScreen.getWorld()).setStartPosition(startPosition)
            .setCategoryFlags(Flag.ENEMY)
            .setMaskFlags(Flag.GROUND, Flag.KID_ATTACK, Flag.WALL)
            .setUserData(UserData.HOMELESS_1)
            .build();
        this.changeState(startState);
        gameScreen.getScreenManager().addEnemy(this);
    }


    @Override
    public Animation<TextureRegion> getAnimation(Homeless1State state) {
        if (state instanceof Homeless1StateIdle1) {
            return Homeless1AnimationLoader.instance.loadedResource().getIdle1();
        }
        if (state instanceof Homeless1StateIdle2) {
            return Homeless1AnimationLoader.instance.loadedResource().getIdle2();
        }
        if (state instanceof Homeless1StateHurt) {
            return Homeless1AnimationLoader.instance.loadedResource().getHurt();
        }
        if (state instanceof Homeless1StateDead) {
            return Homeless1AnimationLoader.instance.loadedResource().getDead();
        }
        if (state instanceof Homeless1StateAttack1) {
            return Homeless1AnimationLoader.instance.loadedResource().getAttack1();
        }
        if (state instanceof Homeless1StateWalk) {
            return Homeless1AnimationLoader.instance.loadedResource().getWalk();
        }
        if (state instanceof Homeless1StateRun) {
            return Homeless1AnimationLoader.instance.loadedResource().getRun();
        }
        if (state instanceof Homeless1StateAttack2) {
            return Homeless1AnimationLoader.instance.loadedResource().getAttack2();
        }
        if (state instanceof Homeless1StateSpecial) {
            return Homeless1AnimationLoader.instance.loadedResource().getSpecial();
        }

        return Homeless1AnimationLoader.instance.loadedResource().getIdle1();
    }

    @Override
    public void changeState(Homeless1State state) {
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
        if (attackCoolDown > 0) {
            attackCoolDown -= delta;
            if (attackCoolDown < 0) {
                attackCoolDown = 0;
            }
        }
    }

    @Override
    public void gettingHurt(int damage) {
        setHp(getHp() - damage);
        changeState(new Homeless1StateHurt());
    }

    @Override
    public int getHp() {
        return this.hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public Physic getPhysic() {
        return this.physic;
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
    public Homeless1State getCurrentState() {
        return currentState;
    }

    @Override
    public Homeless1HitBox getAttack1HitBoxManager() {
        return attack1BoxManager;
    }

    @Override
    public Homeless1HitBox getAttack2HitBoxManager() {
        return attack2BoxManager;
    }

    @Override
    public Homeless1HitBox getSpecialHitBoxManager() {
        return specialBoxManager;
    }

    @Override
    public Homeless1HitBox getCurrentAttackHitBox() {
        if (currentState instanceof Homeless1StateAttack1) {
            return attack1BoxManager;
        }
        else if (currentState instanceof Homeless1StateAttack2) {
            return attack2BoxManager;
        }
        else if (currentState instanceof Homeless1StateSpecial) {
            return specialBoxManager;
        }
        return null;
    }


    @Override
    public float getAttackCoolDown() {
        return attackCoolDown;
    }

    @Override
    public void setAttackCoolDown(float coolDown) {
        this.attackCoolDown = coolDown;
    }

}
