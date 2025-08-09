package dattran.game.superkid.character.kid;

import dattran.game.superkid.config.GameConfig;

import java.util.logging.Level;
import java.util.logging.Logger;

public class KidStateWalk extends KidStateGeneral {
    private static final Logger LOGGER = Logger.getLogger(KidStateWalk.class.getName());
    @Override
    public void handleInput(KidCharacter kid, KidInput input) {
        if (input.isMoveLeftPressed()) {
            kid.setFacingRight(false);
            kid.getBody().setLinearVelocity(-GameConfig.KID_WALK_SPEED, kid.getBody().getLinearVelocity().y);
        } else if (input.isMoveRightPressed()) {
            kid.setFacingRight(true);
            kid.getBody().setLinearVelocity(GameConfig.KID_WALK_SPEED, kid.getBody().getLinearVelocity().y);
        } else {
            kid.changeState(new KidStateIdle());
        }
    }

    @Override
    public void enter(KidCharacter character) {
        LOGGER.log(Level.INFO, "Entering WalkState");
        stateTime = 0;
    }


    @Override
    public void exit(KidCharacter character) {
        LOGGER.log(Level.INFO, "Exiting WalkState");
    }
}
