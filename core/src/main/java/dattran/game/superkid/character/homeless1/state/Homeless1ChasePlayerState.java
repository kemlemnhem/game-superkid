package dattran.game.superkid.character.homeless1.state;

import dattran.game.superkid.character.base.type.Player;
import dattran.game.superkid.character.homeless1.Homeless1Character;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Homeless1ChasePlayerState extends HomeLess1StateBase {
    private static final Logger LOGGER = Logger.getLogger(Homeless1ChasePlayerState.class.getName());
    private final Player target;
    public Homeless1ChasePlayerState(Player target) {
        this.target = target;
    }

    @Override
    public void enter(Homeless1Character homeless1) {
        LOGGER.log(Level.INFO, "Homeless1-Entering CharsePlayer State");
    }

    @Override
    public void update(Homeless1Character homeless1, float delta) {
        if (target == null) {
            homeless1.changeState(new Homeless1StateIdle1());
        }
        else {
            float homeless1X = homeless1.getPhysic().getBody().getPosition().x;
            float playerX = target.getPhysic().getBody().getPosition().x;
            float distance = Math.abs(playerX - homeless1X);
            homeless1.getPhysic().setFacingRight(homeless1X < playerX);
            if (distance < 0.11 /*attack range*/) {
                homeless1.changeState(new Homeless1StateAttack1());
            }
            else {
                float direction = (playerX > homeless1X) ? 1 : -1;
                // TODO run state
                homeless1.getPhysic().getBody().setLinearVelocity(direction*1, homeless1.getPhysic().getBody().getLinearVelocity().y);
            }
        }
    }

    @Override
    public void exit(Homeless1Character character) {
        LOGGER.log(Level.INFO, "Homeless1-Exiting CharsePlayer State");
    }
}
