package dattran.game.superkid.character.base.type;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dattran.game.superkid.character.Physic;
import dattran.game.superkid.character.base.state.State;

public interface GameCharacter<S extends State<C, S>, C extends GameCharacter<S, C>> {
    Animation<TextureRegion> getAnimation(S state);

    void changeState(S state);

    void update(float delta);

    int getHp();

    void setHp(int hp);

    boolean isDead();

    Physic getPhysic();

    void render(Batch batch);
}
