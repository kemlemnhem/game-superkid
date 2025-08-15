package dattran.game.superkid.character.homeless1.state;

import dattran.game.superkid.character.homeless1.Homeless1Character;
import dattran.game.superkid.character.kid.type.KidCharacter;
import dattran.game.superkid.config.GameConfig;

import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Homeless1StateRun extends HomeLess1StateBase {
    private static final Logger LOGGER = Logger.getLogger(Homeless1StateRun.class.getName());
    private final Random random = new SecureRandom();
    @Override
    public void update(Homeless1Character homeless1, float delta) {
        super.update(homeless1, delta);
        KidCharacter kid = homeless1.getGameScreen().getScreenManager().getKid();
        if (kid == null || kid.isDead()) {
            homeless1.changeState(random.nextBoolean() ? new Homeless1StateIdle1() : new Homeless1StateIdle2());
            return;
        }
        float dist = homeless1.getPhysic().getBody().getPosition().dst(kid.getPhysic().getBody().getPosition());
        if (dist <= GameConfig.HOMELESS_1_ATTACK_RANGE && homeless1.getAttackCoolDown() <= 0) {
            attackKid(homeless1);
            return;
        }
        if (dist <= GameConfig.HOMELESS_1_ATTACK_RANGE) {
            return;
        }

        homeless1.getPhysic().setFacingRight(homeless1.getPhysic().getBody().getPosition().x < homeless1.getGameScreen().getScreenManager().getKid().getPhysic().getBody().getPosition().x);
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
