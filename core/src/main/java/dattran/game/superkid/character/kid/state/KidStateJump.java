package dattran.game.superkid.character.kid.state;

import dattran.game.superkid.character.kid.KidCharacter;
import dattran.game.superkid.character.kid.KidStateGeneral;
import dattran.game.superkid.character.kid.input.KidInput;
import dattran.game.superkid.config.GameConfig;

import java.util.logging.Level;
import java.util.logging.Logger;

public class KidStateJump extends KidStateGeneral {
    private static final Logger LOGGER = Logger.getLogger(KidStateJump.class.getName());

    @Override
    public void update(KidCharacter kid, float delta) {
        super.update(kid, delta);
        if ( stateTime >= GameConfig.KID_JUMP_MAX_TIME || kid.getBody().getLinearVelocity().y < 0) {
            kid.changeState(new KidStateFall());
        }
    }

    @Override
    public void handleInput(KidCharacter kid, KidInput input) {
        if (input.isJumpHeld() && stateTime < GameConfig.KID_JUMP_MAX_TIME) {
            float direction = kid.isFacingRight() ? 1 : -1;
            kid.getBody().setLinearVelocity(GameConfig.KID_JUMP_SPEED * direction, GameConfig.KID_JUMP_VELOCITY);
            kid.getBody().setGravityScale(GameConfig.KID_JUMP_GRAVITY_SCALE_SLOW);
        }
        else {
            kid.getBody().setGravityScale(GameConfig.KID_JUMP_GRAVITY_SCALE_FAST);
        }
    }

    @Override
    public void enter(KidCharacter kid) {
        LOGGER.log(Level.INFO, "Entering JumpState");
        stateTime = 0;
        float direction = kid.isFacingRight() ? 1 : -1;
        kid.getBody().setLinearVelocity(GameConfig.KID_JUMP_SPEED * direction, GameConfig.KID_JUMP_VELOCITY);
        kid.getBody().setGravityScale(GameConfig.KID_JUMP_GRAVITY_SCALE_SLOW);
    }

    @Override
    public void exit(KidCharacter kid) {
        LOGGER.log(Level.INFO, "Exiting JumpState");
        kid.getBody().setGravityScale(GameConfig.KID_JUMP_GRAVITY_SCALE_NORMAL);
    }
}
