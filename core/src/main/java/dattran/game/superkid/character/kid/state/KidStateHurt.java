package dattran.game.superkid.character.kid.state;

import dattran.game.superkid.character.kid.input.KidInput;
import dattran.game.superkid.character.kid.type.KidCharacter;
import dattran.game.superkid.config.GameConfig;

import java.util.logging.Level;
import java.util.logging.Logger;

public class KidStateHurt extends KidStateBattle {
    private static final Logger LOGGER = Logger.getLogger(KidStateHurt.class.getName());

    @Override
    public void enter(KidCharacter kid) {
        LOGGER.log(Level.INFO, "Kid-Entering Hurt State");
        stateTime = 0;
        if (kid.getPhysic().getBody() != null) {
            kid.getPhysic().getBody().setLinearVelocity(kid.getPhysic().isFacingRight() ? -GameConfig.KID_HURT_SHIFT_X : GameConfig.KID_HURT_SHIFT_X, GameConfig.KID_HURT_SHIFT_Y);
        }
    }

    @Override
    public void exit(KidCharacter kid) {
        LOGGER.log(Level.INFO, "Kid-Exiting Hurt State");
    }

    @Override
    public void handleInput(KidCharacter kid, KidInput input) {
        KidStateIdle.fullControl(kid, input);
    }
}
