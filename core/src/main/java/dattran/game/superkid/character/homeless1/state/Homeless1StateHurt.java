package dattran.game.superkid.character.homeless1.state;

import dattran.game.superkid.character.homeless1.Homeless1Character;
import dattran.game.superkid.config.GameConfig;

import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Homeless1StateHurt extends HomeLess1StateBase {
    private static final Logger LOGGER = Logger.getLogger(Homeless1StateHurt.class.getName());

    @Override
    public void enter(Homeless1Character homeless1) {
        LOGGER.log(Level.INFO, "Homeless1-Entering Hurt State");
        stateTime = 0;
    }

    @Override
    public void update(Homeless1Character homeless1, float delta) {
        super.update(homeless1, delta);
        if (stateTime >= 0.4f) { // hit time
            if (homeless1.isDead()) {
                homeless1.changeState(new Homeless1StateDead());
            } else {
                homeless1.changeState(random.nextBoolean() ? new Homeless1StateIdle1() : new Homeless1StateIdle2());
            }
        }

    }

    @Override
    public void exit(Homeless1Character homeless1) {
        LOGGER.log(Level.INFO, "Homeless1-Exiting Hurt State");
    }
}
