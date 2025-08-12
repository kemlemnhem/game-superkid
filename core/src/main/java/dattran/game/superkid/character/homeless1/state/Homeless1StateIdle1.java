package dattran.game.superkid.character.homeless1.state;

import dattran.game.superkid.character.homeless1.Homeless1Character;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Homeless1StateIdle1 extends HomeLess1StateBase {
    private static final Logger LOGGER = Logger.getLogger(Homeless1StateIdle1.class.getName());

    @Override
    public void enter(Homeless1Character homeless1) {
        LOGGER.log(Level.INFO, "Homeless1-Entering Idle State");
        stateTime = 0;
        if (homeless1.getPhysic().getBody() != null) {
            homeless1.getPhysic().getBody().setLinearVelocity(0, homeless1.getPhysic().getBody().getLinearVelocity().y);
        }
    }

    @Override
    public void exit(Homeless1Character homeless1) {
        LOGGER.log(Level.INFO, "Homeless1-Exiting Idle State");
    }
}
