package dattran.game.superkid.character.homeless1.state;

import dattran.game.superkid.character.homeless1.Homeless1Character;

import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Homeless1StateIdle extends HomeLess1StateBase {
    private static final Logger LOGGER = Logger.getLogger(Homeless1StateIdle.class.getName());
    private float idleDuration;

    @Override
    public void enter(Homeless1Character homeless1) {
        LOGGER.log(Level.INFO, "Homeless1-Entering Idle State");
        stateTime = 0;
        idleDuration = 1f + random.nextFloat() * 2f;
        if (homeless1.getPhysic().getBody() != null) {
            homeless1.getPhysic().getBody().setLinearVelocity(0, homeless1.getPhysic().getBody().getLinearVelocity().y);
        }
    }

    @Override
    public void update(Homeless1Character homeless1, float delta) {
        super.update(homeless1, delta);

        if (attackKid(homeless1) || huntKid(homeless1)) return;

        if (stateTime >= idleDuration) {
            homeless1.getPhysic().setFacingRight(random.nextBoolean());
            homeless1.changeState(new Homeless1StateWalk());
        }
    }

    @Override
    public void exit(Homeless1Character homeless1) {
        LOGGER.log(Level.INFO, "Homeless1-Exiting Idle State");
    }
}
