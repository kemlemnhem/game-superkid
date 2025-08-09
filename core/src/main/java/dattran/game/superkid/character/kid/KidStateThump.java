package dattran.game.superkid.character.kid;

import java.util.logging.Level;
import java.util.logging.Logger;

public class KidStateThump extends KidStateBattle {
    private static final Logger LOGGER = Logger.getLogger(KidStateThump.class.getName());
    @Override
    public void handleInput(KidCharacter kid, KidInput input) {

    }

    @Override
    public void enter(KidCharacter character) {
        LOGGER.log(Level.INFO, "Entering Thump State");
        stateTime = 0;
    }

    @Override
    public void exit(KidCharacter character) {
        LOGGER.log(Level.INFO, "Existing Thump State");
    }
}
