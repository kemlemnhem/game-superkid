package dattran.game.superkid.character.homeless1.state;

import dattran.game.superkid.character.base.type.Player;
import dattran.game.superkid.character.homeless1.Homeless1Character;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Homeless1StateAttack1 extends Homeless1StateBattle {
    private static final Logger LOGGER = Logger.getLogger(Homeless1StateAttack1.class.getName());


    @Override
    public void enter(Homeless1Character homeless1) {
        LOGGER.log(Level.INFO, "Homeless1-Entering Attack1 State");
        stateTime = 0;
        homeless1.getAttack1HitBoxManager().enterState();
    }

    @Override
    public void exit(Homeless1Character homeless1) {
        LOGGER.log(Level.INFO, "Homeless1-Existing Attack1 State");
        homeless1.getAttack1HitBoxManager().exitState();
    }
}
