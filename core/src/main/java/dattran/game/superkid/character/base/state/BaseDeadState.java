package dattran.game.superkid.character.base.state;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dattran.game.superkid.character.base.type.GameCharacter;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseDeadState<
    C extends GameCharacter<S, C>,
    S extends State<C, S>
    > extends BaseState<C, S> {

    private static final Logger LOGGER = Logger.getLogger(BaseDeadState.class.getSimpleName());

    @Override
    public void enter(C character) {
        LOGGER.log(Level.INFO, character.getClass().getName() + "-Entering Dead State");
    }

    @Override
    public void update(C character, float delta) {
        super.update(character, delta);
        @SuppressWarnings("unchecked")
        Animation<TextureRegion> animation = character.getAnimation((S) this);
        if (animation.isAnimationFinished(stateTime)) {
            character.getPhysic().markForRemoval();
        }
    }

    @Override
    public void exit(C character) {
        LOGGER.log(Level.INFO, character.getClass().getSimpleName() + "-Existing Dead State");
    }

}
