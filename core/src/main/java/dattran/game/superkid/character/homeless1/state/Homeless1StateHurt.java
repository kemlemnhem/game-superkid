package dattran.game.superkid.character.homeless1.state;

import dattran.game.superkid.character.homeless1.Homeless1Character;
import dattran.game.superkid.config.GameConfig;

import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Homeless1StateHurt extends HomeLess1StateBase {

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
}
