package dattran.game.superkid.character.kid.state;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dattran.game.superkid.character.homeless1.Homeless1Character;
import dattran.game.superkid.character.homeless1.state.HomeLess1StateBase;
import dattran.game.superkid.character.kid.input.KidInput;
import dattran.game.superkid.character.kid.type.KidCharacter;

import java.util.logging.Level;
import java.util.logging.Logger;

public class KidStateDead extends KidStateBase {
    private static final Logger LOGGER = Logger.getLogger(KidStateDead.class.getName());

    @Override
    public void enter(KidCharacter kid) {
        LOGGER.log(Level.INFO, "Kid-Entering Dead State");
    }

    public void update(KidCharacter kid, float delta) {
       super.update(kid, delta);
        Animation<TextureRegion> animation = kid.getAnimation(this);
        if (animation.isAnimationFinished(stateTime)) {
            kid.setHp(Integer.MIN_VALUE);
        }
    }

    @Override
    public void exit(KidCharacter kid) {
        LOGGER.log(Level.INFO, "Kid-Exiting Dead State");
    }

    @Override
    public void handleInput(KidCharacter kid, KidInput input) {

    }
}
