package dattran.game.superkid.character.homeless1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import dattran.game.superkid.character.CharacterManager;
import dattran.game.superkid.character.base.type.Enemy;
import dattran.game.superkid.character.homeless1.hitbox.Homeless1Manager;
import dattran.game.superkid.character.homeless1.state.*;
import dattran.game.superkid.character.Physic;
import dattran.game.superkid.character.PhysicImpl;
import dattran.game.superkid.config.Flag;
import dattran.game.superkid.config.UserData;
import dattran.game.superkid.loader.graphic.homeless1.Homeless1AnimationLoader;
import dattran.game.superkid.screen.GameScreen;

public class Homeless1 implements Homeless1Character, Enemy {
    private final GameScreen gameScreen;
    private final Physic physic;

    private Homeless1State currentState;
    private int hp = 20;

    private final Homeless1Manager attack1BoxManager = Homeless1Manager.createAttack1(this);

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
        gameScreen.getCharacterManager().addEnemy(this);
    }


    @Override
    public Animation<TextureRegion> getAnimation(Homeless1State state) {
        if (state instanceof Homeless1StateIdle1) {
            return Homeless1AnimationLoader.instance.loadedResource().getIdle1();
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
    }

    @Override
    public void gettingHurt(int damage) {
        setHp(getHp() - damage);
        if (getHp() <= 0) {
            changeState(new Homeless1StateDead());
            return;
        }
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
    public boolean isDead() {
        return getHp() == Integer.MIN_VALUE;
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
    public Homeless1Manager getAttack1HitBoxManager() {
        return attack1BoxManager;
    }



}
