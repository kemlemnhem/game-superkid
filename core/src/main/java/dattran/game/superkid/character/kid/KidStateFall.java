package dattran.game.superkid.character.kid;

import dattran.game.superkid.config.GameConfig;

import java.util.logging.Level;
import java.util.logging.Logger;

public class KidStateFall extends KidStateGeneral {
    private static final Logger LOGGER = Logger.getLogger(KidStateFall.class.getName());


    @Override
    public void update(KidCharacter kid, float delta) {
        super.update(kid, delta);
        if (kid.isOnGround()) {
            float velocityX = kid.getBody().getLinearVelocity().x;
            if (Math.abs(velocityX) < GameConfig.KID_WALK_IDLE_DIFFERENCE) {
                kid.changeState(new KidStateIdle());
            } else {
                kid.changeState(new KidStateWalk());
            }
        }
    }

    @Override
    public void handleInput(KidCharacter kid, KidInput input) {
        if (input.isMoveLeftPressed()) {
            kid.setFacingRight(false);
        } else if (input.isMoveRightPressed()) {
            kid.setFacingRight(true);
        }
    }

    @Override
    public void enter(KidCharacter kid) {
        LOGGER.log(Level.INFO, "Entering FallState");
        kid.getBody().setGravityScale(GameConfig.KID_JUMP_GRAVITY_SCALE_FAST);
        stateTime = 0;
    }

    @Override
    public void exit(KidCharacter kid) {
        LOGGER.log(Level.INFO, "Exiting FallState");
        kid.getBody().setGravityScale(GameConfig.KID_JUMP_GRAVITY_SCALE_NORMAL);
    }
}
