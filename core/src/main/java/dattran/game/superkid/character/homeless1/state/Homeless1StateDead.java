package dattran.game.superkid.character.homeless1.state;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dattran.game.superkid.character.homeless1.Homeless1Character;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Homeless1StateDead extends HomeLess1StateBase {
    private static final Logger LOGGER = Logger.getLogger(Homeless1StateDead.class.getName());

    @Override
    public void enter(Homeless1Character homeless1) {
        LOGGER.log(Level.INFO, "Homeless1-Entering Dead State");
    }

    public void update(Homeless1Character homeless1, float delta) {
       super.update(homeless1, delta);
        Animation<TextureRegion> animation = homeless1.getAnimation(this);
        if (animation.isAnimationFinished(stateTime)) {
            homeless1.getPhysic().markForRemoval();
        }
    }

    @Override
    public void exit(Homeless1Character homeless1) {
        LOGGER.log(Level.INFO, "Homeless1-Exiting Dead State");
    }
}
