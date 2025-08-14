package dattran.game.superkid.character.homeless1.state;

import dattran.game.superkid.character.homeless1.Homeless1Character;
import dattran.game.superkid.config.GameConfig;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Homeless1StateHurt extends HomeLess1StateBase {
    private static final Logger LOGGER = Logger.getLogger(Homeless1StateHurt.class.getName());

    @Override
    public void enter(Homeless1Character homeless1) {
        LOGGER.log(Level.INFO, "Homeless1-Entering Hurt State");
        stateTime = 0;
        if (homeless1.getPhysic().getBody() != null) {
            homeless1.getPhysic().getBody().setLinearVelocity(homeless1.getPhysic().isFacingRight() ? -GameConfig.HOMELESS1_HURT_SHIFT_X : GameConfig.HOMELESS1_HURT_SHIFT_X, GameConfig.HOMELESS1_HURT_SHIFT_Y);
        }
    }

    @Override
    public void update(Homeless1Character homeless1, float delta) {
        super.update(homeless1, delta);

        if (!attackKid(homeless1)) {
            huntKid(homeless1);
        }

    }

    @Override
    public void exit(Homeless1Character homeless1) {
        LOGGER.log(Level.INFO, "Homeless1-Exiting Hurt State");
    }
}
