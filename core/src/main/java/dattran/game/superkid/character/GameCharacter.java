package dattran.game.superkid.character;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface GameCharacter<S extends CharacterState<C, S>, C extends GameCharacter<S, C>> {
    Animation<TextureRegion> getAnimation(S state);

    void changeState(S state);

    void update(float delta);

    int getHp();

    void setHp(int hp);

    boolean isDead();

    Physik getPhysik();

    void render(Batch batch);
}
