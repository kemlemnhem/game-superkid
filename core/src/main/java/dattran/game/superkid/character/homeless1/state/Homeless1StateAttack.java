package dattran.game.superkid.character.homeless1.state;

import dattran.game.superkid.character.homeless1.hitbox.Homeless1Manager;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Homeless1StateAttack extends Homeless1StateBattle {
    private static final Logger LOGGER = Logger.getLogger(Homeless1StateAttack.class.getName());

    protected void enter(Homeless1Manager attackHitBox) {
        LOGGER.log(Level.INFO, "Homeless1-Entering Attack State");
        stateTime = 0;
        attackHitBox.enterState();
    }

    protected void exit(Homeless1Manager attackHitBox) {
        LOGGER.log(Level.INFO, "Homeless1-Existing Attack State");
        attackHitBox.exitState();
    }
}
