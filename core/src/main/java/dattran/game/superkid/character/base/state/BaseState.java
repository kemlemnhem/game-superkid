package dattran.game.superkid.character.base.state;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dattran.game.superkid.character.base.type.GameCharacter;
import dattran.game.superkid.character.homeless1.state.Homeless1StateHurt;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseState<
    C extends GameCharacter<S, C>,
    S extends State<C, S>
    > implements State<C, S> {
    private static final Logger LOGGER = Logger.getLogger(BaseState.class.getName());

    protected float stateTime;

    @Override
    public void enter(C character) {
        LOGGER.log(Level.INFO, character.getClass().getSimpleName() + "- entering " + this.getClass().getSimpleName());
    }

    @Override
    public void exit(C character) {
        LOGGER.log(Level.INFO, character.getClass().getSimpleName() + "- exiting " + this.getClass().getSimpleName());
    }

    @Override
    public void update(C character, float delta) {
        stateTime += delta;
        @SuppressWarnings("unchecked")
        Animation<TextureRegion> animation = character.getAnimation((S) this);
        TextureRegion frame = animation.getKeyFrame(stateTime);

        if (character.hitWall()) {
            character.getPhysic().setFacingRight(!character.getPhysic().isFacingRight());
            character.getPhysic().getBody().setLinearVelocity(character.getPhysic().isFacingRight() ? -0.2f : 0.2f, character.getPhysic().getBody().getLinearVelocity().y);
        }
        boolean facingRight = character.getPhysic().isFacingRight();
        if (!facingRight && !frame.isFlipX()) {
            frame.flip(true, false);
        } else if (facingRight && frame.isFlipX()) {
            frame.flip(true, false);
        }

        character.getPhysic().setCurrentFrame(frame);
    }

    public float getStateTime() {
        return stateTime;
    }
}
