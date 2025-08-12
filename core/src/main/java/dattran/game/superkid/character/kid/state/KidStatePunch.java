package dattran.game.superkid.character.kid.state;

import dattran.game.superkid.character.kid.type.KidCharacter;
import dattran.game.superkid.character.kid.input.KidInput;

import java.util.logging.Level;
import java.util.logging.Logger;

public class KidStatePunch extends KidStateBattle {
    private static final Logger LOGGER = Logger.getLogger(KidStatePunch.class.getName());
    @Override
    public void handleInput(KidCharacter kid, KidInput input) {

    }

    @Override
    public void enter(KidCharacter kid) {
        LOGGER.log(Level.INFO, "Entering Punch State");
        stateTime = 0;
        kid.getPunchHitBoxManager().enterState();
    }

    @Override
    public void exit(KidCharacter kid) {
        LOGGER.log(Level.INFO, "Existing Punch State");
        kid.getPunchHitBoxManager().exitState();
    }
}
