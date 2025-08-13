package dattran.game.superkid.character.homeless1.state;

import dattran.game.superkid.character.homeless1.Homeless1Character;
import dattran.game.superkid.config.GameConfig;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Homeless1StateRun extends HomeLess1StateBase {
    private static final Logger LOGGER = Logger.getLogger(Homeless1StateRun.class.getName());

    @Override
    public void update(Homeless1Character homeless1, float delta) {
        super.update(homeless1, delta);

        if (attackKid(homeless1)) {
            return;
        }
        if (!isKidVisible(homeless1)) {
            homeless1.getPhysic().getBody().setLinearVelocity(
                0, homeless1.getPhysic().getBody().getLinearVelocity().y
            );
            homeless1.changeState(new Homeless1StateIdle1());
            return;
        }

        homeless1.getPhysic().getBody().setLinearVelocity(homeless1.getPhysic().isFacingRight() ? GameConfig.HOMELESS_1_RUN_SPEED : -GameConfig.HOMELESS_1_RUN_SPEED, homeless1.getPhysic().getBody().getLinearVelocity().y);

    }

    @Override
    public void enter(Homeless1Character homeless1) {
        LOGGER.log(Level.INFO, "Homeless1-Entering Run State");
        stateTime = 0;
    }

    @Override
    public void exit(Homeless1Character homeless1) {
        LOGGER.log(Level.INFO, "Homeless1-Exiting Run State");
    }
}
