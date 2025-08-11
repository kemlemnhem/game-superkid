package dattran.game.superkid.character.kid.state;

import dattran.game.superkid.character.kid.KidCharacter;
import dattran.game.superkid.character.kid.input.KidInput;
import dattran.game.superkid.config.GameConfig;

import java.util.logging.Level;
import java.util.logging.Logger;

public class KidStateWalk extends KidStateGeneral {
    private static final Logger LOGGER = Logger.getLogger(KidStateWalk.class.getName());
    @Override
    public void handleInput(KidCharacter kid, KidInput input) {
        if (input.isMoveLeftPressed()) {
            kid.getPhysik().setFacingRight(false);
            kid.getPhysik().getBody().setLinearVelocity(-GameConfig.KID_WALK_SPEED, kid.getPhysik().getBody().getLinearVelocity().y);
        } else if (input.isMoveRightPressed()) {
            kid.getPhysik().setFacingRight(true);
            kid.getPhysik().getBody().setLinearVelocity(GameConfig.KID_WALK_SPEED, kid.getPhysik().getBody().getLinearVelocity().y);
        } else if (input.isKickPressed()) {
            kid.changeState(new KidStateKick());
        } else {
            kid.changeState(new KidStateIdle());
        }

        if (input.isRunKeyHeld()) {
            kid.changeState(new KidStateRun());
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
