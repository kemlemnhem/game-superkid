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
            kid.getPhysic().setFacingRight(false);
            kid.getPhysic().getBody().setLinearVelocity(-GameConfig.KID_WALK_SPEED, kid.getPhysic().getBody().getLinearVelocity().y);
        } else if (input.isMoveRightPressed()) {
            kid.getPhysic().setFacingRight(true);
            kid.getPhysic().getBody().setLinearVelocity(GameConfig.KID_WALK_SPEED, kid.getPhysic().getBody().getLinearVelocity().y);
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
