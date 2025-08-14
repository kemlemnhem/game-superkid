package dattran.game.superkid.character.kid.state;

import dattran.game.superkid.character.kid.type.KidCharacter;
import dattran.game.superkid.character.kid.input.KidInput;

import java.util.logging.Level;
import java.util.logging.Logger;

public class KidStateIdle extends KidStateBase {
    private static final Logger LOGGER = Logger.getLogger(KidStateIdle.class.getName());


    @Override
    public void handleInput(KidCharacter kid, KidInput input) {
        fullControl(kid, input);
    }

    static void fullControl(KidCharacter kid, KidInput input) {
        if (input.isMoveLeftPressed()) {
            kid.getPhysic().setFacingRight(false);
            kid.changeState(new KidStateWalk());
        } else if (input.isMoveRightPressed()) {
            kid.getPhysic().setFacingRight(true);
            kid.changeState(new KidStateWalk());
        } else if(input.isKickPressed()) {
            kid.changeState(new KidStateKick());
        }
        else if (input.isPunchPressed()) {
            kid.changeState(new KidStatePunch());
        }
        else if (input.isThumpPressed()) {
            kid.changeState(new KidStateThump());
        } else if (input.isShieldPressed()) {
            kid.changeState(new KidStateShield());
        }
        else if (input.isJumpHeld()) {
            kid.changeState(new KidStateJump());
        }
    }

    @Override
    public void enter(KidCharacter kid) {
        LOGGER.log(Level.INFO, "Entering Idle State");
        stateTime = 0;
        if (kid.getPhysic().getBody() != null) {
            kid.getPhysic().getBody().setLinearVelocity(0, kid.getPhysic().getBody().getLinearVelocity().y);
        }
    }

    @Override
    public void exit(KidCharacter character) {
        LOGGER.log(Level.INFO, "Exiting Idle State");
    }
}
