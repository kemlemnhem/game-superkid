package dattran.game.superkid.character.homeless1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import dattran.game.superkid.character.Enemy;
import dattran.game.superkid.character.homeless1.state.Homeless1State;
import dattran.game.superkid.character.homeless1.state.Homeless1StateDead;
import dattran.game.superkid.character.homeless1.state.Homeless1StateHurt;
import dattran.game.superkid.character.homeless1.state.Homeless1StateIdle1;
import dattran.game.superkid.character.kid.KidCharacter;
import dattran.game.superkid.character.Physic;
import dattran.game.superkid.character.PhysicImpl;
import dattran.game.superkid.config.Flag;
import dattran.game.superkid.config.UserData;
import dattran.game.superkid.loader.graphic.homeless1.Homeless1AnimationLoader;

public class Homeless1CharacterImpl implements Homeless1Character, Enemy {
    private final Physic physic;

    private Homeless1State currentState;
    private int hp = 20;

    public Homeless1CharacterImpl(World world, Vector2 startPosition, Homeless1State startState) {
        this.physic = PhysicImpl.PhysikImplBuilder.aPhysikImpl()
            .setCharacter(this)
            .setWorld(world).setStartPosition(startPosition)
            .setCategoryFlags(Flag.ENEMY)
            .setMaskFlags(Flag.GROUND, Flag.KID_ATTACK)
            .setUserData(UserData.HOMELESS_1)
            .build();
        this.changeState(startState);
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
            die();
            return;
        }
        changeState(new Homeless1StateHurt());
    }

    private void die() {
        changeState(new Homeless1StateDead());
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

}
