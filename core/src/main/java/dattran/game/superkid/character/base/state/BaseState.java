package dattran.game.superkid.character.base.state;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import dattran.game.superkid.character.base.type.GameCharacter;
import dattran.game.superkid.config.GameConfig;

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

        float height = frame.getRegionHeight() / GameConfig.PPM;
        float minY = height / 2 + 64 / GameConfig.PPM;
        float maxY = (11 * 32) / GameConfig.PPM - height / 2;

        Vector2 pos = character.getPhysic().getBody().getPosition();
        float clampedY = MathUtils.clamp(pos.y, minY, maxY);

        // Giữ nguyên X, chỉ clamp Y
        character.getPhysic().getBody().setTransform(pos.x, clampedY, 0);


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
