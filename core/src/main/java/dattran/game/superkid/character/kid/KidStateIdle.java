package dattran.game.superkid.character.kid;

import java.util.logging.Level;
import java.util.logging.Logger;

public class KidStateIdle extends KidStateGeneral {
    private static final Logger LOGGER = Logger.getLogger(KidStateIdle.class.getName());


    @Override
    public void handleInput(KidCharacter kid, KidInput input) {
        if (input.isMoveLeftPressed()) {
            kid.setFacingRight(false);
            kid.changeState(new KidStateWalk());
        } else if (input.isMoveRightPressed()) {
            kid.setFacingRight(true);
            kid.changeState(new KidStateWalk());
        }
    }

    @Override
    public void enter(KidCharacter kid) {
        LOGGER.log(Level.INFO, "Entering Idle State");
        stateTime = 0;
        if (kid.getBody() != null) {
            kid.getBody().setLinearVelocity(0, kid.getBody().getLinearVelocity().y);
        }
    }

    @Override
    public void exit(KidCharacter character) {
        LOGGER.log(Level.INFO, "Exiting Idle State");
    }
}
