package dattran.game.superkid.character.homeless1.state;

import dattran.game.superkid.character.homeless1.Homeless1Character;
import dattran.game.superkid.character.homeless1.hitbox.Homeless1HitBox;
import dattran.game.superkid.config.GameConfig;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Homeless1StateAttack extends Homeless1StateBattle {
    private static final Logger LOGGER = Logger.getLogger(Homeless1StateAttack.class.getName());

    protected void enter(Homeless1HitBox attackHitBox) {
        LOGGER.log(Level.INFO, "Homeless1-Entering Attack State");
        stateTime = 0;
        attackHitBox.enterState();
    }

    protected void exit(Homeless1HitBox attackHitBox, Homeless1Character homeless1) {
        homeless1.setAttackCoolDown(GameConfig.HOMELESS_1_ATTACK_DELAY);
        LOGGER.log(Level.INFO, "Homeless1-Existing Attack State");
        attackHitBox.exitState();
    }
}
