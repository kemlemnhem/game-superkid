package dattran.game.superkid.character.kid.state;

import dattran.game.superkid.character.kid.type.KidCharacter;
import dattran.game.superkid.character.kid.input.KidInput;
import dattran.game.superkid.config.GameConfig;

import java.util.logging.Level;
import java.util.logging.Logger;

public class KidStateRun extends KidStateBase {
    private static final Logger LOGGER = Logger.getLogger(KidStateRun.class.getName());


    @Override
    public void handleInput(KidCharacter kid, KidInput input) {
        if (input.isMoveLeftPressed()) {
            kid.getPhysic().setFacingRight(false);
            kid.getPhysic().getBody().setLinearVelocity(-GameConfig.KID_RUN_SPEED, kid.getPhysic().getBody().getLinearVelocity().y);
        } else if (input.isMoveRightPressed()) {
            kid.getPhysic().setFacingRight(true);
            kid.getPhysic().getBody().setLinearVelocity(GameConfig.KID_RUN_SPEED, kid.getPhysic().getBody().getLinearVelocity().y);
        } else {
            kid.changeState(new KidStateIdle());
        }

    }

    @Override
    public void enter(KidCharacter kid) {
        LOGGER.log(Level.INFO, "Entering Run State");
        stateTime = 0;
    }

    @Override
    public void exit(KidCharacter character) {
        LOGGER.log(Level.INFO, "Exiting Run State");
    }
}
